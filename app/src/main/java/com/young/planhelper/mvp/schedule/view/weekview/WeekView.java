package com.young.planhelper.mvp.schedule.view.weekview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.bean.WeekInfo;

import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/1  19:43
 */


public class WeekView extends CardView{


    private static final String TAG = "WeekView";
    private RecyclerView mRecyclerView;
    private Context mContext;
    private WeekAdapter adapter;

    /**
     * 默认第一项为0
     */
    private int index;

    //开始触碰屏幕的坐标
    private int startX;
    private int startY;

    //设置滑动间隔，当滑动距离超过这个间隔时进行滚动下一项或者上一项
    private int GAP = 50;

    public WeekView(Context context) {
        super(context);
        this.mContext = context;
    }

    public WeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public WeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = findViewById(R.id.weekview);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_view_week);
        adapter = new WeekAdapter(mContext, null);
        mRecyclerView.setAdapter(adapter);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setData(List<WeekInfo> datas) {
        adapter.setData(datas);
        Log.e(TAG, "内容为"+adapter.getItemCount());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                int dX = (int) (ev.getX() - startX);
                int dY = (int) (ev.getY() - startY);
                mRecyclerView.scrollBy(-dX, 0);
                if(Math.abs(dX)>Math.abs(dY)) {//左右滑动
                    if(dX < 0) {
//                    if (dX < 0 && Math.abs(dX) > GAP) {
                        if (index != adapter.getItemCount() - 1) {
                            index++;
                        }
                    }else {
//                    }else if(dX > 0 && Math.abs(dX) > GAP){
                        if (index != 0) {
                            index--;
                        }
//                    }else{

                    }
                    return true;
                }else { //上下滑动
                    return false;
                }
            case MotionEvent.ACTION_UP:
                mRecyclerView.smoothScrollToPosition(index);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
