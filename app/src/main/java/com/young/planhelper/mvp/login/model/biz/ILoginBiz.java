package com.young.planhelper.mvp.login.model.biz;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.login.model.bean.User;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  13:53
 */


public interface ILoginBiz extends IBiz{


    void saveUserInfo(User user);
}
