package com.hh.tinnhan.chuctet.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hh.tinnhan.chuctet.R;
import com.hh.tinnhan.chuctet.adapter.FavoriteAdapter;
import com.hh.tinnhan.chuctet.db.ReadDB;
import com.hh.tinnhan.chuctet.db.SMSObject;

public class FavoritesFragment extends Fragment {
	public static ListView lvFavorite;
	private Context mContext;
	private ReadDB mReadDB;
	private ArrayList<SMSObject> lstSmsObjectsFavorite;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_favotite, container, false);
		
		mContext = rootView.getContext();
		lvFavorite = (ListView) rootView.findViewById(R.id.lvFavorite);
		
		lstSmsObjectsFavorite = new ArrayList<SMSObject>();
		
		mReadDB = new ReadDB(mContext);
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
		} catch (Exception e) {

		}
		
		lstSmsObjectsFavorite = mReadDB.getlistSMSObjectFavorite();
		
		FavoriteAdapter PopularSMSAdapter = new FavoriteAdapter(mContext,
				R.layout.item_list_sms_favorite, lstSmsObjectsFavorite);
		
		lvFavorite.setAdapter(PopularSMSAdapter);
		
		return rootView;
	}

}
