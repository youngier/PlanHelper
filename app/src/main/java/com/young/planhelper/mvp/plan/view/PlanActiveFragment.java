package com.young.planhelper.mvp.plan.view;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.view.BaseFragment;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.presenter.IPlanPresenter;
import com.young.planhelper.mvp.plan.presenter.PlanPresenter;
import com.young.planhelper.mvp.plan.view.planitem.PlanItemActivity;
import com.young.planhelper.mvp.plan.view.planview.PlanAdapter;

import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/15  09:53
 */


public class PlanActiveFragment extends BaseFragment implements IView {

    @BindView(R.id.rv_plan_active)
    RecyclerView mArchiveRv;

    private PlanAdapter mPlanAdapter;

    private IPlanPresenter presenter;


    @Override
    protected void setData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getPlanInfoByNetWork(data -> setData(data));
    }

    @Override
    protected void initUI() {

        presenter = new PlanPresenter(this, getActivity());

        mPlanAdapter = new PlanAdapter(getActivity(), null);

        mArchiveRv.setAdapter(mPlanAdapter);

        mArchiveRv.addItemDecoration(new MarginDecoration(getActivity()));
        mArchiveRv.setHasFixedSize(true);
        mArchiveRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mPlanAdapter.setOnClickListener(planInfo -> {
            Intent intent = new Intent(getActivity(), PlanItemActivity.class);
            intent.putExtra("planInfoId", planInfo.getPlanInfoId());
            intent.putExtra("planInfoTitle", planInfo.getTitle());
            startActivity(intent);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_plan_active;
    }

    @Override
    public void setData(Object data) {
//        try {
//            List<PlanInfo> planInfos = (List<PlanInfo>) data;
//            mPlanAdapter.setDatas(planInfos);
//            mPlanAdapter.notifyDataSetChanged();
//        }catch (Exception e){
//            Toast.makeText(getActivity(), (String)data, Toast.LENGTH_SHORT).show();
//        }
        Observable<List<PlanInfo>> planInfoList = (Observable<List<PlanInfo>>) data;
        planInfoList.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PlanInfo>>() {

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
                    public void onNext(List<PlanInfo> planInfoList) {

                        Log.i("way", "onNext" + planInfoList.size());
                        hideProgress();
                        mPlanAdapter.setDatas(planInfoList);
                        mPlanAdapter.notifyDataSetChanged();
                    }
                });
    }
}
