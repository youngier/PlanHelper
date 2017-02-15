package com.young.planhelper.mvp.profile.model.biz;

import com.young.planhelper.mvp.base.model.IBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/29  09:20
 */


public interface IProfileBiz extends IBiz {

    /**
     * 根据月份和完成类型获取任务
     * @param type
     * @param time
     * @param callback
     */
    void getProfileInfoByMonth(int type, String time, ICallback callback);
}
