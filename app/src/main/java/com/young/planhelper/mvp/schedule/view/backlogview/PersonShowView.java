package com.young.planhelper.mvp.schedule.view.backlogview;

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
 * date:17/3/26  21:54
 */


public class PersonShowView extends LinearLayout{

    private Context mContext;
    @BindView(R.id.civ_person_show)
    CircleImageView mSelectCiv;

    public PersonShowView(Context context) {
        super(context);
        this.mContext = context;
    }

    public PersonShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public PersonShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(String data) {
        Glide.with(mContext)
                .load(AppConstant.RECOUSE_IMAGE_URL + data)
                .into(mSelectCiv);
    }

    public CircleImageView getSelectCiv() {
        return mSelectCiv;
    }
}
