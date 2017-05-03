package com.young.planhelper.network.plan;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/3/26  11:42
 */

public interface PlanAddApiService {

    /* relation为1即是好友关系，为0是没关系, 为2是提出申请 */

    @FormUrlEncoded
    @POST("servlet/plan/PlanAddServlet")
    Observable<String> addPlan(@Field("plan_title") String planTitle,
                               @Field("plan_members") String planMembers,
                               @Field("plan_authority")int planAuthority,
                               @Field("plan_create_user_id")String userId);
}