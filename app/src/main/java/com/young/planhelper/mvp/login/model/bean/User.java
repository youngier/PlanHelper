package com.young.planhelper.mvp.login.model.bean;

import java.io.Serializable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  11:07
 */


public class User implements Serializable{

    private String userId = "";

    private String account = "";

    private String password = "";

    private String email = "";

    private String iconUrl = "";

    private String sortLetters = "";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void copyWith(User user) {
        this.userId = user.getUserId();
        this.account = user.getAccount();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.iconUrl = user.getIconUrl();
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters){
        this.sortLetters = sortLetters;
    }
}
