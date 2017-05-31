package com.baselib.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
public class ToolbarLayout extends FrameLayout {
    private Toolbar mToolbar;
    private RelativeLayout mLayoutLeft, mLayoutRight;
    private ImageView mImageLeft, mImageRight;
    private TextView mTextLeft, mTextRight, mTextTitle;


    private int mBackgroudColor;
    private String mTitle;
    private int mLeftIcon;
    private String mLeftText;
    private int mRightIcon;
    private String mRightText;
    private int mTitleColor,mLeftTextColor,mRightTextColor;

    public ToolbarLayout(Context context) {
        super(context);
    }

    public ToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this);
        init(attrs);
        initView();
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = attrs == null ? null : getContext().obtainStyledAttributes(attrs, R.styleable.ToolBar);
        if (ta != null) {
            mBackgroudColor = ta.getColor(R.styleable.ToolBar_backgroudColor, 0xffffffff);
            mLeftIcon = ta.getResourceId(R.styleable.ToolBar_leftIcon, 0);
            mLeftText = ta.getString(R.styleable.ToolBar_leftText);
            mRightIcon = ta.getResourceId(R.styleable.ToolBar_rightIcon, 0);
            mRightText = ta.getString(R.styleable.ToolBar_rightText);
            mTitle = ta.getString(R.styleable.ToolBar_title);
            mTitleColor = ta.getColor(R.styleable.ToolBar_titleColor, 0xff505050);
            mLeftTextColor = ta.getColor(R.styleable.ToolBar_leftTextColor, 0xff505050);
            mRightTextColor = ta.getColor(R.styleable.ToolBar_rightTextColor, 0xff505050);
        }
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mLayoutLeft = (RelativeLayout) findViewById(R.id.layout_left);
        mLayoutRight = (RelativeLayout) findViewById(R.id.layout_right);
        mImageLeft = (ImageView) findViewById(R.id.left_image);
        mImageRight = (ImageView) findViewById(R.id.right_image);
        mTextLeft = (TextView) findViewById(R.id.left_text);
        mTextRight = (TextView) findViewById(R.id.right_text);
        mTextTitle = (TextView) findViewById(R.id.text_title);
        mToolbar.setBackgroundColor(mBackgroudColor);
        mTextTitle.setTextColor(mTitleColor);
        mTextLeft.setTextColor(mLeftTextColor);
        mTextRight.setTextColor(mRightTextColor);
        if (mLeftIcon != 0) {
            mImageLeft.setImageResource(mLeftIcon);
            mImageLeft.setVisibility(View.VISIBLE);
        }
        if (mLeftText != null) {
            mTextLeft.setText(mLeftText);
            mTextLeft.setVisibility(View.VISIBLE);
        }
        if (mRightIcon != 0) {
            mImageRight.setImageResource(mRightIcon);
            mImageRight.setVisibility(View.VISIBLE);
        }
        if (mRightText != null) {
            mTextRight.setText(mRightText);
            mTextRight.setVisibility(View.VISIBLE);
        }
    }

    public ToolbarLayout setTitle(String title,int color) {
        this.mTitle = title;
        mTextTitle.setText(mTitle);
        mTextTitle.setVisibility(View.VISIBLE);
        mTextTitle.setTextColor(color);
        return this;
    }

    public ToolbarLayout setLeftIcon(int icon) {
        this.mLeftIcon = icon;
        mImageLeft.setImageResource(mLeftIcon);
        mImageLeft.setVisibility(View.VISIBLE);
        return this;
    }

    public ToolbarLayout setLeftText(String text,int color) {
        this.mLeftText = text;
        mTextLeft.setText(mLeftText);
        mTextLeft.setTextColor(color);
        mTextLeft.setVisibility(View.VISIBLE);
        return this;
    }

    public ToolbarLayout setRightIcon(int icon) {
        this.mRightIcon = icon;
        mImageRight.setImageResource(mRightIcon);
        mImageRight.setVisibility(View.VISIBLE);
        return this;
    }

    public ToolbarLayout setRightText(String text,int color) {
        this.mRightText = text;
        mTextRight.setText(mRightText);
        mTextRight.setTextColor(color);
        mTextRight.setVisibility(View.VISIBLE);
        return this;
    }

    public ToolbarLayout setBackgroudColor(int color) {
        this.mBackgroudColor = color;
        mToolbar.setBackgroundColor(mBackgroudColor);
        return this;
    }

    public View getView() {
        return this;
    }


    public Toolbar getToolbar() {
        return mToolbar;
    }

    public RelativeLayout getLayoutLeft() {
        return mLayoutLeft;
    }

    public RelativeLayout getLayoutRight() {
        return mLayoutRight;
    }

    public ImageView getImageLeft() {
        return mImageLeft;
    }

    public ImageView getImageRight() {
        return mImageRight;
    }

    public TextView getTextLeft() {
        return mTextLeft;
    }

    public TextView getTextRight() {
        return mTextRight;
    }

    public TextView getTextTitle() {
        return mTextTitle;
    }

}
