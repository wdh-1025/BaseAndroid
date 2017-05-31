package com.baselib.ui.module.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.baselib.R;


public class LoadingDialog extends Dialog {
    private Context mContext;

    public LoadingDialog(Context context) {
        this(context, "加载中...");
    }

    public LoadingDialog(Context context, String str) {
        super(context, R.style.MyDialogStyle);
        setContentView(R.layout.dialog_loading);
        this.mContext = context.getApplicationContext();
        initDialog(str);
    }

    private Animation operatingAnim;
    private ImageView Image;

    private void initDialog(String str) {
        operatingAnim = AnimationUtils.loadAnimation(mContext, R.anim.image_rotating);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);//设置匀速转动
        Image = (ImageView) findViewById(R.id.Image);
        Image.setAnimation(operatingAnim);
        TextView loadingStr = (TextView) findViewById(R.id.str);
        loadingStr.setText(str);
        setCanceledOnTouchOutside(false);
        getWindow().setLayout(dpToPx(200, mContext), WindowManager.LayoutParams.WRAP_CONTENT/* dpToPx(100, mContext)*/);
    }

    public void showDialog() {
        //这样写的原因是：我也不知道为什么第一次show的话图片能正常旋转，但是之后图片旋转就失效了，所以每次只能在这里对图片进行设置旋转动画
        Image.setAnimation(operatingAnim);
        show();
    }



    private int dpToPx(int i, Context mContext) {
        DisplayMetrics displayMetrics = mContext.getResources()
                .getDisplayMetrics();
        return (int) ((i * displayMetrics.density) + 0.5);
    }
}
