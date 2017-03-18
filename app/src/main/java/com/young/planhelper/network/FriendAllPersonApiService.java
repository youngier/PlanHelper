package com.young.planhelper.network;

import com.young.planhelper.mvp.login.model.bean.User;

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



public interface FriendAllPersonApiService {

    /* relation为1即是好友关系，为0是没关系, 为2是提出申请 */

    @FormUrlEncoded
    @POST("servlet/FriendAllPersonServlet")
    Observable<List<User>> getFriends(@Field("user_id") String userId);
}
