package com.young.planhelper.mvp.home.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.home.view.IHomeView;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/9/30  10:51
 */


public class HomePresenter extends Presenter implements IHomePresenter {

    public HomePresenter(IHomeView view){
        super((Context) view);

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroy() {

    }
}
