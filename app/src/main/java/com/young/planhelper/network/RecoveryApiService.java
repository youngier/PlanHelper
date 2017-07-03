package com.young.planhelper.network;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author: young
 * date:17/5/9  18:09
 */

public interface RecoveryApiService {


    @FormUrlEncoded
    @POST("servlet/RecoveryServlet")
    Observable<String> recovery(@Field("user_id") String userId);

}
