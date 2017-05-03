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
import com.young.planhelper.network.LoginApiService;
import com.young.planhelper.network.plan.PlanAddApiService;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

import static android.R.attr.password;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/21  22:07
 */


public class PlanAddPresenter extends Presenter implements IPlanAddPresenter{

    private final Retrofit mRetrofit;
    private IPlanBiz mBIz;

    private User user;

    public PlanAddPresenter(IView view, Context context) {
        super(view, context);
        mBIz = new PlanBiz(context);
        mRetrofit = AppApplication.get(context).getmAppComponent().getRetrofit();
        user = AppApplication.get(context).getmAppComponent().getUserInfo();
    }

    @Override
    public void initData() {

    }

    public void addPlan(PlanInfo planInfo, IBiz.ICallback callback){
        mBIz.addPlan(planInfo, callback);
    }

    @Override
    public void addPlanByNetWork(PlanInfo planInfo, IBiz.ICallback callback) {
        PlanAddApiService planAddApiService = mRetrofit.create(PlanAddApiService.class);

        //获得Observable对象
        Observable<String> data = planAddApiService.addPlan(planInfo.getTitle(), planInfo.getMembers(), planInfo.getAuthority(), user.getUserId());

        callback.onResult(data);
    }
}
