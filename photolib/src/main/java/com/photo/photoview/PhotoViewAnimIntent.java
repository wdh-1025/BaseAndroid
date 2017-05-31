package com.photo.photoview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.photo.photoview.activity.PhotoViewAnimActivity;
import com.photo.photoview.utils.ImageAnimAttribute;


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
 * Created by  Administrator  on 2017/2/7.
 * Email:924686754@qq.com
 * 优点：支持共享元素动画
 * 缺点：只能查看一张图片，不能使用ViewPager同时左右滑动查看多张图片,如果想加载多张图片请使用PhotoViewIntent
 */
public class PhotoViewAnimIntent extends Intent {
    private Context mContext;


    public PhotoViewAnimIntent(Context context) {
        super(context, PhotoViewAnimActivity.class);
        mContext = context;
    }

    /**
     * 设置显示的图片url
     *
     * @param url
     * @return
     */
    public PhotoViewAnimIntent setImageUrls(String url) {
        putExtra("image_url", url);
        return this;
    }


    /**
     * 获取图片的属性，用于使用者自己使用自己的图片加载库去加载图片
     *
     * @param imageAttribute
     * @return
     */
    public PhotoViewAnimIntent getImageAttribute(ImageAnimAttribute imageAttribute) {
        PhotoViewAnimActivity.setImageAttribute(imageAttribute);
        return this;
    }

    public void startActivity(View view) {
        ActivityOptionsCompat aop = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, view, "anim");
        ActivityCompat.startActivity((Activity) mContext, this, aop.toBundle());
    }

}
