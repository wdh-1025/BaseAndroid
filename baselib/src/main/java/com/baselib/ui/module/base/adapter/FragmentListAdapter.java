package com.baselib.ui.module.base.adapter;

import android.content.Context;

import com.baselib.ui.module.base.BaseListFragment;

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
public class FragmentListAdapter<T> extends BaseListAdapter<T> {

    private BaseListFragment mBaseListFragment;

    public FragmentListAdapter(Context context,BaseListFragment baseListFragment, int contentLayoutResId, int headLayoutResId) {
        super(context, contentLayoutResId, headLayoutResId);
        mBaseListFragment =  baseListFragment;
    }

    @Override
    protected void onBindContentViewData(BaseViewHolder helper, T item, int position) {
        mBaseListFragment.onBindContentView(helper, item, position);
    }

    @Override
    protected void onBindHeadViewData(BaseViewHolder helper) {
        mBaseListFragment.onBindHeadView(helper);
    }
}
