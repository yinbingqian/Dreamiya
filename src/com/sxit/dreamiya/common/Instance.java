package com.sxit.dreamiya.common;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sxit.dreamiya.R;


public class Instance {
	public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	public static ImageLoader imageLoader = ImageLoader.getInstance();
	public static DisplayImageOptions user_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.contact_default_headpic)
            .showImageForEmptyUri(R.drawable.contact_default_headpic)
            .showImageOnFail(R.drawable.contact_default_headpic)
            .cacheInMemory(true).cacheOnDisc(true).build();

    public static DisplayImageOptions img_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.kids_photo)
            .showImageForEmptyUri(R.drawable.kids_photo)
            .showImageOnFail(R.drawable.kids_photo)
            .cacheInMemory(true).cacheOnDisc(true).build();
}
