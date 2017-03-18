package com.young.planhelper.network.friend;

import com.young.planhelper.mvp.friend.model.bean.ChatInfo;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/3/13  14:11
 */


public interface FriendGetLastestChatInfoApiService {

    @FormUrlEncoded
    @POST("servlet/FriendFindLastestNewsServlet")
    Observable<List<ChatInfo>> getLastestChatInfo(@Field("user_id") String userId);
}
