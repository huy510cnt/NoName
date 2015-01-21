package com.hh.appnewgroup.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hh.appnewgroup.R;
import com.hh.appnewgroup.adapter.PopularSMSAdapter;
import com.hh.appnewgroup.db.ReadDB;
import com.hh.appnewgroup.db.SMSObject;

public class BetterFragment extends Fragment {
	public static ListView lvPopulated;
	private Context mContext;
	private ReadDB mReadDB;
	private ArrayList<SMSObject> lstSmsObjects;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_better, container, false);
		
		mContext = rootView.getContext();
		lvPopulated = (ListView) rootView.findViewById(R.id.lvPopulated);
		
		lstSmsObjects = new ArrayList<SMSObject>();
		
		mReadDB = new ReadDB(mContext);
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
		} catch (Exception e) {

		}
		
		lstSmsObjects = mReadDB.getlistSMSObjectPopulate();
		
		PopularSMSAdapter PopularSMSAdapter = new PopularSMSAdapter(mContext,
				R.layout.item_list_sms, lstSmsObjects);
		
		lvPopulated.setAdapter(PopularSMSAdapter);
		
		return rootView;
	}

}
