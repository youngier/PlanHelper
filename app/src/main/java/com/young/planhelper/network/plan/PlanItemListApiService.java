package com.young.planhelper.network.plan;

import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;

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


public interface PlanItemListApiService {

    @FormUrlEncoded
    @POST("servlet/plan/PlanItemListServlet")
    Observable<List<PlanItemInfo>> getPlanItemInfoList(@Field("plan_id") long planInfoId);
}
