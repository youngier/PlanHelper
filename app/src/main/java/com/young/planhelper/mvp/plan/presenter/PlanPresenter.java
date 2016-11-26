package com.young.planhelper.mvp.plan.presenter;

import android.content.Context;
import android.view.View;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.biz.IPlanBiz;
import com.young.planhelper.mvp.plan.model.biz.PlanBiz;

import java.util.List;
import java.util.Objects;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/20  12:20
 */


public class PlanPresenter extends Presenter implements IPlanPresenter{

    private IPlanBiz mBiz;

    public PlanPresenter(IView view, Context context) {
        super(view, context);
        mBiz = new PlanBiz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getPlanInfo(IBiz.ICallback callback) {
        mBiz.getPlanInfo(callback);
    }
}
