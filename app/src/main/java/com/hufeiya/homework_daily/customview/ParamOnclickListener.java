package com.hufeiya.homework_daily.customview;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hufeiya.homework_daily.R;

/**
 * Created by hufeiya on 16/4/26.
 */
public class ParamOnclickListener implements View.OnClickListener {

    private int i,j;

    public  ParamOnclickListener(int i,int j){
        this.i = i;
        this.j = j;
    }
    @Override
    public void onClick(View v) {
        Log.d("fuck","" + i + "  " + j);
        int height = v.getHeight();
        v.setBackgroundResource(R.drawable.ic_add_circle_black_24dp);
        ((Button)v).setHeight(height);
        v.setAlpha(0.5f);
    }
}
