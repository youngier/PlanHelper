package com.young.planhelper.mvp.friend.view.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.planhelper.R;
import com.young.planhelper.mvp.friend.model.bean.ChatInfo;
import com.young.planhelper.mvp.plan.view.planview.PlanAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  22:34
 */


public class FriendChatAdapter extends RecyclerView.Adapter<FriendChatViewHolder>{

    private List<ChatInfo> mDatas;
    private Context mContext;
    private OnClickListener listener;

    public FriendChatAdapter(Context context, List<ChatInfo> datas) {
        this.mContext = context;
        if( datas == null )
            mDatas = new ArrayList<>();
        else
            this.mDatas = datas;
    }


    @Override
    public FriendChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_friend_chat_item, parent, false);
        FriendChatViewHolder viewHolder = new FriendChatViewHolder(view);
        viewHolder.friendChatItemView = (FriendChatItemView) view.findViewById(R.id.friend_chat_item_view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendChatViewHolder holder, int position) {
        holder.friendChatItemView.setData(mDatas.get(position));
        if( listener != null )
            holder.friendChatItemView.setOnClickListener(v -> listener.onClick(mDatas.get(position).getUserId()));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<ChatInfo> data) {
        this.mDatas = data;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener{
        void onClick(String id);
    }
}
