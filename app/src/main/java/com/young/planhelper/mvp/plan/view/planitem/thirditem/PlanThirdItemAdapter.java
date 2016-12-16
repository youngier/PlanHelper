package com.young.planhelper.mvp.plan.view.planitem.thirditem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.planhelper.R;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;
import com.young.planhelper.mvp.plan.view.planitem.seconditem.PlanSecondItemView;
import com.young.planhelper.mvp.plan.view.planitem.seconditem.PlanSecondItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/27  12:38
 */


public class PlanThirdItemAdapter extends RecyclerView.Adapter<PlanThirdItemViewHolder>{

    private List<PlanThirdItemInfo> mDatas;
    private Context mContext;
    private int position;
    private OnClickListener listener;
    private PlanThirdItemView.OnSelectChangeListener onSelectChangeListener;

    public PlanThirdItemAdapter(Context context, List<PlanThirdItemInfo> datas) {
        this.mContext = context;
        if( datas == null )
            mDatas = new ArrayList<>();
        else
            this.mDatas = datas;
    }


    @Override
    public PlanThirdItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_plan_third_item, parent, false);
        PlanThirdItemViewHolder viewHolder = new PlanThirdItemViewHolder(view);
        viewHolder.planThirdItemView = (PlanThirdItemView) view.findViewById(R.id.plan_third_item_view);
        viewHolder.planThirdItemView.setOnSelectChangeListener(onSelectChangeListener);
        if( listener != null )
            viewHolder.planThirdItemView.setOnClickListener(v -> listener.onClick(viewHolder.planThirdItemView.getPlanThirdItemInfoId()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlanThirdItemViewHolder holder, int position) {
        holder.planThirdItemView.setData(mDatas.get(position));
        this.position = position;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<PlanThirdItemInfo> data) {
        this.mDatas = data;
    }


    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setOnSelectChangeListener(PlanThirdItemView.OnSelectChangeListener onSelectChangeListener) {
        this.onSelectChangeListener = onSelectChangeListener;
    }

    public interface OnClickListener{
        void onClick(long id);
    }

}
