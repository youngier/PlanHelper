package com.young.planhelper.mvp.timeline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListAdapter;

import com.young.planhelper.R;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.view.backlogview.BacklogItemView;
import com.young.planhelper.mvp.timeline.model.bean.TimelineInfo;
import com.young.planhelper.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/21  19:07
 */


public class TimelineAdapter extends BaseExpandableListAdapter{

    private List<TimelineInfo> mDatas;
    private Context mContext;


    public TimelineAdapter(Context context, List<TimelineInfo> datas) {
        this.mContext = context;
        if( datas == null )
            mDatas = new ArrayList<>();
        else
            this.mDatas = datas;
    }


    @Override
    public int getGroupCount() {
        return mDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mDatas.get(groupPosition).getBacklogInfoList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDatas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDatas.get(groupPosition).getBacklogInfoList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if( convertView == null ){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_timeline_group_item, null);
        }
        TimelineGroupItemView view = (TimelineGroupItemView) convertView;
        view.setData(mDatas.get(groupPosition));

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if( convertView == null ){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_timeline_child_item, null);
        }
        TimelineChildItemView view = (TimelineChildItemView) convertView;
        view.setData(mDatas.get(groupPosition).getBacklogInfoList().get(childPosition));

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void setData(List<TimelineInfo> data) {
        this.mDatas = data;
    }
}
