package com.wdh.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.baselib.ui.module.base.BaseLayoutActivity;
import com.google.zxing.WriterException;
import com.qrcodelib.BitmapUtil;
import com.qrcodelib.CaptureActivity;
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
 * Created by  Administrator  on 2017/5/31.
 * Email:924686754@qq.com
 */
public class QrCodeActivity extends BaseLayoutActivity{

    private final int REQUEST_QR_CODE = 1;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        setToolbarBackgroudColor(0xff228efd);
        setToolbarTitle("二維碼相關",0xffffffff);
        setToolbarLeftIcon(R.drawable.icon_aleft_white);

        image = (ImageView) findViewById(R.id.image);
    }
    public void capture(View v){
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_QR_CODE);
    }
    public void qrcode(View v){
        try {
            image.setImageBitmap(BitmapUtil.createQRCode("我是二维码啊",200));
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_QR_CODE && data != null) {
            try {
                String code = data.getStringExtra("result");
                Toast(code);
            } catch (Exception e) {
            }
        }
    }
}
