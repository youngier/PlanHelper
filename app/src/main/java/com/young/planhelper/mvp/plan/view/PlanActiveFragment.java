package com.young.planhelper.mvp.plan.view;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.view.BaseFragment;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.view.planview.PlanAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/15  09:53
 */


public class PlanActiveFragment extends BaseFragment implements IView {

    @BindView(R.id.rv_plan_active)
    RecyclerView mActiveRv;

    private PlanAdapter mPlanAdapter;


    @Override
    protected void setData() {

    }

    @Override
    protected void initUI() {

        mPlanAdapter = new PlanAdapter(getActivity(), null);

        mActiveRv.setAdapter(mPlanAdapter);

        mActiveRv.addItemDecoration(new MarginDecoration(getActivity()));
        mActiveRv.setHasFixedSize(true);
        mActiveRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_plan_active;
    }

    @Override
    public void setData(Object data) {

    }
}
