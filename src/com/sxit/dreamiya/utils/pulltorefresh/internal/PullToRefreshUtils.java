package com.sxit.dreamiya.utils.pulltorefresh.internal;

import android.util.Log;

public class PullToRefreshUtils {

	static final String LOG_TAG = "PullToRefresh";

	public static void warnDeprecation(String depreacted, String replacement) {
		Log.w(LOG_TAG, "You're using the deprecated " + depreacted + " attr, please switch over to " + replacement);
	}

}
