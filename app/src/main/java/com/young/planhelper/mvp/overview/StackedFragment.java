package com.young.planhelper.mvp.overview;

import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.db.chart.Tools;
import com.db.chart.listener.OnEntryClickListener;
import com.db.chart.model.Bar;
import com.db.chart.model.BarSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.ChartView;
import com.db.chart.view.HorizontalBarChartView;
import com.db.chart.view.HorizontalStackBarChartView;
import com.db.chart.view.StackBarChartView;
import com.db.chart.view.Tooltip;
import com.db.chart.view.XController;
import com.db.chart.view.YController;
import com.db.chart.view.animation.Animation;
import com.db.chart.view.animation.easing.ExpoEase;
import com.young.planhelper.R;
import com.young.planhelper.mvp.base.view.BaseFragment;
import com.young.planhelper.mvp.overview.presenter.IOverviewPresenter;
import com.young.planhelper.mvp.overview.presenter.OverviewPresenter;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;
import com.young.planhelper.widget.NewAlertDialog;
import com.young.planhelper.widget.Toolbar;

import java.sql.Time;
import java.text.DecimalFormat;
import java.util.concurrent.ForkJoinPool;

import butterknife.BindView;


public class StackedFragment extends BaseFragment {


    private IOverviewPresenter presenter;

    private final TimeInterpolator enterInterpolator = new DecelerateInterpolator(1.5f);
    private final TimeInterpolator exitInterpolator = new AccelerateInterpolator();


    /** First chart */
    @BindView(R.id.stackedchart1)
    StackBarChartView mChartOne;

    @BindView(R.id.play1)
    ImageButton mPlayOne;

    private boolean mUpdateOne;
    private final String[] mLabelsOne= {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
    private final float [][] mValuesOne = { {30f, 40f, 25f, 25f, 40f, 25f, 25f, 30f, 30f, 25f, 40f, 25f},
            {30f, 30f, 25f, 40f, 25f, 30f, 40f, 30f, 30f, 25f, 25f, 25f},
            {30f, 30f, 25f, 25f, 25f, 25f, 25f, 30f, 40f, 25, 25, 40f} };

    @BindView(R.id.state_one)
    TextView mLegendOneRed;

    @BindView(R.id.state_two)
    TextView mLegendOneYellow;

    @BindView(R.id.state_three)
    TextView mLegendOneGreen;




    /** Second chart */
    @BindView(R.id.stackedchart2)
    HorizontalStackBarChartView mChartTwo;

    @BindView(R.id.play2)
    ImageButton mPlayTwo;
    private boolean mUpdateTwo;
    private final String[] mLabelsTwo= {"0:00-3:59", "4:00-7:59", "8:00-11:59", "12:00-15:59", "16:00-19:59", "20:00-23:59"};
    private final float [][] mValuesTwo = { {1.8f, 2f, 2.4f, 2.2f, 3.3f, 3.45f},
            {-1.8f, -2.1f, -2.55f, -2.40f, -3.40f, -3.5f}};


    /** Third chart */
    @BindView(R.id.barchart2)
    HorizontalBarChartView mChartThree;

    @BindView(R.id.play3)
    ImageButton mPlayThree;
    private boolean mUpdateThree;
    private final String[] mLabelsThree= {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private final float [] mValuesThree = {23f, 23f, 23f, 34f, 55f, 71f, 98f};
    private int[] chartData2 = new int[7];

    @BindView(R.id.value)
    TextView mTextViewThree;

    @BindView(R.id.metric)
    TextView mTextViewMetricThree;



    @Override
    protected void setData() {

    }

    @Override
    protected void initUI() {
        presenter = new OverviewPresenter(this, getActivity());

        // Init first chart
        mUpdateOne = true;

        mPlayOne.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new NewAlertDialog(getActivity(), "视图内容", "显示一年之内各月份任务的完成情况").show();
//                if(mUpdateOne)
//                    updateChart(0, mChartOne, mPlayOne);
//                else {
//                    dismissChart(0, mChartOne, mPlayOne);
//                }
//                mUpdateOne = !mUpdateOne;
            }
        });

        // Init second chart
        mUpdateTwo = true;
        mPlayTwo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                new NewAlertDialog(getActivity(), "视图内容", "显示一年之内创建任务和需完成任务的时间情况，左边为创建任务时间，右边为需完成任务时间").show();
                //                if(mUpdateTwo)
//                    updateChart(1, mChartTwo, mPlayTwo);
//                else
//                    dismissChart(1, mChartTwo, mPlayTwo);
//                mUpdateTwo = !mUpdateTwo;
            }
        });


        // Init third chart
        mUpdateThree = true;
        mPlayThree.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                if(mUpdateThree)
//                    updateChart(1, mChartThree, mPlayThree);
//                else
//                    dismissChart(1, mChartThree, mPlayThree);
//                mUpdateThree = !mUpdateThree;
                new NewAlertDialog(getActivity(), "视图内容", "显示一年之内周期任务完成的需要情况").show();

            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            mTextViewThree.setAlpha(0);
            mTextViewMetricThree.setAlpha(0);
        }else{
            mTextViewThree.setVisibility(View.INVISIBLE);
            mTextViewMetricThree.setVisibility(View.INVISIBLE);
        }

        mTextViewThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new NewAlertDialog(getActivity(), "视图内容", "显示一年之内按周期任务的完成情况").show();
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
//                    mTextViewThree.animate().alpha(0).setDuration(100);
//                    mTextViewMetricThree.animate().alpha(0).setDuration(100);
//                }
            }
        });


        presenter.getDataByMonth(Integer.parseInt(TimeUtil.getCurrentYearInString()), data -> setData(data));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.stacked;
    }


    /**
     * Show a CardView chart
     * @param tag   Tag specifying which chart should be dismissed
     * @param chart   Chart view
     * @param btn    Play button
     */
    private void showChart(final int tag, final ChartView chart, final ImageButton btn){
        dismissPlay(btn);
        Runnable action =  new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        showPlay(btn);
                    }
                }, 500);
            }
        };

        switch(tag){
            case 0:
                produceOne(chart, action); break;
            case 1:
                produceTwo(chart, action); break;
            case 2:
                produceThree(chart, action); break;

            default:
        }
    }


    /**
     * Update the values of a CardView chart
     * @param tag   Tag specifying which chart should be dismissed
     * @param chart   Chart view
     * @param btn    Play button
     */
    private void updateChart(final int tag, final ChartView chart, ImageButton btn){

        dismissPlay(btn);

        switch(tag){
            case 0:
                updateOne(chart); break;
            case 1:
                updateTwo(chart); break;
            case 2:
                updateThree(chart); break;
            default:
        }
    }


    /**
     * Dismiss a CardView chart
     * @param tag   Tag specifying which chart should be dismissed
     * @param chart   Chart view
     * @param btn    Play button
     */
    private void dismissChart(final int tag, final ChartView chart, final ImageButton btn){

        dismissPlay(btn);

        Runnable action =  new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        showPlay(btn);
                        showChart(tag, chart, btn);
                    }
                }, 500);
            }
        };

        switch(tag){
            case 0:
                dismissOne(chart, action); break;
            case 1:
                dismissTwo(chart, action); break;
            case 2:
                dismissThree(chart, action); break;
            default:
        }
    }


    /**
     * Show CardView play button
     * @param btn    Play button
     */
    private void showPlay(ImageButton btn){
        btn.setEnabled(true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            btn.animate().alpha(1).scaleX(1).scaleY(1);
        else
            btn.setVisibility(View.VISIBLE);
    }


    /**
     * Dismiss CardView play button
     * @param btn    Play button
     */
    private void dismissPlay(ImageButton btn){
        btn.setEnabled(false);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            btn.animate().alpha(0).scaleX(0).scaleY(0);
        else
            btn.setVisibility(View.INVISIBLE);
    }



    /**
     *
     * Chart 1
     *
     */

    private void produceOne(ChartView chart, Runnable action){
        StackBarChartView stackedChart = (StackBarChartView) chart;

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
//            stackedChart.setOnEntryClickListener(new OnEntryClickListener() {
//                @SuppressLint("NewApi")
//                @Override
//                public void onClick(int setIndex, int entryIndex, Rect rect) {
//                    LogUtil.eLog("点击：" + setIndex + ", " + entryIndex);
//                    if(setIndex == 2)
//                        mLegendOneRed.animate()
//                                .scaleY(1.1f)
//                                .scaleX(1.1f)
//                                .setDuration(100)
//                                .withEndAction(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        mLegendOneRed.animate()
//                                                .scaleY(1.0f)
//                                                .scaleX(1.0f)
//                                                .setDuration(100);
//                                    }
//                                });
//                    else if(setIndex == 1){
//                        mLegendOneYellow.animate()
//                                .scaleY(1.1f)
//                                .scaleX(1.1f)
//                                .setDuration(100)
//                                .withEndAction(new Runnable(){
//                                    @Override
//                                    public void run() {
//                                        mLegendOneYellow.animate()
//                                                .scaleY(1.0f)
//                                                .scaleX(1.0f)
//                                                .setDuration(100);
//                                    }
//                                });
//                    }else{
//                        mLegendOneGreen.animate()
//                                .scaleY(1.1f)
//                                .scaleX(1.1f)
//                                .setDuration(100)
//                                .withEndAction(new Runnable(){
//                                    @Override
//                                    public void run() {
//                                        mLegendOneGreen.animate()
//                                                .scaleY(1.0f)
//                                                .scaleX(1.0f)
//                                                .setDuration(100);
//                                    }
//                                });
//                    }
//                }
//            });

        Paint thresPaint = new Paint();
        thresPaint.setColor(Color.parseColor("#dad8d6"));
        thresPaint.setPathEffect(new DashPathEffect(new float[]{10, 20}, 0));
        thresPaint.setStyle(Paint.Style.STROKE);
        thresPaint.setAntiAlias(true);
        thresPaint.setStrokeWidth(Tools.fromDpToPx(.75f));

        BarSet stackBarSet = new BarSet(mLabelsOne, mValuesOne[0]);
        stackBarSet.setColor(Color.parseColor("#a1d949"));
        stackedChart.addData(stackBarSet);

        stackBarSet = new BarSet(mLabelsOne, mValuesOne[1]);
        stackBarSet.setColor(Color.parseColor("#ffcc6a"));
        stackedChart.addData(stackBarSet);

        stackBarSet = new BarSet(mLabelsOne, mValuesOne[2]);
        stackBarSet.setColor(Color.parseColor("#ff7a57"));
        stackedChart.addData(stackBarSet);

        stackedChart.setBarSpacing(Tools.fromDpToPx(15));
        stackedChart.setRoundCorners(Tools.fromDpToPx(1));

        stackedChart.setXAxis(false)
                .setXLabels(XController.LabelPosition.OUTSIDE)
                .setYAxis(false)
                .setYLabels(YController.LabelPosition.NONE)
                .setThresholdLine(89, thresPaint);

        int[] order = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        stackedChart.show(new Animation()
                .setOverlap(.5f, order)
                .setEndAction(action))
        //.show()
        ;
    }

    private void updateOne(ChartView chart){

        chart.updateValues(0, mValuesOne[2]);
        chart.updateValues(1, mValuesOne[0]);
        chart.updateValues(2, mValuesOne[1]);
        chart.notifyDataUpdate();
    }

    private static void dismissOne(ChartView chart, Runnable action){
        int[] order = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        chart.dismiss(new Animation()
                .setOverlap(.5f, order)
                .setEndAction(action));
    }



    /**
     *
     * Chart 2
     *
     */

    private void produceTwo(ChartView chart, Runnable action){
        HorizontalStackBarChartView horChart = (HorizontalStackBarChartView) chart;

        BarSet barSet = new BarSet(mLabelsTwo, mValuesTwo[0]);
        barSet.setColor(Color.parseColor("#90ee7e"));
        horChart.addData(barSet);

        barSet = new BarSet(mLabelsTwo, mValuesTwo[1]);
        barSet.setColor(Color.parseColor("#2b908f"));
        horChart.addData(barSet);

        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.parseColor("#e7e7e7"));
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setAntiAlias(true);
        gridPaint.setStrokeWidth(Tools.fromDpToPx(.7f));

        horChart.setBarSpacing(Tools.fromDpToPx(8));

        horChart.setBorderSpacing(0)
                .setGrid(ChartView.GridType.VERTICAL, gridPaint)
                .setXAxis(false)
                .setYAxis(false)
                .setLabelsFormat(new DecimalFormat("##'M'"))
                .show(new Animation()
                        .setDuration(2500)
                        .setEasing(new ExpoEase())
                        .setEndAction(action))
        ;
    }



    private void updateTwo(ChartView chart){

        float [][] values = { {-1.8f, -2f, -2.4f, -2.2f, -3.3f, -3.45f},
                {1.8f, 2.1f, 2.55f, 2.40f, 3.40f, 3.5f}};
        chart.updateValues(0, values[1]);
        chart.updateValues(1, values[0]);
        chart.notifyDataUpdate();
    }

    private static void dismissTwo(ChartView chart, Runnable action){
        chart.dismiss(new Animation()
                .setDuration(2500)
                .setEasing(new ExpoEase())
                .setEndAction(action));
    }

    /**
     *
     * Chart 2
     *
     */

    public void produceThree(ChartView chart, Runnable action){
        HorizontalBarChartView horChart = (HorizontalBarChartView) chart;

        Tooltip tip = new Tooltip(getActivity(), R.layout.barchart_two_tooltip);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            tip.setEnterAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 1));
            tip.setExitAnimation(PropertyValuesHolder.ofFloat(View.ALPHA,0));
        }

        horChart.setTooltips(tip);


        horChart.setOnEntryClickListener(new OnEntryClickListener() {
            @Override
            public void onClick(int setIndex, int entryIndex, Rect rect) {

                mTextViewThree.setText(chartData2[ 6 - entryIndex]+"");
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    mTextViewThree.animate().alpha(1).setDuration(200);
                    mTextViewMetricThree.animate().alpha(1).setDuration(200);
                }else{
                    mTextViewThree.setVisibility(View.VISIBLE);
                    mTextViewMetricThree.setVisibility(View.VISIBLE);
                }
            }
        });

        horChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    mTextViewThree.animate().alpha(0).setDuration(100);
                    mTextViewMetricThree.animate().alpha(0).setDuration(100);
                }else{
                    mTextViewThree.setVisibility(View.INVISIBLE);
                    mTextViewMetricThree.setVisibility(View.INVISIBLE);
                }
            }
        });


        BarSet barSet = new BarSet();
        Bar bar;
        for(int i = 0; i < mLabelsThree.length; i++){
            bar = new Bar(mLabelsThree[i], mValuesThree[i]);
            if(i == mLabelsThree.length - 1 )
                bar.setColor(Color.parseColor("#b26657"));
            else if (i == 0)
                bar.setColor(Color.parseColor("#998d6e"));
            else
                bar.setColor(Color.parseColor("#506a6e"));
            barSet.addBar(bar);
        }
        horChart.addData(barSet);
        horChart.setBarSpacing(Tools.fromDpToPx(5));

        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.parseColor("#aab6b2ac"));
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setAntiAlias(true);
        gridPaint.setStrokeWidth(Tools.fromDpToPx(.75f));

        horChart.setBorderSpacing(0)
                .setAxisBorderValues(0, 100, 5)
                .setGrid(HorizontalBarChartView.GridType.FULL, gridPaint)
                .setXAxis(false)
                .setYAxis(false)
                .setLabelsColor(Color.parseColor("#FF8E8A84"))
                .setXLabels(XController.LabelPosition.NONE);

        int[] order = {6, 5, 4, 3, 2, 1, 0};
        horChart.show(new Animation()
                .setOverlap(.5f, order)
                .setEndAction(action))
        ;
    }

    public void updateThree(ChartView chart){

        chart.dismissAllTooltips();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            mTextViewThree.animate().alpha(0).setDuration(100);
            mTextViewMetricThree.animate().alpha(0).setDuration(100);
        }else{
            mTextViewThree.setVisibility(View.INVISIBLE);
            mTextViewMetricThree.setVisibility(View.INVISIBLE);
        }

        float[] valuesTwoOne = {17f, 26f, 48f, 63f, 94f};
        chart.updateValues(0, valuesTwoOne);
        chart.notifyDataUpdate();
    }

    public void dismissThree(ChartView chart, Runnable action){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            mTextViewThree.animate().alpha(0).setDuration(100);
            mTextViewMetricThree.animate().alpha(0).setDuration(100);
        }else{
            mTextViewThree.setVisibility(View.INVISIBLE);
            mTextViewMetricThree.setVisibility(View.INVISIBLE);
        }

        chart.dismissAllTooltips();

        int[] order = {0, 1, 2, 3, 4};
        chart.dismiss(new Animation()
                .setOverlap(.5f, order)
                .setEndAction(action));
    }

    @Override
    public void setData(Object data) {

        try {
            int[][] result = (int[][]) data;
            for (int i=0; i<3; i++)
                for (int j = 0; j<12; j++)
                    mValuesOne[i][j] = result[i][j];


            presenter.getDataByWeek(Integer.parseInt(TimeUtil.getCurrentYearInString()), data1 -> setChartData2(data1));

        }catch (Exception e){

        }

    }

    public void setChartData2(Object chartData2) {
        try {

            int[] result = (int[]) chartData2;

            this.chartData2 = result;

            float sum = 0;

            for (int i = 0; i < result.length; i++) {
                sum += result[i];
            }

            for (int i = 0; i < result.length; i++) {

                mValuesThree[i] = 80 * (result[i] / sum);

            }

            presenter.getDataByHour(Integer.parseInt(TimeUtil.getCurrentYearInString()), data2 -> setChartData3(data2));

        }catch (Exception e){

        }
    }

    public void setChartData3(Object chartData3) {
        try{

            int[][] result = (int[][]) chartData3;

            for (int i = 0; i < 2; i++)
                for (int j = 0; j < 6; j++)
                    mValuesTwo[i][j] = result[i][j];


            showChart(0, mChartOne, mPlayOne);
            showChart(1, mChartTwo, mPlayTwo);
            showChart(2, mChartThree, mPlayThree);

        }catch (Exception e){

        }
    }

    public void showAlertDialog(){

    }
}
