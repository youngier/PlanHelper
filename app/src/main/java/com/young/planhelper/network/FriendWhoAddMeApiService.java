package com.young.planhelper.network;

import com.young.planhelper.mvp.friend.model.bean.FriendAddInfo;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  11:03
 */


public interface FriendWhoAddMeApiService {
    @FormUrlEncoded
    @POST("servlet/FriendWhoAddMeServlet")
    Observable<List<FriendAddInfo>> findWhoAddMe(@Field("user_id_two") String userId);
}
