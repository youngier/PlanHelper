package com.young.planhelper.mvp.home.view.monthview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.young.planhelper.R;
import com.young.planhelper.mvp.home.view.weekview.WeekItemView;
import com.young.planhelper.mvp.schedule.model.bean.DayInfo;
import com.young.planhelper.mvp.schedule.model.bean.WeekInfo;
import com.young.planhelper.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/1  20:15
 */

public class MonthAdapter extends RecyclerView.Adapter<MonthViewHolder> {

    private Context mContext;
    private List<DayInfo> mDatas;

    private OnClickListener onClickListener;

    private List<String> taskDay;

    public MonthAdapter(Context context, List<DayInfo> datas){
        this.mContext = context;
        if( datas == null ){
            mDatas = new ArrayList<>();
        }else{
            mDatas = datas;
        }

    }

    public void setData(List<DayInfo> datas) {
        this.mDatas = datas;
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public MonthViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        MonthItemView view = (MonthItemView) LayoutInflater.from(mContext).inflate(R.layout.view_month_item,
                viewGroup, false);
        MonthViewHolder viewHolder = new MonthViewHolder(view);
        viewHolder.mMonthItemView = (MonthItemView) view.findViewById(R.id.mv_view_item);
        viewHolder.mMonthItemView.setOnClickListener(v -> {
            onClickListener.onClick(viewHolder.mMonthItemView.getDayInfo());
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MonthViewHolder holder, int position) {

        int k=0;

        String date = mDatas.get(position).getDate();
        StringTokenizer tokenizer = new StringTokenizer(date, "-");
        String year = tokenizer.nextToken();
        String month = tokenizer.nextToken();
        String day = tokenizer.nextToken();

        date = year+"-";
        if( month.length() == 1 )
            date += "0" + month + "-";
        else
            date += month + "-";

        if( day.length() == 1 )
            date += "0" + day;
        else
            date += day;

        if( taskDay != null )
            for (int j=0; j+k < taskDay.size(); j++){
                if( date.equals(taskDay.get(j+k)) ){
                    LogUtil.eLog("日历时间："+date+"，任务时间："+taskDay.get(j+k));
                    k++;
                    mDatas.get(position).setHave(true);
                }
            }

        holder.mMonthItemView.setData(mDatas.get(position));
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setTaskDay(List<String> taskDay) {
        this.taskDay = taskDay;
    }

    interface OnClickListener{
        void onClick(DayInfo dayInfo);
    }
}