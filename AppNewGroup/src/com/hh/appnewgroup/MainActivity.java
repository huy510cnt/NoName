package com.hh.appnewgroup;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.hh.appnewgroup.adapter.AdapterCategorys;
import com.hh.appnewgroup.db.CategoryObject;
import com.hh.appnewgroup.db.ReadDB;

public class MainActivity extends Activity {

	private ListView lvCategory;
	private ReadDB mReadDB;
	ArrayList<CategoryObject> mListTheLoai;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		lvCategory = (ListView) findViewById(R.id.lvCategory);
		
		mReadDB = new ReadDB(this);
		
		mReadDB = new ReadDB(this);
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
			mReadDB.closeDatabase();
		} catch (Exception e) {

		}
		try {
			mReadDB.createDatabase();

		} catch (Exception e) {

		}
		mReadDB.openDatabase();
		
		mListTheLoai = new ArrayList<CategoryObject>();
		mListTheLoai = mReadDB.getListTheloai();
		
		AdapterCategorys mAdapterCategorys = new AdapterCategorys(this, 0, mListTheLoai);
		
		lvCategory.setAdapter(mAdapterCategorys);
	}
}
