package com.young.planhelper.mvp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
import com.special.ResideMenu.ResideMenuItem01;
import com.young.planhelper.R;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.home.HomeCloneActivity;
import com.young.planhelper.mvp.login.LoginActivity;
import com.young.planhelper.mvp.overview.OverviewActivity;
import com.young.planhelper.mvp.plan.view.PlanCloneActivity;
import com.young.planhelper.mvp.profile.view.ProfileActivity;
import com.young.planhelper.mvp.timeline.TimelineActivity;
import com.young.planhelper.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 最基本的activity，所有的activity都要继承
 * 提供加载框的显示
 * @author: young
 * date:16/9/30  10:23
 */


public abstract class BaseFragmentActivity extends FragmentActivity implements IView, View.OnClickListener {

    protected ResideMenu resideMenu;

    protected ResideMenuItem01 itemIcon;

    protected ResideMenuItem itemHome;
    protected ResideMenuItem itemOverview;
    protected ResideMenuItem itemTimeline;
    protected ResideMenuItem itemProfile;
    protected ResideMenuItem itemPlan;

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(getLayout());
        ButterKnife.bind(this);

        mToolbar.setOnMenuClickListener( () -> {
            resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
        });


        setUpMenu();

        initUI();
    }

    /**
     * 设置activity主题
     */
    protected void setTheme() {

    }

    /**
     * 初始化UI控件
     */
    protected abstract void initUI();

    /**
     * 获取布局，由子类提供
     * @return
     */
    public abstract int getLayout();

    protected void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
//        resideMenu.setUse3D(true);
        resideMenu.setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);

        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.65f);


        itemIcon = new ResideMenuItem01(this, R.mipmap.ic_profile_bg);
        itemIcon.setOnClickListener(this);
        resideMenu.setLeftMenuIcon(itemIcon);

        itemHome = new ResideMenuItem(this, "Home");
        itemHome.setOnClickListener(this);
        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        itemOverview = new ResideMenuItem(this, "Overview");
        itemOverview.setOnClickListener(this);
        resideMenu.addMenuItem(itemOverview, ResideMenu.DIRECTION_LEFT);
        itemTimeline = new ResideMenuItem(this, "Timeline");
        itemTimeline.setOnClickListener(this);
        resideMenu.addMenuItem(itemTimeline, ResideMenu.DIRECTION_LEFT);
        itemProfile = new ResideMenuItem(this, "Profile");
        itemProfile.setOnClickListener(this);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        itemPlan = new ResideMenuItem(this, "Plan");
        itemPlan.setOnClickListener(this);
        resideMenu.addMenuItem(itemPlan, ResideMenu.DIRECTION_LEFT);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    protected ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
        }

        @Override
        public void closeMenu() {
        }
    };

    @Override
    public void onClick(View view) {

        if( view == itemOverview ){
            startActivity(new Intent(this, OverviewActivity.class));
        }else if( view == itemTimeline ){
            startActivity(new Intent(this, TimelineActivity.class));
        }else if( view == itemProfile ){
            startActivity(new Intent(this, ProfileActivity.class));
        }else if( view == itemPlan ){
            startActivity(new Intent(this, PlanCloneActivity.class));
        }else if( view == itemHome )
            startActivity(new Intent(this, HomeCloneActivity.class));
        else if( view == itemIcon )
            startActivity(new Intent(this, LoginActivity.class));

        resideMenu.closeMenu();
    }
}
