package com.solarexsoft.simpleimageloader.utils;

import android.util.Log;

import com.solarexsoft.simpleimageloader.BuildConfig;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public class L {
    public static void d(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }
}
