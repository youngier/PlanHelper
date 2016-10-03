package com.young.planhelper.mvp.schedule;

import android.util.Log;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.view.BaseFragment;
import com.young.planhelper.mvp.schedule.model.CalendarInfo;
import com.young.planhelper.mvp.schedule.model.DayInfo;
import com.young.planhelper.mvp.schedule.model.WeekInfo;
import com.young.planhelper.mvp.schedule.view.calendarview.CalendarView;
import com.young.planhelper.mvp.schedule.view.weekview.WeekView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/9/30  14:19
 */


public class ScheduleFragment extends BaseFragment {

    private static final String TAG = "ScheduleFragment";

    private WeekView mWeekView;
    private CalendarView mCalendarView;

    @Override
    protected void initUI() {
        mWeekView = (WeekView) getView().findViewById(R.id.weekview);
        mCalendarView = (CalendarView) getView().findViewById(R.id.calendarview);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragmnet_schedule;
    }

    @Override
    public void setData() {
        List<WeekInfo> weekInfoList = new ArrayList<>();
        DayInfo[] dayInfos = new DayInfo[7];
        DayInfo[] dayInfos1 = new DayInfo[7];
        dayInfos[0] = new DayInfo("1", "日");
        dayInfos[1] = new DayInfo("2", "一");
        dayInfos[2] = new DayInfo("3", "二");
        dayInfos[3] = new DayInfo("4", "三");
        dayInfos[4] = new DayInfo("5", "四");
        dayInfos[5] = new DayInfo("6", "五");
        dayInfos[6] = new DayInfo("7", "六");
        weekInfoList.add(new WeekInfo(dayInfos));
        dayInfos1[0] = new DayInfo("8", "日");
        dayInfos1[1] = new DayInfo("9", "一");
        dayInfos1[2] = new DayInfo("10", "二");
        dayInfos1[3] = new DayInfo("11", "三");
        dayInfos1[4] = new DayInfo("12", "四");
        dayInfos1[5] = new DayInfo("13", "五");
        dayInfos1[6] = new DayInfo("14", "六");
        weekInfoList.add(new WeekInfo(dayInfos1));
        Log.e(TAG, "内容为"+weekInfoList.size());
        mWeekView.setData(weekInfoList);

        CalendarInfo calendarInfo = new CalendarInfo();
        DayInfo[] dayInfos2 = new DayInfo[31];
        List<String> list = new ArrayList();
        list.add("搞设么");
        dayInfos2[0] = new DayInfo("1", "日", "", null);
        dayInfos2[1] = new DayInfo("2", "一", "", null);
        dayInfos2[2] = new DayInfo("3", "二", "", null);
        dayInfos2[3] = new DayInfo("4", "三", "", null);
        dayInfos2[4] = new DayInfo("5", "四", "", null);
        dayInfos2[5] = new DayInfo("6", "五", "", null);
        dayInfos2[6] = new DayInfo("7", "六", "", null);
        dayInfos2[7] = new DayInfo("8", "日", "", null);
        dayInfos2[8] = new DayInfo("9", "一", "", null);
        dayInfos2[9] = new DayInfo("10", "二", "国庆节", list);
        list.add("啦啦啦啦");
        dayInfos2[10] = new DayInfo("11", "三", "", null);
        dayInfos2[11] = new DayInfo("12", "四", "", null);
        dayInfos2[12] = new DayInfo("13", "五", "", null);
        dayInfos2[13] = new DayInfo("14", "六", "", list);
        dayInfos2[14] = new DayInfo("15", "日", "", null);
        dayInfos2[15] = new DayInfo("16", "一", "", null);
        dayInfos2[16] = new DayInfo("17", "二", "", null);
        dayInfos2[17] = new DayInfo("18", "三", "", null);
        dayInfos2[18] = new DayInfo("19", "四", "", null);
        dayInfos2[19] = new DayInfo("20", "五", "", null);
        dayInfos2[20] = new DayInfo("21", "六", "", null);
        dayInfos2[21] = new DayInfo("22", "日", "", null);
        dayInfos2[22] = new DayInfo("23", "一", "", null);
        dayInfos2[23] = new DayInfo("24", "二", "", null);
        dayInfos2[24] = new DayInfo("25", "三", "", null);
        dayInfos2[25] = new DayInfo("26", "四", "", null);
        dayInfos2[26] = new DayInfo("27", "五", "", null);
        dayInfos2[27] = new DayInfo("28", "六", "", null);
        dayInfos2[28] = new DayInfo("29", "日", "", null);
        dayInfos2[29] = new DayInfo("30", "一", "", null);
        dayInfos2[30] = new DayInfo("31", "二", "", null);
        calendarInfo.setDayInfos(dayInfos2);
        mCalendarView.setData(calendarInfo);

    }
}
