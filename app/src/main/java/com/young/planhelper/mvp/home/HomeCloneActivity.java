package com.young.planhelper.mvp.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.alexvasilkov.foldablelayout.FoldableListLayout;
import com.nineoldandroids.animation.ValueAnimator;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.base.BaseFragmentActivity;
import com.young.planhelper.mvp.home.view.monthview.MonthView;
import com.young.planhelper.mvp.home.view.weekview.WeekItemView;
import com.young.planhelper.mvp.plan.view.planitem.PlanItemActivity;
import com.young.planhelper.mvp.plan.view.planview.PlanAdapter;
import com.young.planhelper.mvp.schedule.ScheduleAddActivity;
import com.young.planhelper.mvp.schedule.ScheduleAddCloneActivity;
import com.young.planhelper.mvp.schedule.ScheduleDetailActivity;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.model.bean.DayInfo;
import com.young.planhelper.mvp.schedule.model.bean.WeekInfo;
import com.young.planhelper.mvp.schedule.presenter.ISchedulePresenter;
import com.young.planhelper.mvp.schedule.presenter.SchedulePresenter;
import com.young.planhelper.mvp.schedule.view.backlogview.BacklogAdapter;
import com.young.planhelper.mvp.schedule.view.backlogview.RecycleViewDivider;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.NestListView;
import com.young.planhelper.widget.Toolbar;
import com.young.planhelper.widget.calendar.manager.Week;
import com.young.planhelper.widget.manager.CustomLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeCloneActivity extends BaseFragmentActivity implements View.OnClickListener{

    private ResideMenu resideMenu;

    @BindView(R.id.wv_view_item)
    WeekItemView mWeekItemView;

    @BindView(R.id.monthview)
    MonthView mMonthView;

    @BindView(R.id.iv_home_calendar)
    ImageView mCalendarIv;

    @BindView(R.id.lv_home_task)
    NestListView mTaskLv;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.sv_home_task)
    ScrollView mTaskSv;

    @BindView(R.id.ll_home_calendar_bg)
    LinearLayout mCalendarBgLl;

    ISchedulePresenter presenter;

    private BacklogAdapter mTaskAdapter;

    @Override
    protected void initUI() {

        presenter = new SchedulePresenter(this, this);

        mToolbar.setOnMenuClickListener( () -> {
            resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
        });

        setUpMenu();

        List<DayInfo> dayInfos = new ArrayList<>();
        dayInfos.add(new DayInfo("16", "日", ""));
        dayInfos.add(new DayInfo("17", "一", ""));
        dayInfos.add(new DayInfo("18", "二", ""));
        dayInfos.add(new DayInfo("19", "三", ""));
        dayInfos.add(new DayInfo("20", "四", ""));
        dayInfos.add(new DayInfo("21", "五", ""));
        dayInfos.add(new DayInfo("22", "六", ""));




        mWeekItemView.setData(dayInfos);

        mWeekItemView.setOnClickListener(new WeekItemView.OnClickListener() {
            @Override
            public void onClick(String day) {

                String time = TimeUtil.getCurrentDateInString();
                time = time.substring(0, time.length() - 3);
                time += day + "日";
                mTaskAdapter.setDatas(null);
                mTaskAdapter.notifyDataSetChanged();
                presenter.getBacklogInfos(time, data -> {
                    setData(data);
                });
            }
        });


        List<DayInfo> monthInfos = new ArrayList<>();
        monthInfos.add(new DayInfo("26", "日", ""));
        monthInfos.add(new DayInfo("27", "一", ""));
        monthInfos.add(new DayInfo("28", "二", ""));
        monthInfos.add(new DayInfo("29", "三", ""));
        monthInfos.add(new DayInfo("30", "四", ""));
        monthInfos.add(new DayInfo("31", "五", ""));
        monthInfos.add(new DayInfo("1", "六", ""));
        monthInfos.add(new DayInfo("2", "日", ""));
        monthInfos.add(new DayInfo("3", "一", ""));
        monthInfos.add(new DayInfo("4", "二", ""));
        monthInfos.add(new DayInfo("5", "三", ""));
        monthInfos.add(new DayInfo("6", "四", ""));
        monthInfos.add(new DayInfo("7", "五", ""));
        monthInfos.add(new DayInfo("8", "六", ""));
        monthInfos.add(new DayInfo("9", "日", ""));
        monthInfos.add(new DayInfo("10", "一", ""));
        monthInfos.add(new DayInfo("11", "二", ""));
        monthInfos.add(new DayInfo("12", "三", ""));
        monthInfos.add(new DayInfo("13", "四", ""));
        monthInfos.add(new DayInfo("14", "五", ""));
        monthInfos.add(new DayInfo("15", "六", ""));
        monthInfos.add(new DayInfo("16", "日", ""));
        monthInfos.add(new DayInfo("17", "一", ""));
        monthInfos.add(new DayInfo("18", "二", ""));
        monthInfos.add(new DayInfo("19", "三", ""));
        monthInfos.add(new DayInfo("20", "四", ""));
        monthInfos.add(new DayInfo("21", "五", ""));
        monthInfos.add(new DayInfo("22", "六", ""));
        monthInfos.add(new DayInfo("23", "日", ""));
        monthInfos.add(new DayInfo("24", "一", ""));
        monthInfos.add(new DayInfo("25", "二", ""));
        monthInfos.add(new DayInfo("26", "三", ""));
        monthInfos.add(new DayInfo("27", "四", ""));
        monthInfos.add(new DayInfo("28", "五", ""));
        monthInfos.add(new DayInfo("29", "六", ""));

        mMonthView.setData(monthInfos);

        mMonthView.setOnClickListener( () -> {
            detailCalendar();
        });

        setListData();

        mToolbar.setOnAddClickListener( () -> {
            startActivity(new Intent(this, ScheduleAddCloneActivity.class));
        });
    }

    @Override
    public void onClick(View view) {

        resideMenu.closeMenu();
    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
//        resideMenu.setUse3D(true);
        resideMenu.setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(HomeCloneActivity.this, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(HomeCloneActivity.this, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_home_01;
    }

    /**
     * 设置备忘录数据
     */
    private void setListData() {

        mTaskAdapter = new BacklogAdapter(this, null);

//        mTaskAdapter.(id -> {
//            Intent intent = new Intent(this, ScheduleDetailActivity.class);
//            intent.putExtra("backlogInfoId", id);
//            startActivity(intent);
//        });

        mTaskLv.setAdapter(mTaskAdapter);
        mTaskLv.setEnabled(false);
        mTaskSv.smoothScrollTo(0, 0);

        presenter.getBackLogInfoToday(data -> setData(data));
    }

    @Override
    public void setData(Object data) {
        try {
            List<BacklogInfo> backlogInfos = (List<BacklogInfo>) data;
//            mTodayCountTv.setText("("+backlogInfos.size()+")");

            mTaskAdapter.setDatas(backlogInfos);
            mTaskAdapter.notifyDataSetChanged();

        }catch (Exception e){
            Toast.makeText(this, (String) data, Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.iv_home_calendar)
    public void changeCalendar() {
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_small);
//        animation.setRepeatCount(1);
//        mCalendarBgRl.clearAnimation();
//        mCalendarBgRl.startAnimation(animation
//
//        for(int i=mCalendarBgRl.getHeight(); i>=0; i-=10){
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mCalendarBgRl.getWidth(), mCalendarBgRl.getHeight());
//            if( layoutParams.height < 10 )
//                layoutParams.height = 0;
//            else
//                layoutParams.height -= 10;
//            mCalendarBgRl.setLayoutParams(layoutParams);
//
//            i = layoutParams.height;
//
//        }

        mCalendarIv.setEnabled(false);

        mMonthView.setOnClickListener( () -> {
            detailCalendar();
        });

        //属性动画对象
        ValueAnimator va ;
        va = ValueAnimator.ofInt(mCalendarBgLl.getHeight(),0);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前的height值
                int h =(Integer)valueAnimator.getAnimatedValue();
                //动态更新view的高度
                mCalendarBgLl.getLayoutParams().height = h;
                mCalendarBgLl.requestLayout();
            }
        });
        va.setDuration(1000);
        //开始动画
        va.start();

        //属性动画对象
        ValueAnimator va1 ;
        va1 = ValueAnimator.ofInt(0, 280);
        va1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前的height值
                int h =(Integer)valueAnimator.getAnimatedValue();
                //动态更新view的高度
                mMonthView.getLayoutParams().height = h;
                mMonthView.requestLayout();
            }
        });
        va1.setDuration(1000);
        //开始动画
        va1.start();

    }

    private void detailCalendar() {

        mCalendarIv.setEnabled(true);

        mMonthView.setOnClickListener(() -> {});


        ValueAnimator va ;
        va = ValueAnimator.ofInt(0, 270);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前的height值
                int h =(Integer)valueAnimator.getAnimatedValue();
                //动态更新view的高度
                mCalendarBgLl.getLayoutParams().height = h;
                mCalendarBgLl.requestLayout();
            }
        });
        va.setDuration(1000);
        //开始动画
        va.start();

        //属性动画对象
        ValueAnimator va1 ;
        va1 = ValueAnimator.ofInt(280, 0);
        va1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前的height值
                int h =(Integer)valueAnimator.getAnimatedValue();
                //动态更新view的高度
                mMonthView.getLayoutParams().height = h;
                mMonthView.requestLayout();
            }
        });
        va1.setDuration(1000);
        //开始动画
        va1.start();


    }
}
