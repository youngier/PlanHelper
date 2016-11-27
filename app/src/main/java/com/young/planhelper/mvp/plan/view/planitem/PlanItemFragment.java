package com.young.planhelper.mvp.plan.view.planitem;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.view.BaseFragment;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanSecondItemPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanSecondItemPresenter;
import com.young.planhelper.mvp.plan.view.planitem.seconditem.PlanSecondItemAdapter;
import com.young.planhelper.mvp.plan.view.planitem.seconditem.PlanSecondItemAddActivity;
import com.young.planhelper.mvp.plan.view.planitem.seconditem.PlanSecondItemDetailActivity;
import com.young.planhelper.mvp.schedule.view.backlogview.RecycleViewDivider;
import com.young.planhelper.util.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/23  21:49
 */


public class PlanItemFragment extends BaseFragment{

    @BindView(R.id.tv_plan_item_title)
    TextView mTitleTv;

    @BindView(R.id.rv_fragment_plan_item)
    RecyclerView mRecyclerView;


    private PlanItemInfo mPlanItemInfo;

    private IPlanSecondItemPresenter presenter;

    private PlanSecondItemAdapter adapter;

    public PlanItemFragment(PlanItemInfo planItemInfo) {
        this.mPlanItemInfo = planItemInfo;
    }

    @Override
    protected void setData() {

        setListData();

    }

    @Override
    protected void initUI() {

        mTitleTv.setText(mPlanItemInfo.getTitle());

        presenter = new PlanSecondItemPresenter(this, getActivity());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_plan_item;
    }

    @Override
    public void setData(Object data) {
        try {
            List<PlanSecondItemInfo> planSecondItemInfos = (List<PlanSecondItemInfo>) data;
            LogUtil.eLog(planSecondItemInfos.size()+"个查找内容");
            adapter.setDatas(planSecondItemInfos);
            adapter.notifyDataSetChanged();

        }catch (Exception e){
            Toast.makeText(getActivity(), (String)data, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.fabtn_fragment_plan_item)
    void addSecondItem(){
        Intent intent = new Intent(getActivity(), PlanSecondItemAddActivity.class);
        intent.putExtra("planItemInfoId", mPlanItemInfo.getPlanItemInfoId());
        startActivity(intent);
    }

    private void setListData() {
        adapter = new PlanSecondItemAdapter(getContext(), null);
        adapter.setOnClickListener(id -> {
            Intent intent = new Intent(getActivity(), PlanSecondItemDetailActivity.class);
            intent.putExtra("planSecondItemInfoId", id);
            startActivity(intent);
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL));

        presenter.getPlanSecondItemInfo(mPlanItemInfo.getPlanItemInfoId(), data -> setData(data));

    }
}
