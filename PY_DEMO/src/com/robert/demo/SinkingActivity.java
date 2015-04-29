package com.robert.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * 使用并测试用例页
 * 
 * @author caizhiming
 */
public class SinkingActivity extends Activity {
    private MySinkingView mSinkingView;

    private float percent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinking);
        mSinkingView = (MySinkingView) findViewById(R.id.sinking);
        final SeekBar controlBar = (SeekBar) findViewById(R.id.waveWidgetControlBar);
        
        percent = 0.56f;
        mSinkingView.setPercent(percent);

        controlBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					float currentVolumn = seekBar.getProgress();
					float maxVolumn = seekBar.getMax();
					percent = currentVolumn / maxVolumn;
			        mSinkingView.setPercent(percent);
				}
			}
			public void onStartTrackingTouch(SeekBar seekBar) {}
			public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        
    }
}
