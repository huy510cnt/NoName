package com.hh.appnewgroup;

import java.util.ArrayList;

import com.hh.appnewgroup.adapter.PopularSMSAdapter;
import com.hh.appnewgroup.db.CategoryObject;
import com.hh.appnewgroup.db.ReadDB;
import com.hh.appnewgroup.db.SMSObject;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
public class GreetingsActivity extends Activity {

	private ListView listGreet;
	private int id_category = 0;
	private String mStringImg="";

	private ReadDB mReadDB;
	private ArrayList<SMSObject> mLisSms;
	private ImageView image_header;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_greetings);

		FadingActionBarHelper helper = new FadingActionBarHelper()
        .actionBarBackground(R.drawable.bg_item_red_actionbar)
        .headerLayout(R.layout.header)
        .contentLayout(R.layout.activity_greetings);
        setContentView(helper.createView(this));
        helper.initActionBar(this);
        
        getActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Lời chúc"));
        getActionBar().setIcon(R.drawable.ic_arrow_left);
        getActionBar().setHomeButtonEnabled(true);
        ImageView iconImage = (ImageView) findViewById(android.R.id.home);
        iconImage.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	onBackPressed();
            }
        });
        
        
		/*Drawable transparentDrawable = new ColorDrawable(Color.TRANSPARENT);
		getActionBar().setBackgroundDrawable(transparentDrawable);
		getActionBar().setTitle("Lời chúc");*/
		
		Bundle extras = getIntent().getExtras();
		id_category = extras.getInt("id_category");
		mStringImg=extras.getString("mStringImg");
		mReadDB = new ReadDB(GreetingsActivity.this);
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
		} catch (Exception e) {

		}
		mLisSms=new ArrayList<SMSObject>();
		mLisSms=mReadDB.getlistSMSObject(id_category);

		mReadDB.close();
		
		image_header=(ImageView)findViewById(R.id.image_header);
		int imgDrawerble = getResources().getIdentifier(mStringImg, "drawable", getPackageName());
		image_header.setImageResource(imgDrawerble);
		
		listGreet = (ListView) findViewById(android.R.id.list);
		PopularSMSAdapter adapter=new PopularSMSAdapter(GreetingsActivity.this, R.layout.item_list_sms, mLisSms);
		listGreet.setAdapter(adapter);
	}


}
