package com.young.planhelper.mvp.friend.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.friend.presenter.FriendPresenter;
import com.young.planhelper.mvp.friend.presenter.IFriendPresenter;
import com.young.planhelper.mvp.friend.view.FriendDetailActivity;
import com.young.planhelper.mvp.friend.view.FriendListAdapter;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.widget.ClearEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FriendSearchActivity extends BaseActivity {

    @BindView(R.id.cet_search)
    ClearEditText mSearchCet;

    @BindView(R.id.ll_friend_search)
    LinearLayout mSearchLl;

    @BindView(R.id.tv_friend_search)
    TextView mSearchTv;

    @BindView(R.id.rv_friend_search)
    RecyclerView mSearchRv;

    @BindView(R.id.rl_friend_search_none)
    RelativeLayout mNoneRl;


    IFriendPresenter presenter;

    FriendListAdapter adapter;

    @Override
    protected void initUI() {

        setStatueBarColor();

        presenter = new FriendPresenter(this, this);

        adapter = new FriendListAdapter(this, null);

        mSearchRv.setAdapter(adapter);

        adapter.setOnClickListener( user -> {
            Intent intent = new Intent(this, FriendDetailActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtra("user", user);
            startActivity(intent);

        } );

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mSearchRv.setLayoutManager(llm);

        mSearchCet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( s.equals("") )
                    mSearchLl.setVisibility(View.GONE);
                else{
                    mSearchLl.setVisibility(View.VISIBLE);
                    mSearchTv.setText(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_friend_search;
    }

    @OnClick(R.id.ll_friend_search)
    void selectSearch(){

        String search = mSearchTv.getText().toString();

        showProgress();

        presenter.findFriend(search, data -> setData(data));


    }

    @OnClick(R.id.iv_toolbar_menu)
    void onBack(){
        finish();
    }

    @Override
    public void setData(Object data) {
        Observable<List<User>> userList = (Observable<List<User>>) data;
        userList.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {

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
                    public void onNext(List<User> s) {

                        if( s != null && s.size() != 0 ){
                            mNoneRl.setVisibility(View.GONE);
                            adapter.setDatas(s);
                            adapter.notifyDataSetChanged();
                        }else{
                            mNoneRl.setVisibility(View.VISIBLE);
                        }

                        hideProgress();
                    }
                });
    }
}
