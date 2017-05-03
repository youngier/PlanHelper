package com.young.planhelper.mvp.profile.view;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.special.ResideMenu.ResideMenu;
import com.young.planhelper.R;
import com.young.planhelper.application.AppApplication;
import com.young.planhelper.constant.AppConstant;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.base.BaseFragmentActivity;
import com.young.planhelper.mvp.home.HomeCloneActivity;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.overview.OverviewActivity;
import com.young.planhelper.mvp.profile.presenter.IProfilePresenter;
import com.young.planhelper.mvp.profile.presenter.ProfilePresenter;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.timeline.TimelineActivity;
import com.young.planhelper.mvp.timeline.TimelineAdapter;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.NestListView;
import com.young.planhelper.widget.Toolbar;

import java.util.List;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseFragmentActivity {

    @BindView(R.id.nlv_profile_task)
    NestListView mTaskLv;

    @BindView(R.id.sv_profile_task)
    ScrollView mTaskSv;

    @BindView(R.id.tv_profile_month)
    TextView mMonthTv;

    @BindView(R.id.ll_profile_finished)
    LinearLayout mFinishedLl;
    @BindView(R.id.iv_profile_finished)
    ImageView mFinishedIv;
    @BindView(R.id.tv_profile_finished)
    TextView mFinishedTv;

    @BindView(R.id.ll_profile_unfinish)
    LinearLayout mUnFinishLl;
    @BindView(R.id.iv_profile_unfinish)
    ImageView mUnFinishIv;
    @BindView(R.id.tv_profile_unfinish)
    TextView mUnFinishTv;

    @BindView(R.id.ll_profile_overdue)
    LinearLayout mOverdueLl;
    @BindView(R.id.iv_profile_overdue)
    ImageView mOverdueIv;
    @BindView(R.id.tv_profile_overdue)
    TextView mOverdueTv;

    @BindView(R.id.civ_profile)
    CircleImageView mProfileCiv;

    private ProfileAdapter mProfileAdapter;

    private IProfilePresenter presenter;

    //默认选中的状态是已完成
    private int selectType = BacklogInfo.FINISHED;

    @Override
    protected void initUI() {

        mToolbar.setMode(Toolbar.PROFILE);

        mToolbar.setTitle(TimeUtil.getCurrentYearInString() + "年况");

        presenter = new ProfilePresenter(this, this);

        setListData();

        User user = AppApplication.get(this).getmAppComponent().getUserInfo();

        if(user == null || TextUtils.isEmpty(user.getIconUrl()))
            mProfileCiv.setImageResource(R.mipmap.ic_profile_bg);
        else
            Glide.with(this)
                    .load(AppConstant.RECOUSE_IMAGE_URL + user.getIconUrl())
                    .into(mProfileCiv);

        mTaskSv.smoothScrollTo(0, 0);

    }

    private void setListData() {
        mProfileAdapter = new ProfileAdapter(this, null);

//        mTaskAdapter.(id -> {
//            Intent intent = new Intent(this, ScheduleDetailActivity.class);
//            intent.putExtra("backlogInfoId", id);
//            startActivity(intent);
//        });

        mTaskLv.setAdapter(mProfileAdapter);

        String currentTime = TimeUtil.getCurrentDateInString1();
        StringTokenizer tokenizer = new StringTokenizer(currentTime, "-");
        String year = tokenizer.nextToken();
        String month = tokenizer.nextToken();

        if( month.charAt(0) == '0' )
            month = month.substring(1, 2);

        mMonthTv.setText(month + "月");

        String time = year + "年" + mMonthTv.getText();

        presenter.getProfileInfoByMonth(selectType, time, data -> setData(data));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_profile;
    }

    @Override
    public void setData(Object data) {
        try {
            List<BacklogInfo> backlogInfos = (List<BacklogInfo>) data;
//            mTodayCountTv.setText("("+backlogInfos.size()+")");

            if( selectType == BacklogInfo.FINISHED )
                mFinishedTv.setText(backlogInfos.size()+"");
            else if( selectType == BacklogInfo.UNFINISH )
                mUnFinishTv.setText(backlogInfos.size()+"");
            else
                mOverdueTv.setText(backlogInfos.size()+"");

            mProfileAdapter.setDatas(backlogInfos);
            mProfileAdapter.notifyDataSetChanged();

        }catch (Exception e){
            Toast.makeText(this, (String) data, Toast.LENGTH_SHORT).show();
        }
    }

    //点击左边按钮
    @OnClick(R.id.rl_profile_left)
    void clickLeft(){
        String month = mMonthTv.getText().toString();

        StringTokenizer tokenizer = new StringTokenizer(month, "月");

        int monthValue = Integer.parseInt(tokenizer.nextToken());

        if( monthValue == 1 )
            monthValue = 12;
        else
            monthValue--;

        mMonthTv.setText(monthValue + "月");

        String time = TimeUtil.getCurrentYearInString();
        time += "年" + mMonthTv.getText();

            presenter.getProfileInfoByMonth(selectType, time, data -> setData(data));
    }

    @OnClick(R.id.rl_profile_right)
    void clickRight(){
        String month = mMonthTv.getText().toString();

        StringTokenizer tokenizer = new StringTokenizer(month, "月");

        int monthValue = Integer.parseInt(tokenizer.nextToken());

        if( monthValue == 12 )
            monthValue = 1;
        else
            monthValue++;

        mMonthTv.setText(monthValue + "月");

        String time = TimeUtil.getCurrentYearInString();
        time += "年" + mMonthTv.getText();

        presenter.getProfileInfoByMonth(selectType, time, data -> setData(data));
    }

    /**
     * 点击已完成的任务状态
     */
    @OnClick(R.id.ll_profile_finished)
    void clickFinished(){

        resetStatue();

        selectType = BacklogInfo.FINISHED;

        mFinishedLl.setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
        mFinishedIv.setImageResource(R.mipmap.ic_profile_finish_select);
        mFinishedTv.setTextColor(getResources().getColor(R.color.white));

        String time = TimeUtil.getCurrentYearInString();
        time += "年" + mMonthTv.getText();
        presenter.getProfileInfoByMonth(selectType, time, data -> setData(data));
    }

    /**
     * 点击正在进行的任务状态
     */
    @OnClick(R.id.ll_profile_unfinish)
    void clickUnFinish(){

        resetStatue();

        selectType = BacklogInfo.UNFINISH;

        mUnFinishLl.setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
        mUnFinishIv.setImageResource(R.mipmap.ic_profile_unfinish_select);
        mUnFinishTv.setTextColor(getResources().getColor(R.color.white));

        String time = TimeUtil.getCurrentYearInString();
        time += "年" + mMonthTv.getText();
        presenter.getProfileInfoByMonth(selectType, time, data -> setData(data));
    }

    /**
     * 点击没完成的任务状态
     */
    @OnClick(R.id.ll_profile_overdue)
    void clickOverdue(){

        resetStatue();

        selectType = BacklogInfo.OVERDUE;

        mOverdueLl.setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
        mOverdueIv.setImageResource(R.mipmap.ic_profile_overdue_select);
        mOverdueTv.setTextColor(getResources().getColor(R.color.white));

        String time = TimeUtil.getCurrentYearInString();
        time += "年" + mMonthTv.getText();
        presenter.getProfileInfoByMonth(selectType, time, data -> setData(data));
    }

    /**
     * 重置状态
     */
    private void resetStatue() {
        mFinishedLl.setBackgroundColor(getResources().getColor(R.color.default_background));
        mFinishedIv.setImageResource(R.mipmap.ic_profile_finish);
        mFinishedTv.setTextColor(getResources().getColor(R.color.black));

        mUnFinishLl.setBackgroundColor(getResources().getColor(R.color.default_background));
        mUnFinishIv.setImageResource(R.mipmap.ic_profile_doing);
        mUnFinishTv.setTextColor(getResources().getColor(R.color.black));

        mOverdueLl.setBackgroundColor(getResources().getColor(R.color.default_background));
        mOverdueIv.setImageResource(R.mipmap.ic_profile_unfinish);
        mOverdueTv.setTextColor(getResources().getColor(R.color.black));
    }

}
