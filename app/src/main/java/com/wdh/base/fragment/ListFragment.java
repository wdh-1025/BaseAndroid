package com.wdh.base.fragment;

import android.os.Handler;

import com.baselib.ui.module.base.adapter.BaseViewHolder;
import com.baselib.ui.module.base.BaseListFragment;
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
 * Created by  Administrator  on 2017/5/27.
 * Email:924686754@qq.com
 */
public class ListFragment extends BaseListFragment<String> {

    @Override
    public void onCreate() {
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
        settings.setListType(ListSettings.LAYOUT_LIST);
        settings.setItemLayout(R.layout.item_list);
        settings.setContentDividerHeight(10);
        settings.setContentDividerColor(0xffffffff);
        settings.setPageSize(10);
        settings.setHeadLayout(R.layout.head_layout);
        return settings;
    }
}
