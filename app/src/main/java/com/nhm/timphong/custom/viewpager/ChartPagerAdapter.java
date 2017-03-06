package com.nhm.timphong.custom.viewpager;



import java.util.List;

import com.nhm.timphong.data.AdData;
import com.nhm.timphong.regist.RegistUserActivity;
import com.nhm.timphong.util.ThisUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


public class ChartPagerAdapter extends PagerAdapter{
	private Context mContext;
	
	private List<AdData> AdInfoLust;
	private int num = 0;
	
	//private final ImageDownloader imageDownloader = new ImageDownloader();
	
	public ChartPagerAdapter(Context context, List<AdData> AdInfoLust) {
		mContext = context;
		this.AdInfoLust = AdInfoLust;
		num = AdInfoLust.size();
		ImageLoader.getInstance().init(ThisUtil.getConfig(mContext));
	}
	
	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager)arg0).removeView((View) arg2);
	}

	@Override
	public void finishUpdate(View arg0) {
	}

	@Override
	public int getCount() {
		return num;
	}

	
	//ImageButton[] childList;
	@Override
	public Object instantiateItem(View arg0, int arg1) {
		final int idxImage = arg1;
		//childList = new ImageButton[Constant.MAIN_PAGER_NUM];
		ImageView child = new ImageView(mContext);
		child.setScaleType(ImageView.ScaleType.FIT_XY);
		//child.setBackgroundResource(imageContents[arg1]);
		if(AdInfoLust != null){
			//Picasso.with(mContext)
			//  .load(Util.encoderHangulUrl(chartMainListData.get(arg1).getItemImg()))
			//  .into(child);
			ImageLoader imageLoader = ImageLoader.getInstance();
			imageLoader.displayImage(AdInfoLust.get(arg1).getImgUrl(), child, ThisUtil.getImageLoaderOption());
			//imageDownloader.download(chartMainListData.get(arg1).getItemImg(), child, 0);
		}
		
		((ViewPager)arg0).addView(child);
		child.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			goVoteActivity();
			}
		});
		return child;
	}
	
	private void goVoteActivity(){
		Intent intent = new Intent(mContext, RegistUserActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Bundle myData = new Bundle();
		intent.putExtras(myData);
		mContext.startActivity(intent);
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View)arg1);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
	}

}
