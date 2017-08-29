package com.young.planhelper.mvp.login;

import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.home.HomeCloneActivity;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.register.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity implements LoginContract.View{

    @BindView(R.id.et_login_account)
    EditText mAccountEt;

    @BindView(R.id.et_login_password)
    EditText mPasswordEt;

    private LoginContract.Presenter presenter;


    @Override
    protected void initUI() {
        presenter = new LoginPresenter(this, this);
        User user = AppApplication.get(this).getmAppComponent().getUserInfo();
        mAccountEt.setText(user.getAccount());
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.tv_login_register)
    void selectRegister(){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @OnClick(R.id.btn_login)
    void login(){

        String account = mAccountEt.getText().toString();
        String password = mPasswordEt.getText().toString();

        if( account.equals("") ){
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }

        if( password.equals("") ){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        showProgress();
        presenter.login(account, password);
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void toHomeActivity() {
        startActivity(new Intent(LoginActivity.this, HomeCloneActivity.class));
    }
}
