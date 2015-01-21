package com.hh.appnewgroup;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.hh.appnewgroup.adapter.SMSViewpagerAdapter;
import com.hh.appnewgroup.db.ReadDB;
import com.hh.appnewgroup.db.SMSObject;

public class SMSsActivity extends Activity{
	private ReadDB mReadDB;
	private ViewPager mPager;
	private SMSViewpagerAdapter mPagerAdapter;
	private ArrayList<SMSObject> lstSMS = new ArrayList<SMSObject>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_view_activity);
		getActionBar().hide();
		
		
		mPager = (ViewPager) findViewById(R.id.view_pager);
		getData(1,1);
		
		mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				SMSViewpagerAdapter.tvPager.setText("" + (position + 1) + " / " + lstSMS.size());
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		
	}
	
	public void getData(int cats_id,int current) {
		mReadDB = new ReadDB(this);
		mReadDB.openDatabase();
		if (lstSMS != null)
			lstSMS.clear();
		lstSMS = new ArrayList<SMSObject>();
		lstSMS = mReadDB.getlistSMSObject(cats_id);
		mReadDB.closeDatabase();

		mPagerAdapter = new SMSViewpagerAdapter(this, lstSMS);
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(current);
	}
}
