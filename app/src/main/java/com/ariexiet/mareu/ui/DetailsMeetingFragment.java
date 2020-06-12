package com.ariexiet.mareu.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.model.Meeting;

import org.joda.time.LocalDate;
import org.joda.time.Minutes;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsMeetingFragment extends Fragment {

	@BindView(R.id.logo)
	public ImageView mLogo;
	@BindView(R.id.subject)
	public TextView mSubject;
	@BindView(R.id.starting_time)
	public TextView mStartingTime;
	@BindView(R.id.length)
	public TextView mLength;
	@BindView(R.id.salle)
	public TextView mRoomName;
	@BindView(R.id.delete_code)
	public TextView mDeleteCode;
	@BindView(R.id.calendar)
	public ImageView mCalendar;
	@BindView(R.id.clock)
	public ImageView mClock;
	@BindView(R.id.place)
	public ImageView mPlace;

	public static final String ARG_MEETING = "argMeeting";

	private Meeting mMeeting;
	private RecyclerView mRecyclerView;
	private static Context mContext;
	private int mColor;

	public static DetailsMeetingFragment newInstance(Meeting meeting, Context context) {
		DetailsMeetingFragment fragment = new DetailsMeetingFragment();
		Bundle args = new Bundle();
		args.putSerializable(ARG_MEETING, meeting);
		fragment.setArguments(args);
		mContext = context;
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((AppCompatActivity) getActivity()).getSupportActionBar().hide();
		getActivity().findViewById(R.id.floatingActionButton).setVisibility(View.GONE);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_details, container, false);

		mMeeting = (Meeting) getArguments().getSerializable(ARG_MEETING);
		ButterKnife.bind(this, view);
		mColor = mMeeting.getRoom().getRoomColor();
		int mDuree = Minutes.minutesBetween(new LocalDate(mMeeting.getStartingTime().getTimeInMillis()),
				new LocalDate(mMeeting.getEndDateTime().getTimeInMillis()))
				.getMinutes();
		mLogo.setImageResource(mMeeting.getRoom().getRoomLogo());
		mLength.setText("Durée: " + String.format("%02d",mDuree) +"mn");
		mSubject.setText("Sujet: " + mMeeting.getSubject());
		mSubject.setTextColor(mColor);
		mStartingTime.setText("Le " + String.format("%02d",mMeeting.getStartingTime().get(Calendar.DAY_OF_MONTH)) +
				"/" + String.format("%02d",mMeeting.getStartingTime().get(Calendar.MONTH)) +
				" à " + String.format("%02d",mMeeting.getStartingTime().get(Calendar.HOUR_OF_DAY)) +
				"h" + String.format("%02d",mMeeting.getStartingTime().get(Calendar.MINUTE)));
		mRoomName.setText("Salle " + mMeeting.getRoom().getRoomNumber());
		mDeleteCode.setText("Code de suppression: " + mMeeting.getDeleteCode());
		mCalendar.setColorFilter(mColor);
		mClock.setColorFilter(mColor);
		mPlace.setColorFilter(mColor);

		ListAttendeesFragment fragment = ListAttendeesFragment.newInstance(mMeeting.getAttendees(), mColor);
		((FragmentActivity)mContext).getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.attendees_container, fragment)
				.addToBackStack("ListMeetingFragment")
				.commit();
		return view;
	}

	@Override
	public void onStop() {
		super.onStop();
	}
}
