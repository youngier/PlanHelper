package com.young.planhelper.mvp.friend.view.chat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.planhelper.R;
import com.young.planhelper.constant.AppConstant;
import com.young.planhelper.mvp.friend.model.bean.ChatInfo;
import com.young.planhelper.util.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  23:00
 */


public class FriendChatItemView extends RelativeLayout {

    @BindView(R.id.civ_frient_chat_item)
    CircleImageView mCiv;

    @BindView(R.id.tv_friend_chat_item_name)
    TextView mNameTv;

    @BindView(R.id.tv_friend_chat_item_content)
    TextView mContentTv;

    @BindView(R.id.tv_friend_chat_item_time)
    TextView mTimeTv;

    private Context mContext;

    public FriendChatItemView(Context context) {
        super(context);
        this.mContext = context;
    }

    public FriendChatItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public FriendChatItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(ChatInfo data) {
        mContentTv.setText(data.getContent());
        mNameTv.setText(data.getAccount());
        Glide.with(mContext)
                .load(AppConstant.RECOUSE_IMAGE_URL + data.getIconUrl())
                .into(mCiv);
        String time = TimeUtil.getTime2(Long.parseLong(data.getTime()));
        time = time.substring(time.length()-5, time.length());
        mTimeTv.setText(time);
    }
}
