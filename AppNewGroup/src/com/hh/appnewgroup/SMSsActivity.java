package com.hh.appnewgroup;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.hh.appnewgroup.adapter.SMSViewpagerAdapter;
import com.hh.appnewgroup.db.ReadDB;
import com.hh.appnewgroup.db.SMSObject;

public class SMSsActivity extends Activity {
	private ReadDB mReadDB;
	private ViewPager mPager;
	private SMSViewpagerAdapter mPagerAdapter ;
	private ArrayList<SMSObject> lstSMS = new ArrayList<SMSObject>();
	private TextView tvBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_view_activity);
		getActionBar().hide();
		
		Intent mIntent = getIntent();
		int position = 0;
		mPager = (ViewPager) findViewById(R.id.view_pager);
		tvBack = (TextView) findViewById(R.id.tvBack);
		
		if (mIntent != null) {
			position = Integer.valueOf(mIntent.getStringExtra("sms_id"));
		}
		
		tvBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		getData(position);
		

	}

	public void getData(int current) {
		mReadDB = new ReadDB(SMSsActivity.this);
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
		} catch (Exception e) {

		}
		if (lstSMS != null)
			lstSMS.clear();
		
		lstSMS = mReadDB.getlistSMSObjectPopulate();
		
		mPagerAdapter = new SMSViewpagerAdapter(SMSsActivity.this, lstSMS);
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(current);
		
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
		
		mReadDB.closeDatabase();
	}
}
