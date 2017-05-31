package com.wdh.base.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.baselib.ui.module.banner.loader.ImageLoader;
import com.wdh.base.utils.LoaderImage;


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
 * Created by  Administrator  on 2016/12/28.
 * Email:924686754@qq.com
 */
public class BannerLoaderImage extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        LoaderImage.onLoadingImage(path.toString(), imageView, 0);
    }
}
