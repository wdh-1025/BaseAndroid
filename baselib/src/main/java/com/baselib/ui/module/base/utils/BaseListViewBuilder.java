package com.baselib.ui.module.base.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.baselib.R;
import com.baselib.ui.module.base.adapter.BaseListAdapter;

public class BaseListViewBuilder<T> {
    private Context mContext;

    public FrameLayout mLayoutContent;
    public SwipeRefreshLayout mSwiperefreshlayout;
    public RecyclerView mRecyclerview;

    public ListSettings mSettings;
    public BaseListAdapter<T> mAdapter;//列表页头部

    public BaseListViewBuilder(Context context, FrameLayout layoutContent, BaseListAdapter<T> adapter, ListSettings settings) {
        this.mContext = context;
        this.mLayoutContent = layoutContent;
        this.mAdapter = adapter;
        this.mSettings = settings;
        initView();
    }

    private void initView() {
        mSwiperefreshlayout = (SwipeRefreshLayout) mLayoutContent.findViewById(R.id.swiperefreshlayout);
        if (mSwiperefreshlayout != null) {
            mSwiperefreshlayout.setColorSchemeResources(R.color.YOUR_APP_THEME, R.color.refresh_1, R.color.refresh_2, R.color.refresh_3, R.color.refresh_4);
            if (!mSettings.isCanRefresh()) {
                mSwiperefreshlayout.setEnabled(false);
            }
        }
        //列表控件
        mRecyclerview = (RecyclerView) mLayoutContent.findViewById(R.id.recyclerview);
        if (mAdapter == null || mRecyclerview == null) {
            return;
        }
        mRecyclerview.setAdapter(mAdapter);
        if (mSettings.getBottomBackgroudColor() != 0) {
            mAdapter.setBottomBgColor(mSettings.getBottomBackgroudColor());
        }
        if (mSettings.getNoDataBackgroudColor() != 0) {
            mAdapter.setNoContBottomHeight(mSettings.getNoDataBackgroudColor());
        }
        if (!mSettings.isCanLoadMore()) {
            mAdapter.setBottomCount(0);
        }
        initRecyclerViewType();
    }

    //初始化recyclerivew，包括布局类型，分隔线，头部底部宽度
    public void initRecyclerViewType() {
        switch (mSettings.getListType()) {
            case ListSettings.LAYOUT_LIST:
                mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
                ItemDecorationList mListDivider = new ItemDecorationList(mContext);
                if (mAdapter.getHeaderCount() != 0) {
                    mListDivider.setHasHeader(true);
                } else {
                    mListDivider.setHasHeader(false);
                }
                mListDivider.setHasFooter(true);
                mListDivider.setHeaderDivider(mSettings.getHeaderDividerHeight(), mSettings.getHeadDividerColor());
                mListDivider.setContentDivider(mSettings.getContentDividerHeight(), mSettings.getContentDividerColor());
                mRecyclerview.addItemDecoration(mListDivider);
                break;
            case ListSettings.LAYOUT_GRID:
                final GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, mSettings.getGridSpanCount());
                mRecyclerview.setLayoutManager(gridLayoutManager);
                //设置头部及底部View占据整行空间
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (mAdapter.isHeaderView(position) || mAdapter.isBottomView(position))
                                ? gridLayoutManager.getSpanCount() : 1;
                    }
                });
                ItemDecorationGrid mGridDivider = new ItemDecorationGrid(mContext);
                if (mAdapter.getHeaderCount() != 0) {
                    mGridDivider.setHasHeader(true);
                } else {
                    mGridDivider.setHasHeader(false);
                }
                mGridDivider.setHasFooter(true);
                mGridDivider.setDivider(mSettings.getContentDividerHeight(), mSettings.getContentDividerColor());
                mRecyclerview.addItemDecoration(mGridDivider);
                break;
            case ListSettings.LAYOUT_STAGGERD:
                //暂不支持瀑布流

                break;
        }
    }
}
