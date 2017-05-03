package com.young.planhelper.mvp.friend.view.chat;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.presenter.IPresenter;
import com.young.planhelper.mvp.base.view.BaseFragment;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.friend.model.bean.ChatInfo;
import com.young.planhelper.mvp.friend.model.bean.FriendAddInfo;
import com.young.planhelper.mvp.friend.presenter.FriendPresenter;
import com.young.planhelper.mvp.friend.presenter.IFriendPresenter;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  22:32
 */


public class FriendChatFragment extends BaseFragment implements IView {

    @BindView(R.id.rv_friend_chat)
    RecyclerView mChatRv;

    private FriendChatAdapter mFriendChatAdapter;

    private IFriendPresenter presenter;

    @Override
    protected void setData() {

    }

    @Override
    protected void initUI() {

        presenter = new FriendPresenter(this, getActivity());

        mFriendChatAdapter = new FriendChatAdapter(getActivity(), null);

        mFriendChatAdapter.setOnClickListener( (id, iconUrl) -> {
            User user = new User();
            user.setUserId(id);
            user.setIconUrl(iconUrl);
            Intent intent = new Intent(getActivity(), FriendChatDetailActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } );

        mChatRv.setAdapter(mFriendChatAdapter);

        mChatRv.addItemDecoration(new RecyclerViewDivider(getActivity(), LinearLayoutManager.VERTICAL, 1, ContextCompat.getColor(getActivity(), R.color.gray)));
        mChatRv.setHasFixedSize(true);
        mChatRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.getLastestChatInfoList(data -> setData(data));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friend_chat;
    }

    @Override
    public void setData(Object data) {
        Observable<List<ChatInfo>> chatInfoList = (Observable<List<ChatInfo>>) data;
        chatInfoList.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ChatInfo>>() {

                    @Override
                    public void onCompleted() {
                        Log.i("way", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("way", "onError" + e.toString());
                    }

                    @Override
                    public void onNext(List<ChatInfo> s) {

                        if( s != null && s.size() != 0 ){
                            mFriendChatAdapter.setDatas(s);
                            mFriendChatAdapter.notifyDataSetChanged();
                        }

                        hideProgress();
                    }
                });
    }
}