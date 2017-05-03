package com.young.planhelper.mvp.plan.presenter;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.model.biz.IPlanBiz;
import com.young.planhelper.mvp.plan.model.biz.PlanBiz;
import com.young.planhelper.network.plan.PlanAddItemApiService;
import com.young.planhelper.network.plan.PlanListApiService;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/23  21:25
 */


public class PlanItemAddPresenter extends Presenter implements IPlanItemAddPresenter{

    private IPlanBiz mBiz;

    private Retrofit mRetrofit;

    public PlanItemAddPresenter(IView view, Context context) {
        super(view, context);
        mBiz = new PlanBiz(context);
        mRetrofit = AppApplication.get(context).getmAppComponent().getRetrofit();
    }

    @Override
    public void initData() {

    }

    @Override
    public void addPlanItem(PlanItemInfo planItemInfo, IBiz.ICallback callback) {
        mBiz.addPlanItem(planItemInfo, callback);
    }

    @Override
    public void addPlanItemByNetWork(PlanItemInfo planItemInfo, IBiz.ICallback callback) {

        PlanAddItemApiService planListApiService = mRetrofit.create(PlanAddItemApiService.class);

        Observable<String> data = planListApiService.addPlanItem(planItemInfo.getPlanInfoId(),
                planItemInfo.getTitle());

        callback.onResult(data);
    }
}
