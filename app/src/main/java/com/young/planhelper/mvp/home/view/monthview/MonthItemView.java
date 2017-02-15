package com.young.planhelper.mvp.home.view.monthview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.bean.DayInfo;
import com.young.planhelper.mvp.schedule.model.bean.WeekInfo;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;

import java.sql.Time;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/1  21:30
 */


public class MonthItemView extends LinearLayout{

    private static final String TAG = "MonthItemView";
    private TextView mDayTv;
    private ImageView mHaveIv;
    private DayInfo mDayInfo = null;
    private boolean isHave;

    public MonthItemView(Context context) {
        super(context);
    }

    public MonthItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MonthItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = findViewById(R.id.mv_view_item);
        mDayTv = (TextView) view.findViewById(R.id.tv_day);
        mHaveIv = (ImageView) view.findViewById(R.id.iv_month_item_have);
    }

    public void setData(DayInfo data) {
        this.mDayInfo = data;
        mDayTv.setText(data.getDay());
        if( TimeUtil.getCurrentDateInString1().equals(data.getDate()) ){
            mDayTv.setTextColor(getResources().getColor(R.color.cyan_week_view_current));
            this.setBackgroundColor(getResources().getColor(R.color.white));

            if( data.isHave() ) {
                mHaveIv.setImageResource(R.mipmap.ic_week_view_have);
                mHaveIv.setVisibility(VISIBLE);
            }
            else
                mHaveIv.setVisibility(INVISIBLE);

        }else{
            mDayTv.setTextColor(getResources().getColor(R.color.white));
            this.setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));

            if( data.isHave() ) {
                mHaveIv.setImageResource(R.mipmap.ic_week_view_have_current);
                mHaveIv.setVisibility(VISIBLE);
            }
            else
                mHaveIv.setVisibility(INVISIBLE);
        }


    }

    public DayInfo getDayInfo() {
        return mDayInfo;
    }

    public void setIsHave(boolean isHave) {
        this.isHave = isHave;

    }
}
