package com.young.planhelper.mvp.friend.view.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.planhelper.R;
import com.young.planhelper.mvp.friend.model.bean.ChatInfo;
import com.young.planhelper.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/25  11:13
 */


public class FriendChatDetailAdapter extends RecyclerView.Adapter<FriendChatDetailViewHolder> {

    private List<ChatInfo> mDatas;
    private Context mContext;

    public FriendChatDetailAdapter(Context context, List<ChatInfo> datas) {
        this.mContext = context;
        if (datas == null)
            mDatas = new ArrayList<>();
        else
            this.mDatas = datas;
    }

    @Override
    public FriendChatDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_friend_chat_detail_item, parent, false);
        FriendChatDetailViewHolder viewHolder = new FriendChatDetailViewHolder(view);
        viewHolder.friendChatDetailItemView = (FriendChatDetailItemView) view.findViewById(R.id.friend_chat_detial_item_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendChatDetailViewHolder holder, int position) {
        holder.friendChatDetailItemView.setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setData(List<ChatInfo> data) {
        this.mDatas = data;
    }

    public void append(ChatInfo chatInfo) {
        mDatas.add(chatInfo);
    }
}