package com.young.planhelper.network.plan;

import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/28  18:27
 */


public interface PlanSecondItemListApiService {

    @FormUrlEncoded
    @POST("servlet/plan/PlanSecondItemListServlet")
    Observable<List<PlanSecondItemInfo>> getPlanSecondItemInfoList(@Field("plan_item_id") long planItemInfoId);
}
