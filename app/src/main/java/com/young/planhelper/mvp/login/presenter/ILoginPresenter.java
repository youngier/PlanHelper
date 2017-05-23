package com.young.planhelper.mvp.login.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;
import com.young.planhelper.mvp.login.model.bean.User;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  13:50
 */


public interface ILoginPresenter extends IPresenter{

    void login(String account, String password, IBiz.ICallback callback);

    /**
     * 保存登录后的用户信息
     * @param user
     */
    void saveUserInfo(User user);


}
