package com.young.planhelper.mvp.base.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.BaseBiz;
import com.young.planhelper.mvp.base.model.IBaseBiz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.login.model.biz.ILoginBiz;
import com.young.planhelper.mvp.login.model.biz.LoginBiz;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import java.util.List;

/**
 * @author: young
 * date:17/5/9  18:04
 */


public class BasePresenter extends Presenter implements IBasePresenter{

    private IBaseBiz mBiz;

    public BasePresenter(IView view, Context context) {
        super(view, context);
        mBiz = new BaseBiz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void backups(List<BacklogInfo> backlogInfoList, IBiz.ICallback iCallback) {
        mBiz.backups(backlogInfoList, iCallback);
    }

    @Override
    public List<BacklogInfo> getBacklogList() {
        return mBiz.getBacklogList();
    }

}
