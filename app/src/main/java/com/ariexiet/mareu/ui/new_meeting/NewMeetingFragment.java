package com.ariexiet.mareu.ui.new_meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.di.DI;
import com.ariexiet.mareu.model.Employee;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.model.MeetingRoom;
import com.ariexiet.mareu.model.ServiceMeeting;
import com.ariexiet.mareu.service.MeetingApiService;
import com.ariexiet.mareu.ui.MainActivity;
import com.ariexiet.mareu.ui.list_meeting.ListMeetingFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewMeetingFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

	public int mStartOrEnd = 0;
	public int mYear;
	public int mMonth;
	public int mDayOfMonth;
	public int mStartHour;
	public int mEndHour;
	public int mStartMinute;
	public int mEndMinute;
	public ServiceMeeting mPreparedMeeting;
	public Calendar mStartDate = Calendar.getInstance();
	public Calendar mEndDate = Calendar.getInstance();
	public Calendar mC = Calendar.getInstance();
	public boolean mCheckStop;
	public boolean mDateOn;
	public boolean mStartOn;
	public boolean mEndOn;
	private static final String TAG = "NewMeetingFragment";

	private static NewMeetingFragment mInstance;
	@BindView(R.id.editText_sujet)
	public TextInputLayout mEditSujet;
	@BindView(R.id.button_date)
	public Button mButtonDate;
	@BindView(R.id.text_date)
	public TextView mTextDate;
	@BindView(R.id.attendees)
	public TextView mAttendees;
	@BindView(R.id.button_starting_time)
	public Button mButtonStartingTime;
	@BindView(R.id.text_starting_time)
	public TextView mTextStartingTime;
	@BindView(R.id.button_ending_time)
	public Button mButtonEndingTime;
	@BindView(R.id.text_ending_time)
	public TextView mTextEndingTime;
	@BindView(R.id.radioGroup)
	public RadioGroup mRadioGroup1;
	@BindView(R.id.radioGroup2)
	public RadioGroup mRadioGroup2;
	@BindView(R.id.radioButton1)
	public RadioButton mRadioButton1;
	@BindView(R.id.radioButton2)
	public RadioButton mRadioButton2;
	@BindView(R.id.radioButton3)
	public RadioButton mRadioButton3;
	@BindView(R.id.radioButton4)
	public RadioButton mRadioButton4;
	@BindView(R.id.radioButton5)
	public RadioButton mRadioButton5;
	@BindView(R.id.radioButton6)
	public RadioButton mRadioButton6;
	@BindView(R.id.radioButton7)
	public RadioButton mRadioButton7;
	@BindView(R.id.radioButton8)
	public RadioButton mRadioButton8;
	@BindView(R.id.radioButton9)
	public RadioButton mRadioButton9;
	@BindView(R.id.radioButton10)
	public RadioButton mRadioButton10;


	private MeetingApiService mApiService;
	private RadioGroup.OnCheckedChangeListener listener1;
	private RadioGroup.OnCheckedChangeListener listener2;


	public static NewMeetingFragment newInstance() {
		mInstance = new NewMeetingFragment();
		return mInstance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_new_meetingbis, container, false);
		ButterKnife.bind(this, view);

		mDateOn = false;
		mStartOn = false;
		mEndOn = false;
		mApiService = DI.getMeetingApiService();
		mPreparedMeeting = mApiService.getServiceMeeting();
		mCheckStop = false;
		mRadioButton1.setEnabled(false);
		mRadioButton2.setEnabled(false);
		mRadioButton3.setEnabled(false);
		mRadioButton4.setEnabled(false);
		mRadioButton5.setEnabled(false);
		mRadioButton6.setEnabled(false);
		mRadioButton7.setEnabled(false);
		mRadioButton8.setEnabled(false);
		mRadioButton9.setEnabled(false);
		mRadioButton10.setEnabled(false);

		mButtonDate.setOnClickListener(v -> {
			DialogFragment datePicker = new DatePickerFragment(mInstance);
			datePicker.show(Objects.requireNonNull(getFragmentManager()), "date picker");
		});
		mButtonStartingTime.setOnClickListener(v -> {
			DialogFragment timePicker = new TimePickerFragment(mInstance);
			timePicker.show(Objects.requireNonNull(getFragmentManager()), "time picker");
			mStartOrEnd = 1;
		});
		mButtonEndingTime.setOnClickListener(v -> {
			DialogFragment timePicker = new TimePickerFragment(mInstance);
			timePicker.show(Objects.requireNonNull(getFragmentManager()), "time picker");
			mStartOrEnd = 2;
		});

		listener1 = (group, checkedId) -> {
			if (checkedId != -1) {
				mRadioGroup2.setOnCheckedChangeListener(null);
				mRadioGroup2.clearCheck();
				mRadioGroup2.setOnCheckedChangeListener(listener2);
			}
		};
		listener2 = (group, checkedId) -> {
			if (checkedId != -1) {
				mRadioGroup1.setOnCheckedChangeListener(null);
				mRadioGroup1.clearCheck();
				mRadioGroup1.setOnCheckedChangeListener(listener1);
			}
		};
		mRadioGroup1.clearCheck();
		mRadioGroup2.clearCheck();

		mRadioGroup1.setOnCheckedChangeListener(listener1);
		mRadioGroup2.setOnCheckedChangeListener(listener2);

		initList();
		return view;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	private void enableButton() {
		List<Meeting> mMeetings = mApiService.getMeetings();
		mRadioButton1.setEnabled(true);
		mRadioButton2.setEnabled(true);
		mRadioButton3.setEnabled(true);
		mRadioButton4.setEnabled(true);
		mRadioButton5.setEnabled(true);
		mRadioButton6.setEnabled(true);
		mRadioButton7.setEnabled(true);
		mRadioButton8.setEnabled(true);
		mRadioButton9.setEnabled(true);
		mRadioButton10.setEnabled(true);
		for (Meeting in : mMeetings) {
				if ((mStartDate.after(in.getStart()) && mStartDate.before(in.getEnd())) ||
						(mEndDate.after(in.getStart()) && mEndDate.before(in.getEnd())) ||
						(mStartDate.before(in.getStart()) && mEndDate.after(in.getEnd()))) {
					switch (in.getRoom().getRoomNumber()) {
						case 1:
							mRadioButton1.setEnabled(false);
							break;
						case 2:
							mRadioButton2.setEnabled(false);
							break;
						case 3:
							mRadioButton3.setEnabled(false);
							break;
						case 4:
							mRadioButton4.setEnabled(false);
							break;
						case 5:
							mRadioButton5.setEnabled(false);
							break;
						case 6:
							mRadioButton6.setEnabled(false);
							break;
						case 7:
							mRadioButton7.setEnabled(false);
							break;
						case 8:
							mRadioButton8.setEnabled(false);
							break;
						case 9:
							mRadioButton9.setEnabled(false);
							break;
						case 10:
							mRadioButton10.setEnabled(false);
							break;
					}
				}
			}
		}

	private void initList() {
		Log.d(TAG, "initList: DEBUG" );
		mRadioButton1.setText(R.string.salle_1);
		mRadioButton2.setText(R.string.salle_2);
		mRadioButton3.setText(R.string.salle_3);
		mRadioButton4.setText(R.string.salle_4);
		mRadioButton5.setText(R.string.salle_5);
		mRadioButton6.setText(R.string.salle_6);
		mRadioButton7.setText(R.string.salle_7);
		mRadioButton8.setText(R.string.salle_8);
		mRadioButton9.setText(R.string.salle_9);
		mRadioButton10.setText(R.string.salle_10);
		if(mPreparedMeeting.isInProgress()) {
			if(mPreparedMeeting.getDate() != null) {
				String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(mPreparedMeeting.getDate().getTime());
				mTextDate.setText(currentDateString);
			}
			if(mPreparedMeeting.getStartHour() != 0 && mPreparedMeeting.getStartMin() != 0)
				mTextStartingTime.setText(String.format("%02d", mPreparedMeeting.getStartHour()) + " : " + String.format("%02d", mPreparedMeeting.getStartMin()));
			if(mPreparedMeeting.getEndHour() != 0 && mPreparedMeeting.getEndMin() != 0)
				mTextEndingTime.setText(String.format("%02d", mPreparedMeeting.getEndHour()) + " : " + String.format("%02d", mPreparedMeeting.getEndMin()));
			if(mPreparedMeeting.getSubject() != null)
				Objects.requireNonNull(mEditSujet.getEditText()).setText(mPreparedMeeting.getSubject());
			String mAttendeesToText = "";
			ArrayList<Employee> mEmployeeToText = mPreparedMeeting.getAttendees();
			if(mEmployeeToText != null) {
				for(Employee in : mEmployeeToText) {
					mAttendeesToText += in.getName();
					if(in != (mEmployeeToText.get(mEmployeeToText.size() - 1))) {
						mAttendeesToText += ", ";
					}
					mAttendees.setText(mAttendeesToText);
				}
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume: DEBUG:");
		//initList();
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "onStart: DEBUG:");
		Log.d(TAG, "enableButton: DEBUG Date " + mStartDate.getTime());
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "NewMeetingFragment onStop: DEBUG:");
		if (!mCheckStop) {
			Fragment fragment = new ListMeetingFragment();
			((MainActivity) Objects.requireNonNull(getActivity())).replaceFragment(fragment, "frags");
		}
	}


	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		Log.d(TAG, "onDateSet: DEBUG");
		mYear = year;
		mMonth = month;
		mDayOfMonth = dayOfMonth;
		mC.set(Calendar.YEAR, year);
		mC.set(Calendar.MONTH, month);
		mC.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		mStartDate.set(Calendar.YEAR, year);
		mStartDate.set(Calendar.MONTH, month);
		mStartDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		mEndDate.set(Calendar.YEAR, year);
		mEndDate.set(Calendar.MONTH, month);
		mEndDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(mC.getTime());
		mTextDate.setText(currentDateString);
		mPreparedMeeting.setDate(mC);
		mDateOn = true;
		if (mStartOn && mEndOn) {
			enableButton();
		}
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		if (mStartOrEnd == 1) {
			mStartHour = hourOfDay;
			mStartMinute = minute;
			Log.d(TAG, "onTimeSet: DEBUG " + hourOfDay);
			mPreparedMeeting.setStartHour(mStartHour);
			mPreparedMeeting.setStartMin(mStartMinute);
			mTextStartingTime.setText(String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
			Log.d(TAG, "onTimeSet: DEBUG " + String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
			mStartOn = true;
			mStartDate.set(Calendar.HOUR_OF_DAY, mStartHour);
			mStartDate.set(Calendar.MINUTE, mStartMinute);
			if (mDateOn && mEndOn) {
				enableButton();
			}
		} else if (mStartOrEnd == 2){
			mEndHour = hourOfDay;
			mEndMinute = minute;
			mPreparedMeeting.setEndHour(mEndHour);
			mPreparedMeeting.setEndMin(mEndMinute);
			mTextEndingTime.setText(String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
			mEndOn = true;
			mEndDate.set(Calendar.HOUR_OF_DAY, mEndHour);
			mEndDate.set(Calendar.MINUTE, mEndMinute);
			if (mDateOn && mStartOn) {
				enableButton();
			}
		}
	}

	@OnClick(R.id.button)
	void chooseAttendees() {
		prepareMeeting();
		mCheckStop = true;
		AttendeesCheckListFragment fragment = new AttendeesCheckListFragment();
		((MainActivity) Objects.requireNonNull(getActivity())) .replaceFragment(fragment, "frags");
	}

	private void createStartDate() {
		mStartDate.set(Calendar.YEAR, mYear);
		mStartDate.set(Calendar.MONTH, mMonth + 1);
		mStartDate.set(Calendar.DAY_OF_MONTH, mDayOfMonth);
		mStartDate.set(Calendar.HOUR, mStartHour);
		mStartDate.set(Calendar.MINUTE, mStartMinute);
	}

	private void createEndDate() {
		mEndDate.set(Calendar.YEAR, mYear);
		mEndDate.set(Calendar.MONTH, mMonth + 1);
		mEndDate.set(Calendar.DAY_OF_MONTH, mDayOfMonth);
		mEndDate.set(Calendar.HOUR, mEndHour);
		mEndDate.set(Calendar.MINUTE, mEndMinute);
	}

	void prepareMeeting() {
		//ArrayList<Employee> mAttendees = mApiService.getServiceMeeting().getAttendees();
		createStartDate();
		createEndDate();
		int mDeleteCode = 0;
		MeetingRoom mRoom;
		int mX = 0;
		int checkedRadioId = mRadioGroup1.getCheckedRadioButtonId();
		int checkedRadioId2 = mRadioGroup2.getCheckedRadioButtonId();
		switch(checkedRadioId) {
			case R.id.radioButton1:
				mX = 0;
				break;
			case R.id.radioButton2:
				mX = 1;
				break;
			case R.id.radioButton3:
				mX = 2;
				break;
			case R.id.radioButton4:
				mX = 3;
				break;
			case R.id.radioButton5:
				mX = 4;
				break;
			default:
				switch(checkedRadioId2) {
					case R.id.radioButton6:
						mX = 5;
						break;
					case R.id.radioButton7:
						mX = 6;
						break;
					case R.id.radioButton8:
						mX = 7;
						break;
					case R.id.radioButton9:
						mX = 8;
						break;
					case R.id.radioButton10:
						mX = 9;
						break;
				}
		}
		mRoom = mApiService.getMeetingRooms().get(mX);
		mPreparedMeeting.setRoom(mRoom);
		mPreparedMeeting.setSubject(Objects.requireNonNull(mEditSujet.getEditText()).getText().toString());
		mPreparedMeeting.setInProgress(true);
	}

	@OnClick(R.id.button2)
	void addMeeting() {
		prepareMeeting();
		//mStartDate.set(mPreparedMeeting.getDate().YEAR, mPreparedMeeting.getDate().MONTH,
				//mPreparedMeeting.getDate().DAY_OF_MONTH, mPreparedMeeting.getStartHour(), mPreparedMeeting.getStartMin());
		//mEndDate.set(mPreparedMeeting.getDate().YEAR, mPreparedMeeting.getDate().MONTH,
				//mPreparedMeeting.getDate().DAY_OF_MONTH, mPreparedMeeting.getEndHour(), mPreparedMeeting.getEndMin());
		Meeting mMeeting = new Meeting(mPreparedMeeting.getDate(), mStartDate, mEndDate, mPreparedMeeting.getRoom(),
				mPreparedMeeting.getSubject(), mPreparedMeeting.getAttendees(),
				mPreparedMeeting.getDeleteCode()
		);
		mApiService.createMeeting(mMeeting);
		mPreparedMeeting.setInProgress(false);
		Fragment fragment = new ListMeetingFragment();
		((MainActivity) Objects.requireNonNull(getActivity())).replaceFragment(fragment, "frags");
	}
}
