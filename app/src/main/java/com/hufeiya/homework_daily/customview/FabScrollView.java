package com.hufeiya.homework_daily.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.hufeiya.homework_daily.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by hufeiya on 16/4/26.
 */
public class FabScrollView extends ScrollView {
    public FabScrollView(Context context) {
        super(context);
    }

    public FabScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(t == 0){
            EventBus.getDefault().post(new MessageEvent(MessageEvent.SHOW_COURSE_FAB));
        }else if(oldt == 0){
            EventBus.getDefault().post(new MessageEvent(MessageEvent.HIDE_COURSE_FAB));
        }
    }

}
