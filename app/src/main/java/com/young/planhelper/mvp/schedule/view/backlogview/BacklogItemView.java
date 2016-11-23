package com.young.planhelper.mvp.schedule.view.backlogview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.model.Text;
import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/5  21:38
 */


public class BacklogItemView extends LinearLayout{

    @BindView(R.id.tv_backlog_item_content)
    TextView mContentTv;

    @BindView(R.id.tv_backlog_item_time)
    TextView mTimeTv;

    @BindView(R.id.tv_backlog_item_location)
    TextView mLocationTv;

    public BacklogItemView(Context context) {
        super(context);
    }

    public BacklogItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BacklogItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(BacklogInfo data) {
        mContentTv.setText(data.getContent());
        mLocationTv.setText(data.getLocation());
        mTimeTv.setText(data.getTime());
    }
}
