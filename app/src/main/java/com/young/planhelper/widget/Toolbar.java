package com.young.planhelper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.planhelper.R;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/2  15:10
 */


public class Toolbar extends RelativeLayout {

    public static final int NORMAL = 0;
    public static final int ADD = 1;

    private int mode;

    private ImageView mMenuIv, mAddIv, mContentIv;
    private TextView mContentTv;
    private LinearLayout mContentLl;

    private OnAddClickListener onAddClickListener;
    private OnMenuClickListener onMenuClickListener;


    public Toolbar(Context context) {
        super(context);
    }

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuIv = (ImageView) findViewById(R.id.iv_toolbar_menu);
        mAddIv = (ImageView) findViewById(R.id.iv_toolbar_add);
        mContentTv = (TextView) findViewById(R.id.tv_toolbar_content);
        mContentLl = (LinearLayout) findViewById(R.id.ll_toolbar_content);
        mContentIv = (ImageView) findViewById(R.id.iv_toolbar_content);

        mMenuIv.setOnClickListener(v -> {
            onMenuClickListener.onMenuClick();
        });

        mAddIv.setOnClickListener(v -> {
            onAddClickListener.onAddClick();
        });

    }

    public void setOnAddClickListener(OnAddClickListener onAddClickListener) {
        this.onAddClickListener = onAddClickListener;
    }

    public void setMode(int mode) {
        this.mode = mode;

        switch ( mode ){
            case ADD:
                mContentIv.setVisibility(GONE);
                mMenuIv.setImageResource(R.mipmap.ic_close);
                mAddIv.setImageResource(R.mipmap.ic_confirm);
                break;
            case NORMAL:
                mContentLl.setVisibility(VISIBLE);
                mMenuIv.setImageResource(R.mipmap.ic_home_menu);
                mAddIv.setImageResource(R.mipmap.ic_home_add);
                break;
        }
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }

    public interface OnAddClickListener{
        void onAddClick();
    }

    public interface OnMenuClickListener{
        void onMenuClick();
    }

}
