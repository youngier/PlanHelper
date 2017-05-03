package com.young.planhelper.mvp.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
import com.special.ResideMenu.ResideMenuItem01;
import com.young.planhelper.R;
import com.young.planhelper.application.AppApplication;
import com.young.planhelper.constant.AppConstant;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.common.people.SelectPeopleActivity;
import com.young.planhelper.mvp.friend.view.FriendActivity;
import com.young.planhelper.mvp.home.HomeCloneActivity;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.login.view.LoginActivity;
import com.young.planhelper.mvp.overview.OverviewActivity;
import com.young.planhelper.mvp.plan.view.PlanCloneActivity;
import com.young.planhelper.mvp.profile.view.ProfileActivity;
import com.young.planhelper.mvp.timeline.TimelineActivity;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.SharePreferenceUtil;
import com.young.planhelper.widget.Toolbar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
    protected ResideMenuItem itemFriend;

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

        SharePreferenceUtil sharePreferenceUtil = new SharePreferenceUtil(this);

        User user = sharePreferenceUtil.getUserInfo();

        AppApplication.get(this).getmAppComponent().getUserInfo().copyWith(user);

        if( !user.getIconUrl().equals("") ){
            LogUtil.eLog("首页图片："+AppConstant.RECOUSE_IMAGE_URL + user.getIconUrl());
            Glide.with(this)
                    .load(AppConstant.RECOUSE_IMAGE_URL + user.getIconUrl())
                    .into(itemIcon.getIconView());
        }

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
        itemFriend = new ResideMenuItem(this, "Friend");
        itemFriend.setOnClickListener(this);
        resideMenu.addMenuItem(itemFriend, ResideMenu.DIRECTION_LEFT);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);
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
        else if( view == itemFriend ) {

            User user = AppApplication.get(this).getmAppComponent().getUserInfo();

            if(user == null || TextUtils.isEmpty(user.getUserId()))
                startActivity(new Intent(this, LoginActivity.class));
            else
                startActivity(new Intent(this, FriendActivity.class));

        }

        resideMenu.closeMenu();
    }

    /**
     * 加载本地图片
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
