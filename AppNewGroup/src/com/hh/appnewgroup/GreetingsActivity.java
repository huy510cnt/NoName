package com.hh.appnewgroup;

import java.util.ArrayList;

import com.hh.appnewgroup.db.CategoryObject;
import com.hh.appnewgroup.db.ReadDB;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class GreetingsActivity extends Activity {

	private TextView tvBack, tvTitle;
	private ListView listGreet;
	private int id_category = 0;

	private ReadDB mReadDB;
	ArrayList<CategoryObject> mListTheLoai;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_greetings);
		getActionBar().hide();
		initUI();
		Bundle extras = getIntent().getExtras();
		id_category = extras.getInt("id_category");

		mReadDB = new ReadDB(GreetingsActivity.this);
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
		} catch (Exception e) {

		}

		mListTheLoai = new ArrayList<CategoryObject>();
		mListTheLoai = mReadDB.getListTheloai();

		mReadDB.close();
		CategoryObject ob = new CategoryObject();
		for (int i = 0; i < mListTheLoai.size(); i++)
			if (mListTheLoai.get(i).getId_category() == id_category)
				ob = mListTheLoai.get(i);

		tvTitle.setText(ob.getName_category());
		tvBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
	}

	public void initUI() {
		tvBack = (TextView) findViewById(R.id.tvBack);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		listGreet = (ListView) findViewById(R.id.listGreet);
	}
}
