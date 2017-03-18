package com.young.planhelper.mvp.friend.view.add;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.young.planhelper.R;
import com.young.planhelper.constant.AppConstant;
import com.young.planhelper.mvp.friend.model.bean.FriendAddInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/22  19:49
 */


public class FriendAddNewItemView extends RelativeLayout {

    @BindView(R.id.civ_friend_new_friend_item)
    CircleImageView mAddNewCiv;

    @BindView(R.id.tv_friend_new_friend_item_account)
    TextView mAddNewAccountTv;

    @BindView(R.id.btn_friend_new_friend_item)
    Button mAddNewBtn;

    @BindView(R.id.tv_friend_new_friend_item_receive)
    TextView mAddNewReceiveTv;

    private Context mContext;

    private FriendAddInfo mFriendAddInfo;

    private OnItemClickListener listener;

    public FriendAddNewItemView(Context context) {
        super(context);
        this.mContext = context;
    }

    public FriendAddNewItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public FriendAddNewItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(FriendAddInfo data) {
        this.mFriendAddInfo = data;
        Glide.with(mContext)
                .load(AppConstant.RECOUSE_IMAGE_URL + data.getIconUrl())
                .into(mAddNewCiv);

        mAddNewAccountTv.setText(data.getAccount());

        if( data.isFriend() ) {
            mAddNewReceiveTv.setVisibility(VISIBLE);
            mAddNewBtn.setVisibility(GONE);
        }else{
            mAddNewReceiveTv.setVisibility(GONE);
            mAddNewBtn.setVisibility(VISIBLE);
        }

        mAddNewBtn.setOnClickListener(v -> {
            listener.onItemClick(data.getUserId());
        });

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(String userId);
    }
}
