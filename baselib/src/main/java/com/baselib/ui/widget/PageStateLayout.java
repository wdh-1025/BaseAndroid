package com.baselib.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baselib.R;


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
 * Created by  Administrator  on 2017/5/25.
 * Email:924686754@qq.com
 */
public class PageStateLayout extends FrameLayout {
    private FrameLayout mLayoutRoot;
    private LinearLayout mLayoutNoData;
    private ImageView mImageNoData;
    private TextView mTextNoData;
    private ProgressBar mProgressBar;

    public final static int HIDE = 0;
    public final static int LOADING = 1;
    public final static int OTHER = 2;
    private int state  = HIDE;

    public PageStateLayout(Context context) {
        super(context);
    }

    public PageStateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_page_state, this);
        initView();
    }

    private void initView() {
        mLayoutRoot = (FrameLayout) findViewById(R.id.layout_root);
        mLayoutNoData = (LinearLayout) findViewById(R.id.layout_nodata);
        mImageNoData = (ImageView) findViewById(R.id.image_nodata);
        mTextNoData = (TextView) findViewById(R.id.text_nodata);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    public void hide() {
        state = HIDE;
        mLayoutRoot.setVisibility(View.GONE);
    }

    public void showLoading() {
        state = LOADING;
        mLayoutRoot.setVisibility(View.VISIBLE);
        mLayoutNoData.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void showOther(int icon,String text) {
        state = OTHER;
        mLayoutRoot.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mLayoutNoData.setVisibility(View.VISIBLE);
        mImageNoData.setImageResource(icon);
        mTextNoData.setText(text);
    }

    public FrameLayout getLayoutRoot(){
        return mLayoutRoot;
    }

    public int getState() {
        return state;
    }


}
