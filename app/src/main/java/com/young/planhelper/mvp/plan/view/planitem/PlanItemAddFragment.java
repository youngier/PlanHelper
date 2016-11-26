package com.young.planhelper.mvp.plan.view.planitem;

import android.content.Intent;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.view.BaseFragment;
import com.young.planhelper.mvp.base.view.IView;

import butterknife.OnClick;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/23  20:27
 */


public class PlanItemAddFragment extends BaseFragment implements IView{


    private long planInfoId;

    @Override
    protected void setData() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_plan_detail_add;
    }

    @Override
    public void setData(Object data) {

    }

    @OnClick(R.id.ll_edit_title)
    void edit(){
        Intent intent = new Intent(getActivity(), PlanItemEditActivity.class);
        intent.putExtra("planInfoId", planInfoId);
        startActivity(intent);
    }


    public void setPlanInfoId(Long planInfoId) {
        this.planInfoId = planInfoId;
    }
}
