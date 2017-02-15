package com.young.planhelper.mvp.plan.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.view.BaseFragment;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanPresenter;
import com.young.planhelper.mvp.plan.view.planitem.PlanItemActivity;
import com.young.planhelper.mvp.plan.view.planview.PlanAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/9/30  14:19
 */


public class PlanFragment extends BaseFragment implements IView{


    @BindView(R.id.rv_plan)
    RecyclerView mRecyclerView;

    private IPlanPresenter presenter;
    private PlanAdapter adapter;

    @Override
    protected void initUI() {
        presenter = new PlanPresenter(this, getActivity());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_plan;
    }

    @Override
    public void setData() {

        setListData();

    }

    /**
     * 设置计划数据
     */
    private void setListData() {

        adapter = new PlanAdapter(getContext(), null);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnClickListener(id -> {
            Intent intent = new Intent(getActivity(), PlanItemActivity.class);
            intent.putExtra("planInfoId", id);
            startActivity(intent);
        });
//        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL));
        presenter.getPlanInfo(data -> setData(data));
    }

    @Override
    public void setData(Object data) {
        try {
            List<PlanInfo> planInfos = (List<PlanInfo>) data;
            adapter.setDatas(planInfos);
            adapter.notifyDataSetChanged();
        }catch (Exception e){
            Toast.makeText(getActivity(), (String)data, Toast.LENGTH_SHORT).show();
        }
    }
}
