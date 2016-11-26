package com.young.planhelper.mvp.plan.view;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseOtherActivity;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanAddPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanAddPresenter;
import com.young.planhelper.util.TimeUtil;
import com.zcw.togglebutton.ToggleButton;

import butterknife.BindView;
import butterknife.OnClick;

public class PlanAddActivity extends BaseOtherActivity {

    @BindView(R.id.et_add_plan_title)
    EditText mTitleEt;

    @BindView(R.id.tv_add_plan_authority)
    TextView mAuthorityTv;

//    @BindView(R.id.tv_add_plan_type)
//    TextView mTypeTv;

    @BindView(R.id.togglebtn)
    ToggleButton mToggleBtn;

    private int authority = 0;

    private IPlanAddPresenter presenter;

    @Override
    protected void initUI() {
        //开关切换事件

    }

    @Override
    protected void config() {
        super.config();
        presenter = new PlanAddPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_plan_add;
    }

    @Override
    public void setData(Object data) {
        Toast.makeText(this, (String)data, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void setTheme() {
        super.setTheme();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @OnClick(R.id.tv_add_plan_authority)
    void selectAuthority(){
    }

    @OnClick(R.id.btn_add_plan)
    void addPlan(){

        String title = mTitleEt.getText().toString();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(this, "计划名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        PlanInfo planInfo = new PlanInfo();
        planInfo.setPlanInfoId(TimeUtil.getCurrentTimeInLong());

        planInfo.setTitle(title);
        planInfo.setAuthority(authority);
        presenter.addPlan(planInfo, data -> setData(data));
    }


}
