package com.young.planhelper.application;

import android.app.Application;
import android.content.Context;

import com.young.planhelper.config.api.ApiServiceModule;
import com.young.planhelper.config.app.AppComponent;
import com.young.planhelper.config.app.AppModule;
import com.young.planhelper.config.app.DaggerAppComponent;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/8  09:46
 */


public class AppApplication extends Application{

    public static AppApplication get(Context context){
        return (AppApplication) context.getApplicationContext();
    }

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    public AppComponent getmAppComponent(){
        return mAppComponent;
    }
}
