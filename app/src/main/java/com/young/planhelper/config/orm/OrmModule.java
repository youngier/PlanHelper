package com.young.planhelper.config.orm;

import com.young.planhelper.application.AppApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/16  23:06
 */

@Module
public class OrmModule {

    @Provides
    @Singleton
    Realm provideRealm(AppApplication appApplication){
        Realm realm = Realm.getInstance(
                new RealmConfiguration.Builder(appApplication)
                        .name("planHelper.realm")
                        .build()
        );

        return realm;
    }

}
