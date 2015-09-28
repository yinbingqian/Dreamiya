package com.sxit.dreamiya.utils;

import java.math.BigDecimal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;

public class TrafficMonitoring {
	Context context;
	ConnectivityManager cm ;
	NetworkInfo nwi;
	long lastTraffic = 0;
	long currentTraffic;

	// ���캯��
	public TrafficMonitoring() {
	}

	public TrafficMonitoring(Context context) {
		this.context = context;
		cm =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);	
		nwi = cm.getActiveNetworkInfo();
	}

	// ��ȡ��ǰ�ֻ���������ͣ�����String
	public int getNetType() {
		if(nwi != null){
			String net = nwi.getTypeName();
			if(net.equals("WIFI")){
				return 0;
			}else {
				return 1;
			}
		}else {
			return -1;
		}
	}	

	// ��ѯ�ֻ�������
	public static long traffic_Monitoring() {
		long recive_Total = TrafficStats.getTotalRxBytes();
		long send_Total = TrafficStats.getTotalTxBytes();
		long total = recive_Total + send_Total;
		return total;
	}

	//��ѯ�ֻ��Mobile��������
	public static long mReceive(){
		return  TrafficStats.getMobileRxBytes();
	}
	
	//��ѯ�ֻ��Mobile��������
	public static long mSend(){
		return  TrafficStats.getMobileTxBytes();
	}
	
	//��ѯ�ֻ��WIFI��������
	public static long wSend(){
		return  TrafficStats.getTotalTxBytes() - TrafficStats.getMobileTxBytes();
	}
	
	//��ѯ�ֻ�Wifi����������
	public static long wReceive(){
		return TrafficStats.getTotalTxBytes() - TrafficStats.getMobileRxBytes();
	}

	// ��ѯĳ��Uid������ֵ
	public static long monitoringEachApplicationReceive(int uid) {
		return TrafficStats.getUidRxBytes(uid);
	}

	// ��ѯĳ��Uid������ֵ
	public static long monitoringEachApplicationSend(int uid) {
		return TrafficStats.getUidTxBytes(uid);
	}

	// ����ת��
	public static String convertTraffic(long traffic) {
		BigDecimal trafficKB;
		BigDecimal trafficMB;
		BigDecimal trafficGB;

		BigDecimal temp = new BigDecimal(traffic);
		BigDecimal divide = new BigDecimal(1000);
		trafficKB = temp.divide(divide, 2, 1);
		if (trafficKB.compareTo(divide) > 0) {
			trafficMB = trafficKB.divide(divide, 2, 1);
			if (trafficMB.compareTo(divide) > 0) {
				trafficGB = trafficMB.divide(divide, 2, 1);
				return trafficGB.doubleValue()+"GB";
			} else {
				return trafficMB.doubleValue()+"MB";
			}
		} else {
			return trafficKB.doubleValue()+"KB";
		}
	}
	

	// ����ת��
	public static long convertLongTraffic(long traffic) {
		BigDecimal trafficKB;
		BigDecimal trafficMB;
		BigDecimal trafficGB;

		BigDecimal temp = new BigDecimal(traffic);
		BigDecimal divide = new BigDecimal(1000);
		trafficKB = temp.divide(divide, 2, 1);
		if (trafficKB.compareTo(divide) > 0) {
			trafficMB = trafficKB.divide(divide, 2, 1);
			if (trafficMB.compareTo(divide) > 0) {
				trafficGB = trafficMB.divide(divide, 2, 1);
				return (long) trafficGB.doubleValue();
			} else {
				return (long) trafficMB.doubleValue();
			}
		} else {
			return (long) trafficKB.doubleValue();
		}
	}
}
