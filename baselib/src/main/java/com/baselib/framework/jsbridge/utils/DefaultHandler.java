package com.baselib.framework.jsbridge.utils;

import com.baselib.framework.jsbridge.listener.BridgeHandler;
import com.baselib.framework.jsbridge.listener.CallBackFunction;

/**
 * 默认处理程序
 */
public class DefaultHandler implements BridgeHandler {
	@Override
	public void handler(String data, CallBackFunction function) {
		if(function != null){
			function.onCallBack("DefaultHandler response data");
		}
	}

}
