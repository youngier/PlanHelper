package com.young.planhelper.mvp.plan.view;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseOtherActivity;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.common.people.SelectPeopleActivity;
import com.young.planhelper.mvp.home.HomeCloneActivity;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.login.view.LoginActivity;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanAddPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanAddPresenter;
import com.young.planhelper.mvp.plan.view.planview.PlanAdapter;
import com.young.planhelper.util.TimeUtil;
import com.zcw.togglebutton.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlanAddActivity extends BaseOtherActivity {

    @BindView(R.id.et_add_plan_title)
    EditText mTitleEt;

    @BindView(R.id.tv_add_plan_authority)
    TextView mAuthorityTv;

//    @BindView(R.id.tv_add_plan_type)
//    TextView mTypeTv;

    @BindView(R.id.togglebtn)
    ToggleButton mToggleBtn;

    @BindView(R.id.ll_plan_add_friend)
    LinearLayout mFriendLl;

    @BindView(R.id.rv_plan_add_select_person)
    RecyclerView mSelectPersonRv;

    @BindView(R.id.rl_plan_add_authority)
    RelativeLayout mAuthorityRl;


    private int authority = 0;

    private boolean isSynchronized = false;

    private PlanSelectAdapter adapter;

    private IPlanAddPresenter presenter;

    private boolean isActive;

    private String[] idList;


    @Override
    protected void initUI() {

        isActive = getIntent().getBooleanExtra("isActive", true);

        if( isActive ){

            adapter = new PlanSelectAdapter(this, null);

            mSelectPersonRv.setAdapter(adapter);

            LinearLayoutManager lm = new LinearLayoutManager(this);
            lm.setOrientation(LinearLayoutManager.HORIZONTAL);
            mSelectPersonRv.setLayoutManager(lm);

            adapter.setOnClickListener( id -> {
                startActivityForResult(new Intent(this, SelectPeopleActivity.class), 0);
            } );

        }else{
            mFriendLl.setVisibility(View.GONE);
            mAuthorityRl.setVisibility(View.GONE);
        }

        if( !isActive ) {
            mToggleBtn.setOnToggleChanged(on -> {
                if (on)
                    isSynchronized = true;
                else
                    isSynchronized = false;
            });
        }else{
            mToggleBtn.setToggleOn();
            mToggleBtn.setEnabled(false);
        }

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
        if( !isActive ) {
            Toast.makeText(this, (String) data, Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Observable<String> user = (Observable<String>) data;
            user.observeOn(AndroidSchedulers.mainThread())
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
                            finish();
                        }
                    });
        }
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

        if( !isActive ){
            PlanInfo planInfo = new PlanInfo();
            planInfo.setPlanInfoId(TimeUtil.getCurrentTimeInLong());

            planInfo.setTitle(title);
            planInfo.setAuthority(authority);
            planInfo.setSynchronized(isSynchronized);
            presenter.addPlan(planInfo, data -> setData(data));
        }else{

            showProgress();

            PlanInfo planInfo = new PlanInfo();
            planInfo.setTitle(mTitleEt.getText().toString());
            List<String> users = new ArrayList<>();
            String members = "";
            for (int i = 0; i < idList.length; i++) {
                users.add(idList[i]);
                members += idList[i] + ",";
            }
            members = members.substring(0, members.length() - 1);
            planInfo.setMembers(members);
            planInfo.setAuthority(authority);
            planInfo.setSynchronized(isSynchronized);

            presenter.addPlanByNetWork(planInfo, data -> setData(data));

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            String[] iconUrlList=data.getStringArrayExtra("iconUrlList");
            idList=data.getStringArrayExtra("idList");
            if( idList != null && idList.length > 0 ){
                List list = new ArrayList<String>();
                int len = iconUrlList.length;
                for (int i=0; i<len; i++)
                    list.add(iconUrlList[i]);
                adapter.setDatas(list);
                adapter.notifyDataSetChanged();
            }
        }
    }

}
