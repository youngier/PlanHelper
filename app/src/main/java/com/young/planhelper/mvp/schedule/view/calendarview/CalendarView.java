package com.young.planhelper.mvp.schedule.view.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.CalendarInfo;
import com.young.planhelper.util.DisplayUtil;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/3  22:24
 */


public class CalendarView extends LinearLayout{

    private RecyclerView mRecyclerView;
    private CalendarAdapter adapter;
    private Context mContext;

    private View mView;

    //开始触碰屏幕的坐标
    private int startX;
    private int startY;
    private int height;

    private boolean isScroll;
    //滑动距离
    private int des;
    private int scrollH;

    public CalendarView(Context context) {
        super(context);
        this.mContext = context;
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mView = findViewById(R.id.calendarview);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview);
        adapter = new CalendarAdapter(mContext, null);
        mRecyclerView.setAdapter(adapter);

        // 如果我们想要一个GridView形式的RecyclerView，那么在LayoutManager上我们就要使用GridLayoutManager
        // 实例化一个GridLayoutManager，列数为3
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new CalendarItemDecoration());
    }

    public void setData(CalendarInfo calendarInfo){
        adapter.setData(calendarInfo.getDayInfos());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(getLayoutParams());
        lp.setMargins(0, -560, 0, 0);
        setLayoutParams(lp);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                startX = (int) ev.getX();
//                startY = (int) ev.getY();
//                isScroll = true;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int dX = (int) (ev.getX() - startX);
//                int dY = (int) (ev.getY() - startY);
//                if(Math.abs(dX)>Math.abs(dY)) {//左右滑动
//                    return false;
//                }else { //上下滑动
////                    height += dY;
//                    scrollH = dY - scrollH;
//                    Log.e("TAG", scrollH+"");
//                    scrollBy(0, scrollH);
//                    des = dY;
//                    return false;
//                }
//            case MotionEvent.ACTION_UP:
//                isScroll = false;
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offY = (int) event.getY() - startY;
                Log.e("TAG", offY+"");
                if( offY > 0 )
                    offsetTopAndBottom(getTop() >= 60?0:offY);
                else
                    offsetTopAndBottom(getTop() <= -510?0:offY);
        }
        return true;
    }
}
