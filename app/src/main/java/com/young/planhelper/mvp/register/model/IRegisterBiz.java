package com.young.planhelper.mvp.register.model;

import android.net.Uri;

import com.young.planhelper.mvp.base.model.IBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/17  13:30
 */


public interface IRegisterBiz extends IBiz{

    /**
     * 注册
     * @param account
     * @param password
     * @param email
     * @param callback
     */
    void register(String account, String password, String email, ICallback callback);

    /**
     * 上传图片
     * @param uri
     * @param callback
     */
    void uploadImage(Uri uri, ICallback callback);
}
