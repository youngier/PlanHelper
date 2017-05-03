package com.young.planhelper.mvp.register.model;

import android.content.Context;
import android.net.Uri;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.network.RegisterApiService;
import com.young.planhelper.network.UploadImageApiService;
import com.young.planhelper.util.LogUtil;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/17  13:31
 */


public class RegisterBiz extends Biz implements IRegisterBiz{

    private final Retrofit mRetrofit;

    private Context mContext;

    public RegisterBiz(Context context) {
        this.mContext = context;
        mRetrofit = AppApplication.get(context).getmAppComponent().getRetrofit();
    }

    @Override
    public void getData(ICallback callback) {

    }

    @Override
    public void register(String account, String nickname, String password, String email, ICallback callback) {
        RegisterApiService loginApiService = mRetrofit.create(RegisterApiService.class);
        //获得Observable对象
        Observable<User> data = loginApiService.login(account, nickname, password, email);

        callback.onResult(data);
    }

    @Override
    public void uploadImage(Uri uri, ICallback callback) {
        UploadImageApiService uploadImageApiService = mRetrofit.create(UploadImageApiService.class);

        File file = new File(uri.getPath());
        String token = "ASDDSKKK19990SDDDSS";//用户token
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型
                .addFormDataPart("image", token);//ParamKey.TOKEN 自定义参数key常量类，即参数名
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("imgfile", file.getName(), imageBody);//imgfile 后台接收图片流的参数名

        List<MultipartBody.Part> parts = builder.build().parts();

        String userId = AppApplication.get(mContext).getmAppComponent().getUserInfo().getUserId();

        LogUtil.eLog("用户id为："+userId);
        //获得Observable对象
        Observable<String> data = uploadImageApiService.uploadImage(userId, parts);

        callback.onResult(data);
    }
}
