package com.jjb.widget;

import java.net.SocketTimeoutException;

import com.jjb.util.Communicator;
import com.jjb.util.Debugger;

import android.content.Context;
import android.os.AsyncTask;

public class SignInTask extends AsyncTask<Void, Void, String> {
	private Context context;
	
	private boolean isSuccess = false;
	
	public SignInTask(Context context) {
		this.context = context;
	}
	
	@Override
	protected String doInBackground(Void... params) {
		String userID = null;
		try {
			// TODO 尝试登陆
			String result = Communicator.sendPost("www.baidu.com", "Hello~");
			// TODO 处理服务器返回结果
			
		} catch (SocketTimeoutException e) {
			Debugger.DisplayToast(context, "连接超时！");
		}
		return userID;
	}
	
	protected void onPostExecute(String userID) {
		if (isSuccess) {
			// TODO Access Key与Expire Time写入SharedPreferences
			// TODO 跳转至MainActivity
		} else {
			Debugger.DisplayToast(context, userID);
		}
	}

}
