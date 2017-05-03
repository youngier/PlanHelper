package com.young.planhelper.mvp.home.view.weekview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.bean.DayInfo;
import com.young.planhelper.mvp.schedule.model.bean.WeekInfo;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;

import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/1  21:30
 */


public class WeekItemView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "WeekItemView";

    private TextView mSunShowTv, mMonShowTv,mTuesShowTv,mWednesShowTv,mThursShowTv,mFriShowTv,mSaturShowTv;

    private TextView mSunTv, mMonTv,mTuesTv,mWednesTv,mThursTv,mFriTv,mSaturTv;

    private ImageView mSunIv, mMonIv,mTuesIv,mWednesIv,mThursIv,mFriIv,mSaturIv;

    private LinearLayout mSunLl, mMonLl,mTuesLl,mWednesLl,mThursLl,mFriLl,mSaturLl;

    private ImageView[] ivs = new ImageView[7];

    private TextView[] tvs = new TextView[7];

    private TextView[] showTvs = new TextView[7];

    private LinearLayout[] lls = new LinearLayout[7];

    private OnClickListener onClickListener;

    private List<DayInfo> mDayInfo;

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

        mSunShowTv = (TextView) view.findViewById(R.id.tv_sun_show);
        mMonShowTv = (TextView) view.findViewById(R.id.tv_mon_show);
        mTuesShowTv = (TextView) view.findViewById(R.id.tv_tues_show);
        mWednesShowTv = (TextView) view.findViewById(R.id.tv_wednes_show);
        mThursShowTv = (TextView) view.findViewById(R.id.tv_thurs_show);
        mFriShowTv = (TextView) view.findViewById(R.id.tv_fri_show);
        mSaturShowTv = (TextView) view.findViewById(R.id.tv_satur_show);

        showTvs[0] = mSunShowTv;
        showTvs[1] = mMonShowTv;
        showTvs[2] = mTuesShowTv;
        showTvs[3] = mWednesShowTv;
        showTvs[4] = mThursShowTv;
        showTvs[5] = mFriShowTv;
        showTvs[6] = mSaturShowTv;

        mSunTv = (TextView) view.findViewById(R.id.tv_sun);
        mMonTv = (TextView) view.findViewById(R.id.tv_mon);
        mTuesTv = (TextView) view.findViewById(R.id.tv_tues);
        mWednesTv = (TextView) view.findViewById(R.id.tv_wednes);
        mThursTv = (TextView) view.findViewById(R.id.tv_thurs);
        mFriTv = (TextView) view.findViewById(R.id.tv_fri);
        mSaturTv = (TextView) view.findViewById(R.id.tv_satur);

        tvs[0] = mSunTv;
        tvs[1] = mMonTv;
        tvs[2] = mTuesTv;
        tvs[3] = mWednesTv;
        tvs[4] = mThursTv;
        tvs[5] = mFriTv;
        tvs[6] = mSaturTv;

        mSunIv = (ImageView) view.findViewById(R.id.iv_sun);
        mMonIv = (ImageView) view.findViewById(R.id.iv_mon);
        mTuesIv = (ImageView) view.findViewById(R.id.iv_tues);
        mWednesIv = (ImageView) view.findViewById(R.id.iv_wednes);
        mThursIv = (ImageView) view.findViewById(R.id.iv_thurs);
        mFriIv = (ImageView) view.findViewById(R.id.iv_fri);
        mSaturIv = (ImageView) view.findViewById(R.id.iv_satur);

        ivs[0] = mSunIv;
        ivs[1] = mMonIv;
        ivs[2] = mTuesIv;
        ivs[3] = mWednesIv;
        ivs[4] = mThursIv;
        ivs[5] = mFriIv;
        ivs[6] = mSaturIv;

        mSunLl = (LinearLayout) view.findViewById(R.id.ll_sun);
        mMonLl = (LinearLayout) view.findViewById(R.id.ll_mon);
        mTuesLl = (LinearLayout) view.findViewById(R.id.ll_tues);
        mWednesLl = (LinearLayout) view.findViewById(R.id.ll_wednes);
        mThursLl = (LinearLayout) view.findViewById(R.id.ll_thurs);
        mFriLl = (LinearLayout) view.findViewById(R.id.ll_fri);
        mSaturLl = (LinearLayout) view.findViewById(R.id.ll_satur);

        lls[0] = mSunLl;
        lls[1] = mMonLl;
        lls[2] = mTuesLl;
        lls[3] = mWednesLl;
        lls[4] = mThursLl;
        lls[5] = mFriLl;
        lls[6] = mSaturLl;

    }

    public void setData(List<DayInfo> dayInfo) {

        this.mDayInfo = dayInfo;

        mSunTv.setText(dayInfo.get(0).getDay());
        mMonTv.setText(dayInfo.get(1).getDay());
        mTuesTv.setText(dayInfo.get(2).getDay());
        mWednesTv.setText(dayInfo.get(3).getDay());
        mThursTv.setText(dayInfo.get(4).getDay());
        mFriTv.setText(dayInfo.get(5).getDay());
        mSaturTv.setText(dayInfo.get(6).getDay());

        mSunLl.setOnClickListener(this);
        mMonLl.setOnClickListener(this);
        mTuesLl.setOnClickListener(this);
        mWednesLl.setOnClickListener(this);
        mThursLl.setOnClickListener(this);
        mFriLl.setOnClickListener(this);
        mSaturLl.setOnClickListener(this);

        for(int i=0; i<7; i++){
            if( dayInfo.get(i).getDay().equals(TimeUtil.getDay()) ){
                lls[i].setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
                tvs[i].setTextColor(getResources().getColor(R.color.white));
                showTvs[i].setTextColor(getResources().getColor(R.color.white));
            }else{
                lls[i].setBackgroundColor(getResources().getColor(R.color.gray_week_view_bg));
                tvs[i].setTextColor(getResources().getColor(R.color.black_week_view));
                showTvs[i].setTextColor(getResources().getColor(R.color.gray_week_view_text));
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_sun:
                resetStatue();
                lls[0].setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
                tvs[0].setTextColor(getResources().getColor(R.color.white));
                showTvs[0].setTextColor(getResources().getColor(R.color.white));
                onClickListener.onClick(tvs[0].getText().toString());
                if( mDayInfo.get(0).isHave() )
                    ivs[0].setImageResource(R.mipmap.ic_week_view_have_current);
                break;
            case R.id.ll_mon:
                resetStatue();
                lls[1].setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
                tvs[1].setTextColor(getResources().getColor(R.color.white));
                showTvs[1].setTextColor(getResources().getColor(R.color.white));
                onClickListener.onClick(tvs[1].getText().toString());
                if( mDayInfo.get(1).isHave() )
                    ivs[1].setImageResource(R.mipmap.ic_week_view_have_current);
                break;
            case R.id.ll_tues:
                resetStatue();
                lls[2].setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
                tvs[2].setTextColor(getResources().getColor(R.color.white));
                showTvs[2].setTextColor(getResources().getColor(R.color.white));
                onClickListener.onClick(tvs[2].getText().toString());
                if( mDayInfo.get(2).isHave() )
                    ivs[2].setImageResource(R.mipmap.ic_week_view_have_current);
                break;
            case R.id.ll_wednes:
                resetStatue();
                lls[3].setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
                tvs[3].setTextColor(getResources().getColor(R.color.white));
                showTvs[3].setTextColor(getResources().getColor(R.color.white));
                onClickListener.onClick(tvs[3].getText().toString());
                if( mDayInfo.get(3).isHave() )
                    ivs[3].setImageResource(R.mipmap.ic_week_view_have_current);
                break;
            case R.id.ll_thurs:
                resetStatue();
                lls[4].setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
                tvs[4].setTextColor(getResources().getColor(R.color.white));
                showTvs[4].setTextColor(getResources().getColor(R.color.white));
                onClickListener.onClick(tvs[4].getText().toString());
                if( mDayInfo.get(4).isHave() )
                    ivs[4].setImageResource(R.mipmap.ic_week_view_have_current);
                break;
            case R.id.ll_fri:
                resetStatue();
                lls[5].setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
                tvs[5].setTextColor(getResources().getColor(R.color.white));
                showTvs[5].setTextColor(getResources().getColor(R.color.white));
                onClickListener.onClick(tvs[5].getText().toString());
                if( mDayInfo.get(5).isHave() )
                    ivs[5].setImageResource(R.mipmap.ic_week_view_have_current);
                break;
            case R.id.ll_satur:
                resetStatue();
                lls[6].setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
                tvs[6].setTextColor(getResources().getColor(R.color.white));
                showTvs[6].setTextColor(getResources().getColor(R.color.white));
                onClickListener.onClick(tvs[6].getText().toString());
                if( mDayInfo.get(6).isHave() )
                    ivs[6].setImageResource(R.mipmap.ic_week_view_have_current);
                break;
        }
    }

    private void resetStatue() {
        for(int i=0; i<7; i++){
            lls[i].setBackgroundColor(getResources().getColor(R.color.gray_week_view_bg));
            tvs[i].setTextColor(getResources().getColor(R.color.black_week_view));
            showTvs[i].setTextColor(getResources().getColor(R.color.gray_week_view_text));

            if( mDayInfo.get(i).isHave() )
                ivs[i].setImageResource(R.mipmap.ic_week_view_have);

        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setTaskDay(List<String> taskDay){

        int k=0;

        for(int i=0; i<7; i++){
            String date = mDayInfo.get(i).getDate();
            StringTokenizer tokenizer = new StringTokenizer(date, "-");
            String year = tokenizer.nextToken();
            String month = tokenizer.nextToken();
            String day = tokenizer.nextToken();

            date = year+"-";
            if( month.length() == 1 )
                date += "0" + month + "-";
            else
                date += month + "-";

            if( day.length() == 1 )
                date += "0" + day;
            else
                date += day;

            boolean isHave = false;

            if( taskDay != null )
                for (int j=0; j+k < taskDay.size(); j++){
                    LogUtil.eLog("比较：date:"+date+", taskDay"+taskDay.get(j+k)+", j:"+j+", k:"+k);
                    if( date.equals(taskDay.get(j+k)) ){
                        k++;
                        mDayInfo.get(i).setHave(true);
                        ivs[i].setVisibility(VISIBLE);
                        if( TimeUtil.getCurrentDateInString1().equals(date) )
                            ivs[i].setImageResource(R.mipmap.ic_week_view_have_current);
                        else
                            ivs[i].setImageResource(R.mipmap.ic_week_view_have);

                        break;
                    }else {
                        ivs[i].setVisibility(INVISIBLE);
                        k=0;
                    }

                }

        }

    }

    public void setSelectItem(DayInfo dayInfo) {
        resetStatue();
        for(int i=0; i<7; i++){
            if( mDayInfo.get(i).getDay().equals(dayInfo.getDay()) ){
                lls[i].setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
                tvs[i].setTextColor(getResources().getColor(R.color.white));
                showTvs[i].setTextColor(getResources().getColor(R.color.white));
                onClickListener.onClick(tvs[i].getText().toString());
                if( mDayInfo.get(i).isHave() ) {
                    ivs[i].setImageResource(R.mipmap.ic_week_view_have_current);
                    ivs[i].setVisibility(VISIBLE);
                }
                else
                    ivs[i].setVisibility(INVISIBLE);
            }
        }
    }

    public void clear() {
        mDayInfo = null;
    }


    public interface OnClickListener{
        void onClick(String day);
    }
}
