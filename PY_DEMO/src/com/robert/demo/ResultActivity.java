package com.robert.demo;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		final TextView voiceResult = (TextView) findViewById(R.id.voiceResult);
		// final TextView semanResult = (TextView) findViewById(R.id.semanResult);
		
		Bundle bundle = getIntent().getExtras();
		ArrayList<String> result = bundle != null ? bundle.getStringArrayList("resultList") : new ArrayList<String>();
		StringBuffer str = new StringBuffer();
		Iterator<String> iter = result.iterator();
		while (true) {
			str.append(iter.next());
			if (iter.hasNext())
				str.append(", ");
			else
				break;
		}
		
		voiceResult.setText(str.toString());
		// semanResult.setText(bundle.getString("semanResult"));
	}
}
