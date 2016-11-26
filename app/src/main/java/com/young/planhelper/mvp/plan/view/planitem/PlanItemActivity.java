package com.young.planhelper.mvp.plan.view.planitem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanItemPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanItemPresenter;
import com.young.planhelper.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PlanItemActivity extends BaseActivity implements IView{

    @BindView(R.id.vp_plan_detail)
    ViewPager mViewPager;

    private Long planInfoId;

    private Adapter adapter;

    private IPlanItemPresenter presenter;


    @Override
    protected void initUI() {

        presenter = new PlanItemPresenter(this, this);

        planInfoId = getIntent().getLongExtra("planInfoId", 0);

        adapter = null;

        adapter = new Adapter(getSupportFragmentManager());

        mViewPager.setAdapter(adapter);

        presenter.getPlanItemInfo(planInfoId, data -> setData(data));

    }

    @Override
    protected void onResume() {

        super.onResume();

        initUI();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_plan_detail;
    }

    @Override
    public void setData(Object data) {

        try {

            List<PlanItemInfo> planInfos = (List<PlanItemInfo>) data;

            if( planInfos != null && planInfos.size() != 0 ){

                int size = planInfos.size();
                for(int i=0; i<size; i++){
                    PlanItemFragment fragment = new PlanItemFragment(planInfos.get(i));
                    adapter.addFragment(fragment, "");
                }

            }

            PlanItemAddFragment fragment = new PlanItemAddFragment();
            fragment.setPlanInfoId(planInfoId);

            adapter.addFragment(fragment, "");

            adapter.notifyDataSetChanged();

        }catch (Exception e){
            e.printStackTrace();
        }
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
