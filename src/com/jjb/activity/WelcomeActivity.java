package com.jjb.activity;

import com.jjb.R;
import com.jjb.util.Constant;
import com.jjb.widget.SignInTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * App启动时的欢迎Activity
 * @author Robert Peng
 */
public class WelcomeActivity extends BaseActivity {
	private Bundle mBundle = new Bundle();
	private String accessKey;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		SharedPreferences settings = getSharedPreferences(Constant.PREF_USER_INFO, Activity.MODE_PRIVATE);
		accessKey = settings.getString(Constant.PREF_ACCESS_KEY, "");
		
		mBundle.putString(Constant.B_ACCESS_KEY, accessKey);
		
		if (settings.getBoolean(Constant.PREF_AUTO_LOGIN_ENABLE, false)) {
			new SignInTask(this).execute();
		} else {
			Intent mIntent = new Intent(WelcomeActivity.this, SignInActivity.class);
			mIntent.putExtras(mBundle);
			startActivity(mIntent);
			WelcomeActivity.this.finish();
		}
	}

}
