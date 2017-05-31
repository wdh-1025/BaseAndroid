package com.wdh.base;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.baselib.framework.permissions.Permission;
import com.baselib.framework.permissions.PermissionsResult;
import com.baselib.framework.permissions.ResultCallBack;
import com.baselib.ui.module.base.BaseLayoutActivity;
import com.wdh.base.activity.BannerActivity;
import com.wdh.base.activity.ButtonActivity;
import com.wdh.base.activity.LinkCallTaskActivity;
import com.wdh.base.activity.ListActivity;
import com.wdh.base.activity.MDDialogActivity;
import com.wdh.base.activity.OKHttpActivity;
import com.wdh.base.activity.PhotoViewActivity;
import com.wdh.base.activity.TabLayoutActivity;
import com.wdh.base.activity.ViewActivity;
import com.wdh.base.activity.WebViewJsBridge;

public class MainActivity extends BaseLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSwipeBackEnable(false);
        setToolbarBackgroudColor(0xff228efd);
        setToolbarTitle("測試頁面",0xffffffff);
        setToolbarLeftIcon(R.drawable.icon_aleft_white);
        setToolbarRightText("调试",0xffffffff);

    }

    public void tobug(View v){
        String test = null;
        test.equals("");
    }

    public void permission(View v){
        Permission.getInstance(this)
                .requestPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .results(new ResultCallBack() {
                    @Override
                    public void result(PermissionsResult result) {
                        if (result.isGranted()){
                            Toast("OK");
                        }else{
                            new AlertDialog.Builder(MainActivity.this).
                                    setTitle("帮助")
                                    .setMessage("请在设置->应用管理->BaseAndroid->权限管理->打开定位权限\n避免影响正常功能的使用")
                                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Uri packageURI = Uri.parse("package:" + getPackageName());
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                            startActivity(intent);
                                        }
                                    }).setNegativeButton("取消", null).show();
                        }
                    }
                });
    }


    public void list(View v){
        startActivity(new Intent(this, ListActivity.class));
    }

    public void tablayout(View v){
        startActivity(new Intent(this, TabLayoutActivity.class));
    }

    public void mddialog(View v){
        startActivity(new Intent(this, MDDialogActivity.class));
    }

    public void photoview(View v){
        startActivity(new Intent(this, PhotoViewActivity.class));
    }


    public void link(View v){
        startActivity(new Intent(this, LinkCallTaskActivity.class));
    }

    public void banner(View v){
        startActivity(new Intent(this, BannerActivity.class));
    }

    public void view(View v){
        startActivity(new Intent(this, ViewActivity.class));
    }

    public void okhttp(View v){
        startActivity(new Intent(this, OKHttpActivity.class));
    }

    public void web(View v){
        Intent intent = new Intent(this, WebViewJsBridge.class);
        intent.putExtra("url","file:///android_asset/demo.html");
        intent.putExtra("title","百度一下你就知道");
        startActivity(intent);
    }

    public void button(View v){
        startActivity(new Intent(this,ButtonActivity.class));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Permission.getInstance(this).unresults();
    }
}
