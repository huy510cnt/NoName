package com.hh.appnewgroup.adapter;

import com.hh.appnewgroup.fragment.CategoryFragment;
import com.hh.appnewgroup.fragment.FavoritesFragment;
import com.hh.appnewgroup.fragment.BetterFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new CategoryFragment();
		case 1:
			// Games fragment activity
			return new FavoritesFragment();
		case 2:
			// Movies fragment activity
			return new BetterFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
