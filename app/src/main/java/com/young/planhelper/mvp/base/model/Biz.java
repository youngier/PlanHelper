package com.young.planhelper.mvp.base.model;

import com.young.planhelper.util.LogUtil;

import io.realm.Realm;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/9/30  10:39
 */


public abstract class Biz implements IBiz {

    @Override
    public abstract void getData(ICallback callback);

    protected void checkRealm(Realm realm, ICallback callback){
        if( realm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("获取失败");
            return;
        }
    }
}
