package com.young.planhelper.config.app;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.config.api.ApiServiceModule;
import com.young.planhelper.config.orm.OrmModule;
import com.young.planhelper.config.user.UserModule;
import com.young.planhelper.mvp.login.model.bean.User;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import retrofit2.Retrofit;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/8  10:09
 */

@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class, OrmModule.class, UserModule.class})
public interface AppComponent {

    AppApplication getApplication();

    Realm getRealm();

    Retrofit getRetrofit();

    User getUserInfo();

}
