package com.baselib.ui.module.base;

import com.baselib.ui.module.base.listener.ActivityLifecycleListener;

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
 * Created by  Administrator  on 2017/5/29.
 * Email:924686754@qq.com
 * Activity生命周期
 */
public class ActivityLifecycle {

    public static ActivityLifecycleListener mActivityLifecycleListener;

    public static void registerActivityLifecycleCallbacks(ActivityLifecycleListener activityLifecycleListener) {
        mActivityLifecycleListener = activityLifecycleListener;
    }

    public static void unregisterActivityLifecycleCallbacks() {
        mActivityLifecycleListener = null;
    }

}
