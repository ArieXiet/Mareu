package com.ariexiet.mareu.ui.new_meeting;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.di.DI;
import com.ariexiet.mareu.model.Employee;
import com.ariexiet.mareu.service.MeetingApiService;
import com.ariexiet.mareu.ui.MainActivity;

import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class AttendeesCheckListFragment extends Fragment {
	private MeetingApiService mApiService;
	private RecyclerView mRecyclerView;
	private AttendeesCheckListRecyclerViewAdapter mAdapter;

	public static AttendeesCheckListFragment newInstance() {
		AttendeesCheckListFragment fragment = new AttendeesCheckListFragment();
		return fragment;
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
		mRecyclerView = view.findViewById(R.id.list_attendees_check);
		Button mAttendeeButton = view.findViewById(R.id.attendees_button);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
		initList();
		mAttendeeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mApiService.getServiceMeeting().setAttendees(mAdapter.mCheckedEmployees);
				NewMeetingFragment fragment = new NewMeetingFragment();
				((MainActivity)getActivity()).replaceFragment(fragment, "frags");

			}
		});
		return view;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}


	private void initList() {
		Log.d(TAG, "DEBUG: initList: ");
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
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop: DEBUG:");
		//NewMeetingFragment fragment = new NewMeetingFragment();
		//((MainActivity)getActivity()).replaceFragment(fragment, "frags");
	}
}
