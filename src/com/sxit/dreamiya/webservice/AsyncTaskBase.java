package com.sxit.dreamiya.webservice;

import org.ksoap2.serialization.SoapObject;

import com.sxit.dreamiya.utils.Utils;

import android.os.AsyncTask;

/**
 * 异步请求类 Base
 * 
 * @author huanyu 类名称：AsyncTaskBase 创建时间:2014-11-4 下午7:15:11
 */
public class AsyncTaskBase {
	public String[] property_nm;// key
	public Object[] property_va;// value
	public String method;
	public SoapObjectResult soapResult;
	/**
	 * 异步请求类
	 * @author huanyu	
	 * 类名称：AsyncTaskCom   
	 * 创建时间:2014-11-4 下午8:08:01
	 */
	class AsyncTaskCom extends AsyncTask<Object, Object, SoapObject> {


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected SoapObject doInBackground(Object... params) {
			Object res_obj = SoapWebService.data(method, property_nm, property_va);
			if (null != res_obj) {
				SoapObject so = (SoapObject) res_obj;
				return so;
			} else {
				return null;
			}

		}

		@Override
		protected void onPostExecute(SoapObject result) {
			Utils.removeProgressDialog();
			if (result == null) {
				soapResult.soapError();
			} else {
				soapResult.soapResult(result);
			}
			super.onPostExecute(result);
		}
	}

	/**
	 * this execute
	 */
	public void executeDo(SoapObjectResult soapResult) {
		this.soapResult = soapResult;
		new AsyncTaskCom().execute();
	}

	public void setProperty_nm(String[] property_nm) {
		this.property_nm = property_nm;
	}

	public void setProperty_va(Object[] property_va) {
		this.property_va = property_va;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setSoapResult(SoapObjectResult soapResult) {
		this.soapResult = soapResult;
	}

	/**
	 * soap回调接口
	 * 
	 * @author huanyu 类名称：SoapObjectResult 创建时间:2014-11-4 下午7:39:05
	 */
	public interface SoapObjectResult {
		void soapResult(SoapObject obj);

		void soapError();
	}
}
