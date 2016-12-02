package com.young.planhelper.mvp.schedule;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
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
import com.young.planhelper.mvp.schedule.view.weekview.WeekView;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.calendar.CollapseCalendarView;
import com.young.planhelper.widget.manager.CustomLinearLayoutManager;

import org.joda.time.LocalDate;

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

    @BindView(R.id.rv_schedule)
    RecyclerView mRecyclerView;

    BacklogAdapter adapter;

    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipeRl;

    @BindView(R.id.calendar)
    CollapseCalendarView mCalendarView;

    @BindView(R.id.tv_schedule_backlog_today_count)
    TextView mTodayCountTv;

    ISchedulePresenter presenter;

    private int MAX_SHOW_SIZE = 3;

    @Override
    protected void initUI() {

        presenter = new SchedulePresenter(this, getActivity());

        mCalendarView.init(LocalDate.now(), LocalDate.now(), LocalDate.now().plusYears(1));

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedule;
    }

    @Override
    public void setData() {

        setListData();

    }

    /**
     * 设置备忘录数据
     */
    private void setListData() {
        adapter = new BacklogAdapter(getContext(), null);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getActivity());
        layoutManager.setScrollEnabled(false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL));
        presenter.getBackLogInfoToday(data -> setData(data));

    }



    @Override
    public void setData(Object data) {
        try {
            List<BacklogInfo> backlogInfos = (List<BacklogInfo>) data;
            mTodayCountTv.setText("("+backlogInfos.size()+")");
            List<BacklogInfo> temp = new ArrayList<BacklogInfo>();
            if( backlogInfos.size() > MAX_SHOW_SIZE ) {
                for (int i = 0; i < 3; i++)
                    temp.add(backlogInfos.get(i));
                adapter.setDatas(temp);
            }else {
                adapter.setDatas(backlogInfos);
            }
            adapter.notifyDataSetChanged();
        }catch (Exception e){
            Toast.makeText(getActivity(), (String) data, Toast.LENGTH_SHORT).show();
        }
    }
}
