package com.young.planhelper.mvp.overview;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.base.BaseFragmentActivity;
import com.young.planhelper.mvp.home.HomeCloneActivity;
import com.young.planhelper.mvp.profile.view.ProfileActivity;
import com.young.planhelper.mvp.timeline.TimelineActivity;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.Toolbar;

import butterknife.BindView;

public class OverviewActivity extends BaseFragmentActivity{


    private StackedFragment mCurrFragment;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void initUI() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new StackedFragment())
                .commit();

        mToolbar.setMode(Toolbar.BACK);

        mToolbar.setTitle(TimeUtil.getCurrentYearInString() + "年");

        mToolbar.setOnDateClickListener( () -> {} );

        mToolbar.setOnMenuClickListener( () -> finish() );

    }

    @Override
    public int getLayout() {
        return R.layout.activity_overview;
    }


    @Override
    public void setData(Object data) {

    }
}

