package com.young.planhelper.network;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/20  16:53
 */


public interface FriendJudgeApiService {
    @FormUrlEncoded
    @POST("servlet/FriendJudgeServlet")
    Observable<String> judge(@Field("user_id_me")String meId, @Field("user_id_friend") String friendId);
}
