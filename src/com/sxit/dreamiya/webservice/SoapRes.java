package com.sxit.dreamiya.webservice;

/**
 * soap 响应类
 * 
 * @author huanyu 类名称：SoapRes 创建时间:2014-11-4 下午8:09:36
 */
public class SoapRes {
	private Object obj;// eventBus接受类
	private String code;// 请求码
	private boolean isPage;// 是否做分页

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isPage() {
		return isPage;
	}

	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}

}
