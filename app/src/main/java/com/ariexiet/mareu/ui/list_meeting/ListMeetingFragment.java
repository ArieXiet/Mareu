package com.ariexiet.mareu.ui.list_meeting;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.di.DI;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.service.MeetingApiService;
import com.ariexiet.mareu.ui.MyApplication;
import com.ariexiet.mareu.ui.new_meeting.DatePickerFragment;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ListMeetingFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
	private MeetingApiService mApiService;
	private RecyclerView mRecyclerView;
	private static final String TAG = "DeleteEvent";
	private ListMeetingRecyclerViewAdapter mAdapter;

	public int mYear;
	public int mMonth;
	public int mDayOfMonth;
	public Calendar mC = Calendar.getInstance();
	private static Context mContext;
	private static ListMeetingRecyclerViewAdapter mInstance;
	private boolean mSort = false;

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
		Log.d(TAG, "DEBUG: initList: " + mSort);
		((AppCompatActivity) getActivity()).getSupportActionBar().show();
		List<Meeting> mMeetings = mApiService.getMeetings();
		if (!mSort){
			mAdapter = new ListMeetingRecyclerViewAdapter(mMeetings, getContext());
			((MyApplication)getActivity().getApplication()).setRefToAdapter(mAdapter);
			mRecyclerView.setAdapter(mAdapter);
		} else {
			List<Meeting> mMeetingsByDate = DI.getMeetingApiService().getMeetingsByDate(mC);
			mAdapter = new ListMeetingRecyclerViewAdapter(mMeetingsByDate, getContext());
			((MyApplication)getActivity().getApplication()).setRefToAdapter(mAdapter);
			mRecyclerView.setAdapter(mAdapter);
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getActivity().getMenuInflater().inflate(R.menu.menu_de_filtrage, menu);
		return true;
	}

	@Override
	public void onResume() {
		super.onResume();
		//initList();
		getActivity().findViewById(R.id.floatingActionButton).setVisibility(View.VISIBLE);
	}

	public void sortItemsByDate(){
		DialogFragment datePicker = new DatePickerFragment(mInstance);
		datePicker.show(((FragmentActivity)mContext).getSupportFragmentManager(), "date picker");
		Log.d(TAG, "sortItemsByDate: DEBUG");
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		mYear = year;
		mMonth = month;
		mDayOfMonth = dayOfMonth;
		mC.set(Calendar.YEAR, year);
		mC.set(Calendar.MONTH, month);
		mC.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		Log.d(TAG, "onDateSet: DEBUG");
		mSort = true;
		List<Meeting> mMeetingsByDate = DI.getMeetingApiService().getMeetingsByDate(mC);
		mAdapter = new ListMeetingRecyclerViewAdapter(mMeetingsByDate, getContext());
		((MyApplication)getActivity().getApplication()).setRefToAdapter(mAdapter);
		mRecyclerView.setAdapter(mAdapter);
	}
}
