package com.young.planhelper.mvp.plan.view.planitem.seconditem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.planhelper.R;
import com.young.planhelper.mvp.plan.model.bean.PlanOperationInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;
import com.young.planhelper.mvp.plan.view.planitem.thirditem.PlanThirdItemView;
import com.young.planhelper.mvp.plan.view.planitem.thirditem.PlanThirdItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/27  20:45
 */


public class PlanRecordAdapter extends RecyclerView.Adapter<PlanRecordViewHolder>{

    private List<PlanOperationInfo> mDatas;
    private Context mContext;
    private int position;
    private OnClickListener listener;

    public PlanRecordAdapter(Context context, List<PlanOperationInfo> datas) {
        this.mContext = context;
        if( datas == null )
            mDatas = new ArrayList<>();
        else
            this.mDatas = datas;
    }


    @Override
    public PlanRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_plan_record, parent, false);
        PlanRecordViewHolder viewHolder = new PlanRecordViewHolder(view);
        viewHolder.planRecordItemView = (PlanRecordItemView) view.findViewById(R.id.plan_record_view);
        if( listener != null )
            viewHolder.planRecordItemView.setOnClickListener(v -> listener.onClick(viewHolder.planRecordItemView.getPlanSecondItenInfoId()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlanRecordViewHolder holder, int position) {
        holder.planRecordItemView.setData(mDatas.get(position));
        this.position = position;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<PlanOperationInfo> data) {
        this.mDatas = data;
    }


    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener{
        void onClick(long id);
    }

}

