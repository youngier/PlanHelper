package com.young.planhelper.mvp.friend.view.add;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.young.planhelper.R;
import com.young.planhelper.mvp.friend.model.bean.FriendAddInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/22  19:34
 */


public class FriendAddNewAdapter extends BaseAdapter {

    private Context mContext;

    private List<FriendAddInfo> mDatas;

    private OnItemClickListener listener;

    public FriendAddNewAdapter(Context context, List<FriendAddInfo> datas) {
        this.mContext = context;

        if( datas == null )
            mDatas = new ArrayList<>();
        else
            this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null ){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_friend_new_friend_item, parent, false);
        }
        FriendAddNewItemView friendAddNewItemView = (FriendAddNewItemView)convertView;
        friendAddNewItemView.setData(mDatas.get(position));
        friendAddNewItemView.setOnItemClickListener( userId -> {
            listener.onItemClick(userId);
        } );
        return convertView;
    }

    public void setDatas(List<FriendAddInfo> datas) {
        this.mDatas = datas;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(String userId);
    }
}
