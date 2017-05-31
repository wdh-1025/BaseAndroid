package com.baselib.ui.module.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baselib.R;


public class BottomViewHolder extends BaseViewHolder {

    public TextView mTextView;
    public ProgressBar mProgress;
    public ImageView mBigImageView;
    public RelativeLayout relativeLayout;

    protected BottomViewHolder(Context context, View view, final int noContBottomHeight, final int bottomColor) {
        super(context, view);
        mProgress = (ProgressBar) view.findViewById(R.id.progressbar);
        mTextView = (TextView) view.findViewById(R.id.text_view);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.id_nocontent);
        mBigImageView = (ImageView) view.findViewById(R.id.bottomBigImage);
        if (bottomColor != 0) {
            relativeLayout.setBackgroundColor(bottomColor);
        }

        if (noContBottomHeight != 0) {
            ViewGroup.LayoutParams params = relativeLayout.getLayoutParams();
            params.height = noContBottomHeight;
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            relativeLayout.setLayoutParams(params);
        }
    }


}
