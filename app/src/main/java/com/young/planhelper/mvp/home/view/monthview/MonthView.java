package com.young.planhelper.mvp.home.view.monthview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.bean.DayInfo;
import com.young.planhelper.mvp.schedule.model.bean.WeekInfo;

import java.util.List;


/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/21  11:46
 */


public class MonthView extends LinearLayout {

    private static final String TAG = "MonthView";
    private RecyclerView mRecyclerView;
    private Context mContext;
    private MonthAdapter adapter;

    private OnClickListener onClickListener;

    public MonthView(Context context) {
        super(context);
        this.mContext = context;
    }

    public MonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public MonthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = findViewById(R.id.monthview);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_view_month);
        adapter = new MonthAdapter(mContext, null);
        mRecyclerView.setEnabled(false);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 7));
    }

    public void setData(List<DayInfo> datas) {
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
        adapter.setOnClickListener( () -> {
            onClickListener.onClick();
        });

    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onClick();
    }
}
