package com.young.planhelper.network;

import com.young.planhelper.mvp.login.model.bean.User;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/17  13:33
 */


public interface RegisterApiService {
    @FormUrlEncoded
    @POST("servlet/RegisterServlet")
    Observable<User> login(@Field("account")String account,
                           @Field("password")String password,
                           @Field("email")String email);
}
