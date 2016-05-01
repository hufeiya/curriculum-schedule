package com.hufeiya.homework_daily.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Robert on 2015/9/21.
 */
public class NetworkState {

    public static boolean isNetworkAvailable(final Context context, final boolean isQuit)
    {
        boolean result = false;
        try
        {
            if (context != null)
            {
                ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager != null)
                {
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isAvailable())
                    {
                        if (networkInfo.getState() == NetworkInfo.State.CONNECTED){
                            result=true;
                        }
                    }
                    else
                    {
                        new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                                .setTitle("网络设置")//设置标题
                                .setMessage("无网络可用！请先开启网络连接！")//设置内容
                                .setNeutralButton("好的", new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int whichButton)
                                    {

                                        if (isQuit)
                                        {
                                            //MobclickAgent.onKillProcess(context);
                                            System.exit(0);
                                        }
                                    }
                                }).show();//显示对话框
                        result = false;
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
