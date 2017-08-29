package com.young.planhelper.mvp.person.view;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.young.planhelper.R;
import com.young.planhelper.application.AppApplication;
import com.young.planhelper.constant.AppConstant;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.login.LoginActivity;
import com.young.planhelper.mvp.person.presenter.IPersonPresenter;
import com.young.planhelper.mvp.person.presenter.PersonPresenter;
import com.young.planhelper.widget.NewAlertDialog;
import com.young.planhelper.widget.Toolbar;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PersonActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.civ_person_show)
    CircleImageView mPersonCiv;

    @BindView(R.id.tv_person_show)
    TextView mPersonTv;

    private IPersonPresenter presenter;
    private NewAlertDialog dialog;

    @Override
    protected void initUI() {
        setStatueBarColor();

        mToolbar.setOnMenuClickListener( () -> finish());

        mToolbar.setMode(Toolbar.BACK);

        mToolbar.setTitle("个人中心");

        User user = AppApplication.get(this).getmAppComponent().getUserInfo();

        Glide.with(this)
                .load(AppConstant.RECOUSE_IMAGE_URL + user.getIconUrl())
                .into(mPersonCiv);

        mPersonTv.setText(user.getAccount());

        presenter = new PersonPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_person;
    }

    @Override
    public void setData(Object data) {
        Observable<String> result = (Observable<String>) data;
        result.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                        Log.i("way", "onCompleted");
                        hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("way", "onError" + e.toString());
                        hideProgress();
                    }

                    @Override
                    public void onNext(String s) {
                        hideProgress();
                        dialog.dismiss();
                        if( s.equals("1") )
                            Toast.makeText(PersonActivity.this, "恢复备份成功", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(PersonActivity.this, "恢复备份失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick(R.id.rl_person_backup)
    void backup(){
        dialog = new NewAlertDialog(this, "备份提醒", "将本地数据全部备份到服务器上", NewAlertDialog.OTHER);
        dialog.setOnDialogClickListener( () ->{
            showProgress();
            presenter.backups(resultData -> setData(resultData));
        } );
        dialog.show();

    }

    @OnClick(R.id.rl_person_recovery)
    void recovery(){
        dialog = new NewAlertDialog(this, "恢复提醒", "将服务器上的备份数据复制到本地", NewAlertDialog.OTHER);
        dialog.setOnDialogClickListener( () ->{
            showProgress();
            presenter.recovery(resultData -> {
                hideProgress();
                String result = (String) resultData;
                if( result.equals("1") )
                    Toast.makeText(PersonActivity.this, "恢复数据成功", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PersonActivity.this, "恢复数据失败", Toast.LENGTH_SHORT).show();
            });
        } );
        dialog.show();
    }

    @OnClick(R.id.rl_person_change)
    void turnToLogin(){
        startActivity(new Intent(this, LoginActivity.class));
    }
}
