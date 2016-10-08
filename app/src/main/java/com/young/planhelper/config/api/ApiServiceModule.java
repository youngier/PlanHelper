package com.young.planhelper.config.api;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/8  09:52
 */

@Module
public class ApiServiceModule {

    /**
     * 提供项目唯一的OkHttpClient实例
     * @return
     */
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    /**
     * 提供项目唯一的Retrofit实例
     * @param application
     * @param okHttpClient
     * @return
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(Application application, OkHttpClient okHttpClient){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient);
        return builder.build();
    }

    /**
     * 提供项目唯一的ApiService实例
     * @param retrofit
     * @return
     */
    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
