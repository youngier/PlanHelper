package com.young.planhelper.util;

import android.app.Application;
import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.login.model.bean.User;

public class SharePreferenceUtil
{
    private static final String DEFAULT_CATEGORY = "default";

    private static final String KEY_USER_ID = "user_id";

    private static final String KEY_ACCOUNT="account";

    private static final String KEY_PASSWORD="password";

    private static final String KEY_EMAIL="email";

    private static final String KEY_ICON_URL="icon_url";

    private final Context mContext;

    public SharePreferenceUtil(Context context){
        this.mContext = context;
    }


    /**
     * 读取值
     * @param key 键值
     * @param defaultValue 默认值
     * @return
     */
    public String getString(String key, String defaultValue)
    {
        return getString(DEFAULT_CATEGORY, key, defaultValue);
    }

    /**
     * 读取值
     * @param category 分类名
     * @param key 键值
     * @param defaultValue 默认值
     * @return
     */
    public String getString(String category, String key, String defaultValue)
    {
        String result = defaultValue;

        Application application = AppApplication.get(mContext);
        if(application==null)
            return result;

        android.content.SharedPreferences references = application.getSharedPreferences(category, Context.MODE_PRIVATE);
        result = references.getString(key, defaultValue);
        return result;
    }

    /**
     * 保存值
     * @param key 键值
     * @param value 值
     * @return
     */
    public boolean setString(String key, String value)
    {
        return setString(DEFAULT_CATEGORY, key, value);
    }

    /**
     * 保存值
     * @param category 分类名
     * @param key 键值
     * @param value 值
     * @return
     */
    public boolean setString(String category, String key, String value)
    {
        boolean result = false;
        Application application = AppApplication.get(mContext);
        if(application==null)
            return result;

        android.content.SharedPreferences references = application.getSharedPreferences(category, Context.MODE_PRIVATE);
        result = references.edit().putString(key, value).commit();
        return result;
    }

    public void saveUser(User user) {
        setString(KEY_USER_ID, user.getUserId());
        setString(KEY_ACCOUNT,user.getAccount());
        setString(KEY_PASSWORD, user.getPassword());
        setString(KEY_EMAIL, user.getEmail());
        setString(KEY_ICON_URL, user.getIconUrl());
    }

    public User getUserInfo() {
        User userInfo = new User();
        userInfo.setUserId(getString(KEY_USER_ID, ""));
        userInfo.setAccount(getString(KEY_ACCOUNT, ""));
        userInfo.setPassword(getString(KEY_PASSWORD, ""));
        userInfo.setEmail(getString(KEY_EMAIL, ""));
        userInfo.setIconUrl(getString(KEY_ICON_URL, ""));
        return userInfo;
    }
}
