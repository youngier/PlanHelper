package com.young.planhelper.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.young.planhelper.mvp.base.view.IView;

import butterknife.ButterKnife;

/**
 * 最基本的activity，所有的activity都要继承
 * 提供加载框的显示
 * @author: young
 * date:16/9/30  10:23
 */


public abstract class BaseActivity extends AppCompatActivity implements IView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(getLayout());
        ButterKnife.bind(this);
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
}
