package com.young.planhelper.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.young.planhelper.R;
import com.young.planhelper.util.DensityUtil;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/3/19  22:08
 */


public class NewAlertDialog extends Dialog{

    public static int ALERT = 0;
    public static int OTHER = 1;

    private String mTitle;
    private String mContent;
    private Context mContext;

    private TextView mContentTv;

    private int type = ALERT;

    private OnDialogClickListener onDialogClickListener;

    public NewAlertDialog(Context context, String title, String content, int type) {
        super(context);
        this.mContext = context;
        this.mTitle = title;
        this.mContent = content;
        this.type = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_dailog_new_alert, null);
        setContentView(view);

        TextView titleTv = (TextView) view.findViewById(R.id.tv_dialog_alert_title);
        mContentTv = (TextView) view.findViewById(R.id.tv_dialog_alert_content);
        Button confirmBtn = (Button) view.findViewById(R.id.btn_dialog_alert_confirm);

        titleTv.setText(mTitle);
        mContentTv.setText(mContent);

        confirmBtn.setOnClickListener( v -> {
            if( type == ALERT )
                dismiss();
            else if( type == OTHER ) {
                dismiss();
                onDialogClickListener.onDialogClick();
            }
        } );


        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContentTv.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.height = DensityUtil.dipToPixels(mContext, 200);
        dialogWindow.setAttributes(lp);
    }

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    public interface OnDialogClickListener{
        void onDialogClick();
    }

}
