package com.young.planhelper.mvp.timeline;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.timeline.model.bean.TimelineInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/22  14:17
 */


public class TimelineGroupItemView extends LinearLayout{

    @BindView(R.id.tv_timeline_group_item_time)
    TextView mTimeTv;

    private TimelineInfo mTimelineInfo;

    public TimelineGroupItemView(Context context) {
        super(context);
    }

    public TimelineGroupItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public TimelineGroupItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(TimelineInfo timelineInfo) {
        this.mTimelineInfo = timelineInfo;
        mTimeTv.setText(timelineInfo.getDate());
    }
}
