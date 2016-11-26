package com.young.planhelper.injection.component;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.injection.module.ApplicationModule;
import com.young.planhelper.injection.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/12  18:00
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AppApplication appApplication);

    @ApplicationContext
    Context context();

    AppApplication application();
}
