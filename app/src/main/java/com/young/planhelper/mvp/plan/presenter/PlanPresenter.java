package com.young.planhelper.mvp.plan.presenter;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.biz.IPlanBiz;
import com.young.planhelper.mvp.plan.model.biz.PlanBiz;
import com.young.planhelper.network.plan.PlanListApiService;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/20  12:20
 */


public class PlanPresenter extends Presenter implements IPlanPresenter{

    private IPlanBiz mBiz;

    private Retrofit mRetrofit;

    private User user;

    public PlanPresenter(IView view, Context context) {
        super(view, context);
        mBiz = new PlanBiz(context);
        mRetrofit = AppApplication.get(context).getmAppComponent().getRetrofit();
        user = AppApplication.get(context).getmAppComponent().getUserInfo();
    }

    @Override
    public void initData() {

    }

    @Override
    public void getPlanInfo(IBiz.ICallback callback) {
        mBiz.getPlanInfo(callback);
    }

    @Override
    public void getPlanInfoByNetWork(IBiz.ICallback callback) {
        PlanListApiService planListApiService = mRetrofit.create(PlanListApiService.class);

        Observable<List<PlanInfo>> data = planListApiService.getPlanInfoList(user.getUserId());

        callback.onResult(data);
    }
}
