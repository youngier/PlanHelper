package com.young.planhelper.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mingle.widget.ShapeLoadingDialog;
import com.young.planhelper.mvp.base.view.IView;

import butterknife.ButterKnife;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/8  14:27
 */


public abstract class BaseOtherActivity extends Activity implements IView {

    private ShapeLoadingDialog shapeLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(getLayout());
        ButterKnife.bind(this);
        config();

        shapeLoadingDialog=new ShapeLoadingDialog(this);
        shapeLoadingDialog.setLoadingText("加载中...");

        initUI();
    }

    /**
     * 给子类提供自己的配置
     */
    protected void config() {
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

}
