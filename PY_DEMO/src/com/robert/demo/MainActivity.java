package com.robert.demo;

import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.baidu.voicerecognition.android.ui.DialogRecognitionListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private BaiduASRDigitalDialog mDialog = null;

	private DialogRecognitionListener mRecognitionListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button clickMeButton = (Button) findViewById(R.id.dummy_button);
		final Button branchButton = (Button) findViewById(R.id.branch_button);

		mRecognitionListener = new DialogRecognitionListener() {
			public void onResults(Bundle bundle) {
				Bundle startBundle = new Bundle();
				Intent intent = new Intent(MainActivity.this,
						ResultActivity.class);
				startBundle
						.putStringArrayList(
								"resultList",
								bundle != null ? bundle
										.getStringArrayList(RESULTS_RECOGNITION)
										: null);
				intent.putExtras(startBundle);
				startActivity(intent);
			}
		};

		clickMeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO 打开百度云语音
				if (mDialog != null) {
					mDialog.dismiss();
				}
				Bundle params = new Bundle();
				
				// API Key
				params.putString(BaiduASRDigitalDialog.PARAM_API_KEY,
						Config.API_KEY);
				params.putString(BaiduASRDigitalDialog.PARAM_SECRET_KEY,
						Config.SECRET_KEY);
				// 设定对话框主题
				params.putInt(BaiduASRDigitalDialog.PARAM_DIALOG_THEME,
						Config.DIALOG_THEME);
				// 设定识别领域
				params.putInt(BaiduASRDigitalDialog.PARAM_PROP,
						Config.CURRENT_PROP);
				// 设定语言
				params.putString(
						BaiduASRDigitalDialog.PARAM_LANGUAGE,
						Config.CURRENT_LANGUAGE);
				// 设定提示音播放
				params.putBoolean(
						BaiduASRDigitalDialog.PARAM_START_TONE_ENABLE,
						Config.PLAY_START_SOUND);
				params.putBoolean(
						BaiduASRDigitalDialog.PARAM_END_TONE_ENABLE,
						Config.PLAY_END_SOUND);
				params.putBoolean(
						BaiduASRDigitalDialog.PARAM_TIPS_TONE_ENABLE,
						Config.DIALOG_TIPS_SOUND);
				// 设定语义识别
				params.putBoolean(
						BaiduASRDigitalDialog.PARAM_NLU_ENABLE,
						Config.NLU);

				// 生成对话框对象，设置回调函数
				mDialog = new BaiduASRDigitalDialog(MainActivity.this, params);
				mDialog.setDialogRecognitionListener(mRecognitionListener);
				// 启动对话框
				mDialog.show();
			}
		});
		
		branchButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SinkingActivity.class);
				startActivity(intent);
			}
		});
		
	}
}
