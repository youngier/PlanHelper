package com.young.planhelper.mvp.login;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.login.model.bean.User;

/**
 * @author: young
 * date:17/8/17  14:11
 */


public class LoginContract {

    public interface Presenter extends IPresenter{

        void login(String account, String password);

        /**
         * 保存登录后的用户信息
         * @param user
         */
        void saveUserInfo(User user);
    }

    public interface View extends IView{

        void toHomeActivity();
    }
}
