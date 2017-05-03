package com.young.planhelper.mvp.overview.model;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.util.DateUtil;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;

import io.realm.Realm;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/3/27  15:59
 */


public class OverviewBiz extends Biz implements IOverviewBiz{


    private final Realm mRealm;

    private Context mContext;

    public OverviewBiz(Context context) {

        this.mContext = context;

        mRealm = AppApplication.get(context).getmAppComponent().getRealm();
    }

    @Override
    public void getData(ICallback callback) {

    }

    @Override
    public void getDataByMonth(int year, ICallback iCallback) {

        checkRealm(mRealm, iCallback);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int[] dayCounts = new int[12];

        int[][] result = new int[3][12];

        try {
            for (int i = 0; i < 12; i++) {

                int monthCount = 0;

                monthCount = DateUtil.getDaysOfMonth(sdf.parse(year + "-" + i + "-1"));

                dayCounts[i] = monthCount;
            }

            for (int i=0; i<12; i++){

                List<BacklogInfo> unfinishList = mRealm.where(BacklogInfo.class)
                        .equalTo("statue", BacklogInfo.UNFINISH)
                        .greaterThan("toTime", TimeUtil.dateToStamp1( year + "-" + (i+1) + "-1 00:00" ))
                        .lessThan("toTime", TimeUtil.dateToStamp1( year + "-" + (i+1) + "-" + dayCounts[i] +" 23:59"))
                        .findAll();

                List<BacklogInfo> finishedList = mRealm.where(BacklogInfo.class)
                        .equalTo("statue", BacklogInfo.FINISHED)
                        .greaterThan("toTime", TimeUtil.dateToStamp1( year + "-" + (i+1) + "-1 00:00" ))
                        .lessThan("toTime", TimeUtil.dateToStamp1( year + "-" + (i+1) + "-" + dayCounts[i] +" 23:59"))
                        .findAll();

                List<BacklogInfo> overdueList = mRealm.where(BacklogInfo.class)
                        .equalTo("statue", BacklogInfo.OVERDUE)
                        .greaterThan("toTime", TimeUtil.dateToStamp1( year + "-" + (i+1) + "-1 00:00" ))
                        .lessThan("toTime", TimeUtil.dateToStamp1( year + "-" + (i+1) + "-" + dayCounts[i] +" 23:59"))
                        .findAll();

                result[0][i] = finishedList.size();
                result[1][i] = unfinishList.size();
                result[2][i] = overdueList.size();

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        iCallback.onResult(result);


    }

    @Override
    public void getDataByWeek(int year, ICallback iCallback) {

        checkRealm(mRealm, iCallback);

        int[] result = new int[7];

        try {
            List<BacklogInfo> backlogInfoList = mRealm.where(BacklogInfo.class)
                    .greaterThan("toTime", TimeUtil.dateToStamp1( year + "-1-1 00:00" ))
                    .lessThan("toTime", TimeUtil.dateToStamp1( year + "-12-31 23:59"))
                    .findAll();

            int len = backlogInfoList.size();
            for(int i=0; i<len; i++){

                long time = backlogInfoList.get(i).getToTime();

                int week = TimeUtil.dayForWeek(TimeUtil.getTime6(time));

                result[week - 1]++;

            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        iCallback.onResult(result);
    }

    @Override
    public void getDataByHour(int year, ICallback iCallback) {
        checkRealm(mRealm, iCallback);

        int[][] result = new int[2][6];

        try {
            List<BacklogInfo> toTimeList = mRealm.where(BacklogInfo.class)
                    .greaterThan("toTime", TimeUtil.dateToStamp1( year + "-1-1 00:00" ))
                    .lessThan("toTime", TimeUtil.dateToStamp1( year + "-12-31 23:59"))
                    .findAll();

            List<BacklogInfo> fromTimeList = mRealm.where(BacklogInfo.class)
                    .greaterThan("fromTime", TimeUtil.dateToStamp1( year + "-1-1 00:00" ))
                    .lessThan("fromTime", TimeUtil.dateToStamp1( year + "-12-31 23:59"))
                    .findAll();

            int len = toTimeList.size();

            for(int i=0; i<len; i++){

                long time = toTimeList.get(i).getToTime();

                String timeString = TimeUtil.getTime3(time);

                timeString = timeString.substring(11, timeString.length());

                StringTokenizer stringTokenizer = new StringTokenizer(timeString, ":");

                int hour = Integer.parseInt(stringTokenizer.nextToken());

                if( hour <= 3 )
                    result[0][0]++;
                else if( hour >= 4 && hour <= 7 )
                    result[0][1]++;
                else if( hour >= 8 && hour <= 11 )
                    result[0][2]++;
                else if( hour >= 12 && hour <= 15 )
                    result[0][3]++;
                else if( hour >= 16 && hour <= 19 )
                    result[0][4]++;
                else
                    result[0][5]++;

            }

            len = fromTimeList.size();
            for(int i=0; i<len; i++){

                long time = fromTimeList.get(i).getFromTime();

                String timeString = TimeUtil.getTime3(time);

                timeString = timeString.substring(11, timeString.length());

                StringTokenizer stringTokenizer = new StringTokenizer(timeString, ":");

                int hour = Integer.parseInt(stringTokenizer.nextToken());

                if( hour <= 3 )
                    result[1][0]--;
                else if( hour >= 4 && hour <= 7 )
                    result[1][1]--;
                else if( hour >= 8 && hour <= 11 )
                    result[1][2]--;
                else if( hour >= 12 && hour <= 15 )
                    result[1][3]--;
                else if( hour >= 16 && hour <= 19 )
                    result[1][4]--;
                else
                    result[1][5]--;

            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        iCallback.onResult(result);
    }
}
