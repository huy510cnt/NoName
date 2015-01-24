package com.hh.tinnhan.chuctet.adapter;

import com.hh.tinnhan.chuctet.fragment.BetterFragment;
import com.hh.tinnhan.chuctet.fragment.CategoryFragment;
import com.hh.tinnhan.chuctet.fragment.FavoritesFragment;

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
			return new BetterFragment();
			
		case 1:
			// Games fragment activity
			return new CategoryFragment();
			
		case 2:
			// Movies fragment activity
			return new FavoritesFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
