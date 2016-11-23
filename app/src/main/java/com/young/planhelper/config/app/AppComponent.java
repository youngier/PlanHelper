package com.young.planhelper.config.app;

import android.app.Application;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.config.api.ApiService;
import com.young.planhelper.config.api.ApiServiceModule;
import com.young.planhelper.config.orm.OrmModule;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/8  10:09
 */

@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class, OrmModule.class})
public interface AppComponent {

    AppApplication getApplication();

    ApiService getService();

    Realm getRealm();

}
