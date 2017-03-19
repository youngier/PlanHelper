package com.young.planhelper.mvp.plan.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.LinearLayout;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseFragmentActivity;
import com.young.planhelper.widget.NoScrollViewPager;
import com.young.planhelper.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PlanCloneActivity extends BaseFragmentActivity {

    @BindView(R.id.ll_plan_active_line)
    LinearLayout mActiveLineLl;

    @BindView(R.id.ll_plan_archived_line)
    LinearLayout mArchivedLineLl;

    @BindView(R.id.nsvp_plan)
    NoScrollViewPager mPlanVp;

    @Override
    protected void initUI() {

        mToolbar.setMode(Toolbar.PLAN);

        mToolbar.setTitle("计划");

        mToolbar.setOnRightClickListener( () -> {
            Intent intent = new Intent(this, PlanAddActivity.class);
            if( mPlanVp.getCurrentItem() == 0 )
                intent.putExtra("isActive", true);
            else
                intent.putExtra("isActive", false);
            startActivity(intent);
        });

        setupViewPager();
    }

    private void setupViewPager() {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new PlanActiveFragment(), "线上合作");
        adapter.addFragment(new PlanArchiveFragment(), "独立副本");
        mPlanVp.setAdapter(adapter);

    }

    @OnClick(R.id.ll_plan_active)
    void selectActive(){
        mArchivedLineLl.setBackgroundColor(getResources().getColor(R.color.white));
        mActiveLineLl.setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
        mPlanVp.setCurrentItem(0);
    }


    @OnClick(R.id.ll_plan_archived)
    void selectArchived(){
        mActiveLineLl.setBackgroundColor(getResources().getColor(R.color.white));
        mArchivedLineLl.setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
        mPlanVp.setCurrentItem(1);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_plan_clone;
    }

    @Override
    public void setData(Object data) {

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
