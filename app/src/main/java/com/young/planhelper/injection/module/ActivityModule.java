package com.young.planhelper.injection.module;

import com.young.planhelper.injection.scope.ActivityContext;
import com.young.planhelper.mvp.base.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/12  17:52
 */

@Module
public class ActivityModule {

    private BaseActivity mActivity;

    public ActivityModule(BaseActivity activity){
        this.mActivity = activity;
    }

    @Provides
    public BaseActivity providesActivity(){
        return mActivity;
    }

    @Provides
    @ActivityContext
    public BaseActivity providesContext(){
        return mActivity;
    }
}
