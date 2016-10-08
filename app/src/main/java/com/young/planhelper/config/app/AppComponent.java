package com.young.planhelper.config.app;
;

import android.app.Application;

import com.young.planhelper.config.api.ApiService;
import com.young.planhelper.config.api.ApiServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/8  10:09
 */

@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {

    Application getApplication();

    ApiService getService();

}
