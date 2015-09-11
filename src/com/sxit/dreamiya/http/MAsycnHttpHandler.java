package com.sxit.dreamiya.http;

import java.lang.reflect.Method;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sxit.dreamiya.common.Common.ResultType;
import com.sxit.dreamiya.config.MLog;
import com.sxit.dreamiya.eventbus.EBCache;

public class MAsycnHttpHandler extends AsyncHttpResponseHandler {

	private RdaResultPack httpPack;
	private String mRemoteMethod = "";
	private String TAG = "SxAsycnHttpHandler";

	public MAsycnHttpHandler(BaseRdaHttp currHttp, Method currMethod) {
		this.httpPack = new RdaResultPack();
		this.mRemoteMethod = currMethod.getName();
		httpPack.setRdaHttp(currHttp);
		httpPack.setMethod(currMethod);
	}

	public void postSuccessData(Object data) {
		MLog.I(TAG, mRemoteMethod + " -------->  请求成功！");
		httpPack.setResultType(ResultType.SUCCESS);
		httpPack.setSuccessData(data);
		EBCache.EB_HTTP.post(httpPack);
	}

	public void postServerError(String data) {
	    MLog.I(TAG, mRemoteMethod + " -------->  请求成功但是服务器处理失败！");
		httpPack.setResultType(ResultType.SERVERERROR);
		httpPack.setMessage(data.toString());
		EBCache.EB_HTTP.post(httpPack);
	}

	@Override
	public void onFailure(Throwable arg) {
	    MLog.E(TAG, mRemoteMethod + " -------->  请求失败:" + arg.getMessage());
		httpPack.setResultType(ResultType.HTTPFAIL);
		httpPack.setMessage("请求失败:" + arg.getMessage());
		EBCache.EB_HTTP.post(httpPack);

	}

	@Override
	public void onFinish() {
		// TODO Finish unpost;
		// SxLog.I(TAG, mRemoteMethod + " -------->  请求完成！");
		// httpPack.setResultType(ResultType.FINISH);
		// EBCache.EB_HTTP.post(httpPack);
	}

}
