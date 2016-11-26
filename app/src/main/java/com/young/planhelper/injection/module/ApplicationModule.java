package com.young.planhelper.injection.module;


import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.injection.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/12  17:22
 */

@Module
public class ApplicationModule {

    protected final AppApplication mAppApplication;

    public ApplicationModule(AppApplication appApplication) {
        this.mAppApplication = appApplication;
    }

    @Provides
    @Singleton
    public AppApplication provideApplication(){
        return mAppApplication;
    }

    @Provides
    @ApplicationContext
    @Singleton
    Context provideContext(){
        return mAppApplication;
    }
}
