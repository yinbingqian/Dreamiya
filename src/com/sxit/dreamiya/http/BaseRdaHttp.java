package com.sxit.dreamiya.http;

import java.lang.reflect.Method;

public class BaseRdaHttp {

	public Method Method(String name) {
		for (Method m : this.getClass().getMethods()) {
			if (m.getName().equals(name)) {				
				return m;
			}
		}
		return null;
	}
}
