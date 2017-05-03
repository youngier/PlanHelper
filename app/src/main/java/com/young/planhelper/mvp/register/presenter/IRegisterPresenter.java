package com.young.planhelper.mvp.register.presenter;

import android.net.Uri;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.login.model.bean.User;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/17  13:24
 */


public interface IRegisterPresenter {

    /**
     * 注册
     * @param account
     * @param nickname
     * @param password
     * @param email
     * @param callback
     */
    void register(String account, String nickname, String password, String email, IBiz.ICallback callback);

    /**
     * 上传图片
     * @param uri
     * @param callback
     */
    void uploadImage(Uri uri, IBiz.ICallback callback);

    /**
     * 保存用户信息
     * @param s
     */
    void saveUserInfo(User s);
}
