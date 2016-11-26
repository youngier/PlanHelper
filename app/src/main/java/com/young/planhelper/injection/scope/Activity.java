package com.young.planhelper.injection.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/12  18:28
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Activity {
}
