package com.young.planhelper.mvp.plan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.young.planhelper.R;
import com.young.planhelper.constant.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/28  20:28
 */


public class PlanSelectItemView extends LinearLayout{

    private Context mContext;
    @BindView(R.id.civ_plan_select_item)
    CircleImageView mSelectCiv;

    public PlanSelectItemView(Context context) {
        super(context);
        this.mContext = context;
    }

    public PlanSelectItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public PlanSelectItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(String data, int position) {
        if( position == 0 )
            mSelectCiv.setImageResource(R.mipmap.chat_add_normal);
        else
            Glide.with(mContext)
                .load(AppConstant.RECOUSE_IMAGE_URL + data)
                .into(mSelectCiv);
    }
}
