package com.sxit.dreamiya.config;

import android.util.Log;

public class MLog {
    
    public static void I(String tag, String msg) {
        if (Configs.DEBUG && msg != null && tag != null)
            Log.i(tag, msg);
    }

    public static void I(String msg) {
        if (Configs.DEBUG && msg != null)
            Log.i("DREAMIYA", msg);
    }

    public static void E(String tag, String msg) {
        if (Configs.DEBUG && msg != null && tag != null)
            Log.e(tag, msg);
    }

    public static void E(String msg) {
        if (Configs.DEBUG && msg != null)
            Log.e("DREAMIYA", msg);
    }

}
