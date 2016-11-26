package com.young.planhelper.mvp.plan.view.planitem.seconditem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanSecondItemDetailPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanSecondItemDetailPresenter;
import com.young.planhelper.util.LogUtil;

import java.util.List;

import butterknife.BindView;

public class PlanSecondItemDetailActivity extends BaseActivity {

    @BindView(R.id.et_title)
    EditText mTitleEt;

    @BindView(R.id.et_content)
    EditText mContentEt;

    @BindView(R.id.tv_time)
    TextView mTimeTv;

    private IPlanSecondItemDetailPresenter presenter;

    private long planSecondItemInfoId;

    @Override
    protected void initUI() {

        planSecondItemInfoId = getIntent().getLongExtra("planSecondItemInfoId", 0);

        presenter = new PlanSecondItemDetailPresenter(this, this);

        presenter.getPlanSecondItemInfoById(planSecondItemInfoId, data -> setData(data));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_plan_third_item;
    }

    @Override
    public void setData(Object data) {

        try {
            PlanSecondItemInfo planSecondItemInfo = (PlanSecondItemInfo) data;

            mTitleEt.setText(planSecondItemInfo.getTitle());

            mContentEt.setText(planSecondItemInfo.getContent());

            mTimeTv.setText(planSecondItemInfo.getTime());

        }catch (Exception e){
            Toast.makeText(this, (String)data, Toast.LENGTH_SHORT).show();
        }

    }
}
