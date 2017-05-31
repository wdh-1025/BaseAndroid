package com.wdh.base.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.baselib.ui.module.base.BaseLayoutActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.photo.PhotoMultiSelectView;
import com.photo.PhotoPickerActivity;
import com.photo.photoview.PhotoViewAnimIntent;
import com.photo.photoview.PhotoViewIntent;
import com.photo.photoview.activity.PhotoViewAnimActivity;
import com.photo.photoview.utils.ImageAnimAttribute;
import com.photo.photoview.utils.ImageAttribute;
import com.photo.photoview.utils.ImageDetailFragment;
import com.photo.utils.PhotoPickerIntent;
import com.wdh.base.R;

import java.util.ArrayList;
import java.util.List;

public class PhotoViewActivity extends BaseLayoutActivity {
    private ImageView image;
    private ImageView imageChoose;
    private PhotoMultiSelectView photoMultiSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        setToolbarBackgroudColor(ContextCompat.getColor(this, R.color.colorPrimary));
        setToolbarLeftText("圖片相關", 0xffffffff);
        image = (ImageView) findViewById(R.id.image);
        imageChoose= (ImageView) findViewById(R.id.image_choose);
        photoMultiSelect= (PhotoMultiSelectView) findViewById(R.id.photoMultiSelect);
        photoMultiSelect.setMultiSize(8);
        ImageLoader.getInstance().displayImage("http://img07.tooopen.com/images/20170206/tooopen_sy_198052642226.jpg", image, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                Log.i("", "");
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Log.i("", "");
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                image.setImageBitmap(loadedImage);
            }
        });
    }

    public void image(View v) {
        PhotoViewAnimIntent photoViewIntent = new PhotoViewAnimIntent(this);
        photoViewIntent.setImageUrls("http://img07.tooopen.com/images/20170206/tooopen_sy_198052642226.jpg")
                .getImageAttribute(new ImageAnimAttribute() {
                    @Override
                    public void ImageAttribute(ImageView imageView, String imageUrl, final PhotoViewAnimActivity activity) {
                        ImageLoader.getInstance().displayImage(imageUrl, imageView, new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingStarted(String imageUri, View view) {
                                activity.onLoadingStarted();
                            }

                            @Override
                            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                                String message = null;
                                switch (failReason.getType()) {
                                    case IO_ERROR:
                                        message = "下载错误";
                                        break;
                                    case DECODING_ERROR:
                                        message = "图片无法显示";
                                        break;
                                    case NETWORK_DENIED:
                                        message = "网络有问题，无法下载";
                                        break;
                                    case OUT_OF_MEMORY:
                                        message = "图片太大无法显示";
                                        break;
                                    case UNKNOWN:
                                        message = "未知的错误";
                                        break;
                                }
                                activity.onLoadingFailed(message);
                            }

                            @Override
                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                activity.onLoadingComplete();
                            }
                        });
                    }
                });
        photoViewIntent.startActivity(image);
    }

    public void images(View v) {
        //可自己决定用什么加载图片框架去加载图片
        //只要适当调用fragment.onLoadingStarted();更新状态即可
        ArrayList<String> urls = new ArrayList<>();
        urls.add("http://img07.tooopen.com/images/20170206/tooopen_sy_198052642226.jpg");
        urls.add("http://img06.tooopen.com/images/20170120/tooopen_sy_197334647747.jpg");
        PhotoViewIntent photoViewIntent = new PhotoViewIntent(this);
        photoViewIntent.setImageUrls(urls)
                .getImageAttribute(new ImageAttribute() {
                    @Override
                    public void ImageAttribute(ImageView imageView, String imageUrl, final ImageDetailFragment fragment) {
                        ImageLoader.getInstance().displayImage(imageUrl, imageView, new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingStarted(String imageUri, View view) {
                                fragment.onLoadingStarted();
                            }

                            @Override
                            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                                String message = null;
                                switch (failReason.getType()) {
                                    case IO_ERROR:
                                        message = "下载错误";
                                        break;
                                    case DECODING_ERROR:
                                        message = "图片无法显示";
                                        break;
                                    case NETWORK_DENIED:
                                        message = "网络有问题，无法下载";
                                        break;
                                    case OUT_OF_MEMORY:
                                        message = "图片太大无法显示";
                                        break;
                                    case UNKNOWN:
                                        message = "未知的错误";
                                        break;
                                }
                                fragment.onLoadingFailed(message);
                            }

                            @Override
                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                fragment.onLoadingComplete();
                            }
                        });
                    }
                });
        startActivity(photoViewIntent);
    }

    PhotoPickerIntent mPhotoPickerIntent = null;
    public void imageChoose(View v){
        mPhotoPickerIntent = new PhotoPickerIntent(this);
        mPhotoPickerIntent.setShowCamera(true);
        mPhotoPickerIntent.setTailoring(true);
        mPhotoPickerIntent.setPhotoCount(1);
        mPhotoPickerIntent.setImageSelect(new PhotoPickerActivity.CallbackHead() {
            @Override
            public void selectResults(Bitmap bitmap, List<String> selectedPhotos) {
                //注意:如果选择的是多张图片的话bitmap为null,只返回selectedPhotos
                if (bitmap != null) {
                    imageChoose.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(PhotoViewActivity.this, "选择了" + selectedPhotos.size() + "张照片", Toast.LENGTH_SHORT).show();
                }
            }
        });
        startActivity(mPhotoPickerIntent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //调用view选择多张图片时
        photoMultiSelect.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPhotoPickerIntent != null) {
            mPhotoPickerIntent.unImageCallback();
        }
    }
}
