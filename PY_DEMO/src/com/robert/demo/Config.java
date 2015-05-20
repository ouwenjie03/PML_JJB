package com.robert.demo;

import com.baidu.voicerecognition.android.VoiceRecognitionConfig;
import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;

public class Config {

	public static String API_KEY = "1WXU1vV4CpQMAYwpZZ0votkx";
	public static String SECRET_KEY = "4azgbTE5iocck26iCMAzx8U4TxzOZmK4";
	public static int DIALOG_THEME = BaiduASRDigitalDialog.THEME_BLUE_LIGHTBG;
	public static int CURRENT_PROP = VoiceRecognitionConfig.PROP_FINANCE;
	public static String CURRENT_LANGUAGE = VoiceRecognitionConfig.LANGUAGE_CHINESE;
	
	public static boolean NLU = true;
	public static boolean PLAY_START_SOUND = true;
	public static boolean PLAY_END_SOUND = true;
	public static boolean DIALOG_TIPS_SOUND = true;
	
}
