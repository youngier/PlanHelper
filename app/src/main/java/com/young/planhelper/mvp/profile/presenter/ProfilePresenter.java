package com.young.planhelper.mvp.profile.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.profile.model.biz.IProfileBiz;
import com.young.planhelper.mvp.profile.model.biz.ProfileBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/29  09:19
 */


public class ProfilePresenter extends Presenter implements IProfilePresenter{

    private IProfileBiz mBiz;

    public ProfilePresenter(IView view, Context context) {
        super(view, context);
        mBiz = new ProfileBiz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getProfileInfoByMonth(int type, String time, IBiz.ICallback callback) {
        mBiz.getProfileInfoByMonth(type, time, callback);
    }
}
