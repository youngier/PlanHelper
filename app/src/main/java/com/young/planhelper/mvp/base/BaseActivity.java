package com.young.planhelper.mvp.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.mingle.widget.ShapeLoadingDialog;
import com.young.planhelper.R;
import com.young.planhelper.mvp.base.view.IView;

import butterknife.ButterKnife;

/**
 * 最基本的activity，所有的activity都要继承
 * 提供加载框的显示
 * @author: young
 * date:16/9/30  10:23
 */


public abstract class BaseActivity extends AppCompatActivity implements IView{

    private ShapeLoadingDialog shapeLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(getLayout());
        ButterKnife.bind(this);

        shapeLoadingDialog=new ShapeLoadingDialog(this);
        shapeLoadingDialog.setLoadingText("加载中...");

        initUI();
    }

    /**
     * 设置activity主题
     */
    protected void setTheme() {

    }

    /**
     * 初始化UI控件
     */
    protected abstract void initUI();

    /**
     * 获取布局，由子类提供
     * @return
     */
    public abstract int getLayout();

    /**
     * 显示加载框
     */
    public void showProgress(){
        shapeLoadingDialog.show();
    }

    /**
     * 关闭加载框
     */
    public void hideProgress(){
        shapeLoadingDialog.dismiss();
    }

    protected void setStatueBarColor(){
        //设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.cyan_week_view_current));
            ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间. 
                ViewCompat.setFitsSystemWindows(mChildView, true);
            }

        }
    }
}
