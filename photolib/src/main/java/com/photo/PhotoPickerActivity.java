package com.photo;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.photo.entity.Photo;
import com.photo.event.OnItemCheckListener;
import com.photo.fragment.ImagePagerFragment;
import com.photo.fragment.PhotoPickerFragment;
import com.photo.utils.Settings;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

@TargetApi(Build.VERSION_CODES.M)
public class PhotoPickerActivity extends AppCompatActivity {

    private PhotoPickerFragment pickerFragment;
    private ImagePagerFragment imagePagerFragment;

    public final static String EXTRA_MAX_COUNT = "MAX_COUNT";
    public final static String EXTRA_SHOW_CAMERA = "SHOW_CAMERA";
    public final static String EXTRA_SHOW_GIF = "SHOW_GIF";
    public final static String KEY_SELECTED_PHOTOS = "SELECTED_PHOTOS";
    public final static String TAILORING = "TAILORING";

    private MenuItem menuDoneItem;

    public final static int DEFAULT_MAX_COUNT = 9;

    private int maxCount = DEFAULT_MAX_COUNT;

    /**
     * to prevent multiple calls to inflate menu
     */
    private boolean menuIsInflated = false;

    private boolean showGif = false;

    private static CallbackHead mCallback = null;
    /**
     * 裁剪后的文件
     */
    private File outputfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_picker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        boolean showGif = getIntent().getBooleanExtra(EXTRA_SHOW_GIF, false);
        setShowGif(showGif);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.images);
        //如果这里报错的话需要设置AndroidManifest.xml
        // theme parent="Theme.AppCompat.Light.NoActionBar"
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            actionBar.setElevation(30);
        }
        initPhoto();
    }


    private void initPhoto() {
        boolean showCamera = getIntent().getBooleanExtra(EXTRA_SHOW_CAMERA, true);
        maxCount = getIntent().getIntExtra(EXTRA_MAX_COUNT, DEFAULT_MAX_COUNT);
        pickerFragment = (PhotoPickerFragment) getSupportFragmentManager().findFragmentById(R.id.photoPickerFragment);
        pickerFragment.getPhotoGridAdapter().setShowCamera(showCamera);
        /**
         * 点击选择
         */
        pickerFragment.getPhotoGridAdapter().setOnItemCheckListener(new OnItemCheckListener() {
            @Override
            public boolean OnItemCheck(int position, Photo photo, final boolean isCheck, int selectedItemCount) {
                int total = selectedItemCount + (isCheck ? -1 : 1);
                menuDoneItem.setEnabled(total > 0);
                if (maxCount <= 1) {
                    List<Photo> photos = pickerFragment.getPhotoGridAdapter().getSelectedPhotos();
                    if (!photos.contains(photo)) {
                        photos.clear();
                        pickerFragment.getPhotoGridAdapter().notifyDataSetChanged();
                    }
                    return true;
                }
                if (total > maxCount) {
                    Toast.makeText(getActivity(), getString(R.string.over_max_count_tips, maxCount),
                            LENGTH_LONG).show();
                    return false;
                }
                menuDoneItem.setTitle(getString(R.string.done_with_count, total, maxCount));
                return true;
            }
        });
    }

    /**
     * Overriding this method allows us to run our exit animation first, then exiting
     * the activity when it complete.
     */
    @Override
    public void onBackPressed() {
        if (imagePagerFragment != null && imagePagerFragment.isVisible()) {
            imagePagerFragment.runExitAnimation(new Runnable() {
                public void run() {
                    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        getSupportFragmentManager().popBackStack();
                    }
                }
            });
        } else {
            super.onBackPressed();
        }
    }


    public void addImagePagerFragment(ImagePagerFragment imagePagerFragment) {
        this.imagePagerFragment = imagePagerFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, this.imagePagerFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
        //都是申请权限惹得祸
        //onSaveInstanceState方法是在该Activity即将被销毁前调用，来保存Activity数据的，如果在保存玩状态后
        //再给它添加Fragment就会出错。解决办法就是把commit（）方法替换成 commitAllowingStateLoss()就行
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!menuIsInflated) {
            getMenuInflater().inflate(R.menu.menu_picker, menu);
            menuDoneItem = menu.findItem(R.id.done);
            menuDoneItem.setEnabled(false);
            menuIsInflated = true;
            return true;
        }
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }

        if (item.getItemId() == R.id.done) {
            Intent intent = new Intent();
            ArrayList<String> selectedPhotos = pickerFragment.getPhotoGridAdapter().getSelectedPhotoPaths();
            //图片单选
            if (maxCount == 1 && selectedPhotos.size() > 0) {
                //如果不裁剪
                if (!getIntent().getBooleanExtra(TAILORING, false)) {
                    String imagePath = selectedPhotos.get(0);
                    //默认800*800  后面有需要再提供方法设置
                    if (mCallback != null) {
                        List<String> images = new ArrayList<>();
                        images.add(imagePath);
                        mCallback.selectResults(convertToBitmap(imagePath, 800, 800), images);
                    } else {
                        //如果mCallback==null说明不需要图片裁剪，直接返回结果
                        intent.putStringArrayListExtra(KEY_SELECTED_PHOTOS, selectedPhotos);
                        setResult(RESULT_OK, intent);
                    }
                    finish();
                } else {
                    ImageTailoring(selectedPhotos.get(0));
                }
            } else {
                //图片多选
                if (mCallback != null) {
                    mCallback.selectResults(null, selectedPhotos);
                }
                intent.putStringArrayListExtra(KEY_SELECTED_PHOTOS, selectedPhotos);
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public PhotoPickerActivity getActivity() {
        return this;
    }

    public boolean isShowGif() {
        return showGif;
    }

    public void setShowGif(boolean showGif) {
        this.showGif = showGif;
    }

    /**
     * 头像选择回调接口
     */
    public interface CallbackHead {
        //多选的话Bitmap为null
        void selectResults(Bitmap bitmap, List<String> selectedPhotos);
    }


    public static void setImageCallback(CallbackHead callback) {
        mCallback = callback;
    }

    public static void unImageCallback() {
        mCallback = null;
    }

    /**
     * 图片多张选择回调接口
     */
    public interface CallbackImage {
        void ImageSelect(Bitmap bitmap, String selectedPhotos);
    }

    //图片裁剪
    private void ImageTailoring(String imagePath) {
        File imageFile = new File(imagePath);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(getImageContentUri(this, imageFile), "image/*");//自己使用Content Uri替换File Uri
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 180);
        intent.putExtra("outputY", 180);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        String imagename = System.currentTimeMillis() + "";
        outputfile = new File(Settings.imageSavePath + "Smk_" + imagename + ".jpg");
        File file = new File(Settings.imageSavePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputfile));//定义输出的File Uri
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetectin", true);
        startActivityForResult(intent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (maxCount == 1 && data != null && requestCode == 200) {
            // 拿到剪切数据
            if (outputfile != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(outputfile.getPath());
                if (bitmap != null) {
                    List<String> images = new ArrayList<>();
                    images.add(outputfile.getPath());
                    mCallback.selectResults(bitmap, images);
                    finish();
                } else {
                    Toast.makeText(PhotoPickerActivity.this, "内部处理错误", Toast.LENGTH_SHORT).show();
                }
            }

           /* Bitmap bmap = data.getParcelableExtra("data");
            if (bmap != null) {
                String imagepath = SaveBitmap.saveBitmap(bmap);
                if (mCallback != null) {
                    List<String> images = new ArrayList<>();
                    images.add(imagepath);
                    mCallback.selectResults(bmap, images);
                    finish();
                }
            }*/
        }
    }


    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    public Bitmap convertToBitmap(String path, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int) scale;

        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), width, height, true);
    }
}
