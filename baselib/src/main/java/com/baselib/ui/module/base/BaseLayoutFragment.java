package com.baselib.ui.module.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.baselib.R;
import com.baselib.ui.module.base.listener.PageErrListener;
import com.baselib.ui.widget.PageStateLayout;

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
public class BaseLayoutFragment extends Fragment {

    protected View mView;

    private ViewStub mStubPageState;

    protected FrameLayout mLayoutContent;
    private PageStateLayout mLayoutPageState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup p = (ViewGroup) mView.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.base_fragment, null);
        initView();
    }

    private void initView() {
        mLayoutContent = (FrameLayout) mView.findViewById(R.id.layout_content);
        mStubPageState = (ViewStub) mView.findViewById(R.id.stub_page_state);
    }

    public void setContentView(@LayoutRes int layoutResID) {
        mLayoutContent.addView(LayoutInflater.from(getContext()).inflate(layoutResID, null));
    }

    public void setContentView(View view) {
        mLayoutContent.addView(view);
    }


    /**
     * ----------------------------------------------------------------------------
     * ----------------------------页面状态---START--------------------------------
     * ----------------------------------------------------------------------------
     */
    private void initPageStateLayout() {
        if (mLayoutPageState == null) {
            mLayoutPageState = (PageStateLayout) mStubPageState.inflate();
        }
    }

    //显示加载中
    protected void showloading() {
        initPageStateLayout();
        mLayoutPageState.showLoading();
    }

    //隐藏页面状态布局
    protected void hidePageState() {
        initPageStateLayout();
        mLayoutPageState.hide();
    }

    //显示其它故障错误、无内容
    protected void showOtherErr(int icon, String text) {
        initPageStateLayout();
        mLayoutPageState.showOther(icon, text);
    }

    //设置LayoutPageState的状态不等于HIDE的时候的点击事件，以便重新加载等
    protected void setPageErrOnClickListener(final PageErrListener pageErrListener) {
        initPageStateLayout();
        mLayoutPageState.getLayoutRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLayoutPageState.getState() == PageStateLayout.LOADING) {
                    return;
                }
                if (pageErrListener != null) {
                    pageErrListener.onClick(v, mLayoutPageState.getState());
                }
            }
        });
    }

    /**
     * ----------------------------------------------------------------------------
     * ----------------------------页面状态---END--------------------------------
     * ----------------------------------------------------------------------------
     */

    protected void Toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
