package com.hufeiya.homework_daily.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by Robert on 2015/9/21.
 */
public class VersionUtil {

    public static String getVersionName(Context context)
    {
        String versionName ="";
        try
        {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.COMPONENT_ENABLED_STATE_DEFAULT).versionName;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return versionName;
    }
}
