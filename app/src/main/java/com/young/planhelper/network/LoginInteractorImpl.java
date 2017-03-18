package com.young.planhelper.network;

import com.young.planhelper.mvp.login.model.bean.User;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  11:21
 */


public class LoginInteractorImpl implements LoginInteractor{

    private final LoginApiService api;

    @Inject
    public LoginInteractorImpl(LoginApiService myApi) {
        this.api = myApi;
    }

    @Override
    public Subscription login(String login, String password, BaseSubsribe<User> subsribe) {
        Observable<User> observable = api.login(login, password);
        Subscription subscribe = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subsribe);
        return subscribe;
    }
}
