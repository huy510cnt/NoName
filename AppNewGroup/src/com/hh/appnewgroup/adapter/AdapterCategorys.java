package com.hh.appnewgroup.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hh.appnewgroup.R;
import com.hh.appnewgroup.db.CategoryObject;

public class AdapterCategorys extends BaseAdapter{
	
	Activity context = null;
	ArrayList<CategoryObject> arr = null;
	
	public AdapterCategorys(Activity context, ArrayList<CategoryObject> list) {
		this.context = context;
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
		if (view == null) {
			view = View.inflate(context, R.layout.list_categorys_layout, null);
		}
		CategoryObject Category = arr.get(position);
		Log.e("", "" + arr.get(position).getName_category());
		ViewHolder mViewHoldertmp = new ViewHolder();
		
		
		mViewHoldertmp.image = (ImageView) view.findViewById(R.id.imgIcon);
		view.setTag(mViewHoldertmp);
		
		String mStringImg = Category.getImage_category();
		
		int imgDrawerble = context.getResources().getIdentifier(mStringImg, "drawable", context.getPackageName());
		
		
		mViewHoldertmp.image.setImageResource(imgDrawerble);
		
		return view;
	}
	
	private class ViewHolder {
        ImageView image;
    }

}
