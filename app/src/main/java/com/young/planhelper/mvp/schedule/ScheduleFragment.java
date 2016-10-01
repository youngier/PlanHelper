package com.young.planhelper.mvp.schedule;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.view.BaseFragment;
import com.young.planhelper.mvp.schedule.model.DayInfo;
import com.young.planhelper.mvp.schedule.model.WeekInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/9/30  14:19
 */


public class ScheduleFragment extends BaseFragment {

    private static final String TAG = "ScheduleFragment";

    WeekView mWeekView;

    @Override
    protected void initUI() {
        mWeekView = (WeekView) getView().findViewById(R.id.cv_week);
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
    }
}
