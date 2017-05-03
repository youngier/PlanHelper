package com.young.planhelper.mvp.schedule.view.backlogview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps2d.model.Text;
import com.young.planhelper.R;
import com.young.planhelper.mvp.plan.view.PlanSelectAdapter;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.util.DensityUtil;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/5  21:38
 */


public class BacklogItemView extends LinearLayout{

    private Context mContext;
    @BindView(R.id.tv_backlog_item_content)
    TextView mContentTv;

    @BindView(R.id.tv_backlog_item_time)
    TextView mTimeTv;

    @BindView(R.id.tv_backlog_item_location)
    TextView mLocationTv;

    @BindView(R.id.rv_backlog_item_person)
    RecyclerView mPersonRv;

    @BindView(R.id.iv_backlog_item_finish)
    ImageView mFinishIv;

    private PersonShowAdapter adapter;

    private long backlogInfoId;

    public BacklogItemView(Context context) {
        super(context);
        mContext = context;
    }

    public BacklogItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public BacklogItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        mPersonRv.setEnabled(false);
    }

    public void setData(BacklogInfo data) {

        this.backlogInfoId = data.getBacklogInfoId();

        mContentTv.setText(data.getContent());
        mLocationTv.setText(data.getLocation());
        mTimeTv.setText(TimeUtil.getTime2(data.getToTime()));

        if( data.getStatue() == BacklogInfo.FINISHED )
            mFinishIv.setVisibility(VISIBLE);
        else
            mFinishIv.setVisibility(GONE);

        StringTokenizer tokenizer = new StringTokenizer(data.getMembers(), ",");
        List<String> iconList = new ArrayList<>();
        int len = tokenizer.countTokens();
        for (int i = 0; i < len; i++) {
            iconList.add(tokenizer.nextToken());
        }

        adapter = new PersonShowAdapter(mContext, iconList);

        mPersonRv.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        mPersonRv.getLayoutParams().width = (int) (len * DensityUtil.dipToPixels(mContext, 30)) + len * DensityUtil.dipToPixels(mContext, 2);

        LinearLayoutManager lm = new LinearLayoutManager(mContext);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mPersonRv.setLayoutManager(lm);
    }

    public long getBacklogInfoId() {
        return backlogInfoId;
    }
}
