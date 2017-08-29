package com.young.planhelper.mvp.login.model.biz;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.login.model.bean.User;

import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  13:53
 */


public interface ILoginBiz extends IBiz{

    /**
     * 保存用户信息
     * @param user
     */
    void saveUserInfo(User user);

    Observable<User> login(String account, String password);
}
