package com.hh.appnewgroup.adapter;

import java.util.ArrayList;

import com.hh.appnewgroup.R;
import com.hh.appnewgroup.db.CategoryObject;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterCategorys extends ArrayAdapter<CategoryObject>{
	
	Activity context = null;
	ArrayList<CategoryObject> arr = null;
	
	public AdapterCategorys(Activity context, int LayoutID, ArrayList<CategoryObject> list) {
		super(context, LayoutID, list);
		this.context = context;
		this.arr = list;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.list_categorys_layout, null);
		}
		
		CategoryObject Category = arr.get(position);
		
		TextView txt = (TextView) convertView.findViewById(R.id.txtTitle);
		
		txt.setText(Category.getName_category());
		
		return convertView;
	}

}
