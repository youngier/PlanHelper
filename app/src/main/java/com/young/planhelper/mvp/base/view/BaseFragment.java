package com.young.planhelper.mvp.base.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingle.widget.ShapeLoadingDialog;
import com.young.planhelper.R;
import com.young.planhelper.mvp.base.presenter.IPresenter;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.util.LogUtil;

import butterknife.ButterKnife;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/1  18:38
 */


public abstract class BaseFragment extends Fragment implements IView{

    View view;

    private ShapeLoadingDialog shapeLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(
                getLayoutId(), container, false);
        ButterKnife.bind(this, view);

        shapeLoadingDialog=new ShapeLoadingDialog(getActivity());
        shapeLoadingDialog.setLoadingText("加载中...");

        initUI();
        setData();
        return view;
    }

    protected abstract void setData();

    protected abstract void initUI();

    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View getView() {
        return view;
    }

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
