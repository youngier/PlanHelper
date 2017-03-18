package com.young.planhelper.mvp.friend.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.planhelper.R;
import com.young.planhelper.mvp.login.model.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/20  09:39
 */


public class FriendListAdapter extends RecyclerView.Adapter<FriendListViewHolder> {

    private List<User> mDatas;

    private Context mContext;

    private OnClickListener listener;

    public FriendListAdapter(Context context, List<User> data) {
        this.mContext = context;
        if( data == null )
            mDatas = new ArrayList<>();
        else
            mDatas = data;
    }

    @Override
    public FriendListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_friend_list_item, parent, false);
        FriendListViewHolder viewHolder = new FriendListViewHolder(view);
        viewHolder.friendListItemView = (FriendListItemView) view.findViewById(R.id.friend_list_item_view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendListViewHolder holder, int position) {
        holder.friendListItemView.setData(mDatas.get(position));
        if( listener != null )
            holder.friendListItemView.setOnClickListener( v -> listener.onClick(mDatas.get(position)) );
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setDatas(List<User> datas) {
        if( mDatas != null )
            this.mDatas = datas;
    }

    public interface OnClickListener{
        void onClick(User user);
    }
}
