package com.baselib.ui.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.baselib.R;
import com.baselib.framework.jsbridge.BridgeWebView;


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
 * Created by  Administrator  on 2017/4/14.
 * Email:924686754@qq.com
 */
public class WebViewLayout extends FrameLayout {
    private ProgressBar mProgressBar;
    private BridgeWebView mWebView;

    public WebViewLayout(Context context) {
        this(context, null);
    }

    public WebViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_jsbridge_webview, this);
        initView();
    }

    private void initView() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mWebView = (BridgeWebView) findViewById(R.id.webView);
        WebSettings seting = mWebView.getSettings();
        seting.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            seting.setLoadsImagesAutomatically(true); //支持自动加载图片
        } else {
            seting.setLoadsImagesAutomatically(false);
        }
        seting.setUseWideViewPort(true); //将图片调整到适合WebView的大小
        seting.setLoadWithOverviewMode(true); //自适应屏幕
        seting.setDomStorageEnabled(true);
        seting.setAppCacheEnabled(true);
        seting.setSaveFormData(true);
        seting.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
    }


    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    public void goBack() {
        mWebView.goBack();
    }

    public boolean canGoBack() {
        return mWebView.canGoBack();
    }

    public BridgeWebView getWebView() {
        return mWebView;
    }

    /**
     * 防止webview内存泄漏
     */
    public void destroyWebView() {
        if (mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }
            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.removeAllViews();
            try {
                mWebView.destroy();
            } catch (Throwable ex) {
            }
        }
    }
}
