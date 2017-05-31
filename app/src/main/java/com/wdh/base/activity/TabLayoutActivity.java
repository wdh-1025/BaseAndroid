package com.wdh.base.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.baselib.ui.module.base.BaseLayoutActivity;
import com.baselib.ui.widget.tablayout.TabLayoutSmk;
import com.wdh.base.R;
import com.wdh.base.fragment.ListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 标签切换
 */
public class TabLayoutActivity extends BaseLayoutActivity implements TabLayoutSmk.CallbackPosition {
    //标签切换
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();
    private List<Integer> mIconUnselectIds = new ArrayList<>();
    private List<Integer> mIconSelectIds = new ArrayList<>();
    TabLayoutSmk tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        setToolbarBackgroudColor(0xff228efd);
        setToolbarTitle("測試頁面",0xffffffff);
        setToolbarLeftIcon(R.drawable.icon_aleft_white);
        tabLayout = (TabLayoutSmk) findViewById(R.id.tabLayout);
        initTab();
    }

    private void initTab() {
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        mTitles.add("消息");
        mTitles.add("通讯录");
        mTitles.add("应用");
        mTitles.add("我");
        mIconUnselectIds.add(R.drawable.tab_speech_unselect);
        mIconUnselectIds.add(R.drawable.tab_more_unselect);
        mIconUnselectIds.add(R.drawable.tab_home_unselect);
        mIconUnselectIds.add(R.drawable.tab_contact_unselect);
        mIconSelectIds.add(R.drawable.tab_speech_select);
        mIconSelectIds.add(R.drawable.tab_more_select);
        mIconSelectIds.add(R.drawable.tab_home_select);
        mIconSelectIds.add(R.drawable.tab_contact_select);
        tabLayout.init(fragments, mTitles, mIconUnselectIds, mIconSelectIds, getSupportFragmentManager(), this);
        tabLayout.setTextSelectColor(Color.parseColor("#7EACBE"));//设置tabText选中的颜色
       // tabLayout.setViewPagerSliding(false);//禁止手动滑动切换
        tabLayout.showMsg(3, 2);  //显示未读消息数量
    }

    @Override
    public void positon(int position) {
        Log.i("TAG", "切换到了第" + position + "个页面");
    }
}
