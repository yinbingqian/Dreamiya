package com.sxit.dreamiya.http;

import java.lang.reflect.Method;

import com.sxit.dreamiya.common.Common.ResultType;


public class RdaResultPack {

	private String message = "";
	private String serverError = "";
	private Object successData = null;
	private ResultType resultType = null;
	private BaseRdaHttp rdaHttp = null;
	private Method method = null;

	public void setServerError(String serverError) {
		this.serverError = serverError;
	}

	public boolean HttpFail() {
		return this.resultType.equals(ResultType.HTTPFAIL);
	}

	public boolean ServerError() {
		return this.resultType.equals(ResultType.SERVERERROR);
	}

	public boolean Success() {
		return this.resultType.equals(ResultType.SUCCESS);
	}

	public boolean Finish() {
		return this.resultType.equals(ResultType.FINISH);
	}

	public boolean Method(String methodName) {
		return this.method.getName().equals(methodName);
	}

	public boolean Match(BaseRdaHttp _class, String _method) {
		if (_class==this.rdaHttp && Method(_method))
			return true;
		else
			return false;
	}

	public Object SuccessData() {
		return this.successData;
	}

	public String Message() {
		return this.message == "" ? serverError : message;
	}

	public void setRdaHttp(BaseRdaHttp rdaHttp) {
		this.rdaHttp = rdaHttp;
	}

	public void setSuccessData(Object successData) {
		this.successData = successData;
	}

	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Method getMethod() {
		return method;
	}

	public BaseRdaHttp getRdaHttp() {
		return rdaHttp;
	}
	
	
	

}
