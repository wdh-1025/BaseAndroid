package com.wdh.base.activity;

import android.os.Bundle;
import android.view.View;

import com.baselib.ui.module.base.BaseLayoutActivity;
import com.baselib.ui.widget.SlidingLinearLayout;
import com.wdh.base.R;


public class ViewActivity extends BaseLayoutActivity {

    SlidingLinearLayout layoutSliding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        setToolbarBackgroudColor(0xff228efd);
        setToolbarTitle("View",0xffffffff);
        setToolbarLeftIcon(R.drawable.icon_aleft_white);

        layoutSliding = (SlidingLinearLayout) findViewById(R.id.layout_sliding);
        layoutSliding.setDuration(1000);
    }

    public void TOP(View v) {
        layoutSliding.ScrollToY(layoutSliding.getCurrY(), 200);
    }

    public void BOTTOM(View v) {
        layoutSliding.ScrollToY(layoutSliding.getCurrY(), -200);
    }

    public void LEFT(View v) {
        layoutSliding.ScrollToX(layoutSliding.getCurrX(), 200);
    }

    public void RIGHT(View v) {
        layoutSliding.ScrollToX(layoutSliding.getCurrX(), -200);
    }


}
