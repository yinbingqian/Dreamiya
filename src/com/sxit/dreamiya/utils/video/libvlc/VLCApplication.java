/*****************************************************************************
 * VLCApplication.java
 *****************************************************************************
 * Copyright 漏 2010-2013 VLC authors and VideoLAN
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston MA 02110-1301, USA.
 *****************************************************************************/
package com.sxit.dreamiya.utils.video.libvlc;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.sxit.dreamiya.utils.video.vlc.MediaDatabase;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.Log;

public class VLCApplication extends Application {
	public final static String TAG = "VLC/VLCApplication";
	private static VLCApplication instance;

	public final static String SLEEP_INTENT = "org.videolan.vlc.SleepIntent";

	// 环信

	public static Context applicationContext;
	// login user name
	public final String PREF_USERNAME = "username";
	private String userName = null;
	// login password
	private static final String PREF_PWD = "pwd";
	private String password = null;
	/**
	 * 当前用户nickname,为了苹果推送不是userid而是昵称
	 */
	public static String currentUserNick = "";
	
	

	@Override
	public void onCreate() {
		super.onCreate();

		int pid = android.os.Process.myPid();
		String processAppName = getAppName(pid);
		// 如果使用到百度地图或者类似启动remote service的第三方库，这个if判断不能少
		if (processAppName == null || processAppName.equals("")) {
			return;
		}

		applicationContext = this;
		instance = this;

		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		String p = pref.getString("set_locale", "");
		if (p != null && !p.equals("")) {
			Locale locale;
			// workaround due to region code
			if (p.equals("zh-TW")) {
				locale = Locale.TRADITIONAL_CHINESE;
			} else if (p.startsWith("zh")) {
				locale = Locale.CHINA;
			} else if (p.equals("pt-BR")) {
				locale = new Locale("pt", "BR");
			} else if (p.equals("bn-IN") || p.startsWith("bn")) {
				locale = new Locale("bn", "IN");
			} else {
				/**
				 * Avoid a crash of java.lang.AssertionError: couldn't
				 * initialize LocaleData for locale if the user enters
				 * nonsensical region codes.
				 */
				if (p.contains("-"))
					p = p.substring(0, p.indexOf('-'));
				locale = new Locale(p);
			}
			Locale.setDefault(locale);
			Configuration config = new Configuration();
			config.locale = locale;
			getBaseContext().getResources().updateConfiguration(config,
					getBaseContext().getResources().getDisplayMetrics());
		}

		instance = this;

		// Initialize the database soon enough to avoid any race condition and
		// crash
		MediaDatabase.getInstance(this);
	}

	public static VLCApplication getInstance() {
		return instance;
	}

	/**
	 * Called when the overall system is running low on memory
	 */
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		Log.w(TAG, "System is running low on memory");

	}

	/**
	 * @return the main context of the Application
	 */
	public static Context getAppContext() {
		return instance;
	}

	/**
	 * @return the main resources from the Application
	 */
	public static Resources getAppResources() {
		if (instance == null)
			return null;
		return instance.getResources();
	}

	/**
	 * 获取当前登陆用户名
	 *
	 * @return
	 */
	public String getUserName() {
		if (userName == null) {
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
			userName = preferences.getString(PREF_USERNAME, null);
		}
		return userName;
	}

	/**
	 * 获取密码
	 *
	 * @return
	 */
	public String getPassword() {
		if (password == null) {
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
			password = preferences.getString(PREF_PWD, null);
		}
		return password;
	}

	/**
	 * 设置用户名
	 *
	 * @param user
	 */
	public void setUserName(String username) {
		if (username != null) {
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
			SharedPreferences.Editor editor = preferences.edit();
			if (editor.putString(PREF_USERNAME, username).commit()) {
				userName = username;
			}
		}
	}

	/**
	 * 设置密码 下面的实例代码 只是demo，实际的应用中需要加password 加密后存入 preference 环信sdk
	 * 内部的自动登录需要的密码，已经加密存储了
	 *
	 * @param pwd
	 */
	public void setPassword(String pwd) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
		SharedPreferences.Editor editor = preferences.edit();
		if (editor.putString(PREF_PWD, pwd).commit()) {
			password = pwd;
		}
	}

	private String getAppName(int pID) {
		String processName = null;
		ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
		List l = am.getRunningAppProcesses();
		Iterator i = l.iterator();
		PackageManager pm = this.getPackageManager();
		while (i.hasNext()) {
			ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
			try {
				if (info.pid == pID) {
					CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
					// Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
					// info.processName +"  Label: "+c.toString());
					// processName = c.toString();
					processName = info.processName;
					return processName;
				}
			} catch (Exception e) {
				// Log.d("Process", "Error>> :"+ e.toString());
			}
		}
		return processName;
	}
}
