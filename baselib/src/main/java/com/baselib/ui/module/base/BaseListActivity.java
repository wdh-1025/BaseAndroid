package com.baselib.ui.module.base;

import android.os.Bundle;

import com.baselib.R;
import com.baselib.ui.module.base.adapter.BaseViewHolder;
import com.baselib.ui.module.base.adapter.ActivityListAdapter;
import com.baselib.ui.module.base.utils.BaseListEventHandler;
import com.baselib.ui.module.base.utils.BaseListViewBuilder;
import com.baselib.ui.module.base.utils.ListSettings;

import java.util.List;

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
 * Created by  Administrator  on 2017/5/26.
 * Email:924686754@qq.com
 */
public abstract class BaseListActivity<T> extends BaseLayoutActivity implements BaseListEventHandler.OnListDataListener {

    protected BaseListViewBuilder<T> mViewBuilder;
    protected BaseListEventHandler<T> mEventHandler;
    protected ActivityListAdapter<T> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBuilder();
        onCreate();
        requestData(true);
    }

    private void initViewBuilder() {
        ListSettings settings = getBaseSettings();
        int layoutId = settings.getCustomLayout();
        setContentView(layoutId != 0 ? layoutId : R.layout.base_list);
        mAdapter = new ActivityListAdapter<T>(this, settings.getItemLayout(), settings.getHeadLayout());
        mViewBuilder = new BaseListViewBuilder<>(this, mLayoutContent, mAdapter, getBaseSettings());
        mEventHandler = new BaseListEventHandler(this, mViewBuilder);
        mEventHandler.setOnListDataListener(this);
    }


    @Override
    public void onNoNetwork() {
        showOtherErr(R.drawable.default_error_image, getString(R.string.errorInfo));
    }

    @Override
    public void onNoContent() {
        showOtherErr(R.drawable.base_nocontent_img, getString(R.string.nocontent));
    }

    @Override
    public void onHideEmptyLayout() {
        hidePageState();
    }

    protected void onDataLoaded(List<T> data) {
        mEventHandler.onDataLoaded(data);
    }


    public abstract void onCreate();

    public abstract void requestData(boolean refresh);

    public abstract void onBindContentView(BaseViewHolder helper, T item, final int position);

    public void onBindHeadView(BaseViewHolder helper) {
    }

    protected ListSettings getBaseSettings() {
        ListSettings settings = new ListSettings();
        settings.setListType(ListSettings.LAYOUT_LIST);
        return settings;
    }
}
