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



public interface FriendChatSendApiService {


    @FormUrlEncoded
    @POST("servlet/FriendChatSendServlet")
    Observable<String> sendChatInfo(@Field("user_id_from") String meId,
                                 @Field("user_id_to") String friendId,
                                 @Field("time") long time,
                                 @Field("content") String content);
}
