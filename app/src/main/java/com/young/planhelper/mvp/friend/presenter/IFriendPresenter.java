package com.young.planhelper.mvp.friend.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/19  22:13
 */


public interface IFriendPresenter extends IPresenter{

    /**
     * 查询好友
     * @param friend
     * @param callback
     */
    void findFriend(String friend, IBiz.ICallback callback);

    /**
     * 判断是否是好友
     * @param userId
     * @param callback
     */
    void judgeIsFriend(String userId, IBiz.ICallback callback);

    /**
     * 添加好友
     * @param userFriendId
     * @param remark
     * @param callback
     */
    void addFriend(String userFriendId, String remark,IBiz.ICallback callback);

    /**
     * 查找那些添加自己的人
     */
    void findFriendWhoAddMe(IBiz.ICallback callback);

    /**
     * 接受这个人当自己的好友
     * @param userId
     * @param callback
     */
    void acceptFriend(String userId, IBiz.ICallback callback);

    /**
     * 获取所有的好友
     * @param callback
     */
    void getAllFriend(IBiz.ICallback callback);

    /**
     * 发送聊天信息
     * @param userId
     * @param content
     * @param callback
     */
    void sendChatInfo(String userId, String content,IBiz.ICallback callback);

    /**
     * 获取聊天记录
     * @param userId
     * @param callback
     */
    void getChatInfo(String userId, IBiz.ICallback callback);

    /**
     * 获取最新的聊天列表
     * @param callback
     */
    void getLastestChatInfoList(IBiz.ICallback callback);
}
