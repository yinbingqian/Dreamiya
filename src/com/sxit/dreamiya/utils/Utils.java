package com.sxit.dreamiya.utils;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sxit.dreamiya.R;
import com.sxit.dreamiya.dialog.SXProgressDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.widget.Toast;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 工具类
 * 
 * @author huanyu 类名称：Utils 创建时间:2014-10-26 下午11:15:47
 */
public class Utils {
	public static Toast toast;

	/**
	 * 消除Toast显示重复
	 * 
	 * @param context
	 * @param msg
	 *            提示语
	 */
	public static void showTextToast(Context context, String msg) {
		if (toast == null) {
			toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}

	public static String getImei(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

	public static String getSystemDate() {
		SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String a1 = dateformat1.format(new java.util.Date());
		return a1;
	}

	/**
	 * 返回屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getWindowWidth(Context context) {
		DisplayMetrics dm = new android.util.DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 返回屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getWindowHeight(Context context) {
		DisplayMetrics dm = new android.util.DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	/**
	 * 获取汉子首字母
	 * 
	 * @param chines
	 * @return
	 */
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	/**
	 * raw下文件读取
	 * 
	 * @param context
	 * @param res
	 * @return
	 */
	public static JSONObject getObject(Context context, int res) {
		try {
			InputStream is = context.getResources().openRawResource(res);
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			String json = new String(buffer, "UTF-8");
			// 将字符串json转换为json对象，以便于取出数据
			JSONObject jsonObject = new JSONObject(json);
			return jsonObject;
		} catch (Exception e) {
			return null;
		}
	}

	public static JSONArray getProvinces(Context context) {
		// List<String> provinces = new ArrayList<String>();
		String json = getObject(context, R.raw.province_city).toString();
		JSONObject jo = getObject(context, R.raw.province_city);
		JSONArray jsonArray;
		try {
			jsonArray = jo.getJSONArray("provinces");
			return jsonArray;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// try {
		// jsonArray = jo.getJSONArray("provinces");
		// for (int i = 0; i < jsonArray.length(); i++) {
		// JSONObject province = (JSONObject) jsonArray.get(i);
		// String pStr = province.get("name").toString();
		// System.out.println(">>>>   " + pStr);
		// // provinces.add(pStr);
		// }
		// // System.out.println(">>> province city json :" + jsonArray);
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public static String[] getCitys(String province, JSONArray jsonArray) {
		String[] citys;
		JSONArray city_jsonArray = null;
		JSONObject jo = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				jo = (JSONObject) jsonArray.get(i);
				if (jo.getString("name").toString().equals(province)) {
					city_jsonArray = (JSONArray) jo.get("citys");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		citys = new String[city_jsonArray.length()];
		for (int j = 0; j < city_jsonArray.length(); j++) {
			try {

				String[] strs = city_jsonArray.get(j).toString().split("[:]");
				String temp_str = strs[1];
				temp_str = temp_str.substring(temp_str.indexOf("\"") + 1, temp_str.lastIndexOf("\""));
				// citys[j] = city_jsonArray.get(j).toString();
				citys[j] = temp_str;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return citys;
	}

	public static String getVersionName(Context context) {
		String version = "";
		// 获取packagemanager的实例
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			version = packInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			version = "未知版本信息";

		}
		System.out.println(">>> version : " + version);
		return version;
	}

	public static boolean ExistSDCard() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else
			return false;
	}

	// AlertDialog
	public static Dialog cv_dialog;

	public static void showRechargeDialog(Context context) {
//		TipChargeDialog.Builder builder = new TipChargeDialog.Builder(context);
//		cv_dialog = builder.create();
//		cv_dialog.setCancelable(true);
//		cv_dialog.show();
	}

	// ProgressDialog
	public static SXProgressDialog progressDialog;

	/**
	 * 显示 progressDialog
	 * 
	 * @param context
	 * @param msg
	 * @param closeable
	 */
	public static void showProgressDialog(Context context, String msg, boolean closeable) {
		if (progressDialog != null && progressDialog.isShowing() == true) {
			return;
		} else {
			progressDialog = SXProgressDialog.createDialog(context);
			progressDialog.setMessage(msg);
			progressDialog.setCloseable(closeable);
			progressDialog.show();
		}

	}

	/**
	 * 移除 progressDialog
	 */
	public static void removeProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

	public static String getMsgDateString(String msgdate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now;
		StringBuffer sb = new StringBuffer();
		try {
			now = new Date();
			java.util.Date date = df.parse(msgdate);
			long l = now.getTime() - date.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

			// StringBuffer sb = new StringBuffer();
			// sb.append("发表于：");
			if (day > 0) {

				if (day > 4) {
					sb.append(msgdate);
				} else {
					sb.append(day + "天前");
				}
			} else {
				if (hour > 0) {
					sb.append(hour + "小时前");
				} else {
					if (min > 0) {
						sb.append(min + "分钟前");
					} else {
						sb.append("刚刚");
					}

				}
			}

			System.out.println(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();

	}

	public static String vcode() {
		int resInt = (int) (Math.random() * 8999+1000);
		return resInt+"";
	}
}
