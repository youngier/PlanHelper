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
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.presenter.IScheduleAddPresenter;
import com.young.planhelper.mvp.schedule.presenter.ScheduleAddPresenter;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.DateTimePickDialog;
import com.young.planhelper.widget.Toolbar;

import org.feezu.liuli.timeselector.TimeSelector;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.OnClick;

public class PlanThirdItemAddActivity extends BaseActivity {

    @BindView(R.id.et_title)
    EditText mTitleEt;

    @BindView(R.id.tv_time)
    TextView mTimeTv;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private IPlanThirdItemPresenter presenter;

    private IScheduleAddPresenter scheduleAddPresenter;

    private long planSecondItemInfoId;

    private long planInfoId;

    @Override
    protected void initUI() {

        presenter = new PlanThirdItemPresenter(this,this);

        scheduleAddPresenter = new ScheduleAddPresenter(this, this);

        planSecondItemInfoId = getIntent().getLongExtra("planSecondItemInfoId", 0);

        planInfoId = getIntent().getLongExtra("planInfoId", 0);

        setStatueBarColor();

        mToolbar.setOnMenuClickListener( () -> finish());

        mToolbar.setMode(Toolbar.BACK);

        mToolbar.setTitle("添加单元子任务");

    }

    @Override
    public int getLayout() {
        return R.layout.activity_plan_third_item_add;
    }

    @Override
    public void setData(Object data) {

        BacklogInfo backlogInfo = new BacklogInfo();
        backlogInfo.setPlanInfoId(TimeUtil.getCurrentTimeInLong());
        backlogInfo.setContent(mTitleEt.getText().toString());
        backlogInfo.setFromTime(TimeUtil.getCurrentTimeInLong());
        try {
            backlogInfo.setToTime(TimeUtil.dateToStamp(mTimeTv.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        backlogInfo.setStatue(BacklogInfo.UNFINISH);
        backlogInfo.setPlanInfoId(planInfoId);

        scheduleAddPresenter.addBacklogInfo(backlogInfo, data1 -> {
            Toast.makeText(this, (String)data, Toast.LENGTH_SHORT).show();
            finish();
        });

    }

    /**
     * 选择时间
     */
    @OnClick(R.id.ll_time)
    void selectTime(){
        TimeSelector timeSelector = new TimeSelector(this, time -> {

            mTimeTv.setText(time);

        }, TimeUtil.getCurrentDateTimeInString1(), "2050-12-30 23:59");

        timeSelector.show();
    }

    @OnClick(R.id.btn_confirm)
    void addItem(){

        String title = mTitleEt.getText().toString();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        PlanThirdItemInfo planThirdItemInfo = new PlanThirdItemInfo();
        planThirdItemInfo.setPlanThirdItemInfoId(TimeUtil.getCurrentTimeInLong());
        planThirdItemInfo.setPlanSecondItemInfoId(planSecondItemInfoId);
        planThirdItemInfo.setTitle(title);
        planThirdItemInfo.setTime(mTimeTv.getText().toString());

        presenter.addPlanThirdItem(planThirdItemInfo, data -> setData(data));

    }
}
