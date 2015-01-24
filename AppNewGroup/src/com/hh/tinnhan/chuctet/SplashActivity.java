package com.hh.tinnhan.chuctet;


import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hh.tinnhan.chuctet.db.CategoryObject;
import com.hh.tinnhan.chuctet.db.ReadDB;
import com.hh.tinnhan.chuctet.db.SMSObject;

public class SplashActivity extends ActivityBase {

	private Animation animation;
	private ImageView logo;
	private TextView title_txt;
	private TextView title2_txt;
	private static String TAG = SplashActivity.class.getName();
	private ArrayList<SMSObject> lstSmsObjects;
	private ArrayList<CategoryObject> mListTheLoai;
	private ReadDB mReadDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		getActionBar().hide();
		logo = (ImageView) findViewById(R.id.logo_img);
		title_txt = (TextView) findViewById(R.id.track_txt);
		title2_txt = (TextView) findViewById(R.id.pro_txt);

		new PrefetchData().execute();
		flyIn();   
		// Start timer and launch main activity
	}

	/**
     * Async Task to make http call
     */
    private class PrefetchData extends AsyncTask<Void, Void, Void> {
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
        	mReadDB = new ReadDB(SplashActivity.this);
    		try {
    			mReadDB.createDatabase();
    			mReadDB.openDatabase();
    		} catch (Exception e) {

    		}

    		mListTheLoai = new ArrayList<CategoryObject>();
    		mListTheLoai = mReadDB.getListTheloai();
    		lstSmsObjects = mReadDB.getlistSMSObjectPopulate();
    		
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // After completing http call
            // will close this activity and lauch main activity
            mReadDB.close();
            endSplash();
        }
 
    }
	
	private void flyIn() {
		animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
		logo.startAnimation(animation);

		animation = AnimationUtils.loadAnimation(this, R.anim.app_name_animation);
		title_txt.startAnimation(animation);

		animation = AnimationUtils.loadAnimation(this, R.anim.pro_animation);
		title2_txt.startAnimation(animation);
	}

	private void endSplash() {
		animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation_back);
		logo.startAnimation(animation);

		animation = AnimationUtils.loadAnimation(this, R.anim.app_name_animation_back);
		title_txt.startAnimation(animation);

		animation = AnimationUtils.loadAnimation(this, R.anim.pro_animation_back);
		title2_txt.startAnimation(animation);

		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				 
	            Intent i = new Intent(SplashActivity.this, MainActivity.class);
//	            i.putExtra("mListTheLoai", mListTheLoai);
//	            i.putExtra("lstSmsObjects", lstSmsObjects);
	            startActivity(i);
	            Log.e(TAG,"mListTheLoai.size() = " + mListTheLoai.size());
	            Log.e(TAG,"lstSmsObjects.size() = " + lstSmsObjects.size());
	            // close this activity
	            finish();
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationStart(Animation arg0) {
			}
		});

	}
@Override
public void onBackPressed() {
}
}
