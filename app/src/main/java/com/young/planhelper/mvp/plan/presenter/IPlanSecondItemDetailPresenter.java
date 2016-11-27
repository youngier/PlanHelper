package com.young.planhelper.mvp.plan.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/25  19:28
 */


public interface IPlanSecondItemDetailPresenter extends IPresenter{

    /**
     * 根据具体id获取具体详情
     * @param planSecondItemInfoId
     * @param callback
     */
    void getPlanSecondItemInfoById(long planSecondItemInfoId, final IBiz.ICallback callback);

    /**
     * 根据子任务项id获取具体详情
     * @param planSecondItemInfoId
     * @param callback
     */
    void getPlanThirdItemInfoBySecondId(long planSecondItemInfoId, final IBiz.ICallback callback);


}
