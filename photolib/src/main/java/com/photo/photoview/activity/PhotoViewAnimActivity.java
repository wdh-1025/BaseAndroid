package com.photo.photoview.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.photo.R;
import com.photo.photoview.utils.ImageAnimAttribute;
import com.photo.photoview.utils.PhotoViewAttacher;


/**
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃永无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * Created by  Administrator  on 2017/3/9.
 * Email:924686754@qq.com
 * 查看大图
 * 优点：支持共享元素动画
 * 缺点：只能查看一张图片，不能使用ViewPager同时左右滑动查看多张图片,如果想加载多张图片请使用PhotoViewActivity
 */
public class PhotoViewAnimActivity extends AppCompatActivity {
    private ImageView mImageView;
    private PhotoViewAttacher mAttacher;
    private ProgressBar mProgressBar;
    private static ImageAnimAttribute mImageAttribute;
    private String mImageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //隐藏状态栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.photoview_image_detail);
        init();
    }

    private void init() {
        mImageView = (ImageView) findViewById(R.id.image);
        ViewCompat.setTransitionName(mImageView, "anim");
        mAttacher = new PhotoViewAttacher(mImageView);
        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

            @Override
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                onBackPressed();
            }
        });
        mProgressBar = (ProgressBar) findViewById(R.id.loading);
        if (mImageAttribute != null) {
            mImageUrl = getIntent().getStringExtra("image_url");
            mImageAttribute.ImageAttribute(mImageView, mImageUrl, this);
        }
    }

    public static void setImageAttribute(ImageAnimAttribute imageAttribute) {
        mImageAttribute = imageAttribute;
    }

    public static void unImageAttribute() {
        mImageAttribute = null;
    }

    public void onLoadingStarted() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void onLoadingFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        mProgressBar.setVisibility(View.GONE);
    }

    public void onLoadingComplete() {
        mProgressBar.setVisibility(View.GONE);
        mAttacher.update();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unImageAttribute();
        mAttacher = null;
    }
}
