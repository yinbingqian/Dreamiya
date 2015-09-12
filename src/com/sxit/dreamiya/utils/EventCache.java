package com.sxit.dreamiya.utils;

import de.greenrobot.event.EventBus;

/**
 * eventbus 
 * @author huanyu	
 * 类名称：EventCache   
 * 创建时间:2014-10-27 下午1:40:58
 */
public class EventCache {
	public static EventBus commandActivity = new EventBus();//通用
	public static EventBus errorHttp = new EventBus();//请求失败
	public static EventBus opAnswerEvent = new EventBus();//解答时限
	public static EventBus msgNotiEvent = new EventBus();
}
