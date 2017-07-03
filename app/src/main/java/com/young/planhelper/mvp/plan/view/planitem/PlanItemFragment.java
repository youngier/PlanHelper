package com.young.planhelper.mvp.plan.view.planitem;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.constant.AppStatic;
import com.young.planhelper.mvp.base.view.BaseFragment;
import com.young.planhelper.mvp.home.HomeCloneActivity;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.login.view.LoginActivity;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanSecondItemPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanSecondItemPresenter;
import com.young.planhelper.mvp.plan.view.planitem.seconditem.PlanSecondItemAdapter;
import com.young.planhelper.mvp.plan.view.planitem.seconditem.PlanSecondItemAddActivity;
import com.young.planhelper.mvp.plan.view.planitem.seconditem.PlanSecondItemDetailActivity;
import com.young.planhelper.mvp.schedule.view.backlogview.RecycleViewDivider;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.widget.EditDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/23  21:49
 */


public class PlanItemFragment extends BaseFragment{

    @BindView(R.id.tv_plan_item_title)
    TextView mTitleTv;

    @BindView(R.id.rv_fragment_plan_item)
    RecyclerView mRecyclerView;

    @BindView(R.id.iv_fragment_plan_item_more)
    ImageView mMoreIv;

    private PopupWindow mPopupWindow;

    private PlanItemInfo mPlanItemInfo;

    private IPlanSecondItemPresenter presenter;

    private PlanSecondItemAdapter adapter;

    private Handler handler;

    private boolean isActive;

    //子任务排列次序
    private int order;

    public PlanItemFragment(PlanItemInfo planItemInfo) {
        this.mPlanItemInfo = planItemInfo;
    }

    @Override
    protected void setData() {

        setList();

    }

    @Override
    protected void initUI() {

        mTitleTv.setText(mPlanItemInfo.getTitle());

        presenter = new PlanSecondItemPresenter(this, getActivity());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_plan_item;
    }

    @Override
    public void setData(Object data) {

        if( !isActive )
            setListData((List<PlanSecondItemInfo>) data);
        else{
            Observable<List<PlanSecondItemInfo>> user = (Observable<List<PlanSecondItemInfo>>) data;
            user.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<PlanSecondItemInfo>>() {

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
                        public void onNext(List<PlanSecondItemInfo> s) {
                            hideProgress();
                            setListData(s);
                        }
                    });
        }

    }

    @OnClick(R.id.iv_fragment_plan_item_more)
    void selectMore(){
        showPopupWindow();
    }

    /**
     * 添加子任务
     */
    @OnClick(R.id.iv_fragment_plan_item)
    void addSecondItem(){
        AppStatic.planItemIndex = order;
        Intent intent = new Intent(getActivity(), PlanSecondItemAddActivity.class);
        intent.putExtra("planItemInfoId", mPlanItemInfo.getPlanItemInfoId());
        intent.putExtra("planInfoId", mPlanItemInfo.getPlanInfoId());
        intent.putExtra("isActive", isActive);
        startActivity(intent);
    }

    private void setList() {
        adapter = new PlanSecondItemAdapter(getContext(), null);
        adapter.setOnClickListener(id -> {
            Intent intent = new Intent(getActivity(), PlanSecondItemDetailActivity.class);
            intent.putExtra("planSecondItemInfoId", id);
            intent.putExtra("planInfoId", mPlanItemInfo.getPlanInfoId());
            intent.putExtra("isActive", isActive);
            startActivity(intent);
        });

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL));

        if( !isActive )
            presenter.getPlanSecondItemInfo(mPlanItemInfo.getPlanItemInfoId(), data -> setData(data));
        else
            presenter.getPlanSecondItemInfoByNetWork(mPlanItemInfo.getPlanItemInfoId(), data -> setData(data));
    }

    private void showPopupWindow() {
        View contentView = getPopupWindowContentView();
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        // popupWindow.showAsDropDown(mButton2);  // 默认在mButton2的左下角显示
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mPopupWindow.showAsDropDown(mMoreIv);
    }

    //浮动列表选项
    private View getPopupWindowContentView() {
        // 一个自定义的布局，作为显示的内容
        int layoutId = R.layout.view_popup_plan_item;   // 布局ID
        View contentView = LayoutInflater.from(getContext()).inflate(layoutId, null);

        View.OnClickListener menuItemOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.menu_item1:
                        editTask();
                        break;
//                    case R.id.menu_item2:
//                        copyTask();
//                        break;
                    case R.id.menu_item3:
                        moveTask();
                        break;
                    case R.id.menu_item4:
                        deleteTask();
                        break;
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        };
        contentView.findViewById(R.id.menu_item1).setOnClickListener(menuItemOnClickListener);
//        contentView.findViewById(R.id.menu_item2).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item3).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item4).setOnClickListener(menuItemOnClickListener);
        return contentView;
    }

    /**
     * 编辑任务
     */
    private void editTask() {
        EditDialog editDialog = new EditDialog(getActivity(), "编辑任务名称");

        editDialog.setOnConfirmListener( () -> {
            presenter.modifyPlanSecondItemInfoTitle(mPlanItemInfo.getPlanItemInfoId(),
                    editDialog.getContent(), data -> {
                        mTitleTv.setText(editDialog.getContent());
                    });
        } );

        editDialog.show();
    }


    /**
     * 移动任务
     */
    private void moveTask() {
        Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();
    }

    /**
     * 删除任务
     */
    private void deleteTask(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示")
                .setMessage("确认删除？")
                .setPositiveButton("确认",  (dialog, which) -> {
                    dialog.dismiss();
                    deletePlanItemInfo();
                })
                .setNegativeButton("取消", (dialog, which) -> {
                    dialog.dismiss();
                });

        builder.show();
    }

    /**
     * 删除任务项
     */
    private void deletePlanItemInfo() {
        presenter.deletePlanItemInfo(mPlanItemInfo.getPlanItemInfoId(), data -> {
            String result = (String) data;
            Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
            Message msg = new Message();
            msg.what = 0x001;
            msg.obj = mTitleTv.getText().toString();
            handler.sendMessage(msg);
        });
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setListData(List<PlanSecondItemInfo> planSecondItemInfos) {
        try {
            adapter.setDatas(planSecondItemInfos);
            adapter.notifyDataSetChanged();

        }catch (Exception e){
        }
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public interface OnDeleteCallBack{
        void onDelete(String title);
    }
}
