package com.young.planhelper.mvp.plan.view.planitem.seconditem;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.plan.model.bean.PlanOperationInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/27  20:49
 */


public class PlanRecordItemView extends LinearLayout{

    @BindView(R.id.tv_plan_record_title)
    TextView mTitleTv;

    private PlanOperationInfo mData;

    public PlanRecordItemView(Context context) {
        super(context);
    }

    public PlanRecordItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlanRecordItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(PlanOperationInfo data) {
        this.mData = data;
        mTitleTv.setText(data.getTime() + " " + data.getName() + ": "+ data.getContent());
    }

    public long getPlanSecondItenInfoId(){
        return mData.getPlanSecondItemInfoId();
    }

}
