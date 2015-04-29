package com.jjb.jjb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.DatePicker.OnDateChangedListener;

public class MainActivity extends Activity {

	DBManager db = new DBManager(this);
	
	Button btn;
	DatePicker fromDp, toDp;
	TextView tv;

	private int fromYear;
	private int fromMonth;
	private int fromDay;
	
	private int toYear;
	private int toMonth;
	private int toDay;
	
	private void init() {
		Date curDate = new Date(System.currentTimeMillis());
		toYear = fromYear = Integer.parseInt(Constant.YEAR_FORMAT.format(curDate));
		toMonth = fromMonth = Integer.parseInt(Constant.MONTH_FORMAT.format(curDate));
		toDay = fromDay = Integer.parseInt(Constant.DAY_FORMAT.format(curDate));
		
		
		fromDp.init(fromYear, fromMonth-1, fromDay, new OnDateChangedListener(){

            public void onDateChanged(DatePicker view, int year,
                    int monthOfYear, int dayOfMonth) {
            	fromYear = year;
            	fromMonth = monthOfYear+1;
            	fromDay = dayOfMonth;
            }
            
        });
		
		toDp.init(toYear, toMonth-1, toDay, new OnDateChangedListener(){

            public void onDateChanged(DatePicker view, int year,
                    int monthOfYear, int dayOfMonth) {
            	toYear = year;
            	toMonth = monthOfYear+1;
            	toDay = dayOfMonth;
            }
            
        });
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fromDp = (DatePicker)findViewById(R.id.fromDatePicker);
		toDp = (DatePicker)findViewById(R.id.toDatePicker);
		tv = (TextView)findViewById(R.id.textView);
		btn = (Button)findViewById(R.id.button);
		init();
		
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String fromDate = ""+fromYear+"-"+fromMonth+"-"+fromDay;
				String toDate = ""+toYear+"-"+toMonth+"-"+toDay;
				List<ItemBean> li = db.listItemsByTime("abc", fromDate, toDate);
				String res = new String();
				for (int i=0; i<li.size(); i++) {
					res += li.get(i).toString()+"\n";
				}
				tv.setText(res);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
