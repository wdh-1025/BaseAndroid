package com.baselib.framework.link;

import android.os.Handler;
import android.os.Looper;

import com.baselib.framework.link.listener.CancelInterface;
import com.baselib.framework.link.listener.CompleteInterface;
import com.baselib.framework.link.listener.ErrorInterface;
import com.baselib.framework.link.listener.LinkInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
 * Created by  Administrator  on 2017/3/14.
 * Email:924686754@qq.com
 * 链路式调用,你的處理邏輯有多長、多複雜執行鏈就有多長
 * 从第一步調用setLink后开始可以一直setLink，可以在調用start執行第一步后不斷调用toNext()不断往下执行
 * setNext默认在主线程执行，如果中途需要切换到子线程执行则使用setNextThread
 * 到最后执行完会直接执行Complete，中途也可以直接执行toComplete
 * 如果执行链中不严谨的代码导致程序出错则会直接跳到Error
 * 在执行过程中可以手动调用toCancel，则会自动跳到Cancel
 * 如果在执行连上如果想重复执行当前操作调用toRepeat,调用前可调用setRepeatTime设置重复执行的间隔时间，调用一次toRepeat默认只重复执行一次
 * 在执行链当中可以使用携带的参数map设置暂存你执行链可能需要用到的参数，在执行链中的任何一个地方都可以取出来，就算不是在执行链条中也可以使用getMap获取
 */
public class LinkCallTask {

    private List<LinkInterface> mLinkInterfaces = null;
    private CompleteInterface mCompleteInterface = null;
    private ErrorInterface mErrorInterface = null;
    private CancelInterface mCancelInterface = null;
    private Integer mCurrentNext = 0;
    private List<Integer> mThread = null;
    private Handler mHandler = null;

    public static final Integer CURRENT_THREAD = 0;
    public static final Integer NEW_THREAD = 1;

    private ExecutorService mExecutorService;

    private Map<String, Object> mMap = new HashMap<>();

    private boolean mCancel;//是否调用了取消函数，如果调用了必须重新调用start才能重新激活执行链
    private boolean mComplete;//是否调用了完成函数，如果调用了必须重新调用start才能重新激活执行链
    private Integer mRepeatTime = 0; //重复执行的间隔时间，單位：毫秒

    public LinkCallTask setLink(LinkInterface linkInterface) {
        if (mThread == null) {
            mThread = new ArrayList<>();
        }
        mThread.add(CURRENT_THREAD);
        if (this.mLinkInterfaces == null) {
            mLinkInterfaces = new ArrayList<>();
        }
        this.mLinkInterfaces.add(linkInterface);
        return this;
    }

    public LinkCallTask setLinkThread(LinkInterface linkInterface) {
        if (mThread == null) {
            mThread = new ArrayList<>();
        }
        mThread.add(NEW_THREAD);
        if (this.mLinkInterfaces == null) {
            mLinkInterfaces = new ArrayList<>();
        }
        this.mLinkInterfaces.add(linkInterface);
        return this;
    }

    public LinkCallTask setComplete(CompleteInterface complete) {
        this.mCompleteInterface = complete;
        return this;
    }

    public LinkCallTask setError(ErrorInterface error) {
        this.mErrorInterface = error;
        return this;
    }

    public LinkCallTask setCancel(CancelInterface cancelInterface) {
        this.mCancelInterface = cancelInterface;
        return this;
    }

    /**
     * 跳转到错误
     */
    private void toError(final Object objects) {
        if (mExecutorService != null) {
            mExecutorService.shutdown();
            mExecutorService = null;
        }
        if (mErrorInterface == null) {
            return;
        }
        mCurrentNext = 0;
        if (mErrorInterface != null) {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                if (mHandler == null) {
                    mHandler = new Handler(Looper.getMainLooper());
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mErrorInterface.onError(objects);
                        } catch (Exception e) {
                            //这里不再让他执行onError了，避免造成死循环
                        }
                    }
                });
            } else {
                try {
                    mErrorInterface.onError(objects);
                } catch (Exception e) {
                    //这里不再让他执行onError了，避免造成死循环
                }
            }
        }
    }

    /**
     * ------------------------------------------------------------------------------------------
     */
    public void start() {
        try {
            mCancel = false;
            mComplete = false;
            if (mLinkInterfaces == null) {
                return;
            }
            mCurrentNext = 0;
            if (mCurrentNext >= mLinkInterfaces.size()) {
                toComplete();
            } else if (mLinkInterfaces.get(mCurrentNext) != null) {
                //如果需要在子线程执行
                if (mThread.get(mCurrentNext) == NEW_THREAD) {
                    //如果当前线程非子线程则从线程池中取一个或者new一个子线程
                    if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                        Runnable syncRunnable = new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    mLinkInterfaces.get(mCurrentNext).onNext(mMap);
                                } catch (Exception e) {
                                    toError(e);
                                }
                            }
                        };
                        if (mExecutorService == null) {
                            mExecutorService = Executors.newCachedThreadPool();
                        }
                        mExecutorService.execute(syncRunnable);
                    } else {
                        //已经在子线程了
                        mLinkInterfaces.get(mCurrentNext).onNext(mMap);
                    }
                } else {
                    //如果需要运行在UI线程
                    //判断当前线程是否是主线程
                    if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                        //不是主线程的话new一个Handler
                        if (mHandler == null) {
                            mHandler = new Handler(Looper.getMainLooper());
                        }
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    mLinkInterfaces.get(mCurrentNext).onNext(mMap);
                                } catch (Exception e) {
                                    toError(e);
                                }
                            }
                        });
                    } else {
                        //是的话直接回调出去
                        mLinkInterfaces.get(mCurrentNext).onNext(mMap);
                    }
                }
            }
        } catch (Exception e) {
            toError(e);
        }
    }

    public LinkCallTask setRepeatTime(Integer repeattime) {
        this.mRepeatTime = repeattime;
        return this;
    }

    public void toRepeat() {
        if (mCancel || mComplete) {
            return;
        }
        if (mLinkInterfaces != null && mCurrentNext < mLinkInterfaces.size()) {
            try {
                if (mRepeatTime > 0) {
                    //子线程中不能使用Handler这里要区分一下
                    if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                        Thread.sleep(mRepeatTime);
                        mLinkInterfaces.get(mCurrentNext).onNext(mMap);
                    } else {
                        if (mHandler == null) {
                            mHandler = new Handler();
                        }
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    mLinkInterfaces.get(mCurrentNext).onNext(mMap);
                                } catch (Exception e) {
                                    toError(e);
                                }
                            }
                        }, mRepeatTime);
                    }
                } else {
                    mLinkInterfaces.get(mCurrentNext).onNext(mMap);
                }
            } catch (Exception e) {
                toError(e);
            }
        }
    }

    public void toNext() {
        try {
            if (mLinkInterfaces == null || mCancel || mComplete) {
                return;
            }
            mCurrentNext++;
            if (mCurrentNext >= mLinkInterfaces.size()) {
                toComplete();
            } else if (mLinkInterfaces.get(mCurrentNext) != null) {
                //如果需要在子线程执行
                if (mThread.get(mCurrentNext) == NEW_THREAD) {
                    //如果当前线程非子线程则从线程池中取一个或者new一个子线程
                    if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                        Runnable syncRunnable = new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    mLinkInterfaces.get(mCurrentNext).onNext(mMap);
                                } catch (Exception e) {
                                    toError(e);
                                }
                            }
                        };
                        if (mExecutorService == null) {
                            mExecutorService = Executors.newCachedThreadPool();
                        }
                        mExecutorService.execute(syncRunnable);
                    } else {
                        //已经在子线程了
                        mLinkInterfaces.get(mCurrentNext).onNext(mMap);
                    }
                } else {
                    //如果需要运行在UI线程
                    //判断当前线程是否是主线程
                    if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                        //不是主线程的话new一个Handler
                        if (mHandler == null) {
                            mHandler = new Handler(Looper.getMainLooper());
                        }
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    mLinkInterfaces.get(mCurrentNext).onNext(mMap);
                                } catch (Exception e) {
                                    toError(e);
                                }
                            }
                        });
                    } else {
                        //是的话直接回调出去
                        mLinkInterfaces.get(mCurrentNext).onNext(mMap);
                    }
                }
            }
        } catch (Exception e) {
            toError(e);
        }
    }

    /**
     * 跳转到完成
     */
    public void toComplete() {
        mComplete = true;
        if (mExecutorService != null) {
            mExecutorService.shutdown();
            mExecutorService = null;
        }
        mCurrentNext = mLinkInterfaces == null ? 0 : mLinkInterfaces.size();
        if (mCompleteInterface != null) {
            //如果需要运行在UI线程
            //判断当前线程是否是主线程
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                //不是主线程的话new一个Handler
                if (mHandler == null) {
                    mHandler = new Handler(Looper.getMainLooper());
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mCompleteInterface.onComplete(mMap);
                        } catch (Exception e) {
                            toError(e);
                        }
                    }
                });
            } else {
                //是的话直接回调出去
                try {
                    mCompleteInterface.onComplete(mMap);
                } catch (Exception e) {
                    toError(e);
                }
            }
        }
    }

    /**
     * 跳转到取消
     */
    public void toCancel() {
        mCancel = true;
        if (mExecutorService != null) {
            mExecutorService.shutdown();
            mExecutorService = null;
        }
        mCurrentNext = 0;
        if (mCancelInterface != null) {
            //如果需要运行在UI线程
            //判断当前线程是否是主线程
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                //不是主线程的话new一个Handler
                if (mHandler == null) {
                    mHandler = new Handler(Looper.getMainLooper());
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mCancelInterface.onCancel(null);
                        } catch (Exception e) {
                            toError(e);
                        }
                    }
                });
            } else {
                //是的话直接回调出去
                mCancelInterface.onCancel(null);
            }
        }
    }

    public int getCurrentLink() {
        return this.mCurrentNext;
    }

    public Map<String, Object> getMap() {
        return this.mMap;
    }
}
