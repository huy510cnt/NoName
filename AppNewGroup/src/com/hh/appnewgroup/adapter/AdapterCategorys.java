package com.hh.appnewgroup.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hh.appnewgroup.db.CategoryCache;
import com.hh.appnewgroup.db.CategoryObject;

public class AdapterCategorys extends BaseAdapter{
	private int resouce;
	Activity context = null;
	 private LayoutInflater inflater;
	ArrayList<CategoryObject> arr = null;
	
	public AdapterCategorys(Activity context, int resouce,ArrayList<CategoryObject> list) {
		this.context = context;
		this.resouce = resouce;
		inflater = LayoutInflater.from( context );
		this.arr = list;
	}
	
	 @Override
	    public int getCount(){
	        return arr.size();
	    }
	    @Override
	    public CategoryObject getItem(int i) {
	        return null;
	    }
	    @Override
	    public long getItemId(int i) {
	        return 0;
	    }
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		CategoryCache viewCache;
		CategoryObject Category = arr.get(position);
		
		if (view == null) {
			view = inflater.inflate(resouce, null);
			viewCache = new CategoryCache(view);
			view.setTag(viewCache);
		}else{
			view = convertView;
			viewCache = ( CategoryCache ) view.getTag();
		}
		
		Log.e("", "" + arr.get(position).getName_category());
		
		
		String mStringImg = Category.getImage_category();
		
		ImageView mImageView = viewCache.getImageView(resouce);
		
		int imgDrawerble = context.getResources().getIdentifier(mStringImg, "drawable", context.getPackageName());
		
		
		mImageView.setImageResource(imgDrawerble);
		
		return view;
	}
	

}
