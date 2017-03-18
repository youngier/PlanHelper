package com.young.planhelper.mvp.friend.view;

import android.content.Intent;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseOtherActivity;
import com.young.planhelper.mvp.friend.presenter.FriendPresenter;
import com.young.planhelper.mvp.friend.presenter.IFriendPresenter;
import com.young.planhelper.mvp.login.model.bean.User;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FriendRemarkActivity extends BaseOtherActivity {

    @BindView(R.id.et_friend_remark)
    TextView mRemarkTv;

    private IFriendPresenter presenter;

    private User user;

    @Override
    protected void initUI() {

        user = (User) getIntent().getSerializableExtra("user");

        presenter = new FriendPresenter(this, this);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_friend_remark;
    }

    @Override
    protected void setTheme() {
        super.setTheme();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void setData(Object data) {
        Observable<String> user = (Observable<String>) data;
        user.observeOn(AndroidSchedulers.mainThread())
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
                            startActivity(new Intent(FriendRemarkActivity.this, FriendActivity.class));
                    }
                });
    }

    @OnClick(R.id.btn_friend_remark)
    void sendRemark(){

        showProgress();

        presenter.addFriend(user.getUserId(), mRemarkTv.getText().toString(), data -> setData(data));
    }
}
