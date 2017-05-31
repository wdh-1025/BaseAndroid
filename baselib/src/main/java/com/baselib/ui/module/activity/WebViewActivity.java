package com.baselib.ui.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;

import com.baselib.R;
import com.baselib.ui.module.base.BaseLayoutActivity;
import com.baselib.ui.widget.WebViewLayout;


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
 * Created by  Administrator  on 2017/1/16.
 * Email:924686754@qq.com
 */

public class WebViewActivity extends BaseLayoutActivity {
    protected WebViewLayout jsbridgeWebciew;
    private String url;
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jsbridgeWebciew = new WebViewLayout(this);
        setContentView(jsbridgeWebciew);
        init();
    }


    private void init() {
        Intent intent = this.getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        if (title != null && !title.equals("")) {
            if (title.length() > 8) {
                title = title.substring(0, 8) + "...";
            }
        }
        setToolbarTitle(title, ContextCompat.getColor(this,R.color.YOUR_APP_THEME));
        setToolbarLeftIcon(R.drawable.img_btn_close_blue);
        jsbridgeWebciew.loadUrl(url);
    }

    //返回的时候返回到webview的前一页而不是后腿
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!jsbridgeWebciew.canGoBack()) {
                finish();
            } else {
                jsbridgeWebciew.goBack();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (jsbridgeWebciew != null) {
            jsbridgeWebciew.destroyWebView();
        }
    }




}
