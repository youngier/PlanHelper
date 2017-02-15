package com.young.planhelper.mvp.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.login.LoginActivity;
import com.young.planhelper.widget.Toolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @OnClick(R.id.tv_register_login)
    void clickLogin(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    protected void initUI() {

        mToolbar.setMode(Toolbar.REGISTER);

        mToolbar.setTitle("注册");

    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void setData(Object data) {

    }
}
