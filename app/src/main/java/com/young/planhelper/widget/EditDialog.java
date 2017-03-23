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

import static com.litao.android.lib.R.attr.title;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/3/19  22:08
 */


public class EditDialog extends Dialog{

    private String mTitle;
    private Context mContext;

    private OnConfirmListener onConfirmListener;
    private EditText mContentEt;

    public EditDialog(Context context, String title) {
        super(context);
        this.mContext = context;
        this.mTitle = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_dailog_edit, null);
        setContentView(view);

        TextView titleTv = (TextView) view.findViewById(R.id.tv_dialog_edit_title);
        mContentEt = (EditText) view.findViewById(R.id.et_dialog_edit_content);
        Button confirmBtn = (Button) view.findViewById(R.id.btn_dialog_edit_confirm);
        Button cancelBtn = (Button) view.findViewById(R.id.btn_dialog_edit_cancel);

        titleTv.setText(mTitle);

        confirmBtn.setOnClickListener( v -> {
            if(TextUtils.isEmpty(mContentEt.getText().toString())){
                Toast.makeText(mContext, "标题不能为空", Toast.LENGTH_SHORT).show();
                return;
            }else{
                onConfirmListener.confirm();
                this.dismiss();
            }
        } );
        cancelBtn.setOnClickListener( v -> {
            this.dismiss();
        } );


        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContentEt.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.height = DensityUtil.dipToPixels(mContext, 200);
        dialogWindow.setAttributes(lp);
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    public String getContent() {
        return mContentEt.getText().toString();
    }

    public interface OnConfirmListener{
        void confirm();
    }
}
