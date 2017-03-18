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



public interface FriendAddApiService {

    /* relation为1即是好友关系，为0是没关系, 为2是提出申请 */

    @FormUrlEncoded
    @POST("servlet/FriendAddServlet")
    Observable<String> addFriend(@Field("user_id_me") String meId,
                             @Field("user_id_friend") String friendId,
                             @Field("relation")int relation,
                             @Field("relation_remark")String relationRemark);
}
