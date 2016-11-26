package com.young.planhelper.mvp.schedule;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.view.BaseFragment;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.model.bean.CalendarInfo;
import com.young.planhelper.mvp.schedule.model.bean.DayInfo;
import com.young.planhelper.mvp.schedule.model.bean.WeekInfo;
import com.young.planhelper.mvp.schedule.presenter.ISchedulePresenter;
import com.young.planhelper.mvp.schedule.presenter.SchedulePresenter;
import com.young.planhelper.mvp.schedule.view.backlogview.BacklogAdapter;
import com.young.planhelper.mvp.schedule.view.backlogview.RecycleViewDivider;
import com.young.planhelper.mvp.schedule.view.calendarview.CalendarView;
import com.young.planhelper.mvp.schedule.view.weekview.WeekView;
import com.young.planhelper.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/9/30  14:19
 */


public class ScheduleFragment extends BaseFragment {

    private static final String TAG = "ScheduleFragment";

    @BindView(R.id.weekview)
    WeekView mWeekView;

    @BindView(R.id.calendarview)
    CalendarView mCalendarView;

    @BindView(R.id.rv_schedule)
    RecyclerView mRecyclerView;

    BacklogAdapter adapter;

    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipeRl;

    ISchedulePresenter presenter;

    @Override
    protected void initUI() {

        presenter = new SchedulePresenter(this, getActivity());

        mCalendarView.setOnMoveListener(new CalendarView.OnMoveListener() {
            @Override
            public void onMove(float value) {
                mSwipeRl.setAlpha(1 - value);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedule;
    }

    @Override
    public void setData() {
        setWeekData();

        setCalendarData();

        setListData();

    }

    /**
     * 设置备忘录数据
     */
    private void setListData() {
        adapter = new BacklogAdapter(getContext(), null);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL));
        presenter.getBacklogInfos(new IBiz.ICallback() {
            @Override
            public void onResult(Object data) {
                setData(data);
            }
        });

    }

    /**
     * 设置每月的数据
     */
    private void setCalendarData() {
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

    /**
     * 设置每周的数据
     */
    private void setWeekData() {
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
        mWeekView.setData(weekInfoList);
    }

    @Override
    public void setData(Object data) {
        try {
            List<BacklogInfo> backlogInfos = (List<BacklogInfo>) data;
            adapter.setDatas(backlogInfos);
            adapter.notifyDataSetChanged();
        }catch (Exception e){
            Toast.makeText(getActivity(), (String)data, Toast.LENGTH_SHORT).show();
        }
    }
}
