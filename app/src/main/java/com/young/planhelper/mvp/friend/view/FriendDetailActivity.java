package com.young.planhelper.mvp.friend.view;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.planhelper.R;
import com.young.planhelper.constant.AppConstant;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.friend.presenter.FriendPresenter;
import com.young.planhelper.mvp.friend.presenter.IFriendPresenter;
import com.young.planhelper.mvp.friend.view.chat.FriendChatDetailActivity;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.util.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FriendDetailActivity extends BaseActivity {

    @BindView(R.id.civ_friend_detail)
    CircleImageView mCiv;

    @BindView(R.id.tv_friend_detail)
    TextView mTv;


    @BindView(R.id.btn_friend_detail)
    Button mBtn;

    IFriendPresenter presenter;

    private User user;

    @Override
    protected void initUI() {

        user = (User) getIntent().getSerializableExtra("user");

        LogUtil.eLog("FriendDetailActivity: userId:"+user.getUserId());

        showProgress();

        Glide.with(this)
                .load(AppConstant.RECOUSE_IMAGE_URL + user.getIconUrl())
                .into(mCiv);

        mTv.setText(user.getAccount());

        presenter = new FriendPresenter(this, this);

        presenter.judgeIsFriend(user.getUserId(), data -> setData(data));

    }

    @Override
    public int getLayout() {
        return R.layout.activity_friend_detail;
    }

    @Override
    public void setData(Object data) {
        Observable<String> result = (Observable<String>) data;
        result.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                        Log.e("way", "onCompleted");
                        hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("way", "onError" + e.toString());
                        hideProgress();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("way", "该好友的判断结果为" + s);
                        if( s.equals("0") )
                            mBtn.setText("添加至联系人");
                        else
                            mBtn.setText("发送消息");
                    }
                });
    }

    @OnClick(R.id.btn_friend_detail)
    void addFriend(){
        if( mBtn.getText().equals("添加至联系人") ){
            Intent intent = new Intent(this, FriendRemarkActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, FriendChatDetailActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
    }
}
