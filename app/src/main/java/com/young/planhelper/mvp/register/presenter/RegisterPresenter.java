package com.young.planhelper.mvp.register.presenter;

import android.content.Context;
import android.net.Uri;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.register.model.IRegisterBiz;
import com.young.planhelper.mvp.register.model.RegisterBiz;
import com.young.planhelper.util.SharePreferenceUtil;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/17  13:24
 */


public class RegisterPresenter extends Presenter implements IRegisterPresenter{

    private IRegisterBiz mBiz;

    public RegisterPresenter(IView view, Context context) {
        super(view, context);
        mBiz = new RegisterBiz(context);
    }
    @Override
    public void initData() {

    }

    @Override
    public void register(String account, String nickname, String password, String email, IBiz.ICallback callback) {
        mBiz.register(account, nickname, password, email, callback);
    }

    @Override
    public void uploadImage(Uri uri, IBiz.ICallback callback) {
        mBiz.uploadImage(uri, callback);

    }

    @Override
    public void saveUserInfo(User user) {
        AppApplication.get(context).getmAppComponent().getUserInfo().copyWith(user);
        SharePreferenceUtil sharePreferenceUtil = new SharePreferenceUtil(context);
        sharePreferenceUtil.saveUser(user);
    }
}
