package com.young.planhelper.mvp.plan.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.planhelper.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/28  20:23
 */


public class PlanSelectAdapter extends RecyclerView.Adapter<PlanSelectViewHolder>{

    private List<String> mDatas;
    private Context mContext;
    private OnClickListener listener;

    int itemViewWidth;

    public PlanSelectAdapter(Context context, List<String> datas) {
        this.mContext = context;
        if( datas == null )
            mDatas = new ArrayList<>();
        else
            this.mDatas = datas;
    }


    @Override
    public PlanSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_plan_select_item, parent, false);
        PlanSelectViewHolder viewHolder = new PlanSelectViewHolder(view);
        viewHolder.planSelectItemView = (PlanSelectItemView) view.findViewById(R.id.plan_select_item_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlanSelectViewHolder holder, int position) {

        if( position == getItemCount() - 1 ){
            holder.planSelectItemView.isAdd();
            holder.planSelectItemView.setOnClickListener( v -> listener.onClick(position) );
        }else{
            if( mDatas.size() > 0 )
                holder.planSelectItemView.setData(mDatas.get(position));
        }

        itemViewWidth = holder.planSelectItemView.getmSelectCiv().getLayoutParams().width;
    }


    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }

    public void setDatas(List<String> data) {
        this.mDatas = data;
    }


    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public int getItemViewWidth() {
        return itemViewWidth;
    }


    public interface OnClickListener{
        void onClick(long id);
    }
}
