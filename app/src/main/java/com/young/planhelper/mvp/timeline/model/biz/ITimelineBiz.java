package com.young.planhelper.mvp.timeline.model.biz;

import com.young.planhelper.mvp.base.model.IBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/22  14:34
 */


public interface ITimelineBiz extends IBiz{


    /**
     * 获取时间线记录的数据
     * @param statue
     * @param callback
     */
    void getTimelineInfoByStatue(int statue, ICallback callback);
}
