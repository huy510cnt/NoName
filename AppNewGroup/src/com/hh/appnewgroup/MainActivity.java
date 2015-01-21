package com.hh.appnewgroup;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hh.appnewgroup.adapter.AdapterCategorys;
import com.hh.appnewgroup.animation.Constant;
import com.hh.appnewgroup.animation.SwitchAnimationUtil;
import com.hh.appnewgroup.animation.SwitchAnimationUtil.AnimationType;
import com.hh.appnewgroup.db.CategoryObject;
import com.hh.appnewgroup.db.ReadDB;

public class MainActivity extends Activity {

	private ListView lvCategory;
	private ReadDB mReadDB;
	ArrayList<CategoryObject> mListTheLoai;
	private SwitchAnimationUtil mSwitchAnimationUtil;

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (mSwitchAnimationUtil == null) {
			mSwitchAnimationUtil = new SwitchAnimationUtil();
			mSwitchAnimationUtil.startAnimation(lvCategory, randomStype());
		}
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		lvCategory = (ListView) findViewById(R.id.lvCategory);

		mReadDB = new ReadDB(this);
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
		} catch (Exception e) {

		}

		mListTheLoai = new ArrayList<CategoryObject>();
		mListTheLoai = mReadDB.getListTheloai();

		AdapterCategorys mAdapterCategorys = new AdapterCategorys(this,R.layout.list_categorys_layout,mListTheLoai);

		lvCategory.setAdapter(mAdapterCategorys);

		lvCategory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});

		mReadDB.closeDatabase();
	}
	
	public AnimationType randomStype() {
		int random = getRandomIndex(1, 8);
		AnimationType mAnimationType = null;
		
		if(random == 1){
			mAnimationType = Constant.mAlpha;
		}else if(random == 2){
			mAnimationType = Constant.mFlipHorizon;
		}else if(random == 3){
			mAnimationType = Constant.mFlipVertical;
		}else if(random == 4){
			mAnimationType = Constant.mHorizionCross;
		}else if(random == 5){
			mAnimationType = Constant.mHorizionLeft;
		}else if(random == 6){
			mAnimationType = Constant.mHorizionRight;
		}else if(random == 7){
			mAnimationType = Constant.mRotate;
		}else {
			mAnimationType = Constant.mScale;
		}
		
		
		return mAnimationType;
		
	}
	private int getRandomIndex(int min, int max) {
		return (int) (Math.random() * (max - min + 1)) + min;
	}
}
