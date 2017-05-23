package com.young.planhelper.mvp.person.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.login.view.LoginActivity;
import com.young.planhelper.widget.Toolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void initUI() {
        setStatueBarColor();

        mToolbar.setOnMenuClickListener( () -> finish());

        mToolbar.setMode(Toolbar.BACK);

        mToolbar.setTitle("个人中心");
    }

    @Override
    public int getLayout() {
        return R.layout.activity_person;
    }

    @Override
    public void setData(Object data) {

    }

    @OnClick(R.id.rl_person_change)
    void turnToLogin(){
        startActivity(new Intent(this, LoginActivity.class));
    }
}
