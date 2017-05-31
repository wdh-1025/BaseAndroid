package com.wdh.base.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.baselib.framework.link.LinkCallTask;
import com.baselib.framework.link.listener.CancelInterface;
import com.baselib.framework.link.listener.CompleteInterface;
import com.baselib.framework.link.listener.ErrorInterface;
import com.baselib.framework.link.listener.LinkInterface;
import com.baselib.ui.module.base.BaseLayoutActivity;
import com.wdh.base.R;

import java.util.Map;

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
 * Created by  Administrator  on 2017/5/28.
 * Email:924686754@qq.com
 */
public class LinkCallTaskActivity extends BaseLayoutActivity {

    private LinkCallTask mLinkCallTask = new LinkCallTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkcalltask);
        mViewStatusBar.setBackgroundColor(0xffffffff);
        setToolbarLeftText("链式调用", ContextCompat.getColor(this, R.color.colorPrimary));


        mLinkCallTask.setLink(new LinkInterface() {
            @Override
            public void onNext(Map<String, Object> map) {
                Log.i("TAGTAG", Thread.currentThread().getName() + "=ID:" + Thread.currentThread().getId());
                mLinkCallTask.toNext();
            }
        }).setLink(new LinkInterface() {
            @Override
            public void onNext(Map<String, Object> map) {
                Log.i("TAGTAG", Thread.currentThread().getName() + "=ID:" + Thread.currentThread().getId());
                mLinkCallTask.toNext();
            }
        }).setLinkThread(new LinkInterface() {
            @Override
            public void onNext(Map<String, Object> map) {
                Log.i("TAGTAG", Thread.currentThread().getName() + "=ID:" + Thread.currentThread().getId());
                mLinkCallTask.toNext();
            }
        }).setLinkThread(new LinkInterface() {
            @Override
            public void onNext(Map<String, Object> map) {
                Log.i("TAGTAG", Thread.currentThread().getName() + "=ID:" + Thread.currentThread().getId());
                mLinkCallTask.toNext();
            }
        }).setLink(new LinkInterface() {
            @Override
            public void onNext(Map<String, Object> map) {
                Log.i("TAGTAG", Thread.currentThread().getName() + "=ID:" + Thread.currentThread().getId());
                mLinkCallTask.toNext();
            }
        }).setLinkThread(new LinkInterface() {
            @Override
            public void onNext(Map<String, Object> map) {
                Log.i("TAGTAG", Thread.currentThread().getName() + "=ID:" + Thread.currentThread().getId());
                mLinkCallTask.toNext();
            }
        }).setCancel(new CancelInterface() {
            @Override
            public void onCancel(Map<String, Object> map) {
                Log.i("TAGTAG", "取消咯：" + Thread.currentThread().getName() + "=ID:" + Thread.currentThread().getId());
            }
        }).setError(new ErrorInterface() {
            @Override
            public void onError(Object object) {
                Log.i("TAGTAG", "奔潰咯：" + Thread.currentThread().getName() + "=ID:" + Thread.currentThread().getId());
            }
        }).setComplete(new CompleteInterface() {
            @Override
            public void onComplete(Map<String, Object> map) {
                Log.i("TAGTAG", "完成了：" + Thread.currentThread().getName() + "=ID:" + Thread.currentThread().getId());
            }
        });
        mLinkCallTask.start();
    }


}
