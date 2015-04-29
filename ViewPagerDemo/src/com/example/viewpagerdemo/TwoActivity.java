package com.example.viewpagerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class TwoActivity extends Activity implements OnPageChangeListener{
	/**
	 * ViewPager
	 */
	private ViewPager viewPager;
	
	/**
	 * 装点点的ImageView数组
	 */
	private ImageView[] tips;
	
	/**
	 * 装ImageView数组
	 */
	private ImageView[] mImageViews;
	
	/**
	 * 图片资源id
	 */
	private int[] imgIdArray ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewGroup group = (ViewGroup)findViewById(R.id.viewGroup);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		
		//载入图片资源ID
		imgIdArray = new int[]{R.drawable.item01, R.drawable.item02, R.drawable.item03, R.drawable.item04,
				R.drawable.item05,R.drawable.item06, R.drawable.item07};
		
		
		//将点点加入到ViewGroup中
		tips = new ImageView[imgIdArray.length];
		for(int i=0; i<tips.length; i++){
			ImageView imageView = new ImageView(this);
	    	imageView.setLayoutParams(new LayoutParams(10,10));
	    	tips[i] = imageView;
	    	if(i == 0){
	    		tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
	    	}else{
	    		tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
	    	}
	    	
	    	 group.addView(imageView);
		}
		
		
		//将图片装载到数组中
		mImageViews = new ImageView[imgIdArray.length];
		for(int i=0; i<mImageViews.length; i++){
			ImageView imageView = new ImageView(this);
			mImageViews[i] = imageView;
			imageView.setBackgroundResource(imgIdArray[i]);
		}
		
		//设置Adapter
		viewPager.setAdapter(new MyAdapter());
		//设置监听，主要是设置点点的背景
		viewPager.setOnPageChangeListener(this);
		//设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
		viewPager.setCurrentItem((mImageViews.length) * 100);
		
	}
	
	/**
	 * 
	 * @author xiaanming
	 *
	 */
	public class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);
			
		}

		/**
		 * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
		 */
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);
			return mImageViews[position % mImageViews.length];
		}
		
		
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		setImageBackground(arg0 % mImageViews.length);
	}
	
	/**
	 * 设置选中的tip的背景
	 * @param selectItems
	 */
	private void setImageBackground(int selectItems){
		for(int i=0; i<tips.length; i++){
			if(i == selectItems){
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			}else{
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

}
