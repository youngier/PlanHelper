package com.young.planhelper.network;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/18  12:23
 */


public interface UploadImageApiService {
    /**
     * 图片上传
     */
    @Multipart
    @POST("servlet/UploadServlet")
    Observable<String> uploadImage(@Query("user_id") String userId, @Part List<MultipartBody.Part> partList);
}
