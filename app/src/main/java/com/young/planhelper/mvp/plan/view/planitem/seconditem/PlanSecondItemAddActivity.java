package com.young.planhelper.mvp.plan.view.planitem.seconditem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanSecondItemAddPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanSecondItemAddPresenter;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.DateTimePickDialog;
import com.zcw.togglebutton.ToggleButton;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class PlanSecondItemAddActivity extends BaseActivity {

    @BindView(R.id.et_title)
    EditText mTitleEt;

    @BindView(R.id.et_content)
    EditText mContentEt;

    @BindView(R.id.ll_time)
    LinearLayout mTimeLl;

    @BindView(R.id.tv_time)
    TextView mTimeTv;

    @BindView(R.id.togglebtn)
    ToggleButton mToggleBtn;

    private IPlanSecondItemAddPresenter presenter;

    private long planItemInfoId;
    private boolean hasNotification = true;

    @Override
    protected void initUI() {

        planItemInfoId = getIntent().getLongExtra("planItemInfoId", 0);

        presenter = new PlanSecondItemAddPresenter(this, this);

        mToggleBtn.setOnToggleChanged(on -> {
            if(on)
                hasNotification = true;
            else
                hasNotification = false;
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_plan_second_item_add;
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
        dateTimePickDialog.setOnTimeSelectListener( time -> {mTimeTv.setText(time);}
        );
    }

    @OnClick(R.id.btn_confirm)
    void confirm(){

        String title = mTitleEt.getText().toString();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String content = mContentEt.getText().toString();
        if(TextUtils.isEmpty(content)){
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        PlanSecondItemInfo planSecondItemInfo = new PlanSecondItemInfo();
        planSecondItemInfo.setTitle(title);
        planSecondItemInfo.setContent(content);
        planSecondItemInfo.setTime(mTimeTv.getText().toString());
        planSecondItemInfo.setPlanSecondItemInfoId(TimeUtil.getCurrentTimeInLong());
        planSecondItemInfo.setPlanItemInfoId(planItemInfoId);

        presenter.addPlanSecondItem(planSecondItemInfo, data -> setData(data));
    }
}
