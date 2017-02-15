package com.young.planhelper.mvp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.register.RegisterActivity;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Override
    protected void initUI() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.tv_login_register)
    void selectRegister(){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void setData(Object data) {

    }
}
