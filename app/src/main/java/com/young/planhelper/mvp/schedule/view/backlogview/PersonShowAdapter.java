package com.young.planhelper.mvp.schedule.view.backlogview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.young.planhelper.R;
import com.young.planhelper.mvp.plan.view.PlanSelectAdapter;
import com.young.planhelper.mvp.plan.view.PlanSelectItemView;
import com.young.planhelper.mvp.plan.view.PlanSelectViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/3/26  21:38
 */


public class PersonShowAdapter extends RecyclerView.Adapter<PersonShowViewHolder>{

    private List<String> mDatas;
    private Context mContext;
    private int itemViewWidth;

    public PersonShowAdapter(Context context, List<String> datas) {
        this.mContext = context;
        if( datas == null )
            mDatas = new ArrayList<>();
        else
            this.mDatas = datas;
    }


    @Override
    public PersonShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_person_show, parent, false);
        PersonShowViewHolder viewHolder = new PersonShowViewHolder(view);
        viewHolder.personShowView = (PersonShowView) view.findViewById(R.id.person_show_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PersonShowViewHolder holder, int position) {

        holder.personShowView.setData(mDatas.get(position));

        itemViewWidth = holder.personShowView.getSelectCiv().getLayoutParams().width;
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<String> data) {
        this.mDatas = data;
    }

    public int getItemViewWidth() {
        return itemViewWidth;
    }
}