package com.young.planhelper.mvp.base.model;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/9/30  10:36
 */
public interface IBiz {
    void getData(ICallback callback);

    interface ICallback{
        void onResult(Object data);
    }
}
