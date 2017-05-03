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

public interface PlanAddItemApiService {

    /* relation为1即是好友关系，为0是没关系, 为2是提出申请 */

    @FormUrlEncoded
    @POST("servlet/plan/PlanAddItemServlet")
    Observable<String> addPlanItem(@Field("plan_id") long planInfoId,
                                   @Field("plan_item_title") String planItemTitle);
}