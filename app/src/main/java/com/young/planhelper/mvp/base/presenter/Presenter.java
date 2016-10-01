package com.young.planhelper.mvp.base.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.view.IView;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/9/30  10:44
 */


public abstract class Presenter<T extends IBiz, T1 extends IView> implements IPresenter{


    private Context context;
    private T biz;
    private T1 view;

    public Presenter(T biz, T1 view, Context context){
        this.view=view;
        this.biz = biz;
        this.context = context;
    }

    public Presenter(T1 view, Context context){
        this.view=view;
        this.biz = null;
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public abstract void initData();

    @Override
    public void onDestroy() {

    }
}
