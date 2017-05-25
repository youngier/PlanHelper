package com.young.planhelper.network;

import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author: young
 * date:17/5/9  18:09
 */

public interface BackupsApiService {


    @FormUrlEncoded
    @POST("servlet/BackupsServlet")
    Observable<String> backups(@Field("user_id") String userId,
                               @Field("backupsList") String backlogInfoList,
                               @Field("planInfoList") String planInfoList,
                               @Field("planItemInfoList") String planItemInfoList,
                               @Field("planSecondItemInfoList") String planSecondItemInfoList,
                               @Field("planThirdItemInfoList") String planThirdItemInfoList,
                               @Field("planOperationInfoList") String planOperationInfoList);

}
