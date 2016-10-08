package com.young.planhelper.mvp.schedule.view.backlogview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.young.planhelper.mvp.schedule.model.BacklogInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/5  21:38
 */


public class BacklogItemView extends LinearLayout{

    private BacklogInfo data;

    public BacklogItemView(Context context) {
        super(context);
    }

    public BacklogItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BacklogItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setData(BacklogInfo data) {
        this.data = data;
    }
}
