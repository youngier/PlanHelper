package com.young.planhelper.mvp.friend.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.planhelper.R;
import com.young.planhelper.constant.AppConstant;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/20  10:07
 */


public class FriendListItemView extends LinearLayout{

    @BindView(R.id.tv_friend_list_item)
    TextView mTv;

    @BindView(R.id.civ_friend_list_item)
    CircleImageView mCiv;

    private Context mContext;

    public FriendListItemView(Context context) {
        super(context);
        this.mContext = context;
    }

    public FriendListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public FriendListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(User user) {

        this.mTv.setText(user.getAccount());
        Glide.with(mContext)
                .load(AppConstant.RECOUSE_IMAGE_URL + user.getIconUrl())
                .into(mCiv);
        LogUtil.eLog("图片地址：" + AppConstant.RECOUSE_IMAGE_URL + user.getIconUrl());
    }
}
