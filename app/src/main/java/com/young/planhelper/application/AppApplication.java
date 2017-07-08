package com.young.planhelper.application;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;
import com.young.planhelper.config.api.ApiServiceModule;
import com.young.planhelper.config.app.AppComponent;
import com.young.planhelper.config.app.AppModule;
import com.young.planhelper.config.app.DaggerAppComponent;
import com.young.planhelper.config.orm.OrmModule;
import com.young.planhelper.mvp.common.crop.basic.ActivityStack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/8  09:46
 */


public class AppApplication extends Application{

    public static AppApplication get(Context context){
        return (AppApplication) context.getApplicationContext();
    }

    private AppComponent mAppComponent;

    /** Activity 栈 */
    /** 裁剪图片需要 **/
    public ActivityStack mActivityStack = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, "0ce9333c1b", false, strategy);

//        LeakCanary.install(this);

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .ormModule(new OrmModule())
                .build();
        mActivityStack = new ActivityStack();   // 初始化Activity 栈
    }

    public AppComponent getmAppComponent(){
        return mAppComponent;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
