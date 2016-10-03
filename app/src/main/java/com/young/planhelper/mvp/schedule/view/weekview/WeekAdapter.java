package com.young.planhelper.mvp.schedule.view.weekview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.WeekInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/1  20:15
 */

class WeekAdapter extends RecyclerView.Adapter<WeekViewHolder> {

    private Context mContext;
    private List<WeekInfo> mDatas;

    public WeekAdapter(Context context, List<WeekInfo> datas){
        this.mContext = context;
        if( datas == null ){
            mDatas = new ArrayList<>();
        }else{
            mDatas = datas;
        }

    }

    public void setData(List<WeekInfo> datas) {
        this.mDatas = datas;
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public WeekViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        WeekItemView view = (WeekItemView) LayoutInflater.from(mContext).inflate(R.layout.view_week_item,
                viewGroup, false);
        WeekViewHolder viewHolder = new WeekViewHolder(view);
        viewHolder.mWeekItemView = (WeekItemView) view.findViewById(R.id.wv_view_item);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeekViewHolder holder, int position) {
        holder.mWeekItemView.setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}