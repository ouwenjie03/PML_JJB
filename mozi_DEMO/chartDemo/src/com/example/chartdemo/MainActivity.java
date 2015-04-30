package com.example.chartdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.achartengine.*;
//import org.achartengine.chartdemo.demo.chart.IDemoChart;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {

	  private String[] mMenuText;

	  private String[] mMenuSummary;
	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

        mMenuText = new String[2];
        mMenuSummary = new String[2];
        mMenuText[0] = "Embedded line chart demo";//’€œﬂÕº
        mMenuSummary[0] = "A demo on how to include a clickable line chart into a graphical activity";
        mMenuText[1] = "Embedded pie chart demo";//±˝Õº
        mMenuSummary[1] = "A demo on how to include a clickable pie chart into a graphical activity";
        
        
        setListAdapter(new SimpleAdapter(this, getListValues(), android.R.layout.simple_list_item_2,
            new String[] { IDemoChart.NAME, IDemoChart.DESC }, new int[] { android.R.id.text1,
                android.R.id.text2 }));
    }
    private List<Map<String, String>> getListValues() {
        List<Map<String, String>> values = new ArrayList<Map<String, String>>();
        int length = mMenuText.length;
        for (int i = 0; i < length; i++) {
          Map<String, String> v = new HashMap<String, String>();
          v.put(IDemoChart.NAME, mMenuText[i]);
          v.put(IDemoChart.DESC, mMenuSummary[i]);
          values.add(v);
        }
        return values;
	}
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
      super.onListItemClick(l, v, position, id);
      Intent intent = null;
      if (position == 0) {
        intent = new Intent(this, XYChartBuilder.class);
      } else if (position == 1) {
        intent = new Intent(this, PieChartBuilder.class);
      }
      startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
