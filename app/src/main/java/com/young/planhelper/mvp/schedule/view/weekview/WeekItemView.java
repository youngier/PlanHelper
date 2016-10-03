package com.young.planhelper.mvp.schedule.view.weekview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.DayInfo;
import com.young.planhelper.mvp.schedule.model.WeekInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/1  21:30
 */


public class WeekItemView extends LinearLayout{

    private static final String TAG = "WeekItemView";
    private TextView mSunTv, mMonTv,mTuesTv,mWednesTv,mThursTv,mFriTv,mSaturTv;

    public WeekItemView(Context context) {
        super(context);
    }

    public WeekItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeekItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = findViewById(R.id.wv_view_item);
        mSunTv = (TextView) view.findViewById(R.id.tv_sun);
        mMonTv = (TextView) view.findViewById(R.id.tv_mon);
        mTuesTv = (TextView) view.findViewById(R.id.tv_tues);
        mWednesTv = (TextView) view.findViewById(R.id.tv_wednes);
        mThursTv = (TextView) view.findViewById(R.id.tv_thurs);
        mFriTv = (TextView) view.findViewById(R.id.tv_fri);
        mSaturTv = (TextView) view.findViewById(R.id.tv_satur);
    }

    public void setData(WeekInfo data) {
        DayInfo[] dayInfo = data.getDayInfos();
        mSunTv.setText(dayInfo[0].getDay());
        mMonTv.setText(dayInfo[1].getDay());
        mTuesTv.setText(dayInfo[2].getDay());
        mWednesTv.setText(dayInfo[3].getDay());
        mThursTv.setText(dayInfo[4].getDay());
        mFriTv.setText(dayInfo[5].getDay());
        mSaturTv.setText(dayInfo[6].getDay());
    }
}
