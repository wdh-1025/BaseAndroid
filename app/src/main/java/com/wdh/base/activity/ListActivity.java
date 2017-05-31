package com.wdh.base.activity;

import android.os.Handler;

import com.baselib.ui.module.base.adapter.BaseViewHolder;
import com.baselib.ui.module.base.BaseListActivity;
import com.baselib.ui.module.base.utils.ListSettings;
import com.wdh.base.R;

import java.util.ArrayList;
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
 * 不提供item的点击方法，直接在onBindContentView中直接设置，要什么样就涨什么样，提供了有点多余
 */
public class ListActivity extends BaseListActivity<String> {

    @Override
    public void onCreate() {
        setToolbarBackgroudColor(0xff228efd);
        setToolbarTitle("列表頁面", 0xffffffff);
        setToolbarLeftIcon(R.drawable.icon_aleft_white);
        showloading();
    }

    @Override
    public void requestData(boolean refresh) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> data = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    data.add("--" + i + "--");
                }
                onDataLoaded(data);
            }
        }, 1000);
    }

    @Override
    public void onBindContentView(BaseViewHolder helper, String item, int position) {
        helper.setText(R.id.text, item);
    }

    /**
     * 如果你有设置头部而且需要绑定头部数据的话可以重写onBindHeadView进行内容设置
     *
     * @param helper
     */
    @Override
    public void onBindHeadView(BaseViewHolder helper) {
        super.onBindHeadView(helper);
    }

    @Override
    protected ListSettings getBaseSettings() {
        ListSettings settings = new ListSettings();
        settings.setListType(ListSettings.LAYOUT_GRID);
        settings.setGridSpanCount(3);
        settings.setItemLayout(R.layout.item_list);
        settings.setContentDividerHeight(10);
        settings.setContentDividerColor(0xffffffff);
        settings.setPageSize(10);
        settings.setHeadLayout(R.layout.head_layout);
        return settings;
    }

}
