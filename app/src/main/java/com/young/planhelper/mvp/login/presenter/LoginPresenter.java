package com.young.planhelper.mvp.login.presenter;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.login.model.biz.ILoginBiz;
import com.young.planhelper.mvp.login.model.biz.LoginBiz;
import com.young.planhelper.network.LoginApiService;
import com.young.planhelper.util.SharePreferenceUtil;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  13:51
 */


public class LoginPresenter extends Presenter implements ILoginPresenter{

    private ILoginBiz mBiz;

    private final Retrofit mRetrofit;

    public LoginPresenter(IView view, Context context) {
        super(view, context);
        mBiz = new LoginBiz(context);
        mRetrofit = AppApplication.get(context).getmAppComponent().getRetrofit();
    }

    @Override
    public void initData() {

    }

    @Override
    public void login(String account, String password, IBiz.ICallback iCallback) {
        LoginApiService loginApiService = mRetrofit.create(LoginApiService.class);
        //获得Observable对象
        Observable<User> data = loginApiService.login(account, password);

        iCallback.onResult(data);
    }

    @Override
    public void saveUserInfo(User user) {
        mBiz.saveUserInfo(user);
    }
}
