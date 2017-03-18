package com.young.planhelper.mvp.friend.view.chat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.friend.model.bean.ChatInfo;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/25  11:35
 */


public class FriendChatDetailItemView extends LinearLayout{

    @BindView(R.id.ll_chat_detail_item_left)
    LinearLayout mLeftLl;

    @BindView(R.id.ll_chat_detail_item_right)
    LinearLayout mRightLl;

    @BindView(R.id.tv_friend_chat_detail_item_time)
    TextView mTimeTv;

    @BindView(R.id.civ_friend_chat_detail_item)
    CircleImageView mCiv;

    @BindView(R.id.tv_friend_chat_detail_item_content)
    TextView mContentLTv;

    @BindView(R.id.tv_friend_chat_detail_item_content_r)
    TextView mContentRTv;

    public FriendChatDetailItemView(Context context) {
        super(context);
    }

    public FriendChatDetailItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FriendChatDetailItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(ChatInfo data) {

        if( data.getType() == 0 ) {
            mRightLl.setVisibility(GONE);
            mLeftLl.setVisibility(VISIBLE);
            mTimeTv.setText(TimeUtil.getTime2(Long.parseLong(data.getTime())));
            mContentLTv.setText(data.getContent());
        }else{
            mRightLl.setVisibility(VISIBLE);
            mLeftLl.setVisibility(GONE);
            mTimeTv.setText(TimeUtil.getTime2(Long.parseLong(data.getTime())));
            mContentRTv.setText(data.getContent());
        }
    }
}
