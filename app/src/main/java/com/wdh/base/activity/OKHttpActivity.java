package com.wdh.base.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baselib.ui.module.base.BaseLayoutActivity;
import com.baselib.utils.L;
import com.okhttplib.OkHttpUtils;
import com.okhttplib.ResponseResult;
import com.okhttplib.callback.StringCallback;
import com.wdh.base.R;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class OKHttpActivity extends BaseLayoutActivity {

    TextView textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        setToolbarBackgroudColor(0xff228efd);
        setToolbarTitle("网络相关", 0xffffffff);
        setToolbarLeftIcon(R.drawable.icon_aleft_white);
        textContent = (TextView) findViewById(R.id.text_content);
    }

    /**
     * 設置緩存的時候生成的緩存文件以URL為準，如果是post請自行在後面追加標識，例：http://xxxx?id=1
     * 即便postid是纯放在params里的也要加上，也就是自行加上?id=1
     * 否则默认缓存最新那条
     * @param v
     */
    public void post(View v) {
        textContent.setText("请求中...");
        Map<String,String> map = new HashMap<>();
        map.put("key","123");
        OkHttpUtils
                .post()
                .url("http://route.showapi.com/64-21")
                .params(map)
                .tag(this)
                .cache(true)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        L.i("", "");
                    }

                    @Override
                    public void onResponse(ResponseResult response, int id) {
                        textContent.setText("是否来自缓存：" + response.isCache() + " content：" + response.getResult().toString());
                        L.i("", "");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消当前页面所有未完成请求
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
