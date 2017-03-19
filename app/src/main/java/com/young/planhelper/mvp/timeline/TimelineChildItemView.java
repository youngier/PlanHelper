package com.young.planhelper.mvp.timeline;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.model.Text;
import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.timeline.model.bean.TimelineInfo;
import com.young.planhelper.util.TimeUtil;

import java.nio.Buffer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/22  13:41
 */


public class TimelineChildItemView extends LinearLayout{

    private Context mContext;

    @BindView(R.id.tv_timeline_child_item_time)
    TextView mTimeTv;

    @BindView(R.id.tv_timeline_child_item_content)
    TextView mContentTv;

    @BindView(R.id.tv_timeline_child_item_location)
    TextView mLocationTv;

    @BindView(R.id.ll_timeline_child_item_tag)
    LinearLayout mTagLl;

    @BindView(R.id.iv_timeline_child_item_person)
    ImageView mPersonIv;

    private BacklogInfo mBacklogInfo;

    public TimelineChildItemView(Context context) {
        super(context);
    }

    public TimelineChildItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public TimelineChildItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(BacklogInfo backlogInfo) {
        this.mBacklogInfo = backlogInfo;

        mContentTv.setText(backlogInfo.getContent());
        mLocationTv.setText(backlogInfo.getLocation());
        mTimeTv.setText(TimeUtil.getTime2(backlogInfo.getToTime()));

        switch (backlogInfo.getStatue()){
            case BacklogInfo.FINISHED:
                mTagLl.setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
                break;
            case BacklogInfo.UNFINISH:
                mTagLl.setBackgroundColor(getResources().getColor(R.color.puple_backlog_statue));
                break;
            case BacklogInfo.OVERDUE:
                mTagLl.setBackgroundColor(getResources().getColor(R.color.orange_backlog_statue));
                break;
        }

    }
}
