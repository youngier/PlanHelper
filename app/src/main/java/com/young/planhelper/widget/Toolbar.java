package com.young.planhelper.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/2  15:10
 */


public class Toolbar extends RelativeLayout {

    public static final int NORMAL = 0;
    public static final int ADD = 1;
    public static final int TIMELINE = 2;
    public static final int PROFILE = 3;
    public static final int PLAN = 4;
    public static final int REGISTER = 5;
    public static final int FRIEND = 6;
    public static final int SELECT = 7;
    public static final int BACK = 8;


    private int mode;

    private ImageView mMenuIv, mRightIv, mContentIv;
    private TextView mContentTv;
    private LinearLayout mContentLl;

    private OnRightClickListener onRightClickListener;
    private OnMenuClickListener onMenuClickListener;
    private OnDateClickListener onDateClickListener;

    private PopupWindow mPopupWindow;

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
        mRightIv = (ImageView) findViewById(R.id.iv_toolbar_add);
        mContentTv = (TextView) findViewById(R.id.tv_toolbar_content);
        mContentLl = (LinearLayout) findViewById(R.id.ll_toolbar_content);
        mContentIv = (ImageView) findViewById(R.id.iv_toolbar_content);

        mMenuIv.setOnClickListener(v -> {
            onMenuClickListener.onMenuClick();
        });

        mRightIv.setOnClickListener(v -> {
            onRightClickListener.onRightClick();
        });

        mContentLl.setOnClickListener(v -> {
            onDateClickListener.onDateClick();
        });

    }

    public void setOnAddClickListener(OnRightClickListener onRightClickListener) {
        this.onRightClickListener = onRightClickListener;
    }

    public void setMode(int mode) {
        this.mode = mode;

        switch ( mode ){
            case ADD:
                mContentIv.setVisibility(GONE);
                mMenuIv.setImageResource(R.mipmap.ic_close);
                mRightIv.setImageResource(R.mipmap.ic_confirm);
                break;
            case NORMAL:
                mContentIv.setVisibility(VISIBLE);
                mMenuIv.setImageResource(R.mipmap.ic_home_menu);
                mRightIv.setImageResource(R.mipmap.ic_home_add);
                break;
            case TIMELINE:
                mContentIv.setVisibility(GONE);
                mContentLl.setOnClickListener(null);
                mRightIv.setImageResource(R.mipmap.ic_more);
                mRightIv.setOnClickListener(v -> {
                    showPopupWindow();
                });
                break;
            case PROFILE:
                mContentIv.setVisibility(GONE);
                mContentLl.setOnClickListener(null);
                mRightIv.setVisibility(GONE);
                break;
            case PLAN:
            case FRIEND:
                mContentIv.setVisibility(GONE);
                mContentLl.setOnClickListener(null);
                mRightIv.setImageResource(R.mipmap.ic_home_add);
                break;
            case REGISTER:
            case SELECT:
                mContentIv.setVisibility(GONE);
                mContentLl.setOnClickListener(null);
                mRightIv.setImageResource(0);
                mMenuIv.setImageResource(R.mipmap.ic_close);
                break;
            case BACK:
                mContentIv.setVisibility(GONE);
                mContentLl.setOnClickListener(null);
                mRightIv.setImageResource(0);
                mMenuIv.setImageResource(R.mipmap.ic_back);
                break;
        }
    }

    private void showPopupWindow() {
        View contentView = getPopupWindowContentView();
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        // popupWindow.showAsDropDown(mButton2);  // 默认在mButton2的左下角显示
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int xOffset = mRightIv.getWidth() / 2 - contentView.getMeasuredWidth() / 2;
        mPopupWindow.showAsDropDown(mRightIv, xOffset, 0);    // 在mButton2的中间显示
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }

    public void setOnDateClickListener(OnDateClickListener onDateClickListener) {
        this.onDateClickListener = onDateClickListener;
    }

    public String getDate() {
        return mContentTv.getText().toString();
    }

    public void setTitle(String title){
        mContentTv.setText(title);
    }

    public interface OnRightClickListener{
        void onRightClick();
    }

    public interface OnMenuClickListener{
        void onMenuClick();
    }

    public interface OnDateClickListener{
        void onDateClick();
    }

    //浮动列表选项
    private View getPopupWindowContentView() {
        // 一个自定义的布局，作为显示的内容
        int layoutId = R.layout.view_popup_content;   // 布局ID
        View contentView = LayoutInflater.from(getContext()).inflate(layoutId, null);
        View.OnClickListener menuItemOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Click " + ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        };
        contentView.findViewById(R.id.menu_item1).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item2).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item3).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item4).setOnClickListener(menuItemOnClickListener);
        return contentView;
    }

}
