package com.ariexiet.mareu.ui.new_meeting;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.di.DI;
import com.ariexiet.mareu.events.DeleteMeetingEvent;
import com.ariexiet.mareu.model.Employee;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.service.MeetingApiService;
import com.ariexiet.mareu.ui.list_meeting.ListMeetingFragment;
import com.ariexiet.mareu.ui.list_meeting.ListMeetingRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class AttendeesCheckListFragment extends Fragment {
	private MeetingApiService mApiService;
	private RecyclerView mRecyclerView;
	private AttendeesCheckListRecyclerViewAdapter mAdapter;

	public static AttendeesCheckListFragment newInstance() {
		return new AttendeesCheckListFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApiService = DI.getMeetingApiService();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.attendees_check_list, container, false);
		Context context = view.getContext();
		mRecyclerView = (RecyclerView) view;
		mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
		initList();
		return view;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	/**
	 * Init the List of neighbours
	 */
	private void initList() {
		Log.d(TAG, "DEBUG: initList: ");
		((AppCompatActivity) getActivity()).getSupportActionBar().show();
		List<Employee> mEmployees = mApiService.getEmployees();
		mAdapter = new AttendeesCheckListRecyclerViewAdapter(mEmployees, getContext());
		mRecyclerView.setAdapter(mAdapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		initList();
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "DEBUG: onStart: register");
		EventBus.getDefault().register(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "DEBUG: onStop: unregister");
		EventBus.getDefault().unregister(this);
	}

	@Subscribe
	public void onDeleteMeeting(DeleteMeetingEvent event) {
		Log.d(TAG, "DEBUG: onDeleteMeeting");
		mApiService.deleteMeeting(event.mMeeting);
		initList();
	}
}
