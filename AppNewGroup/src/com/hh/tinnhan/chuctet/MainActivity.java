package com.hh.tinnhan.chuctet;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.hh.tinnhan.chuctet.adapter.TabsPagerAdapter;
import com.hh.tinnhan.chuctet.animation.Constant;
import com.hh.tinnhan.chuctet.animation.SwitchAnimationUtil;
import com.hh.tinnhan.chuctet.animation.SwitchAnimationUtil.AnimationType;
import com.hh.tinnhan.chuctet.fragment.BetterFragment;
import com.hh.tinnhan.chuctet.function.UtilFuntion;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.banner.Banner;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	
	private StartAppAd startAppAd = new StartAppAd(this);
	// Tab titles
	private int[] tabs = { R.drawable.ic_home, R.drawable.ic_category, R.drawable.ic_heart };
	
	private SwitchAnimationUtil mSwitchAnimationUtil;
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (mSwitchAnimationUtil == null) {
			mSwitchAnimationUtil = new SwitchAnimationUtil();
			mSwitchAnimationUtil.startAnimation(BetterFragment.lvPopulated,getRanDomType());
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.popup_right_in, R.anim.popup_left_out);
		StartAppSDK.init(this, Config.DEVELOPE_ID_STARTAPP, Config.APP_ID_STARTAPP, true); //TODO: Replace with your IDs
		/** Create Splash Ad **/
//		StartAppAd.showSplash(this, savedInstanceState,
//				new SplashConfig()
//					.setTheme(Theme.GLOOMY)
//					.setLogo(R.drawable.ic_launcher_lg)
//					.setAppName(getString(R.string.app_name))
//		);
		
		setContentView(R.layout.activity_main);
		
		RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.second_layout);
		
		// Create new StartApp banner
		Banner startAppBanner = new Banner(this);
		RelativeLayout.LayoutParams bannerParameters = 
				new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
		bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bannerParameters.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
		// Add the banner to the main layout
		mainLayout.addView(startAppBanner, bannerParameters);

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		
		viewPager.setAdapter(mAdapter);
		
		//	actionBar.setHomeButtonEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);	
		// Adding Tabs
		for (int tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setIcon(tab_name).setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		actionBar.setSelectedNavigationItem(1);
		viewPager.setCurrentItem(1);
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	private AnimationType getRanDomType() {
		AnimationType mAnimationType = null;
		int radom = UtilFuntion.getRandomIndex(1, 8);
		
		if(radom == 1){
			mAnimationType = Constant.mAlpha;
		}else if(radom == 2){
			mAnimationType = Constant.mFlipHorizon;
		}else if(radom == 3){
			mAnimationType = Constant.mFlipVertical;
		}else if(radom == 4){
			mAnimationType = Constant.mHorizionCross;
		}else if(radom == 5){
			mAnimationType = Constant.mHorizionLeft;
		}else if(radom == 6){
			mAnimationType = Constant.mHorizionRight;
		}else if(radom == 7){
			mAnimationType = Constant.mRotate;
		}else{
			mAnimationType = Constant.mScale;
		}
		return mAnimationType;
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.popup_right_in, R.anim.popup_left_out);
		startAppAd.onBackPressed();
	}
	
	/**
	 * Part of the activity's life cycle, StartAppAd should be integrated here.
	 */
	@Override
	public void onResume(){
		super.onResume();
		startAppAd.onResume();
	}
	
	/**
	 * Part of the activity's life cycle, StartAppAd should be integrated here
	 * for the home button exit ad integration.
	 */
	@Override
	public void onPause(){
		super.onPause();
		startAppAd.onPause();
	}
	
	/**
	 * Part of the activity's life cycle, StartAppAd should be integrated here
	 * for the back button exit ad integration.
	 */
}
