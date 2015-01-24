package com.hh.tinnhan.chuctet.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hh.tinnhan.chuctet.R;
import com.hh.tinnhan.chuctet.db.SMSObject;

public class SMSViewpagerAdapter extends PagerAdapter {

	private Context context = null;
	private ArrayList<SMSObject> arr = null;
	
	public static TextView tvPager;
	
	
	public SMSViewpagerAdapter(Context context, ArrayList<SMSObject> arr) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.arr = arr;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr.size();
	}

	@Override
	public Object instantiateItem(View container, int position) {
		
		LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(context);
		
		
		LinearLayout v = (LinearLayout)inflater.inflate(R.layout.item_sms_viewpager, null);

		SMSObject sms1 = arr.get(position);
		
		TextView tvSMSView = (TextView) v.findViewById(R.id.tvSMSView);
		tvPager = (TextView) v.findViewById(R.id.tvnumPages);
		
		tvPager.setText((position+1) +" / " + arr.size());

		
		tvSMSView.setText(sms1.getContent());

		((ViewPager) container).addView(v);
		return v;
	}

	@Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager)collection).removeView((View) view);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

}
