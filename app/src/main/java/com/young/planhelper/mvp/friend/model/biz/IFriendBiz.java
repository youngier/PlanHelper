package com.young.planhelper.mvp.friend.model.biz;

import com.young.planhelper.mvp.base.model.IBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/19  22:15
 */


public interface IFriendBiz extends IBiz{

    /**
     * 查询好友
     * @param friend
     * @param callback
     */
    void findFriend(String friend, ICallback callback);

    /**
     * 判断该用户id是否是好友
     * @param userId
     * @param callback
     */
    void judgeIsFriend(String userId, ICallback callback);

    /**
     * 添加好友
     * @param userFriendId
     * @param remark
     * @param callback
     */
    void addFriend(String userFriendId, String remark, ICallback callback);
}
