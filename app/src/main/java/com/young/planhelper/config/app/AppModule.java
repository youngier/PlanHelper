package com.young.planhelper.config.app;

import android.app.Application;

import com.young.planhelper.application.AppApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/8  10:34
 */

@Module
public class AppModule {

    private AppApplication mAppApplication;

    public AppModule(AppApplication appApplication){
        this.mAppApplication = appApplication;
    }

    @Provides
    @Singleton
    public AppApplication provideApplication(){
        return mAppApplication;
    }
}
