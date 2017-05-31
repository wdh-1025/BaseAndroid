package com.wdh.base.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.baselib.framework.error.CustomActivityOnCrash;
import com.baselib.ui.module.base.ActivityLifecycle;
import com.baselib.ui.module.base.listener.ActivityLifecycleListener;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.okhttplib.OkHttpUtils;
import com.okhttplib.cache.CacheConfig;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


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
 * Created by root on 2016/6/19  11:16.
 * Email:924686754@qq.com
 */
public class AppContext extends Application {
    private static Context context;
    public static final String APP_SDCARD_DIR = Environment.getExternalStorageDirectory().getPath()
            + "/base-android/";
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        //app奔溃收集
        CustomActivityOnCrash.install(this, true);

        configUniversalImageLoader();

        LeakCanary.install(this);

        /**
         * BaseLayoutActivity、BaseListActivity生命周期
         */
        ActivityLifecycle.registerActivityLifecycleCallbacks(new ActivityLifecycleListener() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState, Map<String, Object> map) {
                Log.i("TAGTAG", "onActivityCreated");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.i("TAGTAG", "onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.i("TAGTAG", "onActivityPaused");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.i("TAGTAG", "onActivityDestroyed");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.i("TAGTAG", "onActivitySaveInstanceState");
            }
        });


        //okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20000, TimeUnit.MILLISECONDS)
                .readTimeout(20000, TimeUnit.MILLISECONDS)
                .build();
        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setCache_path(APP_SDCARD_DIR).setDisk_size(5 * (1024 * 1024));
        OkHttpUtils.initClient(okHttpClient, cacheConfig);

    }

    private void configUniversalImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(true) //
                .cacheOnDisk(true) //
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration//
                .Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheExtraOptions(480, 800).threadPoolSize(5)
                .threadPriority(4).diskCacheSize(5 * 1024 * 1024)//
//                .writeDebugLogs()
                .diskCache(new UnlimitedDiskCache(new File(Environment.getExternalStorageDirectory().getPath()
                        + "/base-xiaoma/" + "UILCache")))// 自定义缓存路径
                .build();//
        ImageLoader.getInstance().init(config);
    }

    public static Context getContext() {
        return context;
    }

}
