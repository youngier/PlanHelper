package com.young.planhelper.util;

import android.util.Log;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/3  13:27
 */

public class LogUtil {
    /**
     * 默认是DEBUG模式，正式打包需改成false
    */
    public static boolean DEBUG = true;

    public static boolean LOG_DEBUG = true;

    static String LOG = "test";

    public static void eLog(String msg) {
        if (LOG_DEBUG)
            Log.e(LOG, msg);
    }

    public static void dLog(String msg) {
        if (LOG_DEBUG)
            Log.d(LOG, msg);
    }
}
