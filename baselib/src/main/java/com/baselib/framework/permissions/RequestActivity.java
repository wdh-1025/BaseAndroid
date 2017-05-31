package com.baselib.framework.permissions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;

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
 * Created by  Administrator  on 2016/11/15.
 * Email:924686754@qq.com
 * 权限申请页面的Activity
 */

@TargetApi(Build.VERSION_CODES.M)
public class RequestActivity extends Activity {
    PackageManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pm = getPackageManager();
        RequestPermissions(getIntent());
    }


    private void RequestPermissions(Intent intent) {
        String[] permissions = intent.getStringArrayExtra("permissions");
        //是否需要弹窗跟用户解析为什么需要权限
        /*if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            L.i("");
        }*/
        ActivityCompat.requestPermissions(this, permissions, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Permission permission = Permission.getInstance(this);
        for (int i = 0; i < permissions.length; i++) {
            // boolean granted = grantResults[i] == PackageManager.PERMISSION_GRANTED;
            //不用上面的方法去判断是否获取权限，坑B的小米
            boolean granted = (PackageManager.PERMISSION_GRANTED == PermissionChecker.checkPermission(this, permissions[i], Process.myPid(), Process.myUid(), getPackageName()));
            permission.setPermission(permissions[i], granted);
        }
        finish();
    }
}
