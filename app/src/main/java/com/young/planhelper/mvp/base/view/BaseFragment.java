package com.young.planhelper.mvp.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.planhelper.R;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/1  18:38
 */


public abstract class BaseFragment extends Fragment implements IView{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(
                getLayoutId(), container, false);
        return view;
    }

    public abstract int getLayoutId();
}
