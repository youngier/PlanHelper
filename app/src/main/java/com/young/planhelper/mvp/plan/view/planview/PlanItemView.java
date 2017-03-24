package com.young.planhelper.mvp.plan.view.planview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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

    private Context mContext;
    @BindView(R.id.tv_plan_item_title)
    TextView mTitleTv;

    @BindView(R.id.iv_plan_item_delete)
    ImageView mDeleteIv;

    private PlanInfo mData;

    private OnDeleteListener onDeleteListener;


    public PlanItemView(Context context) {
        super(context);
        mContext = context;
    }

    public PlanItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public PlanItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(PlanInfo data, boolean isOnDelete) {
        this.mData = data;
        mTitleTv.setText(data.getTitle());

        mDeleteIv.setOnClickListener( v -> onDeleteListener.onDelete(mData) );

        if( isOnDelete ){
            Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);

            this.startAnimation(shake);
            shake.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mDeleteIv.setVisibility(VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }

        else
            mDeleteIv.setVisibility(GONE);
    }


    public PlanInfo getPlanInfo() {
        return mData;
    }

    public OnDeleteListener getOnDeleteListener() {
        return onDeleteListener;
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public interface OnDeleteListener{
        void onDelete(PlanInfo planInfo);
    }
}
