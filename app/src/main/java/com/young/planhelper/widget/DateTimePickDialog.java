package com.young.planhelper.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.young.planhelper.R;
import com.young.planhelper.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/7  20:42
 */


public class DateTimePickDialog implements DatePicker.OnDateChangedListener,
        TimePicker.OnTimeChangedListener{

    private String mInitDateTime;
    private Activity mActivity;
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;
    private AlertDialog mAlertDialog;
    private String dateTime;

    /**
     * 日期时间弹出选择框构造函数
     *
     * @param activity
     *            ：调用的父activity
     * @param initDateTime
     *            初始日期时间值，作为弹出窗口的标题和日期时间初始值
     */
    public DateTimePickDialog(Activity activity, String initDateTime) {
        this.mActivity = activity;
        this.mInitDateTime = initDateTime;

    }

    public void init(DatePicker datePicker, TimePicker timePicker) {
        Calendar calendar = Calendar.getInstance();
        if (!(null == mInitDateTime || "".equals(mInitDateTime))) {
            calendar = this.getCalendarByInintData(mInitDateTime);
        } else {
            mInitDateTime = calendar.get(Calendar.YEAR) + "年"
                    + calendar.get(Calendar.MONTH) + "月"
                    + calendar.get(Calendar.DAY_OF_MONTH) + "日 "
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                    + calendar.get(Calendar.MINUTE);
        }

        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), this);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    }

    /**
     * 弹出日期时间选择框方法
     *
     * @return
     */
    public AlertDialog dateTimePicKDialog() {
        LinearLayout dateTimeLayout = (LinearLayout) mActivity
                .getLayoutInflater().inflate(R.layout.view_dialog_datetime, null);
        mDatePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
        mTimePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
        init(mDatePicker, mTimePicker);
        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener(this);

        mAlertDialog = new AlertDialog.Builder(mActivity)
                .setTitle(mInitDateTime)
                .setView(dateTimeLayout)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        listener.onTimeSelect(dateTime);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        listener.onTimeSelect("");
                    }
                }).show();

        onDateChanged(null, 0, 0, 0);
        return mAlertDialog;
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        // 获得日历实例
        Calendar calendar = Calendar.getInstance();

        calendar.set(mDatePicker.getYear(), mDatePicker.getMonth(),
                mDatePicker.getDayOfMonth(), mTimePicker.getCurrentHour(),
                mTimePicker.getCurrentMinute());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

        dateTime = sdf.format(calendar.getTime());
        mAlertDialog.setTitle(dateTime);
    }

    /**
     * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
     *
     * @param initDateTime
     *            初始日期时间值 字符串型
     * @return Calendar
     */
    private Calendar getCalendarByInintData(String initDateTime) {
        Calendar calendar = Calendar.getInstance();

        LogUtil.eLog("时间："+initDateTime);
        // 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
        String date = spliteString(initDateTime, "日", "index", "front"); // 日期
        String time = spliteString(initDateTime, "日", "index", "back"); // 时间

        String yearStr = spliteString(date, "年", "index", "front"); // 年份
        String monthAndDay = spliteString(date, "年", "index", "back"); // 月日

        String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
        String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日

        String hourStr = spliteString(time, ":", "index", "front"); // 时
        String minuteStr = spliteString(time, ":", "index", "back"); // 分

        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
        int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
        int currentDay = Integer.valueOf(dayStr.trim()).intValue();
        int currentHour = Integer.valueOf(hourStr.trim()).intValue();
        int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

        calendar.set(currentYear, currentMonth, currentDay, currentHour,
                currentMinute);
        return calendar;
    }

    /**
     * 截取子串
     *
     * @param srcStr
     *            源串
     * @param pattern
     *            匹配模式
     * @param indexOrLast
     * @param frontOrBack
     * @return
     */
    public static String spliteString(String srcStr, String pattern,
                                      String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;
        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
        } else {
            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 截取子串
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
        }
        return result;
    }

    private OnTimeSelectListener listener;

    public void setOnTimeSelectListener(OnTimeSelectListener listener){
        this.listener = listener;
    }

    public interface OnTimeSelectListener{
        void onTimeSelect(String time);
    }

}
