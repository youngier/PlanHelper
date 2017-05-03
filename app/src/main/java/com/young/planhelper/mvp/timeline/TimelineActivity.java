package com.young.planhelper.mvp.timeline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.base.BaseFragmentActivity;
import com.young.planhelper.mvp.home.HomeCloneActivity;
import com.young.planhelper.mvp.overview.OverviewActivity;
import com.young.planhelper.mvp.profile.view.ProfileActivity;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.view.backlogview.BacklogAdapter;
import com.young.planhelper.mvp.timeline.model.bean.TimelineInfo;
import com.young.planhelper.mvp.timeline.presenter.ITimelinePresenter;
import com.young.planhelper.mvp.timeline.presenter.TimelinePresenter;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.young.planhelper.util.TimeUtil.DATE_FORMAT_DATE_01;

public class TimelineActivity extends BaseFragmentActivity implements View.OnClickListener{



    @BindView(R.id.elv_timeline)
    ExpandableListView mTimelineElv;

    @BindView(R.id.rl_timeline)
    RelativeLayout mTimelineRl;

    private TimelineAdapter mTimelineAdapter;

    private ITimelinePresenter presenter;

    @Override
    protected void initUI() {

        mToolbar.setMode(Toolbar.TIMELINE);

        mToolbar.setTitle("时间轴");

        mToolbar.setOnStatueClickListener( selectItem -> {
            presenter.getTimelineInfoByStatue(selectItem, data -> setData(data));
        });

        presenter = new TimelinePresenter(this, this);


        setListData();

    }

    private void setListData() {
        mTimelineAdapter = new TimelineAdapter(this, null);

//        mTaskAdapter.(id -> {
//            Intent intent = new Intent(this, ScheduleDetailActivity.class);
//            intent.putExtra("backlogInfoId", id);
//            startActivity(intent);
//        });

        mTimelineElv.setAdapter(mTimelineAdapter);

        presenter.getTimelineInfoByStatue(Toolbar.ALL, data -> setData(data));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_timeline;
    }

    @Override
    public void setData(Object data) {
        try {
            List<BacklogInfo> backlogInfos = (List<BacklogInfo>) data;
//            mTodayCountTv.setText("("+backlogInfos.size()+")");

            List<TimelineInfo> timelineInfoList = new ArrayList<>();

            //添加进去的下标
            int index= 0;

            if( backlogInfos != null && backlogInfos.size() != 0 ) {

                mTimelineRl.setVisibility(View.GONE);
                long id = backlogInfos.get(0).getBacklogInfoId();

                TimelineInfo timelineInfo = new TimelineInfo();
                timelineInfo.setDate(TimeUtil.getTime(id, DATE_FORMAT_DATE_01));
                timelineInfo.getBacklogInfoList().add(backlogInfos.get(0));

                timelineInfoList.add(timelineInfo);
            }else{
                mTimelineRl.setVisibility(View.VISIBLE);
            }

            for(int i=1; i<backlogInfos.size(); i++){

                long id = backlogInfos.get(i).getBacklogInfoId();

                //如果上一个的时间与当前的是相同的，则加到当前的时间线里
                if( timelineInfoList.get(index).getDate().equals(TimeUtil.getTime(id, DATE_FORMAT_DATE_01)) ){

                    timelineInfoList.get(index).getBacklogInfoList().add(backlogInfos.get(i));

                    //如果不相同的，那么备份加入时间线列表，设置的新的时间线，并加入当前这个"异类"，开启新的时间线
                }else{
                    TimelineInfo timelineInfo = new TimelineInfo();
                    timelineInfo.setDate(TimeUtil.getTime(id, DATE_FORMAT_DATE_01));
                    timelineInfo.getBacklogInfoList().add(backlogInfos.get(i));

                    timelineInfoList.add(timelineInfo);

                }

                LogUtil.eLog(TimeUtil.getTime(id, DATE_FORMAT_DATE_01));
            }

            mTimelineAdapter.setData(timelineInfoList);
            mTimelineAdapter.notifyDataSetChanged();

            mTimelineElv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    return true;
                }
            });
            for( int i=0; i< mTimelineAdapter.getGroupCount(); i++)
                mTimelineElv.expandGroup(i);
            mTimelineElv.setGroupIndicator(null);
            mTimelineElv.setDivider(null);

        }catch (Exception e){
            Toast.makeText(this, (String) data, Toast.LENGTH_SHORT).show();
        }
    }
}
