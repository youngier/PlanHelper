package com.young.planhelper.mvp.schedule.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.TimeUtils;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.litao.android.lib.entity.PhotoEntry;
import com.young.planhelper.R;
import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.BaseOtherActivity;
import com.young.planhelper.mvp.common.photo.EventEntry;
import com.young.planhelper.mvp.common.photo.SelectPhotoActivity;
import com.young.planhelper.mvp.schedule.model.bean.BacklogImageInfo;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.presenter.IScheduleAddPresenter;
import com.young.planhelper.mvp.schedule.presenter.ScheduleAddPresenter;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.DateTimePickDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;

public class ScheduleAddActivity extends BaseOtherActivity{

    @BindView(R.id.vs)
    ViewStub mViewStub;
    private MapView mMapView;

    @BindView(R.id.et_content)
    EditText mContentEt;

    @BindView(R.id.iv_time)
    TextView mTimeTv;

    @BindView(R.id.rl_schedule_add_time)
    RelativeLayout mAddTimeRl;
    @BindView(R.id.tv_schedule_add_time)
    TextView mAddTimeTv;

    IScheduleAddPresenter presenter;

    private List<PhotoEntry> photos;

    @Inject
    Realm mRealm;

    @Override
    protected void config() {
        super.config();
        EventBus.getDefault().register(this);
        presenter = new ScheduleAddPresenter(this, this);
    }

    @Override
    protected void initUI() {
        mTimeTv.setText(TimeUtil.getCurrentDateTimeInString());
    }

    @Override
    public int getLayout() {
        return R.layout.activity_add_schedule;
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

    @Override
    protected void setTheme() {
        super.setTheme();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }


    /**
     * 显示位置地图
     */
    @OnClick(R.id.ll_location)
    void showMapView() {
        View view = mViewStub.inflate();
        mMapView = (MapView) view.findViewById(R.id.mapview);
        mMapView.getMap();
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
                mAddTimeTv.setText(mTimeTv.getText().toString());
                mAddTimeRl.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 取消时间
     */
    @OnClick(R.id.iv_schedule_add_time)
    void cancelAddTime(){
        mAddTimeRl.setVisibility(View.GONE);
    }

    /**
     * 选择图片
     */
    @OnClick(R.id.ll_photo)
    void selectPhoto(){
        startActivity(new Intent(this, SelectPhotoActivity.class));
        EventBus.getDefault().postSticky(new EventEntry(photos,EventEntry.SELECTED_PHOTOS_ID));
    }

    /**
     * 保存任务
     */
    @OnClick(R.id.tv_finish)
    void saveBacklog(){
        BacklogInfo backlogInfo = new BacklogInfo();
        backlogInfo.setBacklogInfoId(TimeUtil.getCurrentTimeInLong());
        try {
          //  backlogInfo.setTime(TimeUtil.dateToStamp(mTimeTv.getText().toString()));
            LogUtil.eLog(TimeUtil.dateToStamp(mTimeTv.getText().toString())+"dede");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        backlogInfo.setStatue(BacklogInfo.UNFINISH);
        backlogInfo.setLocation("");
        backlogInfo.setContent(mContentEt.getText().toString());
        backlogInfo.setBacklogImageInfos(new RealmList<>());
        presenter.addBacklogInfo(backlogInfo, data -> {});
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if( mMapView!=null )
            mMapView.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {

        EventBus.getDefault().unregister(this);

        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        if( mMapView!=null )
            mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        if( mMapView!=null )
            mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        if( mMapView!=null )
            mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        if( mMapView!=null )
            mMapView.onSaveInstanceState(outState);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(EventEntry entries){
        if (entries.id == EventEntry.RECEIVED_PHOTOS_ID) {
//            mAdapter.reloadList(entries.photos);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photoMessageEvent(PhotoEntry entry){
//        mAdapter.appendPhoto(entry);
    }
}
