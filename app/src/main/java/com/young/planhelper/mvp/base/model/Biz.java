package com.young.planhelper.mvp.base.model;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/9/30  10:39
 */


public abstract class Biz implements IBiz {

    @Override
    public abstract void getData(ICallback callback);
}
