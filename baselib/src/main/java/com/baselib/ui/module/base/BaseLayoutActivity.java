package com.baselib.ui.module.base;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.baselib.R;
import com.baselib.framework.swipebacklayout.app.SwipeBackActivity;
import com.baselib.ui.module.base.listener.ActivityLifecycleListener;
import com.baselib.ui.module.base.listener.PageErrListener;
import com.baselib.ui.module.base.listener.ToolbarListener;
import com.baselib.ui.widget.PageStateLayout;
import com.baselib.ui.widget.ToolbarLayout;
import com.baselib.utils.DisplayUtil;

import java.util.HashMap;
import java.util.Map;


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
 * Created by  Administrator  on 2017/5/25.
 * Email:924686754@qq.com
 */
public class BaseLayoutActivity extends SwipeBackActivity {
    protected View mViewStatusBar;

    private ViewStub mStubToolbar, mStubPageState;

    protected FrameLayout mLayoutContent;
    private ToolbarLayout mToolbarLayout;
    private PageStateLayout mLayoutPageState;

    private ActivityLifecycleListener mActivityLifecycle = ActivityLifecycle.mActivityLifecycleListener;

    protected Map<String, Object> mMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mActivityLifecycle != null) {
            mActivityLifecycle.onActivityCreated(this, savedInstanceState, mMap);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.base_activity);
        initView();
        mLayoutContent.addView(LayoutInflater.from(this).inflate(layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(LayoutInflater.from(this).inflate(R.layout.base_activity, null));
        initView();
        mLayoutContent.addView(view);
    }

    private void initView() {
        mLayoutContent = (FrameLayout) findViewById(R.id.layout_content);
        mStubToolbar = (ViewStub) findViewById(R.id.stub_toolbar);
        mStubPageState = (ViewStub) findViewById(R.id.stub_page_state);
        mViewStatusBar = findViewById(R.id.view_status_bar);
        setStatusBar(mViewStatusBar);
    }
    /**
     * 设置沉浸式状态栏
     */
    protected void setStatusBar(final View statusBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            final int statusHeight = DisplayUtil.getStatusBarHeight(this);
            statusBar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = statusBar.getHeight();
                    ViewGroup.LayoutParams params =  statusBar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    statusBar.setLayoutParams(params);
                }
            });
        }
    }
    /**
     * ----------------------------------------------------------------------------
     * ----------------------------toolbar---START--------------------------------
     * ----------------------------------------------------------------------------
     */
    private void initToolbarLayout() {
        if (mToolbarLayout == null) {
            mToolbarLayout = (ToolbarLayout) mStubToolbar.inflate();
        }
        mToolbarLayout.getLayoutLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //設置左邊點擊事件，不設置默認點擊返回
    protected void setToolbarLeftOnClick(final ToolbarListener toolbarListener) {
        initToolbarLayout();
        mToolbarLayout.getLayoutLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toolbarListener != null) {
                    toolbarListener.onClick(v);
                }
            }
        });
    }

    //设置右边点击事件
    protected void setToolbarRightOnClick(final ToolbarListener toolbarListener) {
        initToolbarLayout();
        mToolbarLayout.getLayoutRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toolbarListener != null) {
                    toolbarListener.onClick(v);
                }
            }
        });
    }

    //设置toolbar背景颜色
    protected void setToolbarBackgroudColor(int color) {
        initToolbarLayout();
        mToolbarLayout.setBackgroudColor(color);
    }

    //设置标题和颜色，以下差不多雷同
    protected void setToolbarTitle(String title, int color) {
        initToolbarLayout();
        mToolbarLayout.setTitle(title, color);
    }

    protected void setToolbarLeftText(String text, int color) {
        initToolbarLayout();
        mToolbarLayout.setLeftText(text, color);
    }

    protected void setToolbarRightText(String text, int color) {
        initToolbarLayout();
        mToolbarLayout.setRightText(text, color);
    }

    protected void setToolbarLeftIcon(int icon) {
        initToolbarLayout();
        mToolbarLayout.setLeftIcon(icon);
    }

    protected void setToolbarRightIcon(int icon) {
        initToolbarLayout();
        mToolbarLayout.setRightIcon(icon);
    }
    /**
     * ----------------------------------------------------------------------------
     * ----------------------------toolbar---END--------------------------------
     * ----------------------------------------------------------------------------
     */


    /**
     * ----------------------------------------------------------------------------
     * ----------------------------页面状态---START--------------------------------
     * ----------------------------------------------------------------------------
     */
    private void initPageStateLayout() {
        if (mLayoutPageState == null) {
            mLayoutPageState = (PageStateLayout) mStubPageState.inflate();
        }
    }

    //显示加载中
    protected void showloading() {
        initPageStateLayout();
        mLayoutPageState.showLoading();
    }

    //隐藏页面状态布局
    protected void hidePageState() {
        initPageStateLayout();
        mLayoutPageState.hide();
    }

    //显示其它故障错误、无内容
    protected void showOtherErr(int icon, String text) {
        initPageStateLayout();
        mLayoutPageState.showOther(icon, text);
    }

    //设置LayoutPageState的状态不等于HIDE的时候的点击事件，以便重新加载等
    protected void setPageErrOnClickListener(final PageErrListener pageErrListener) {
        initPageStateLayout();
        mLayoutPageState.getLayoutRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLayoutPageState.getState() == PageStateLayout.LOADING) {
                    return;
                }
                if (pageErrListener != null) {
                    pageErrListener.onClick(v, mLayoutPageState.getState());
                }
            }
        });
    }

    /**
     * ----------------------------------------------------------------------------
     * ----------------------------页面状态---END--------------------------------
     * ----------------------------------------------------------------------------
     */




    protected void Toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mActivityLifecycle != null) {
            mActivityLifecycle.onActivityResumed(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mActivityLifecycle != null) {
            mActivityLifecycle.onActivityPaused(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mActivityLifecycle != null) {
            mActivityLifecycle.onActivityDestroyed(this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (mActivityLifecycle != null) {
            mActivityLifecycle.onActivitySaveInstanceState(this, outState);
        }
    }
}
