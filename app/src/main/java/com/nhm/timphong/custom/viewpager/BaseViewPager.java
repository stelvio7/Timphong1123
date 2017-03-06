package com.nhm.timphong.custom.viewpager;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class BaseViewPager extends RelativeLayout {
	private ViewPagerCustomDuration mViewPager;
	private IPageControl iPageControl;
	private int mCount;
	
	public BaseViewPager(Context context) {
		super(context);
		initialization();
	}
	
	public BaseViewPager(Context context, AttributeSet attrs) {
		super(context , attrs);
		initialization();
	}
	
	public BaseViewPager(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialization();
	}
		
	private final void initialization() {
		LayoutParams params = new LayoutParams(-1, -1);
		params.addRule(RelativeLayout.ABOVE, 1);
		
		mViewPager = new ViewPagerCustomDuration(getContext());
		mViewPager.setLayoutParams(params);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) {}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			@Override
			public void onPageSelected(int arg0) {
				if (iPageControl != null) {
					iPageControl.setPageIndex(arg0);
				}
			}
		});
		addView(mViewPager);
	}
	
	public ViewPagerCustomDuration getMViewPager(){
		return mViewPager;
	}
	
	
	
	
	public ViewPager getViewPager() {
		return mViewPager;
	}
	
	public void setAdapter(PagerAdapter adapter) {
		mCount = adapter.getCount();
		iPageControl.setPageSize(mCount);
			
		mViewPager.setAdapter(adapter);
		
		if(adapter.getCount() > 0)
			mViewPager.setCurrentItem(0, true);
	}
		
	public void setItem(int page){
		mViewPager.setCurrentItem(page, true);
	}
	
	public int getCurrentItem(){
		return mViewPager.getCurrentItem();
	}
	
	public int getItemCount(){
		return mCount;
	}
	
	public void setPageControl(IPageControl c) {
		setPageControl(c , RelativeLayout.ALIGN_PARENT_BOTTOM);
	}
	
	public void setPageControl(IPageControl c , int rule) {
		iPageControl = c;
		
		View v = (View)c;
		v.setId(1);
		
		LayoutParams params = new LayoutParams(-2, -2);
		params.topMargin = 20;
		params.bottomMargin = 20;
		params.addRule(rule);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		v.setLayoutParams(params);
		
		super.addView(v);
	}
}
