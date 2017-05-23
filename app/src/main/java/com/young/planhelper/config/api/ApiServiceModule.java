package com.young.planhelper.config.api;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.young.planhelper.application.AppApplication;
import com.young.planhelper.constant.AppConstant;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/8  09:52
 */

@Module
public class ApiServiceModule {

    /**
     * 提供项目唯一的Retrofit实例
     * @param application
     * @return
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(AppApplication application){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        OkHttpClient.Builder builder = okHttpClient.newBuilder();
        builder.retryOnConnectionFailure(true);
        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl(AppConstant.REQUEST_URL)
                .addConverterFactory(gsonConverterFactory)

                //RxJava和Retrofit结合的关键。
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit;
    }

//    /**
//     * 提供项目唯一的ApiService实例
//     * @param retrofit
//     * @return
//     */
//    @Provides
//    @Singleton
//    ApiService provideApiService(Retrofit retrofit){
//        return retrofit.create(ApiService.class);
//    }
}
