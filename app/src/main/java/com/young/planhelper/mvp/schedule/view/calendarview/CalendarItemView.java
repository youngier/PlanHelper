package com.young.planhelper.mvp.schedule.view.calendarview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.DayInfo;

import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/3  23:36
 */


public class CalendarItemView extends LinearLayout{

    private TextView mDayTv;
    private TextView mHolidayTv;
    private TextView mContentTv;
    private TextView mCountTv;

    public CalendarItemView(Context context) {
        super(context);
    }

    public CalendarItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDayTv = (TextView) findViewById(R.id.tv_calendar_day);
        mHolidayTv = (TextView) findViewById(R.id.tv_calendar_holiday);
        mContentTv = (TextView) findViewById(R.id.tv_calendar_content);
        mCountTv = (TextView) findViewById(R.id.tv_calendar_content_count);
    }

    public void setData(DayInfo dayInfo) {
        mDayTv.setText(dayInfo.getDay());
        mHolidayTv.setText(dayInfo.getHoliday());
        List<String> list = dayInfo.getContent();
        if( list == null || list.size() == 0 ) {
            mContentTv.setText("");
            mCountTv.setText("");
            mContentTv.setBackgroundColor(getResources().getColor(R.color.white));
        } else {
            mContentTv.setText(list.get(0));
            mCountTv.setText("+"+list.size());
            mContentTv.setBackgroundColor(getResources().getColor(R.color.light_pink));
        }
    }
}
