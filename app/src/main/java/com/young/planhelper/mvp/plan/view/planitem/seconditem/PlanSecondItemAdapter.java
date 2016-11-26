package com.young.planhelper.mvp.plan.view.planitem.seconditem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.planhelper.R;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.view.backlogview.BacklogItemView;
import com.young.planhelper.mvp.schedule.view.backlogview.BacklogViewHolder;
import com.young.planhelper.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/24  14:57
 */


public class PlanSecondItemAdapter extends RecyclerView.Adapter<PlanSecondItemViewHolder>{

    private List<PlanSecondItemInfo> mDatas;
    private Context mContext;
    private int position;
    private OnClickListener listener;

    public PlanSecondItemAdapter(Context context, List<PlanSecondItemInfo> datas) {
        this.mContext = context;
        if( datas == null )
            mDatas = new ArrayList<>();
        else
            this.mDatas = datas;
    }


    @Override
    public PlanSecondItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_plan_second_item, parent, false);
        PlanSecondItemViewHolder viewHolder = new PlanSecondItemViewHolder(view);
        viewHolder.planSecondItemView = (PlanSecondItemView) view.findViewById(R.id.plan_second_item_view);
        if( listener != null )
            viewHolder.planSecondItemView.setOnClickListener(v -> listener.onClick(viewHolder.planSecondItemView.getPlanSecondItemInfoId()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlanSecondItemViewHolder holder, int position) {
        holder.planSecondItemView.setData(mDatas.get(position));
        this.position = position;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<PlanSecondItemInfo> data) {
        this.mDatas = data;
    }


    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener{
        void onClick(long id);
    }

}