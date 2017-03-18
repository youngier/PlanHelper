package com.young.planhelper.network.plan;

import com.young.planhelper.mvp.plan.model.bean.PlanInfo;

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


public interface PlanListApiService {

    @FormUrlEncoded
    @POST("servlet/PlanListServlet")
    Observable<List<PlanInfo>> getPlanInfoList(@Field("user_id") String userId);
}
