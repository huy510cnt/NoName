package com.hh.appnewgroup.fragment;


import java.util.ArrayList;

import com.hh.appnewgroup.R;
import com.hh.appnewgroup.adapter.AdapterCategorys;
import com.hh.appnewgroup.db.CategoryObject;
import com.hh.appnewgroup.db.ReadDB;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CategoryFragment extends Fragment {
	public static ListView lvCategory;
	private ReadDB mReadDB;
	ArrayList<CategoryObject> mListTheLoai;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_category, container, false);
		
		lvCategory = (ListView) rootView.findViewById(R.id.lvCategory);

		mReadDB = new ReadDB(getActivity());
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
		} catch (Exception e) {

		}

		mListTheLoai = new ArrayList<CategoryObject>();
		mListTheLoai = mReadDB.getListTheloai();

		AdapterCategorys mAdapterCategorys = new AdapterCategorys(getActivity(),
				R.layout.list_categorys_layout, mListTheLoai);

		lvCategory.setAdapter(mAdapterCategorys);

		lvCategory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});

		mReadDB.closeDatabase();
		return rootView;
	}
}
