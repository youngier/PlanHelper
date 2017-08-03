package com.young.planhelper.mvp.friend.view.add;

import android.content.Intent;
import android.util.Log;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.friend.model.bean.FriendAddInfo;
import com.young.planhelper.mvp.friend.presenter.FriendPresenter;
import com.young.planhelper.mvp.friend.presenter.IFriendPresenter;
import com.young.planhelper.mvp.friend.view.FriendActivity;
import com.young.planhelper.mvp.friend.view.search.FriendSearchActivity;
import com.young.planhelper.widget.NestListView;
import com.young.planhelper.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FriendAddActivity extends BaseActivity{

    @BindView(R.id.nlv_friend_add_new_friend)
    NestListView mNewFriendNlv;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    FriendAddNewAdapter adapter;

    private IFriendPresenter presenter;

    @Override
    protected void initUI() {

        setStatueBarColor();

        mToolbar.setMode(Toolbar.BACK);

        mToolbar.setOnMenuClickListener( () -> finish());

        mToolbar.setOnDateClickListener( () -> {} );

        mToolbar.setTitle("查找好友");

        adapter = new FriendAddNewAdapter(this, null);

        mNewFriendNlv.setAdapter(adapter);

        presenter = new FriendPresenter(this, this);

        adapter.setOnItemClickListener( userId -> {

            showProgress();
            presenter.acceptFriend(userId, data -> {
                afterAccept(data);
            });
        } );

        showProgress();

        presenter.findFriendWhoAddMe(data -> setData(data));
    }

    private void afterAccept(Object data) {
        Observable<String> userList = (Observable<String>) data;
        userList.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                        Log.i("way", "onCompleted");
                        hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("way", "onError" + e.toString());
                        hideProgress();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("way", "onNext" + s);
                        hideProgress();
                        if( s.equals("1") )
                            startActivity(new Intent(FriendAddActivity.this, FriendActivity.class));
                    }
                });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_friend_add;
    }

    @Override
    public void setData(Object data) {
        Observable<List<FriendAddInfo>> userList = (Observable<List<FriendAddInfo>>) data;
        userList.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<FriendAddInfo>>() {

                    @Override
                    public void onCompleted() {
                        Log.i("way", "onCompleted");
                        hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("way", "onError" + e.toString());
                        hideProgress();
                    }

                    @Override
                    public void onNext(List<FriendAddInfo> s) {

                        if( s != null && s.size() != 0 ){
                            adapter.setDatas(s);
                            adapter.notifyDataSetChanged();
                        }

                        hideProgress();
                    }
                });
    }

    @OnClick(R.id.ll_friend_add_search)
    void selectSearch(){
        startActivity(new Intent(this, FriendSearchActivity.class));
    }
}