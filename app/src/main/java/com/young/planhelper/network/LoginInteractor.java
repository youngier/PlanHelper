package com.young.planhelper.network;

import com.young.planhelper.mvp.login.model.bean.User;

import rx.Subscription;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  11:19
 */


public interface LoginInteractor {
    Subscription login(String login, String password, BaseSubsribe<User> subsribe);
}
