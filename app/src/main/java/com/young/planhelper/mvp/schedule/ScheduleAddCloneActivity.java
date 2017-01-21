package com.young.planhelper.mvp.schedule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.presenter.IScheduleAddPresenter;
import com.young.planhelper.mvp.schedule.presenter.ScheduleAddPresenter;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.Toolbar;
import com.zcw.togglebutton.ToggleButton;

import org.feezu.liuli.timeselector.TimeSelector;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.RealmList;

public class ScheduleAddCloneActivity extends BaseActivity {

    @BindView(R.id.et_schedule_add_content)
    EditText mContentEt;

    @BindView(R.id.tgbtn_schedule_add_switch)
    ToggleButton mSwitchTgBtn;

    @BindView(R.id.ll_schedule_add_time)
    LinearLayout mTimeLl;

    @BindView(R.id.tv_schedule_add_from_time)
    TextView mFromTimeTv;

    @BindView(R.id.tv_schedule_add_to_time)
    TextView mToTimeTv;

    @BindView(R.id.tv_schedule_add_repeat)
    TextView mRepeatTv;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private boolean isAllDay;

    IScheduleAddPresenter presenter;

    @Override
    protected void initUI() {

        presenter = new ScheduleAddPresenter(this, this);

        mToolbar.setMode(Toolbar.ADD);

        mToolbar.setOnAddClickListener( () -> {
            saveBacklog();
        });

        mFromTimeTv.setText(TimeUtil.getCurrentDateTimeInString1());
        mToTimeTv.setText(TimeUtil.getCurrentDateTimeInString1( 60 * 60 * 24 * 1000));

        mSwitchTgBtn.setToggleOn();

        mSwitchTgBtn.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if( on ){
                    isAllDay = true;
                    mTimeLl.setVisibility(View.GONE);
                }else{
                    isAllDay = false;
                    mTimeLl.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_schedule_add_clone;
    }

    @OnClick(R.id.tv_schedule_add_from_time)
    void selectFromTime(){

        TimeSelector timeSelector = new TimeSelector(this, time -> {

            mFromTimeTv.setText(time);

        }, TimeUtil.getCurrentDateTimeInString1(), "2050-12-30 23:59");

        timeSelector.show();
    }

    @OnClick(R.id.tv_schedule_add_to_time)
    void selectToTime(){

        TimeSelector timeSelector = new TimeSelector(this, time -> {

            mToTimeTv.setText(time);

        }, mFromTimeTv.getText().toString(), "2050-12-30 23:59");

        timeSelector.show();
    }

    @OnClick(R.id.tv_schedule_add_repeat)
    void selectRepeat(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //    指定下拉列表的显示数据
        final String[] repeatsShow = {"不重复", "每年一次", "每月一次", "每日一次"};
        final String[] repeats = {"none", "yearly", "monthly", "daily"};
        //    设置一个下拉的列表选择项
        builder.setItems(repeatsShow, (dialog, which) -> {
            mRepeatTv.setText(repeatsShow[which]);
        });
        builder.show();
    }

    /**
     * 保存任务
     */
    void saveBacklog(){
        BacklogInfo backlogInfo = new BacklogInfo();
        backlogInfo.setBacklogInfoId(TimeUtil.getCurrentTimeInLong());

        try {
            if( isAllDay ){
                backlogInfo.setFromTime(TimeUtil.dateToStamp1(TimeUtil.getCurrentDateTimeInString1()));
                backlogInfo.setToTime(TimeUtil.dateToStamp1(TimeUtil.getCurrentDateTimeInString1( 60 * 60 * 24 * 1000 )));
            }else{
                backlogInfo.setFromTime(TimeUtil.dateToStamp1(mFromTimeTv.getText().toString()));
                backlogInfo.setToTime(TimeUtil.dateToStamp1(mToTimeTv.getText().toString()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        backlogInfo.setStatue(BacklogInfo.UNFINISH);
        backlogInfo.setLocation("广东省");
        backlogInfo.setContent(mContentEt.getText().toString());
        backlogInfo.setBacklogImageInfos(new RealmList<>());

        LogUtil.eLog("success");

        presenter.addBacklogInfo(backlogInfo);
    }

    /**
     * 变化后显示的内容
     * @param data
     */
    @Override
    public void setData(Object data) {
        Toast.makeText(this, (String)data, Toast.LENGTH_SHORT).show();
        finish();
    }
}
