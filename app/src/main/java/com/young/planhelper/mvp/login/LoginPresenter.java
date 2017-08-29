package com.young.planhelper.mvp.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.home.HomeCloneActivity;
import com.young.planhelper.mvp.login.LoginContract;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.login.model.biz.ILoginBiz;
import com.young.planhelper.mvp.login.model.biz.LoginBiz;
import com.young.planhelper.network.LoginApiService;
import com.young.planhelper.util.SharePreferenceUtil;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  13:51
 */


public class LoginPresenter extends Presenter implements LoginContract.Presenter{

    private ILoginBiz mBiz;

    private LoginContract.View mView;


    public LoginPresenter(LoginContract.View view, Context context) {
        super(context);
        mView = view;
        mBiz = new LoginBiz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void login(String account, String password) {

        //获得Observable对象
        Observable<User> user = mBiz.login(account, password);

        user.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {

                    @Override
                    public void onCompleted() {
                        Log.i("way", "onCompleted");
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("way", "onError" + e.toString());
                        mView.hideProgress();
                    }

                    @Override
                    public void onNext(User s) {
                        saveUserInfo(s);
                        Log.i("way", "onNext" + s.getAccount());
                        mView.hideProgress();
                        mView.toHomeActivity();
                    }
                });
    }

    @Override
    public void saveUserInfo(User user) {
        mBiz.saveUserInfo(user);
    }
}
