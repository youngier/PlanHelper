package com.young.planhelper.mvp.profile.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/29  09:18
 */

public interface IProfilePresenter extends IPresenter{

    /**
     * 根据类型， 获取月份内的所有任务书
     * @param type
     */
    void getProfileInfoByMonth(int type, String time, IBiz.ICallback callback);

}
