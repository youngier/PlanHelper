package com.young.planhelper.injection.component;

import com.young.planhelper.injection.module.ActivityModule;
import com.young.planhelper.injection.scope.Activity;
import com.young.planhelper.mvp.base.BaseActivity;

import dagger.Component;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/12  18:02
 */
@Activity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);

}
