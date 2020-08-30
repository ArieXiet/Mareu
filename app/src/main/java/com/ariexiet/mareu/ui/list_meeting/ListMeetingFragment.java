package com.ariexiet.mareu.ui.list_meeting;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.di.DI;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.service.MeetingApiService;


import java.util.List;
import java.util.Objects;

public class ListMeetingFragment extends Fragment {
	private MeetingApiService mApiService;
	private RecyclerView mRecyclerView;
	private static final String TAG = "DeleteEvent";
	private ListMeetingRecyclerViewAdapter mAdapter;

	public static ListMeetingFragment newInstance() {
		return new ListMeetingFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApiService = DI.getMeetingApiService();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_meeting_list, container, false);
		Context context = view.getContext();
		mRecyclerView = (RecyclerView) view;
		mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
		initList();
		return view;
	}

	private void initList() {
		Log.d(TAG, "DEBUG: initList: ");
		((AppCompatActivity) getActivity()).getSupportActionBar().show();
		List<Meeting> mMeetings = mApiService.getMeetings();
		mAdapter = new ListMeetingRecyclerViewAdapter(mMeetings, getContext());
		mRecyclerView.setAdapter(mAdapter);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getActivity().getMenuInflater().inflate(R.menu.menu_de_filtrage, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_sort:
				mAdapter.sortItemsByDate(mApiService.getMeetings());
				Toast.makeText(getActivity(), "Item 1 selected", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_sort2:
				Toast.makeText(getActivity(), "Item 2 selected", Toast.LENGTH_SHORT).show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		initList();
	}

}
