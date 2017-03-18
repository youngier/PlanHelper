package com.young.planhelper.config.user;

import com.young.planhelper.mvp.login.model.bean.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/17  14:38
 */

@Module
public class UserModule {

    private static User user = new User();

    @Provides
    @Singleton
    User provideUser(){
        return user;
    }

}
