package com.jjb.util;

import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import com.baidu.voicerecognition.android.VoiceRecognitionConfig;
import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;

@SuppressLint("SimpleDateFormat")
public class Constant {
	
	// DateFormat相关常量
	public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");
	public static final SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat("MM");
	public static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("dd");
	
	// 百度云语音相关常量
	public static final String BAIDU_API_KEY = "1WXU1vV4CpQMAYwpZZ0votkx";
	public static final String BAIDU_SECRET_KEY = "4azgbTE5iocck26iCMAzx8U4TxzOZmK4";
	public static final int BAIDU_DIALOG_THEME = BaiduASRDigitalDialog.THEME_BLUE_LIGHTBG;
	public static final int BAIDU_PROP = VoiceRecognitionConfig.PROP_FINANCE;
	public static final String BAIDU_LANGUAGE = VoiceRecognitionConfig.LANGUAGE_CHINESE;
	public static final boolean BAIDU_START_TONE_ENABLE = true;
	public static final boolean BAIDU_END_TONE_ENABLE = true;
	public static final boolean BAIDU_TIP_TONE_ENABLE = true;
	public static final boolean BAIDU_NLU_ENABLE = true;
	
	// Broadcast Action相关常量
	public static final String EXIT_APP_ACTION = "ExitApp";
	
	// SharedPreferences Key
	public static final String PREF_USER_INFO = "userInfo";
	public static final String PREF_ACCESS_KEY = "accessKey";
	public static final String PREF_AUTO_LOGIN_ENABLE = "autoLogin";
	
	// Bundle Key
	public static final String B_ACCESS_KEY = "accessKey";
	public static final String B_USERID = "userID";
	
}
