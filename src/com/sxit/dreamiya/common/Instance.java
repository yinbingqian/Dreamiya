package com.sxit.dreamiya.common;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.nostra13.universalimageloader.core.ImageLoader;


public class Instance {
	public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	public static ImageLoader imageLoader = ImageLoader.getInstance();

}
