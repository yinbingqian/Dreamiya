package com.sxit.dreamiya.webservice;

import org.ksoap2.serialization.SoapObject;

import com.sxit.dreamiya.utils.EventCache;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.webservice.AsyncTaskBase.SoapObjectResult;

public class SoapService implements ISoapService {
	private AsyncTaskBase asynTaskBase = new AsyncTaskBase();
	private SoapRes soapRes = new SoapRes();

	@Override
	public void userLogin(Object[] property_va) {
		String[] property_nm = { "name", "password" };
		asynTaskBase.setMethod(SOAP_UTILS.METHOD.ADMINLOGIN);
		asynTaskBase.setProperty_nm(property_nm);
		asynTaskBase.setProperty_va(property_va);
		asynTaskBase.executeDo(new SoapObjectResult() {

			@Override
			public void soapResult(SoapObject obj) {
//				LoginUser loginUser = null;
//				SoapObject soapchild = (SoapObject) obj.getProperty(0);
//				if (soapchild.getProperty("Id").toString().equals("0")) {
//
//				} else {
//					loginUser = new LoginUser();
//					loginUser.setUserid(soapchild.getProperty("Id").toString());
//					loginUser.setGroupid(soapchild.getProperty("GroupId")
//							.toString());
//					loginUser.setName(soapchild.getProperty("Name").toString());
//					loginUser.setPassword(soapchild.getProperty("Password")
//							.toString());
//					loginUser.setRealname(soapchild.getProperty("RealName")
//							.toString());
//					loginUser.setMobilephone(soapchild.getProperty(
//							"Mobilephone").toString());
//					loginUser.setEmail(soapchild.getProperty("Email")
//							.toString());
//					loginUser.setCrtime(soapchild.getProperty("CrTime")
//							.toString());
//					loginUser.setIslock(soapchild.getProperty("Islock")
//							.toString());
//					loginUser.setSex(soapchild.getProperty("Sex").toString());
//					loginUser.setOrgid(soapchild.getProperty("Orgid")
//							.toString());
//					loginUser.setSpecialty(soapchild.getProperty("Specialty")
//							.toString());
//					loginUser.setResume(soapchild.getProperty("Resume")
//							.toString());
//					loginUser.setLevel(soapchild.getProperty("Level")
//							.toString());
//					loginUser.setStatus(soapchild.getProperty("Status")
//							.toString());
//					loginUser.setMark(soapchild.getProperty("Mark").toString());
//					loginUser.setRewardmark(soapchild.getProperty("Rewardmark")
//							.toString());
//					loginUser.setPaidmark(soapchild.getProperty("Paidmark")
//							.toString());
//					loginUser.setHeadpic(soapchild.getProperty("HeadPic")
//							.toString());
//					loginUser.setPtitle(soapchild.getProperty("PTitle")
//							.toString());
//					loginUser.setOrgname(soapchild.getProperty("OrgName")
//							.toString());
//					MyApplication.getInstance().setA_heartcount(
//							soapchild.getProperty("Heartcount").toString());
//				}
//				soapRes.setObj(loginUser);
//				soapRes.setCode(SOAP_UTILS.METHOD.ADMINLOGIN);
//				EventCache.commandActivity.post(soapRes);
			}

			@Override
			public void soapError() {
				soapRes.setObj(null);
				soapRes.setCode(SOAP_UTILS.METHOD.ADMINLOGIN);
				EventCache.commandActivity.post(soapRes);
			}
		});
	}

}
