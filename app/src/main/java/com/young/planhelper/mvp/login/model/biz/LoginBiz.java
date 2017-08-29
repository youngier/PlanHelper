package com.young.planhelper.mvp.login.model.biz;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.network.LoginApiService;
import com.young.planhelper.util.SharePreferenceUtil;

import retrofit2.Retrofit;
import rx.Observable;


/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  13:53
 */


public class LoginBiz extends Biz implements ILoginBiz{


    private Context mContext;

    private final Retrofit mRetrofit;

    public LoginBiz(Context context) {
        this.mContext = context;
        mRetrofit = AppApplication.get(context).getmAppComponent().getRetrofit();

    }

    @Override
    public void getData(ICallback callback) {

    }


    @Override
    public void saveUserInfo(User user) {

        AppApplication.get(mContext).getmAppComponent().getUserInfo().copyWith(user);
        SharePreferenceUtil sharePreferenceUtil = new SharePreferenceUtil(mContext);
        sharePreferenceUtil.saveUser(user);
    }

    @Override
    public Observable<User> login(String account, String password) {
        LoginApiService loginApiService = mRetrofit.create(LoginApiService.class);
        return loginApiService.login(account, password);
    }
}
