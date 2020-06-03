package com.ariexiet.mareu.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.di.DI;
import com.ariexiet.mareu.events.DeleteMeetingEvent;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Objects;

public class ListMeetingFragment extends Fragment {
	private MeetingApiService mApiService;
	private RecyclerView mRecyclerView;

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

	/**
	 * Init the List of neighbours
	 */
	private void initList() {
		List<Meeting> mMeetings = mApiService.getMeetings();
		mRecyclerView.setAdapter(new ListMeetingRecyclerViewAdapter(mMeetings));
	}

	@Override
	public void onResume() {
		super.onResume();
		initList();
	}

	@Override
	public void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EventBus.getDefault().unregister(this);
	}

	@Subscribe
	public void onDeleteMeeting(DeleteMeetingEvent event) {
		mApiService.deleteMeeting(event.mMeeting);
		initList();
	}
}
