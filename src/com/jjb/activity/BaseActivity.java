package com.jjb.activity;

import com.jjb.util.Constant;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

/**
 * 所有Activity继承自本Activity。
 * BaseActivity功能包括：
 * 		- 接收到广播后关闭activity
 * 
 * @author Robert Peng
 */
public abstract class BaseActivity extends Activity {
	
	protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            finish();  
        }  
    };
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	@Override  
    public void onResume() {  
        super.onResume();  
        // 在当前的activity中注册广播  
        IntentFilter filter = new IntentFilter();  
        filter.addAction(Constant.EXIT_APP_ACTION);
        this.registerReceiver(this.broadcastReceiver, filter); 
    }  
      
    @Override  
    protected void onPause() {
        this.unregisterReceiver(this.broadcastReceiver);  
        super.onPause();
    }

}
