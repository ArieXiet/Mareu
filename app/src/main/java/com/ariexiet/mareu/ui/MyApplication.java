package com.ariexiet.mareu.ui;

import android.app.Application;

import com.ariexiet.mareu.ui.list_meeting.ListMeetingRecyclerViewAdapter;

public class MyApplication extends Application {

	private ListMeetingRecyclerViewAdapter mAdapter;

	public void setRefToAdapter(ListMeetingRecyclerViewAdapter adapter) {
		mAdapter = adapter;
	}

	public ListMeetingRecyclerViewAdapter getRefToAdapter () {
		return mAdapter;
	}
}
