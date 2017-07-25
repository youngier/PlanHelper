package com.special.ResideMenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/15  11:22
 */


public class ResideMenuItem01 extends LinearLayout {

    /** menu item  icon  */
    private CircleImageView iv_icon;

    public ResideMenuItem01(Context context) {
        super(context);
        initViews(context);
    }

    public ResideMenuItem01(Context context, int title) {
        super(context);
        initViews(context);
        iv_icon.setImageResource(title);
    }


    public ResideMenuItem01(Context context, int icon, int title) {
        super(context);
        initViews(context);
        iv_icon.setImageResource(icon);
    }

    public ResideMenuItem01(Context context, int icon, String title) {
        super(context);
        initViews(context);
        iv_icon.setImageResource(icon);
    }

    private void initViews(Context context){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.residemenu_item_01, this);
        iv_icon = (CircleImageView) findViewById(R.id.iv_icon);
    }

    /**
     * set the icon color;
     *
     * @param icon
     */
    public void setIcon(int icon){
        iv_icon.setImageResource(icon);
    }

    public void setIconByLocal(Bitmap bitmap) {
        iv_icon.setImageBitmap(bitmap);
    }

    public ImageView getIconView() {
        return iv_icon;
    }
}
