package com.jjb.activity;

import com.jjb.R;
import com.jjb.util.Debugger;
import com.jjb.widget.SignInTask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SignInActivity extends BaseActivity {
	private TextView userNameView;
	private TextView passwordView;
	
	private String userName;
	private String password;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		
		userNameView = (TextView) findViewById(R.id.username);
		passwordView = (TextView) findViewById(R.id.password);
		
		findViewById(R.id.confirm).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				userName = userNameView.getText().toString();
				password = passwordView.getText().toString();
				
				Log.e("Signin userName", userName);
				Log.e("Signin userPassword", password);
			
				if (userName.isEmpty() || password.isEmpty()) {
					Debugger.DisplayToast(SignInActivity.this, "上述信息不能为空");
					return;
				}
				
				new SignInTask(SignInActivity.this).execute(userName, password);
			}
		});
	}
}
