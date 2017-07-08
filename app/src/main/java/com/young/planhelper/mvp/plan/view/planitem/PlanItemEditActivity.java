package com.young.planhelper.mvp.plan.view.planitem;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanItemAddPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanItemAddPresenter;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.Toolbar;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlanItemEditActivity extends BaseActivity implements IView {


    @BindView(R.id.tv_add_plan_detail_title)
    TextView mTitleTv;

    @BindView(R.id.et_add_plan_detail_title)
    TextView mTitleEt;

    private long planInfoId;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private IPlanItemAddPresenter presenter;

    private String planInfoTitle = "";

    private boolean isActive;

    @Override
    protected void initUI() {

        setStatueBarColor();

        planInfoId = getIntent().getLongExtra("planInfoId", 0);

        planInfoTitle = getIntent().getStringExtra("planInfoTitle");

        isActive = getIntent().getBooleanExtra("isActive", false);

        presenter = new PlanItemAddPresenter(this, this);

        mToolbar.setMode(Toolbar.BACK);

        mToolbar.setTitle("添加任务项");

        mToolbar.setOnMenuClickListener( () -> finish() );

    }


    @Override
    public int getLayout() {
        return R.layout.activity_plan_detail_edit;
    }


    @Override
    public void setData(Object data) {
        if( !isActive ) {
            Toast.makeText(this, (String) data, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PlanItemActivity.class);
            intent.putExtra("add", true);
            intent.putExtra("planInfoId", planInfoId);
            intent.putExtra("planInfoTitle", planInfoTitle);
            startActivity(intent);
        }else{
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
                            Log.i("way", "onNext" + s);
                            hideProgress();
                            Intent intent = new Intent(PlanItemEditActivity.this, PlanItemActivity.class);
                            intent.putExtra("add", true);
                            intent.putExtra("planInfoId", planInfoId);
                            intent.putExtra("planInfoTitle", planInfoTitle);
                            startActivity(intent);
                        }
                    });
        }


    }

    @OnTextChanged(R.id.et_add_plan_detail_title)
    void textChange() {
        if (TextUtils.isEmpty(mTitleEt.getText()))
            mTitleTv.setVisibility(View.INVISIBLE);
        else
            mTitleTv.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_add_plan_detail_title)
    void addPlanItem() {
        String title = mTitleEt.getText().toString();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "任务名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        PlanItemInfo planItemInfo = new PlanItemInfo();
        planItemInfo.setPlanItemInfoId(TimeUtil.getCurrentTimeInLong());
        planItemInfo.setTitle(title);
        planItemInfo.setPlanInfoId(planInfoId);

        if( !isActive )
            presenter.addPlanItem(planItemInfo, data -> setData(data));
        else
            presenter.addPlanItemByNetWork(planItemInfo, data -> setData(data));
    }
}

