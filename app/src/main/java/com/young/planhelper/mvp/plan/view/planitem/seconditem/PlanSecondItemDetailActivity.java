package com.young.planhelper.mvp.plan.view.planitem.seconditem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanOperationInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanSecondItemDetailPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanSecondItemDetailPresenter;
import com.young.planhelper.mvp.plan.view.planitem.thirditem.PlanThirdItemAdapter;
import com.young.planhelper.mvp.plan.view.planitem.thirditem.PlanThirdItemAddActivity;
import com.young.planhelper.mvp.schedule.view.backlogview.RecycleViewDivider;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.DateTimePickDialog;
import com.young.planhelper.widget.manager.CustomLinearLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.young.planhelper.constant.AppConstant.MODIFY_CONTENT;
import static com.young.planhelper.constant.AppConstant.MODIFY_CONTENT_AND_TIME;
import static com.young.planhelper.constant.AppConstant.MODIFY_NOT;
import static com.young.planhelper.constant.AppConstant.MODIFY_TIME;

public class PlanSecondItemDetailActivity extends BaseActivity {

    @BindView(R.id.cb_title)
    CheckBox mTitleCb;

    @BindView(R.id.et_title)
    EditText mTitleEt;

    @BindView(R.id.et_content)
    EditText mContentEt;

    @BindView(R.id.tv_time)
    TextView mTimeTv;

    @BindView(R.id.rv_child)
    RecyclerView mChildRv;

    @BindView(R.id.rv_record)
    RecyclerView mRecordRv;

    @BindView(R.id.tv_dynamic)
    TextView mDynamicTv;


    private IPlanSecondItemDetailPresenter presenter;

    private long planSecondItemInfoId;

    private PlanThirdItemAdapter adapter;

    private PlanRecordAdapter mRecordAdapter;

    private boolean hasChanged = false;

    private int modifyModle = -1;

    private PlanSecondItemInfo mPlanSecondItemInfo;

    @Override
    protected void initUI() {

        planSecondItemInfoId = getIntent().getLongExtra("planSecondItemInfoId", 0);

        presenter = new PlanSecondItemDetailPresenter(this, this);

        CustomLinearLayoutManager layoutManager1 = new CustomLinearLayoutManager(this);
        layoutManager1.setScrollEnabled(false);

        mChildRv.setLayoutManager(layoutManager1);

        CustomLinearLayoutManager layoutManager2 = new CustomLinearLayoutManager(this);
        layoutManager1.setScrollEnabled(false);

        mRecordRv.setLayoutManager(layoutManager2);

        mTitleCb.setOnClickListener( (id) -> {
            presenter.modifyPlanSecondItemInfoStateById(planSecondItemInfoId, mTitleCb.isChecked(), data -> {

            });
        } );

        presenter.getPlanSecondItemInfoById(planSecondItemInfoId, data -> setData(data));

    }

    @Override
    public int getLayout() {
        return R.layout.activity_plan_third_item;
    }

    public void setListData(Object listData) {
        adapter = new PlanThirdItemAdapter(this, null);
        adapter.setOnClickListener(id -> {
//            Intent intent = new Intent(this, PlanThirdItemAddActivity.class);
//            intent.putExtra("isAdd",false);
//            startActivity(intent);
        });

        adapter.setOnSelectChangeListener( (id, isChecked) -> {
            presenter.modifyPlanThirdItemInfoStateById(id, isChecked, data -> {

            });

        } );

        mChildRv.setAdapter(adapter);
        mChildRv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));

        try {
            List<PlanThirdItemInfo> planInfos = (List<PlanThirdItemInfo>) listData;
            adapter.setDatas(planInfos);
            adapter.notifyDataSetChanged();
        }catch (Exception e){
            Toast.makeText(this, (String)listData, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setData(Object data) {

        try {
            PlanSecondItemInfo planSecondItemInfo = (PlanSecondItemInfo) data;

            this.mPlanSecondItemInfo = planSecondItemInfo;

            mTitleCb.setChecked(planSecondItemInfo.isFinished());

            mTitleEt.setText(planSecondItemInfo.getTitle());

            mContentEt.setText(planSecondItemInfo.getContent());

            mContentEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    hasChanged = true;
                    if( modifyModle == MODIFY_NOT )
                        modifyModle = MODIFY_CONTENT;
                    else if( modifyModle == MODIFY_TIME )
                        modifyModle = MODIFY_CONTENT_AND_TIME;
                }
            });

            mTimeTv.setText(planSecondItemInfo.getTime());

            presenter.getPlanThirdItemInfoBySecondId(planSecondItemInfoId, listData -> setListData(listData));

        } catch (Exception e) {
            Toast.makeText(this, (String) data, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 选择时间
     */
    @OnClick(R.id.ll_time)
    void selectTime(){
        hasChanged = true;
        if( modifyModle == MODIFY_NOT )
            modifyModle = MODIFY_TIME;
        else if( modifyModle == MODIFY_CONTENT )
            modifyModle = MODIFY_CONTENT_AND_TIME;
        DateTimePickDialog dateTimePickDialog = new DateTimePickDialog(
                this, TimeUtil.getCurrentDateTimeInString());
        dateTimePickDialog.dateTimePicKDialog();
        dateTimePickDialog.setOnTimeSelectListener(new DateTimePickDialog.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(String time) {
                mTimeTv.setText(time);
            }
        });
    }


    @OnClick(R.id.ll_add_child)
    void addItem(){
        Intent intent = new Intent(this, PlanThirdItemAddActivity.class);
        intent.putExtra("planSecondItemInfoId", planSecondItemInfoId);
        startActivity(intent);
    }

    /**
     * 显示操作记录
     */
    @OnClick(R.id.ll_dynamics)
    void showRecord(){
        if( mRecordRv.getVisibility() == View.GONE ){

            mRecordRv.setVisibility(View.VISIBLE);

            mDynamicTv.setText("隐藏操作记录");

            presenter.getPlanOperationInfoBySecondId(planSecondItemInfoId, data -> {
                try {

                    mRecordAdapter = new PlanRecordAdapter(this, null);

                    mRecordRv.setAdapter(mRecordAdapter);
                    mRecordRv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));

                    List<PlanOperationInfo> planInfos = (List<PlanOperationInfo>) data;

                    mRecordAdapter.setDatas(planInfos);
                    mRecordAdapter.notifyDataSetChanged();

                }catch (Exception e){
                    Toast.makeText(this, (String)data, Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            mRecordRv.setVisibility(View.GONE);

            mDynamicTv.setText("显示操作记录");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if( keyCode == KeyEvent.KEYCODE_BACK ){
            checkDataChange();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 检查退出前是否对原来任务进行了修改
     */
    private void checkDataChange() {
        if( hasChanged ){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示")
                    .setMessage("数据发生变化，确认更改？")
                    .setPositiveButton("确认",  (dialog, which) -> {
                        dialog.dismiss();
                        saveChangeData();
                    })
                    .setNegativeButton("取消", (dialog, which) -> {
                        dialog.dismiss();
                        this.finish();
                    });

            builder.show();
        }
    }

    /**
     * 保存改变的数据
     */
    private void saveChangeData() {

        presenter.modifyPlanSecondItemInfo(mPlanSecondItemInfo,
                mContentEt.getText().toString(),
                mTimeTv.getText().toString(),
                modifyModle,
                data -> {
            this.finish();
        });
    }
}
