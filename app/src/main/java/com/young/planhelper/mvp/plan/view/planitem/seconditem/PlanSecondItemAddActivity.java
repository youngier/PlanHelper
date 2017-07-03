package com.young.planhelper.mvp.plan.view.planitem.seconditem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.constant.AppStatic;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanSecondItemAddPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanSecondItemAddPresenter;
import com.young.planhelper.mvp.plan.view.planitem.PlanItemActivity;
import com.young.planhelper.mvp.plan.view.planitem.PlanItemEditActivity;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.presenter.IScheduleAddPresenter;
import com.young.planhelper.mvp.schedule.presenter.ScheduleAddPresenter;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.DateTimePickDialog;
import com.young.planhelper.widget.Toolbar;
import com.zcw.togglebutton.ToggleButton;

import org.feezu.liuli.timeselector.TimeSelector;

import java.sql.Time;
import java.text.ParseException;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private IPlanSecondItemAddPresenter presenter;

    private IScheduleAddPresenter scheduleAddPresenter;

    private long planItemInfoId;

    private long planInfoId;

    private boolean hasNotification = true;

    private boolean isActive;

    @Override
    protected void initUI() {

        planItemInfoId = getIntent().getLongExtra("planItemInfoId", 0);

        planInfoId = getIntent().getLongExtra("planInfoId", 0);

        isActive = getIntent().getBooleanExtra("isActive", false);

        presenter = new PlanSecondItemAddPresenter(this, this);

        scheduleAddPresenter = new ScheduleAddPresenter(this, this);

        setStatueBarColor();

        mToolbar.setOnMenuClickListener( () -> finish());

        mToolbar.setMode(Toolbar.BACK);

        mToolbar.setTitle("添加子任务");

        mToggleBtn.setToggleOn();

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

        if( isActive ){
            Observable<String> result = (Observable<String>) data;
            result.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<String>() {

                        @Override
                        public void onCompleted() {
                            Log.i("way", "onCompleted");
                            hideProgress();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("way", "onError" + e.toString());
                            hideProgress();
                        }

                        @Override
                        public void onNext(String s) {
                            Log.i("way", "onNext" + s);
                            hideProgress();
                            finish();
                        }
                    });
        }else{
            finish();
        }

//        BacklogInfo backlogInfo = new BacklogInfo();
//
//        backlogInfo.setPlanInfoId(planInfoId);
//        backlogInfo.setContent(mContentEt.getText().toString());
//        backlogInfo.setBacklogInfoId(TimeUtil.getCurrentTimeInLong());
//        backlogInfo.setStatue(BacklogInfo.UNFINISH);
//        backlogInfo.setFromTime(TimeUtil.getCurrentTimeInLong());
//        try {
//            backlogInfo.setToTime(TimeUtil.dateToStamp(mTimeTv.getText().toString()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        scheduleAddPresenter.addBacklogInfo(backlogInfo, data1 -> {
//            Toast.makeText(this, (String)data1, Toast.LENGTH_SHORT).show();
//            finish();
//        });
    }

    /**
     * 选择时间
     */
    @OnClick(R.id.ll_time)
    void selectTime(){
        TimeSelector timeSelector = new TimeSelector(this, time -> {

            mTimeTv.setText(TimeUtil.transfromToChinese(time));

        }, TimeUtil.getCurrentDateTimeInString1(), "2050-12-30 23:59");

        timeSelector.show();
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

        String time = mTimeTv.getText().toString();

        if(TextUtils.isEmpty(time)){
            Toast.makeText(this, "设置通知提醒，时间不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        PlanSecondItemInfo planSecondItemInfo = new PlanSecondItemInfo();
        planSecondItemInfo.setTitle(title);
        planSecondItemInfo.setContent(content);
        if( TextUtils.isEmpty(time) )
            planSecondItemInfo.setTime(TimeUtil.getCurrentTimeInLong());
        else
            try {
                planSecondItemInfo.setTime(TimeUtil.dateToStamp(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        planSecondItemInfo.setPlanSecondItemInfoId(TimeUtil.getCurrentTimeInLong());
        planSecondItemInfo.setPlanItemInfoId(planItemInfoId);
        planSecondItemInfo.setHasNotification(hasNotification);

        if( !isActive )
            presenter.addPlanSecondItem(planSecondItemInfo, data -> setData(data));
        else
            presenter.addPlanSecondItemByNetWork(planSecondItemInfo, data -> setData(data));
    }
}
