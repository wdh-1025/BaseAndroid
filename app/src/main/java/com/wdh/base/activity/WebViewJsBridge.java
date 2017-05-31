package com.wdh.base.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.baselib.framework.jsbridge.BridgeWebView;
import com.baselib.framework.jsbridge.listener.BridgeHandler;
import com.baselib.framework.jsbridge.listener.CallBackFunction;
import com.baselib.ui.module.activity.WebViewActivity;
import com.baselib.ui.module.base.listener.ToolbarListener;
import com.wdh.base.R;

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
 * Created by  Administrator  on 2017/5/30.
 * Email:924686754@qq.com
 * js桥：如果你需要的話，如果需要jsqiao直接使用webViewActivity就可以了
 */
public class WebViewJsBridge extends WebViewActivity implements BridgeHandler {

    private String[] registerHandlerArray = new String[]{"Toast"};
    private BridgeWebView bridgeWebView = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initJsBridgge();
        setToolbarRightText("调用JS", ContextCompat.getColor(this, R.color.YOUR_APP_THEME));
        setToolbarRightOnClick(new ToolbarListener() {
            @Override
            public void onClick(View v) {
                //调用JS的函数
                bridgeWebView.callHandler("functionJs", "我是給js的數據", new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        Toast(data);
                    }
                });
            }
        });
    }

    private void initJsBridgge(){
        //设置已有的处理程序
        bridgeWebView = jsbridgeWebciew.getWebView();
        //注册你要提供给js调用的方法函数
        bridgeWebView.setRegisterHandlerArray(registerHandlerArray);
        //监听js发送消息过来（理程序默认处）
        bridgeWebView.setDefaultHandler(this);
        //提供給js調用
        bridgeWebView.registerHandler("Toast", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Toast(data);
                //回調告訴h5
                function.onCallBack("完成了");
            }
        });

    }

    @Override
    public void handler(String data, CallBackFunction function) {
        function.onCallBack("調用程序沒有找到");
    }
}
