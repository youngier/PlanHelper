package com.young.planhelper.mvp.plan.view.planview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.planhelper.R;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.view.PlanActiveFragment;
import com.young.planhelper.mvp.plan.view.planitem.seconditem.PlanSecondItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/23  19:30
 */


public class PlanAdapter extends RecyclerView.Adapter<PlanViewHolder>{

    private List<PlanInfo> mDatas;
    private Context mContext;
    private OnClickListener listener;

    public PlanAdapter(Context context, List<PlanInfo> datas) {
        this.mContext = context;
        if( datas == null )
            mDatas = new ArrayList<>();
        else
            this.mDatas = datas;
    }


    @Override
    public PlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_plan_item, parent, false);
        PlanViewHolder viewHolder = new PlanViewHolder(view);
        viewHolder.planItemView = (PlanItemView) view.findViewById(R.id.plan_item_view);
        if( listener != null )
            viewHolder.planItemView.setOnClickListener(v -> listener.onClick(viewHolder.planItemView.getPlanInfoId()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlanViewHolder holder, int position) {
        holder.planItemView.setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<PlanInfo> data) {
        this.mDatas = data;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener{
        void onClick(long id);
    }

}
