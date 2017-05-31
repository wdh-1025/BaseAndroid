package com.baselib.ui.module.base.adapter;

import android.content.Context;

import com.baselib.ui.module.base.BaseListActivity;


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
 * Created by  Administrator  on 2017/5/27.
 * Email:924686754@qq.com
 */
public class ActivityListAdapter<T> extends BaseListAdapter<T> {
    private BaseListActivity mBaseListActivity;

    public ActivityListAdapter(Context context, int contentLayoutResId, int headLayoutResId) {
        super(context, contentLayoutResId, headLayoutResId);
        mBaseListActivity = (BaseListActivity) context;
    }

    @Override
    protected void onBindContentViewData(BaseViewHolder helper, T item, int position) {
        mBaseListActivity.onBindContentView(helper, item, position);
    }

    @Override
    protected void onBindHeadViewData(BaseViewHolder helper) {
        mBaseListActivity.onBindHeadView(helper);

    }
}
