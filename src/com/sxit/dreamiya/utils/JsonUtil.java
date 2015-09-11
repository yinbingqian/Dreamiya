package com.sxit.dreamiya.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.sxit.dreamiya.common.Instance;
import com.sxit.dreamiya.config.MLog;

//TODO UnTest
public class JsonUtil {

	private static String TAG = "JsonUtil";

	public static String getErrorCode(String json) {
		String result = JsonUtil.GetStringByLevel(json, "resultInfo", "errorcode");
		return result == null || result == "" ? "E000" : result;
	}

	/**
	 * 
	 * @param json
	 * @param c
	 * @return
	 */
	public static Object jsonToBean(String json, Class<?> c) {
		Gson gson = new Gson();
		Object object = gson.fromJson(json, c);
		return object;
	}

	public static boolean isSuccess(String data) {

		boolean result = false;
		try {
			if (data != null && data != "") {
				JsonObject json = Instance.gson.fromJson(data, JsonObject.class);
				result = json.getAsJsonPrimitive("resultType").toString().replace('"', ' ').trim().equals("0") ? true : false;
			}
		} catch (JsonSyntaxException e) {
			MLog.E(TAG, "isSuccess 发生异常：" + e.getMessage());
		}
		return result;
	}

	public static JsonObject GetJsonObjByLevel(String data, String... param) {
		JsonObject json = null;
		try {
			if (data != null && data != "") {
				json = Instance.gson.fromJson(data, JsonObject.class);
				if (param != null && param.length > 0) {
					for (String item : param) {
						json = json.getAsJsonObject(item);
					}
				}
			}
		} catch (Exception e) {
		    MLog.E(TAG, "GetJsonObjByLevel 发生异常：" + e.getMessage());
			json = null;
		}
		return json;
	}

	public static String GetStringByLevel(String data, String... param) {
		JsonObject json = null;
		String result = null;
		try {
			if (data != null && data != "") {
				json = Instance.gson.fromJson(data, JsonObject.class);
				if (param != null && param.length > 0) {
					for (int i = 0; i < param.length - 1; i++) {
						json = json.getAsJsonObject(param[i]);
					}
				}
			}
			if (json != null) {
				result = json.getAsJsonPrimitive(param[param.length - 1]).toString();
			}
		} catch (Exception e) {
		    MLog.E(TAG, "GetStringByLevel 发生异常：" + e.getMessage());
		}

		return result == null ? null : result.replace('"', ' ').trim();
	}

	public static JsonArray GetJsonArrayByLevel(String data, String... param) {
		JsonArray jsonArray = null;
		JsonObject json = null;
		try {
			if (data != null && data != "") {
				json = Instance.gson.fromJson(data, JsonObject.class);
				if (param != null && param.length > 0) {
					for (int i = 0; i < param.length - 1; i++) {
						json = json.getAsJsonObject(param[i]);
					}
					jsonArray = json.getAsJsonArray(param[param.length - 1]);
				}
			}
		} catch (Exception e) {
		    MLog.E(TAG, "GetJsonArrayByLevel 发生异常：" + e.getMessage());
		}
		return jsonArray;
	}

	public static <T> T GetEntity(JsonObject ele, Class<T> classT) {
		T result = null;
		try {
			if (ele != null && ele.toString() != "") {
				result = Instance.gson.fromJson(ele, classT);
			}
		} catch (Exception e) {
		    MLog.E(TAG, "GetEntity 发生异常：" + e.getMessage());
		}
		return result;
	}

	public static <T> List<T> GetEntityS(JsonArray array, Class<T> classT) {
		List<T> result = null;
		T t = null;
		try {
			if (array != null && array.size() > 0) {
				result = new ArrayList<T>();
				for (JsonElement item : array) {
					if (!(item == null || item.toString() == "")) {
						t = GetEntity(item.getAsJsonObject(), classT);
						if (t != null) {
							result.add(GetEntity(item.getAsJsonObject(), classT));
						}
					}
				}
			}
		} catch (Exception e) {
		    MLog.E(TAG, "GetEntityS 发生异常：" + e.getMessage());
		}
		return result;
	}
}
