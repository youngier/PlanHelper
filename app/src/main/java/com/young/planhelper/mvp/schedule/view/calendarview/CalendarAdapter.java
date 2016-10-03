package com.young.planhelper.mvp.schedule.view.calendarview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.DayInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/3  21:49
 */


public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{

    private Context mContext;
    private List<DayInfo> mDatas;

    public CalendarAdapter(Context context, List<DayInfo> datas){
        this.mContext = context;
        this.mDatas = datas;
    }

    public void setData(DayInfo[] datas) {
        mDatas = new ArrayList<>();
        if( datas != null && datas.length != 0 ){
            //记录当前第一项是星期几
            String week = datas[0].getWeek();
            addEmptyDay(week);
            for (int i = 0; i < datas.length; i++) {
                mDatas.add(datas[i]);
            }
        }

    }

    /**
     * 增加上个月占用本个月的空位置
     * @param week
     */
    private void addEmptyDay(String week) {
        switch (week){
            case "一":
                addDay(1);
                break;
            case "二":
                addDay(2);
                break;
            case "三":
                addDay(3);
                break;
            case "四":
                addDay(4);
                break;
            case "五":
                addDay(5);
                break;
            case "六":
                addDay(6);
                break;
            default:
                break;
        }
    }

    /**
     * 增加i个空位
     * @param count
     */
    private void addDay(int count) {
        for (int i = 0; i < count; i++) {
            mDatas.add(new DayInfo());
        }
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_calendar_item,parent, false);
        CalendarViewHolder viewHolder = new CalendarViewHolder(view);
        viewHolder.mCalendarItemView = (CalendarItemView) view.findViewById(R.id.cv_view_item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, int position) {
        DayInfo dayInfo = mDatas.get(position);
        holder.mCalendarItemView.setData(dayInfo);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
