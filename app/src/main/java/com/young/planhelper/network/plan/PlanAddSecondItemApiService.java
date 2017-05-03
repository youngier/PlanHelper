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

public interface PlanAddSecondItemApiService {

    /* relation为1即是好友关系，为0是没关系, 为2是提出申请 */

    @FormUrlEncoded
    @POST("servlet/plan/PlanAddSecondItemServlet")
    Observable<String> addPlanSecondItem(@Field("plan_item_id") long planItemInfoId,
                                         @Field("plan_second_item_title") String planSecondItemTitle,
                                         @Field("plan_second_item_content") String planSecondItemContent,
                                         @Field("plan_second_item_time") long planSecondItemTime);
}