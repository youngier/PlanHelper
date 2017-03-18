package com.young.planhelper.mvp.friend.model.biz;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.network.FriendAddApiService;
import com.young.planhelper.network.FriendFindApiService;
import com.young.planhelper.network.FriendJudgeApiService;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/19  22:16
 */


public class FriendBiz extends Biz implements IFriendBiz{

    private final Retrofit mRetrofit;

    private Context mContext;

    public FriendBiz(Context context) {
        mRetrofit = AppApplication.get(context).getmAppComponent().getRetrofit();

        this.mContext = context;
    }

    @Override
    public void getData(ICallback callback) {

    }

    @Override
    public void findFriend(String friend, ICallback callback) {
        FriendFindApiService friendFindApiService = mRetrofit.create(FriendFindApiService.class);
        //获得Observable对象
        Observable<List<User>> data = friendFindApiService.login(friend);

        callback.onResult(data);
    }

    @Override
    public void judgeIsFriend(String userId, ICallback callback) {
        FriendJudgeApiService friendJudgeApiService = mRetrofit.create(FriendJudgeApiService.class);

        User user = AppApplication.get(mContext).getmAppComponent().getUserInfo();
        //获得Observable对象
        Observable<String> data = friendJudgeApiService.judge(user.getUserId(), userId);

        callback.onResult(data);
    }

    @Override
    public void addFriend(String userFriendId, String remark, ICallback callback) {
        FriendAddApiService friendAddApiService = mRetrofit.create(FriendAddApiService.class);

        User user = AppApplication.get(mContext).getmAppComponent().getUserInfo();
        //获得Observable对象
        Observable<String> data = friendAddApiService.addFriend(user.getUserId(), userFriendId, 2, remark);

        callback.onResult(data);
    }
}
