package com.young.planhelper.mvp.friend.view.chat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.friend.model.bean.ChatInfo;
import com.young.planhelper.mvp.friend.presenter.FriendPresenter;
import com.young.planhelper.mvp.friend.presenter.IFriendPresenter;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FriendChatDetailActivity extends BaseActivity {

    @BindView(R.id.rv_friend_chat_detail)
    RecyclerView mRv;

    @BindView(R.id.btn_friend_chat_detail_send)
    Button mSendBtn;

    @BindView(R.id.iv_friend_chat_detail_more)
    ImageView mMoreIv;

    @BindView(R.id.et_friend_chat_detail)
    EditText mEt;

    User user;

    FriendChatDetailAdapter adapter;

    IFriendPresenter presenter;

    @Override
    protected void initUI() {

        user = (User) getIntent().getSerializableExtra("user");

        LogUtil.eLog("FriendChatDetailActivity: userId:"+user.getUserId());

        adapter = new FriendChatDetailAdapter(this, null);

        presenter = new FriendPresenter(this, this);

        mRv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(llm);

        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(TextUtils.isEmpty(s)){
                    LogUtil.eLog("onTextChanged:  '' "+mEt.getText());
                    mSendBtn.setVisibility(View.GONE);
                    mMoreIv.setVisibility(View.VISIBLE);
                }else{
                    LogUtil.eLog("onTextChanged:  !'' "+mEt.getText());
                    mSendBtn.setVisibility(View.VISIBLE);
                    mMoreIv.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        presenter.getChatInfo(user.getUserId(), data -> {
            Observable<List<ChatInfo>> chatInfoList = (Observable<List<ChatInfo>>) data;
            chatInfoList.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<ChatInfo>>() {

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
                        public void onNext(List<ChatInfo> chatInfoList) {
                            hideProgress();

                            adapter.setData(chatInfoList);

                            adapter.notifyDataSetChanged();

                        }
                    });
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_friend_chat_detail;
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
                        hideProgress();
                    }
                });

    }

    @OnClick(R.id.btn_friend_chat_detail_send)
    void sendChatInfo(){

        String content = mEt.getText().toString();

        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setContent(content);
        chatInfo.setTime(TimeUtil.getCurrentTimeInLong()+"");
        chatInfo.setType(1);

        adapter.append(chatInfo);

        mEt.setText("");

        adapter.notifyDataSetChanged();

        showProgress();
        presenter.sendChatInfo(user.getUserId(), content, data -> setData(data));
    }
}
