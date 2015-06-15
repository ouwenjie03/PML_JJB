package com.jjb.widget;

import static com.jjb.util.Constant.*;

import java.net.SocketTimeoutException;

import org.json.JSONException;
import org.json.JSONObject;

import com.jjb.activity.MainActivity;
import com.jjb.util.Communicator;
import com.jjb.util.Constant;
import com.jjb.util.Debugger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

public class SignInTask extends AsyncTask<String, Void, String> {
	protected Context context;
	protected boolean isSuccess = false;
	protected String targetMethod = "signIn";
	
	protected String accessKey;
	protected String expiresTime;
	protected int userId;
	
	public SignInTask(Context context) {
		this.context = context;
	}
	
	@Override
	protected String doInBackground(String... params) {
		String userID = null;
		String result = null;
		if (params.length < 2)
			return userID;
		try {
			result =
					Communicator.sendPost(targetMethod, "?username=" + params[0] + "&password=" + params[1]);
			JSONObject response = new JSONObject(result);
			accessKey = response.getString("accessKey");
			expiresTime = response.getString("expiresTime");
			userId = response.getInt("userId");
			
			isSuccess = true;
		} catch (SocketTimeoutException e) {
			Debugger.DisplayToast(context, "连接超时！");
		} catch (JSONException e) {
			Debugger.DisplayToast(context, "啊哦，不知道怎么了~");
			Log.e("JJB", "Malformed response from server: " + result);
		}
		return userID;
	}
	
	protected void onPostExecute(String userID) {
		if (isSuccess) {
			SharedPreferences settings = context.getSharedPreferences(PREF_USER_INFO, Activity.MODE_PRIVATE);
			Editor editor = settings.edit();
			editor.putString(PREF_ACCESS_KEY, accessKey);
			editor.putString(PREF_EXPIRES_TIME, expiresTime);
			editor.putInt(PREF_USERID, userId);
			editor.commit();
			
			Intent intent = new Intent(context, MainActivity.class);
			context.startActivity(intent);
		} else {
			Debugger.DisplayToast(context, userID);
		}
	}

}
