package com.baselib.framework.jsbridge.listener;


public interface WebViewJavascriptBridge {
	public void send(String data);
	public void send(String data, CallBackFunction responseCallback);
}
