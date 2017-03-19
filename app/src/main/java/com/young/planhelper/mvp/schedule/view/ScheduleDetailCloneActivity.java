package com.young.planhelper.mvp.schedule.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.presenter.IScheduleAddPresenter;
import com.young.planhelper.mvp.schedule.presenter.ISchedulePresenter;
import com.young.planhelper.mvp.schedule.presenter.ScheduleAddPresenter;
import com.young.planhelper.mvp.schedule.presenter.SchedulePresenter;
import com.young.planhelper.util.DensityUtil;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.Toolbar;
import com.zcw.togglebutton.ToggleButton;

import org.feezu.liuli.timeselector.TimeSelector;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.RealmList;

import static com.amap.api.mapcore2d.p.h;

public class ScheduleDetailCloneActivity extends BaseActivity {

    @BindView(R.id.et_schedule_detail_content)
    EditText mContentEt;

    @BindView(R.id.tgbtn_schedule_detail_switch)
    ToggleButton mSwitchTgBtn;

    @BindView(R.id.ll_schedule_detail_time)
    LinearLayout mTimeLl;

    @BindView(R.id.tv_schedule_detail_from_time)
    TextView mFromTimeTv;

    @BindView(R.id.tv_schedule_detail_to_time)
    TextView mToTimeTv;

    @BindView(R.id.tv_schedule_detail_repeat)
    TextView mRepeatTv;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.iv_schedule_detail_finish_big)
    ImageView mFinishBigIv;

    @BindView(R.id.iv_schedule_detail_finish)
    ImageView mFinishIv;

    @BindView(R.id.rl_schedule_detail_buttons)
    RelativeLayout mButtonsRl;

    @BindView(R.id.ll_schedule_detail_grap)
    LinearLayout mGrapLl;

    @BindView(R.id.rl_schedule_detail_finish)
    RelativeLayout mFinishRl;

    @BindView(R.id.rl_schedule_detail_modify)
    RelativeLayout mModifyRl;

    @BindView(R.id.rl_schedule_detail_delete)
    RelativeLayout mDeleteRl;

    private boolean isAllDay;

    private int mRepeatType;

    private long backlogInfoId;

    ISchedulePresenter presenter;

    private BacklogInfo mBacklogInfo;


    @Override
    protected void initUI() {

        setStatueBarColor();

        backlogInfoId = getIntent().getLongExtra("backlogInfoId", 0);

        presenter = new SchedulePresenter(this, this);

        mToolbar.setMode(Toolbar.BACK);

        mToolbar.setTitle("任务详情");

        mToolbar.setRightText("修改完成");

        mToolbar.setOnDateClickListener( () -> {

        });

        mToolbar.setOnRightClickListener( () -> {
            mContentEt.setEnabled(false);
            mSwitchTgBtn.setEnabled(false);
            mRepeatTv.setEnabled(false);
            mButtonsRl.setVisibility(View.VISIBLE);
            mToolbar.setMode(Toolbar.BACK);
            saveModifyBacklog();
        });

        mContentEt.setEnabled(false);
        mSwitchTgBtn.setEnabled(false);
        mRepeatTv.setEnabled(false);

        presenter.getBacklogDetailById(backlogInfoId, data -> {
            try {
                BacklogInfo backlogInfo = (BacklogInfo) data;

                this.mBacklogInfo = backlogInfo;

                mContentEt.setText(backlogInfo.getContent());

                switch (backlogInfo.getRepeatType()){
                    case BacklogInfo.NONE:
                        mRepeatTv.setText("不重复");
                        break;
                    case BacklogInfo.YEARLY:
                        mRepeatTv.setText("每年一次");
                        break;
                    case BacklogInfo.MONTHLY:
                        mRepeatTv.setText("每月一次");
                        break;
                    case BacklogInfo.DAILY:
                        mRepeatTv.setText("每日一次");
                        break;
                }

                mFromTimeTv.setText(TimeUtil.getTime3(backlogInfo.getFromTime()));
                mToTimeTv.setText(TimeUtil.getTime3(backlogInfo.getToTime()));
                if( backlogInfo.getToTime() - backlogInfo.getFromTime() == 60 * 60 * 24 * 1000 ) {
                    mSwitchTgBtn.setToggleOn();
                    isAllDay = true;
                }
                else {
                    isAllDay = false;
                    mSwitchTgBtn.setToggleOn();
                }

                if( backlogInfo.getStatue() == BacklogInfo.FINISHED ){
                    mButtonsRl.setVisibility(View.GONE);
                    mFinishIv.setVisibility(View.VISIBLE);
                    mGrapLl.getLayoutParams().height = DensityUtil.dipToPixels(this, 60);
                }
            }catch (Exception e){
                Toast.makeText(this, (String) data, Toast.LENGTH_SHORT).show();
            }

        });




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
        return R.layout.activity_schedule_detail_clone;
    }

    @OnClick(R.id.tv_schedule_detail_from_time)
    void selectFromTime(){

        TimeSelector timeSelector = new TimeSelector(this, time -> {

            mFromTimeTv.setText(time);

        }, TimeUtil.getCurrentDateTimeInString1(), "2050-12-30 23:59");

        timeSelector.show();
    }

    @OnClick(R.id.tv_schedule_detail_to_time)
    void selectToTime(){

        TimeSelector timeSelector = new TimeSelector(this, time -> {

            mToTimeTv.setText(time);

        }, mFromTimeTv.getText().toString(), "2050-12-30 23:59");

        timeSelector.show();
    }

    @OnClick(R.id.tv_schedule_detail_repeat)
    void selectRepeat(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //    指定下拉列表的显示数据
        final String[] repeatsShow = {"不重复", "每年一次", "每月一次", "每日一次"};
        final int[] repeats = {BacklogInfo.NONE, BacklogInfo.YEARLY, BacklogInfo.MONTHLY, BacklogInfo.DAILY};
        //    设置一个下拉的列表选择项
        builder.setItems(repeatsShow, (dialog, which) -> {
            mRepeatTv.setText(repeatsShow[which]);
            mRepeatType = repeats[which];
        });
        builder.show();
    }

    @OnClick(R.id.tv_detail_location)
    public void selectLocation(){
        startActivity(new Intent(this, MarkerActivity.class));
    }

    @OnClick(R.id.rl_schedule_detail_modify)
    public void selectModify(){
        mContentEt.setEnabled(true);
        mSwitchTgBtn.setEnabled(true);
        mRepeatTv.setEnabled(true);
        mButtonsRl.setVisibility(View.GONE);
        mToolbar.setMode(Toolbar.MODIFY);
    }

    @OnClick(R.id.rl_schedule_detail_delete)
    public void selectDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("确认删除？")
                .setPositiveButton("确认",  (dialog, which) -> {
                    dialog.dismiss();
                    deleteBacklogInfo();
                })
                .setNegativeButton("取消", (dialog, which) -> {
                    dialog.dismiss();
                    this.finish();
                });

        builder.show();
    }

    /**
     * 删除日程任务
     */
    private void deleteBacklogInfo() {
        presenter.deleteBacklog(mBacklogInfo, data -> {
            String result = (String) data;
            if( result.equals("") )
                finish();
            else
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * 完成日程任务
     */
    @OnClick(R.id.rl_schedule_detail_finish)
    public void selectFinish(){

        mFinishRl.setEnabled(false);
        mModifyRl.setEnabled(false);
        mDeleteRl.setEnabled(false);

        mFinishBigIv.setVisibility(View.VISIBLE);
        ValueAnimator va = ValueAnimator.ofFloat(0.1f, 1.0f);
        va.addUpdateListener(valueAnimator -> {

            float f =(Float)valueAnimator.getAnimatedValue();
            mFinishBigIv.setAlpha(f);
            mButtonsRl.setAlpha(1 - f);
            //动态更新view
            int height = (int) (DensityUtil.dipToPixels(this, 200) - DensityUtil.dipToPixels(this, 100) * f);
            mFinishBigIv.getLayoutParams().height = height;
            mFinishBigIv.getLayoutParams().width = height;
            //获取当前的height值
            int grapH = (int) (DensityUtil.dipToPixels(this, 10) + DensityUtil.dipToPixels(this, 50) * f);
            //动态更新view的高度
            mGrapLl.getLayoutParams().height = grapH;
            mGrapLl.requestLayout();
            mFinishBigIv.requestLayout();
        });
        va.setDuration(500);
        //开始动画
        va.start();
        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mFinishBigIv.setVisibility(View.GONE);
                mFinishIv.setVisibility(View.VISIBLE);
                mButtonsRl.setVisibility(View.GONE);
                presenter.finishBacklogInfo(mBacklogInfo, data -> {
                });
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 保存修改任务
     */
    void saveModifyBacklog(){
        BacklogInfo backlogInfo = new BacklogInfo();

        backlogInfo.setRepeatType(mRepeatType);

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
        backlogInfo.setLocation("");
        backlogInfo.setContent(mContentEt.getText().toString());
        backlogInfo.setBacklogImageInfos(new RealmList<>());

        LogUtil.eLog("success");

        presenter.modifyBacklogInfo(mBacklogInfo, backlogInfo, data -> setData(data));
    }



    /**
     * 变化后显示的内容
     * @param data
     */
    @Override
    public void setData(Object data) {
        String result = (String) data;
        if( result.equals("") ){

        }else{
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }

    private void closeInputMethod() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        LogUtil.eLog("任务详情键盘："+isOpen);
        if (isOpen) {
            // imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//没有显示则显示
            imm.hideSoftInputFromWindow(mContentEt.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
