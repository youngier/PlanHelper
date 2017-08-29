package com.young.planhelper.mvp.plan.presenter;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.model.biz.IPlanBiz;
import com.young.planhelper.mvp.plan.model.biz.PlanBiz;
import com.young.planhelper.network.plan.PlanItemListApiService;
import com.young.planhelper.network.plan.PlanSecondItemListApiService;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/24  14:50
 */


public class PlanSecondItemPresenter extends Presenter implements IPlanSecondItemPresenter{

    private IPlanBiz mBiz;
    private Retrofit mRetrofit;

    public PlanSecondItemPresenter(IView view, Context context) {
        super(view, context);
        mBiz = new PlanBiz(context);
        mRetrofit = ((AppApplication)context.getApplicationContext()).getmAppComponent().getRetrofit();
    }

    @Override
    public void initData() {

    }

    @Override
    public void getPlanSecondItemInfo(long planItemInfoId, IBiz.ICallback callback) {
        mBiz.getPlanSecondItemInfo(planItemInfoId, callback);
    }

    @Override
    public void getPlanSecondItemInfoByNetWork(long planItemInfoId, IBiz.ICallback callback) {

        PlanSecondItemListApiService planSecondItemListApiService = mRetrofit.create(PlanSecondItemListApiService.class);

        Observable<List<PlanSecondItemInfo>> data = planSecondItemListApiService.getPlanSecondItemInfoList(planItemInfoId);

        System.out.println("planItemInfoId:"+planItemInfoId);

        callback.onResult(data);
    }

    @Override
    public void modifyPlanSecondItemInfoStateById(long planSecondItemInfoId, boolean isChecked, IBiz.ICallback callback) {
        mBiz.modifyPlanSecondItemInfoStateById(planSecondItemInfoId, isChecked, callback);
    }

    @Override
    public void modifyPlanSecondItemInfoTitle(long planItemInfoId, String title, IBiz.ICallback callback) {
        mBiz.modifyPlanSecondItemInfoTitle(planItemInfoId, title, callback);
    }

    @Override
    public void deletePlanItemInfo(long planItemInfoId, IBiz.ICallback callback) {
        mBiz.deletePlanItemInfo(planItemInfoId, callback);
    }
}
