package com.young.planhelper.mvp.plan.view.planitem.thirditem;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/27  12:40
 */


public class PlanThirdItemView extends LinearLayout {

    @BindView(R.id.tv_plan_third_item_title)
    TextView mTitleTv;

    private PlanThirdItemInfo mData;

    public PlanThirdItemView(Context context) {
        super(context);
    }

    public PlanThirdItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlanThirdItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(PlanThirdItemInfo data) {
        this.mData = data;
        mTitleTv.setText(data.getTitle());
    }

    public long getPlanThirdItemInfoId(){
        return mData.getPlanThridItemInfoId();
    }


}
