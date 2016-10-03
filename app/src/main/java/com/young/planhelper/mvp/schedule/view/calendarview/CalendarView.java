package com.young.planhelper.mvp.schedule.view.calendarview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.CalendarInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/3  22:24
 */


public class CalendarView extends LinearLayout{

    private RecyclerView mRecyclerView;
    private CalendarAdapter adapter;
    private Context mContext;

    public CalendarView(Context context) {
        super(context);
        this.mContext = context;
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = findViewById(R.id.calendarview);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        adapter = new CalendarAdapter(mContext, null);
        mRecyclerView.setAdapter(adapter);

        // 如果我们想要一个GridView形式的RecyclerView，那么在LayoutManager上我们就要使用GridLayoutManager
        // 实例化一个GridLayoutManager，列数为3
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new CalendarItemDecoration());
    }

    public void setData(CalendarInfo calendarInfo){
        adapter.setData(calendarInfo.getDayInfos());
        adapter.notifyDataSetChanged();
    }
}
