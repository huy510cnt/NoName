package com.hh.tinnhan.chuctet.db;

import com.hh.tinnhan.chuctet.R;

import android.view.View;
import android.widget.ImageView;


public class CategoryCache {
	private View baseView;
	private ImageView imageCity;

	public CategoryCache(View baseView) {
		this.baseView = baseView;
	}

	public View getViewBase() {
		return baseView;
	}

	public ImageView getImageView(int resource) {
		if (imageCity == null) {
			imageCity = (ImageView) baseView.findViewById(R.id.imgIcon);
		}
		return imageCity;
	}
}
