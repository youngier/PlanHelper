package com.young.planhelper.mvp.schedule.view.backlogview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.BacklogInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/5  21:33
 */


public class BacklogAdapter extends RecyclerView.Adapter<BacklogViewHolder>{

    private List<BacklogInfo> mDatas;
    private Context mContext;

    public BacklogAdapter(Context context, List<BacklogInfo> datas) {
        this.mContext = context;
        if( datas == null )
            mDatas = new ArrayList<>();
        else
            this.mDatas = datas;
    }


    @Override
    public BacklogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_backlog_item, parent, false);
        BacklogViewHolder viewHolder = new BacklogViewHolder(view);
        viewHolder.backlogItemView = (BacklogItemView) view.findViewById(R.id.backlog_item_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BacklogViewHolder holder, int position) {
        holder.backlogItemView.setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<BacklogInfo> data) {
        this.mDatas = data;
    }
}
