package com.young.planhelper.mvp.friend.presenter;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.friend.model.bean.ChatInfo;
import com.young.planhelper.mvp.friend.model.bean.FriendAddInfo;
import com.young.planhelper.mvp.friend.model.biz.FriendBiz;
import com.young.planhelper.mvp.friend.model.biz.IFriendBiz;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.network.FriendAddApiService;
import com.young.planhelper.network.FriendAllPersonApiService;
import com.young.planhelper.network.FriendChatSendApiService;
import com.young.planhelper.network.FriendGetChatInfoApiService;
import com.young.planhelper.network.FriendWhoAddMeApiService;
import com.young.planhelper.network.friend.FriendGetLastestChatInfoApiService;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/19  22:14
 */


public class FriendPresenter extends Presenter implements IFriendPresenter{

    private IFriendBiz mBiz;

    private final Retrofit mRetrofit;

    private User user;

    public FriendPresenter(IView view, Context context) {
        super(view, context);

        mBiz = new FriendBiz(context);

        mRetrofit = AppApplication.get(context).getmAppComponent().getRetrofit();

        user = AppApplication.get(context).getmAppComponent().getUserInfo();
    }

    @Override
    public void initData() {

    }

    @Override
    public void findFriend(String friend, IBiz.ICallback callback) {
        mBiz.findFriend(friend, callback);
    }

    @Override
    public void judgeIsFriend(String userId, IBiz.ICallback callback) {
        mBiz.judgeIsFriend(userId, callback);
    }

    @Override
    public void addFriend(String userFriendId, String remark,IBiz.ICallback callback) {
        mBiz.addFriend(userFriendId, remark, callback);
    }

    @Override
    public void findFriendWhoAddMe(IBiz.ICallback callback) {
        FriendWhoAddMeApiService friendWhoAddMeApiService = mRetrofit.create(FriendWhoAddMeApiService.class);

        //获得Observable对象
        Observable<List<FriendAddInfo>> data = friendWhoAddMeApiService.findWhoAddMe(user.getUserId());

        callback.onResult(data);
    }

    @Override
    public void acceptFriend(String userId, IBiz.ICallback callback) {

        FriendAddApiService friendAddApiService = mRetrofit.create(FriendAddApiService.class);

        //获得Observable对象
        Observable<String> data = friendAddApiService.addFriend(user.getUserId(), userId, 1, "");

        callback.onResult(data);
    }

    @Override
    public void getAllFriend(IBiz.ICallback callback) {
        FriendAllPersonApiService friendAllPersonApiService = mRetrofit.create(FriendAllPersonApiService.class);

        //获得Observable对象
        Observable<List<User>> data = friendAllPersonApiService.getFriends(user.getUserId());

        callback.onResult(data);
    }

    @Override
    public void sendChatInfo(String userId, String content, IBiz.ICallback callback) {
        FriendChatSendApiService  friendChatSendApiService = mRetrofit.create(FriendChatSendApiService.class);

        LogUtil.eLog("发送id："+user.getUserId()+", 接受id："+userId);

        //获得Observable对象
        Observable<String> data = friendChatSendApiService.sendChatInfo(user.getUserId(), userId, TimeUtil.getCurrentTimeInLong(), content);

        callback.onResult(data);
    }

    @Override
    public void getChatInfo(String userId, IBiz.ICallback callback) {
        FriendGetChatInfoApiService friendGetChatInfoApiService = mRetrofit.create(FriendGetChatInfoApiService.class);

        //获得Observable对象
        Observable<List<ChatInfo>> data = friendGetChatInfoApiService.getChatInfos(user.getUserId(), userId);

        callback.onResult(data);
    }

    @Override
    public void getLastestChatInfoList(IBiz.ICallback callback) {
        FriendGetLastestChatInfoApiService apiService = mRetrofit.create(FriendGetLastestChatInfoApiService.class);

        //获得Observable对象
        Observable<List<ChatInfo>> data = apiService.getLastestChatInfo(user.getUserId());

        callback.onResult(data);
    }
}
