package com.young.planhelper.mvp.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.MapView;
import com.litao.android.lib.entity.PhotoEntry;
import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseOtherActivity;
import com.young.planhelper.mvp.common.photo.EventEntry;
import com.young.planhelper.mvp.common.photo.SelectPhotoActivity;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.presenter.IScheduleAddPresenter;
import com.young.planhelper.mvp.schedule.presenter.IScheduleDetailPresenter;
import com.young.planhelper.mvp.schedule.presenter.ScheduleAddPresenter;
import com.young.planhelper.mvp.schedule.presenter.ScheduleDetailPresenter;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.DateTimePickDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;

public class ScheduleDetailActivity extends BaseOtherActivity {

    @BindView(R.id.tv_content)
    TextView mContentTv;

    @BindView(R.id.tv_schedule_detail_location)
    TextView mDetailLocationTv;

    @BindView(R.id.tv_schedule_detail_time)
    TextView mDetailTimeTv;

    IScheduleDetailPresenter presenter;

    private List<PhotoEntry> photos;

    @Inject
    Realm mRealm;

    @Override
    protected void config() {
        super.config();
        presenter = new ScheduleDetailPresenter(this, this);
    }

    @Override
    protected void initUI() {
        long backlogInfoId = getIntent().getLongExtra("backlogInfoId", 0);
        presenter.getBacklogInfoDetail(backlogInfoId, data -> setData(data));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_schedule_detail;
    }

    /**
     * 变化后显示的内容
     * @param data
     */
    @Override
    public void setData(Object data) {
        try{
            BacklogInfo backlogInfo = (BacklogInfo) data;
            mContentTv.setText(backlogInfo.getContent());
            mDetailTimeTv.setText(TimeUtil.getTime(backlogInfo.getToTime()));
            mDetailLocationTv.setText(backlogInfo.getLocation());

        }catch (Exception e){
            Toast.makeText(this, (String)data, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void setTheme() {
        super.setTheme();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

}
