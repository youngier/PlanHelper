package com.young.planhelper.mvp.plan.view.planitem.seconditem;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/24  14:59
 */


public class PlanSecondItemView extends LinearLayout{

    @BindView(R.id.tv_plan_second_item_title)
    TextView mTitleTv;

    private PlanSecondItemInfo mData;

    public PlanSecondItemView(Context context) {
        super(context);
    }

    public PlanSecondItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlanSecondItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(PlanSecondItemInfo data) {
        this.mData = data;
        mTitleTv.setText(data.getTitle());
    }

    public long getPlanSecondItemInfoId(){
        return mData.getPlanSecondItemInfoId();
    }


}
