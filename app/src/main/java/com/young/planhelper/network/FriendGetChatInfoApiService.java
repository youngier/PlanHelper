package com.young.planhelper.network;

import com.young.planhelper.mvp.friend.model.bean.ChatInfo;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/20  16:53
 */



public interface FriendGetChatInfoApiService {


    @FormUrlEncoded
    @POST("servlet/FriendGetChatInfoServlet")
    Observable<List<ChatInfo>> getChatInfos(@Field("user_id_one") String userIdOne, @Field("user_id_two") String userIdTwo);
}
