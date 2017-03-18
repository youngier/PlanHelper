package com.young.planhelper.mvp.friend.model.bean;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  22:36
 */


public class ChatInfo {

    private String userId;
    private String account;
    private int type;   //1-发送方， 0-接受方
    private String content;
    private String time;
    private String iconUrl;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
