package com.young.planhelper.mvp.base.presenter;

import android.content.Context;
import android.view.View;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.view.IView;

import retrofit2.Retrofit;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/9/30  10:44
 */


public abstract class Presenter implements IPresenter{

    protected Context context;



    public Presenter(Context context){
        this.context = context;
    }

    public Presenter(IView view, Context context){
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
