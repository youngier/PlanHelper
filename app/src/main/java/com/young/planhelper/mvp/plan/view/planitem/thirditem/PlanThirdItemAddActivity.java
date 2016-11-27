package com.young.planhelper.mvp.plan.view.planitem.thirditem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanThirdItemPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanThirdItemPresenter;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.DateTimePickDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class PlanThirdItemAddActivity extends BaseActivity {

    @BindView(R.id.et_title)
    EditText mTitleEt;

    @BindView(R.id.tv_time)
    TextView mTimeTv;

    private IPlanThirdItemPresenter presenter;

    private long planSecondItemInfoId;

    @Override
    protected void initUI() {

        presenter = new PlanThirdItemPresenter(this,this);

        planSecondItemInfoId = getIntent().getLongExtra("planSecondItemInfoId", 0);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_plan_third_item_add;
    }

    @Override
    public void setData(Object data) {
        Toast.makeText(this, (String)data, Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * 选择时间
     */
    @OnClick(R.id.ll_time)
    void selectTime(){
        DateTimePickDialog dateTimePickDialog = new DateTimePickDialog(
                this, TimeUtil.getCurrentDateTimeInString());
        dateTimePickDialog.dateTimePicKDialog();
        dateTimePickDialog.setOnTimeSelectListener(new DateTimePickDialog.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(String time) {
                mTimeTv.setText(time);
            }
        });
    }

    @OnClick(R.id.btn_confirm)
    void addItem(){

        String title = mTitleEt.getText().toString();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        PlanThirdItemInfo planThirdItemInfo = new PlanThirdItemInfo();
        planThirdItemInfo.setPlanThridItemInfoId(TimeUtil.getCurrentTimeInLong());
        planThirdItemInfo.setPlanSecondItemInfoId(planSecondItemInfoId);
        planThirdItemInfo.setTitle(title);
        planThirdItemInfo.setTime(mTimeTv.getText().toString());

        presenter.addPlanThirdItem(planThirdItemInfo, data -> setData(data));

    }
}
