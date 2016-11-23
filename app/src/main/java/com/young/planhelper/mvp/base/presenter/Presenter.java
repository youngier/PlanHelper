package com.young.planhelper.mvp.base.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.view.IView;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/9/30  10:44
 */


public abstract class Presenter implements IPresenter{


    protected Context context;
    protected IView view;


    public Presenter(IView view, Context context){
        this.view=view;
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
