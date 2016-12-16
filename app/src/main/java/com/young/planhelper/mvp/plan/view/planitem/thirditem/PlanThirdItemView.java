package com.young.planhelper.mvp.plan.view.planitem.thirditem;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    @BindView(R.id.cb_plan_third_item)
    CheckBox mCb;

    private OnSelectChangeListener onSelectChangeListener;

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

        mCb.setChecked(data.isFinished());
        mCb.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            onSelectChangeListener.onSelectChange(data.getPlanThirdItemInfoId(), isChecked);
        } );
    }

    public long getPlanThirdItemInfoId(){
        return mData.getPlanThirdItemInfoId();
    }

    public void setOnSelectChangeListener(OnSelectChangeListener onSelectChangeListener) {
        this.onSelectChangeListener = onSelectChangeListener;
    }

    public interface OnSelectChangeListener{
        void onSelectChange(long id, boolean isChecked);
    }
}
