package com.sxit.dreamiya.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sxit.dreamiya.config.Configs;

public class MHttpClient {

	private static AsyncHttpClient client = new AsyncHttpClient();
	public static final String BASE_URL = Configs.WEB_URL;

	public static void get(String function, String paramStr, AsyncHttpResponseHandler responseHandler) {

		if (paramStr == null || paramStr.equals("") || paramStr == "") {
			client.get(getAbsoluteUrl(function), responseHandler);
		} else {
			RequestParams params = new RequestParams("params", paramStr);
			client.get(getAbsoluteUrl(function), params, responseHandler);
		}

	}

	public static void post(String function, String paramStr, AsyncHttpResponseHandler responseHandler) {

		if (paramStr == null || paramStr.equals("") || paramStr == "") {
			client.post(getAbsoluteUrl(function), responseHandler);
		} else {
			RequestParams params = new RequestParams("params", paramStr);
			client.post(getAbsoluteUrl(function), params, responseHandler);
		}
	}

	public static void post(String function, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		if (params != null) {
			client.post(getAbsoluteUrl(function), params, responseHandler);
		}
	}
	
	public static void uploadPic(String uploadUrl, RequestParams params , AsyncHttpResponseHandler responseHandler) {
		if (params != null) {
			client.setTimeout(Configs.HTTP_TIMEOUT);
			client.post(Configs.WEB_BASE_UPLOAD_URL + uploadUrl , params, responseHandler);
		}
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		client.setTimeout(Configs.HTTP_TIMEOUT);
		return BASE_URL + relativeUrl;
	}

}
