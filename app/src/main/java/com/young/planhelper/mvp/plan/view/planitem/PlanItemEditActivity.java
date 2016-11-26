package com.young.planhelper.mvp.plan.view.planitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanItemAddPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanItemAddPresenter;
import com.young.planhelper.util.TimeUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class PlanItemEditActivity extends BaseActivity implements IView {


    @BindView(R.id.tv_add_plan_detail_title)
    TextView mTitleTv;

    @BindView(R.id.et_add_plan_detail_title)
    TextView mTitleEt;

    private long planInfoId;

    private IPlanItemAddPresenter presenter;

    @Override
    protected void initUI() {
        planInfoId = getIntent().getLongExtra("planInfoId", 0);
        presenter = new PlanItemAddPresenter(this, this);

    }


    @Override
    public int getLayout() {
        return R.layout.activity_plan_detail_edit;
    }


    @Override
    public void setData(Object data) {
        Toast.makeText(this, (String)data, Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnTextChanged(R.id.et_add_plan_detail_title)
    void textChange(){
        if( TextUtils.isEmpty(mTitleEt.getText()) )
            mTitleTv.setVisibility(View.INVISIBLE);
        else
            mTitleTv.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_add_plan_detail_title)
    void addPlanItem(){
        String title = mTitleEt.getText().toString();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(this, "任务名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        PlanItemInfo planItemInfo = new PlanItemInfo();
        planItemInfo.setPlanItemInfoId(TimeUtil.getCurrentTimeInLong());
        planItemInfo.setTitle(title);
        planItemInfo.setPlanInfoId(planInfoId);

        presenter.addPlanItem(planItemInfo, data -> setData(data));
    }
}
