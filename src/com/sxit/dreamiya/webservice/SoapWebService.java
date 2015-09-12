package com.sxit.dreamiya.webservice;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.sxit.dreamiya.utils.SOAP_UTILS;

import android.util.Log;

public class SoapWebService {
	/**
	 * 
	 * @param method SOAP_UTILS
	 * @param property_nm
	 * @param property_va
	 * @return
	 */
	public static Object data(String method, String[] property_nm, Object[] property_va) {
		String comurl = SOAP_UTILS.URL;
		String commethodname = method;
		String comnamespace = SOAP_UTILS.NAMESPACE;
		String comsoapaction = comnamespace + "/" + commethodname;

		SoapObject rpc = new SoapObject(comnamespace, commethodname);
		for (int i = 0; i < property_nm.length; i++) {
			rpc.addProperty(property_nm[i], property_va[i]);
			System.out.println(">>> : "+property_nm[i]+"  : "+property_va[i]);
		}
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(rpc);
		HttpTransportSE ht = new HttpTransportSE(comurl);
		ht.debug = true;
		try {
			ht.call(comsoapaction, envelope);
//			SoapObject commu = (SoapObject) envelope.bodyIn;
			return (SoapObject) envelope.bodyIn;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("TOPIC GET DATA ERROR : ", e.toString());
			return null;
		}
	}
}
