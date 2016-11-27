package com.young.planhelper.mvp.plan.view.planitem.seconditem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseActivity;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanSecondItemDetailPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanSecondItemDetailPresenter;
import com.young.planhelper.mvp.plan.view.planitem.thirditem.PlanThirdItemAdapter;
import com.young.planhelper.mvp.plan.view.planitem.thirditem.PlanThirdItemAddActivity;
import com.young.planhelper.mvp.schedule.view.backlogview.RecycleViewDivider;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.DateTimePickDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PlanSecondItemDetailActivity extends BaseActivity {

    @BindView(R.id.et_title)
    EditText mTitleEt;

    @BindView(R.id.et_content)
    EditText mContentEt;

    @BindView(R.id.tv_time)
    TextView mTimeTv;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private IPlanSecondItemDetailPresenter presenter;

    private long planSecondItemInfoId;

    private PlanThirdItemAdapter adapter;

    @Override
    protected void initUI() {

        planSecondItemInfoId = getIntent().getLongExtra("planSecondItemInfoId", 0);

        presenter = new PlanSecondItemDetailPresenter(this, this);

        presenter.getPlanSecondItemInfoById(planSecondItemInfoId, data -> setData(data));

        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this);
        linearLayoutManager.setScrollEnabled(false);

        mRecyclerView.setLayoutManager(linearLayoutManager);

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

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));

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

            mTitleEt.setText(planSecondItemInfo.getTitle());

            mContentEt.setText(planSecondItemInfo.getContent());

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


    public class CustomLinearLayoutManager extends LinearLayoutManager {
        private boolean isScrollEnabled = true;

        public CustomLinearLayoutManager(Context context) {
            super(context);
        }

        public void setScrollEnabled(boolean flag) {
            this.isScrollEnabled = flag;
        }

        @Override
        public boolean canScrollVertically() {
            //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
            return isScrollEnabled && super.canScrollVertically();
        }
    }
}
