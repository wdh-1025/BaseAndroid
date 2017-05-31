package com.wdh.base.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baselib.ui.module.base.BaseLayoutActivity;
import com.baselib.ui.module.dialog.DialogOnClickListener;
import com.baselib.ui.module.dialog.DialogOnItemClickListener;
import com.baselib.ui.module.dialog.MDAlertDialog;
import com.baselib.ui.module.dialog.MDEditDialog;
import com.baselib.ui.module.dialog.MDSelectionDialog;
import com.baselib.ui.module.dialog.SheetDialog;
import com.wdh.base.R;

import java.util.ArrayList;


/**
 * MD风格提示框
 */
public class MDDialogActivity extends BaseLayoutActivity {
    private MDAlertDialog dialog1;
    private MDSelectionDialog dialog2;
    private MDEditDialog dialog3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mddialog);
        setToolbarBackgroudColor(ContextCompat.getColor(this, R.color.colorPrimary));
        setToolbarLeftText("MD弹出框", 0xffffffff);

        dialog1 = new MDAlertDialog.Builder(MDDialogActivity.this)
                .setHeight(0.21f)  //屏幕高度*0.21
                .setWidth(0.7f)  //屏幕宽度*0.7
                .setTitleVisible(true)
                .setTitleText("温馨提示")
                .setTitleTextColor(R.color.black)
                .setContentText("确定发送文件？")
                .setContentTextColor(R.color.gray)
                .setLeftButtonText("不发送")
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText("发送")
                .setRightButtonTextColor(R.color.black)
                .setTitleTextSize(16)
                .setContentTextSize(14)
                .setButtonTextSize(14)
                .setOnclickListener(new DialogOnClickListener() {
                    @Override
                    public void clickLeftButton(View view) {
                        dialog1.dismiss();
                    }

                    @Override
                    public void clickRightButton(View view) {
                        dialog1.dismiss();
                    }
                })
                .build();

        final ArrayList<String> s = new ArrayList<>();
        s.add("标为未读");
        s.add("置顶聊天");
        s.add("删除聊天");
        dialog2 = new MDSelectionDialog.Builder(MDDialogActivity.this)
                .setCanceledOnTouchOutside(true)
                .setItemTextColor(R.color.gray)
                .setItemHeight(50)
                .setItemWidth(0.8f)  //屏幕宽度*0.8
                .setItemTextSize(15)
                .setCanceledOnTouchOutside(true)
                .setOnItemListener(new DialogOnItemClickListener() {
                    @Override
                    public void onItemClick(Button button, int position) {
                        Toast.makeText(MDDialogActivity.this, s.get(position), Toast.LENGTH_SHORT).show();
                        dialog2.dismiss();
                    }
                })
                .build();
        dialog2.setDataList(s);


        dialog3 = new MDEditDialog.Builder(MDDialogActivity.this)
                .setTitleVisible(true)
                .setTitleText("设置密码")
                .setTitleTextSize(20)
                .setTitleTextColor(R.color.gray)
                .setContentText("xxxx")
                .setContentTextSize(18)
                .setMaxLength(7)
                .setHintText("最少8位")
                .setMaxLines(1)
                .setContentTextColor(R.color.black)
                .setButtonTextSize(14)
                .setLeftButtonTextColor(R.color.gray)
                .setLeftButtonText("取消")
                .setRightButtonTextColor(R.color.black)
                .setRightButtonText("确定")
                .setLineColor(R.color.gray)
                .setOnclickListener(new MDEditDialog.OnClickEditDialogListener() {
                    @Override
                    public void clickLeftButton(View view, String editText) {
                        dialog3.dismiss();
                        Toast.makeText(MDDialogActivity.this, editText.trim(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void clickRightButton(View view, String editText) {
                        Toast.makeText(MDDialogActivity.this, editText.trim(), Toast.LENGTH_SHORT).show();
                        dialog3.dismiss();
                    }
                })
                .setMinHeight(0.25f)
                .setWidth(0.8f)
                .build();
    }


    public void MDAlert(View view) {
        dialog1.show();
    }


    public void MDSelect(View view) {
        dialog2.show();
    }


    public void MDEdit(View view) {
        dialog3.show();
    }


    private View bottomView;
    private SheetDialog bottomDialog;

    public void BottomSheetDialog(View view) {
        if (bottomView == null) {
            bottomView = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
            bottomDialog = new SheetDialog(this);
            bottomDialog.setView(bottomView);
        }
        bottomDialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog1.unRegister();
        dialog2.unRegister();
        dialog3.unRegister();
    }
}
