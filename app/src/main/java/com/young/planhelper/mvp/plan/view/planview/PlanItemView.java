package com.young.planhelper.mvp.plan.view.planview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/23  19:46
 */


public class PlanItemView extends CardView {

    @BindView(R.id.tv_plan_item_title)
    TextView mTitleTv;
    private PlanInfo mData;


    public PlanItemView(Context context) {
        super(context);
    }

    public PlanItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlanItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(PlanInfo data) {
        this.mData = data;
        mTitleTv.setText(data.getTitle());
    }

    public long getPlanInfoId(){
        return mData.getPlanInfoId();
    }
}
