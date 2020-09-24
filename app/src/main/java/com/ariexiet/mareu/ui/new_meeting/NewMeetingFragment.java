package com.ariexiet.mareu.ui.new_meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

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
import com.santalu.maskedittext.MaskEditText;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
	private static Context mContext;


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
		Context context = view.getContext();
		Context mTestContext = getActivity().getApplicationContext();
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

		mButtonDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment datePicker = new DatePickerFragment(mInstance);
				datePicker.show(getFragmentManager(), "date picker");
			}
		});
		mButtonStartingTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment timePicker = new TimePickerFragment(mInstance);
				timePicker.show(getFragmentManager(), "time picker");
				mStartOrEnd = 1;
			}
		});
		mButtonEndingTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment timePicker = new TimePickerFragment(mInstance);
				timePicker.show(getFragmentManager(), "time picker");
				mStartOrEnd = 2;
			}
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
		Log.d(TAG, "enableButton: DEBUG Date " + mStartDate.getTime());
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
			Log.d(TAG, "enableButton: DEBUG DateCompaDebut " + in.getSubject() + " " + in.getStart().getTime());
			Log.d(TAG, "enableButton: DEBUG DateCompaFin " + in.getSubject() + " " + in.getEnd().getTime());
			if (in.getDate() == mC) {
				if (((mStartDate.getTimeInMillis() >= in.getStart().getTimeInMillis()) &&
						(mStartDate.getTimeInMillis() < in.getEnd().getTimeInMillis())) ||
						((mEndDate.getTimeInMillis() > in.getStart().getTimeInMillis()) &&
								(mEndDate.getTimeInMillis() <= in.getEnd().getTimeInMillis())) ||
						((mStartDate.getTimeInMillis() <= in.getStart().getTimeInMillis()) &&
								(mEndDate.getTimeInMillis() >= in.getEnd().getTimeInMillis()))) {
					switch (in.getRoom().getRoomNumber()) {
						case 1:
							mRadioButton1.setEnabled(false);
						case 2:
							mRadioButton2.setEnabled(false);
						case 3:
							mRadioButton3.setEnabled(false);
						case 4:
							mRadioButton4.setEnabled(false);
						case 5:
							mRadioButton5.setEnabled(false);
						case 6:
							mRadioButton6.setEnabled(false);
						case 7:
							mRadioButton7.setEnabled(false);
						case 8:
							mRadioButton8.setEnabled(false);
						case 9:
							mRadioButton9.setEnabled(false);
						case 10:
							mRadioButton10.setEnabled(false);
					}
				}
			}
		}
		mRadioButton1.setEnabled(true);
	}

	private void initList() {
		mRadioButton1.setText("Salle 1");
		mRadioButton2.setText("Salle 2");
		mRadioButton3.setText("Salle 3");
		mRadioButton4.setText("Salle 4");
		mRadioButton5.setText("Salle 5");
		mRadioButton6.setText("Salle 6");
		mRadioButton7.setText("Salle 7");
		mRadioButton8.setText("Salle 8");
		mRadioButton9.setText("Salle 9");
		mRadioButton10.setText("Salle 10");
		if(mPreparedMeeting.isInProgress()) {
			if(mPreparedMeeting.getDate() != null) {
				String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(mPreparedMeeting.getDate().getTime());
				mTextDate.setText(currentDateString);
			}
			if(mPreparedMeeting.getStartHour() != 0 && mPreparedMeeting.getStartMin() != 0) {
				mTextStartingTime.setText(String.format("%02d", mPreparedMeeting.getStartHour()) + " : " + String.format("%02d", mPreparedMeeting.getStartMin()));
			}
			if(mPreparedMeeting.getEndHour() != 0 && mPreparedMeeting.getEndMin() != 0) {
				mTextEndingTime.setText(String.format("%02d", mPreparedMeeting.getEndHour()) + " : " + String.format("%02d", mPreparedMeeting.getEndMin()));
			}
			if(mPreparedMeeting.getSubject() != null) {
				mEditSujet.getEditText().setText(mPreparedMeeting.getSubject());
			}
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
		initList();
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
			((MainActivity) getActivity()).replaceFragment(fragment, "frags");
		}
	}


	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		mYear = year;
		mMonth = month;
		mDayOfMonth = dayOfMonth;
		mC.set(Calendar.YEAR, year);
		mC.set(Calendar.MONTH, month);
		mC.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(mC.getTime());
		mTextDate.setText(currentDateString);
		mPreparedMeeting.setDate(mC);
		mPreparedMeeting.setInProgress(true);
		mDateOn = true;
		if (mDateOn && mStartOn && mEndOn) {
			enableButton();
		}
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		if (mStartOrEnd == 1) {
			mTextStartingTime.setText(String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
			mStartHour = hourOfDay;
			mStartMinute = minute;
			mPreparedMeeting.setStartHour(mStartHour);
			mPreparedMeeting.setStartMin(mStartMinute);
			mPreparedMeeting.setInProgress(true);
			mStartOn = true;
			mStartDate.set(Calendar.YEAR, mC.YEAR);
			mStartDate.set(Calendar.MONTH, mC.MONTH);
			mStartDate.set(Calendar.DAY_OF_MONTH, mC.DAY_OF_MONTH);
			mStartDate.set(Calendar.HOUR, mStartHour);
			mStartDate.set(Calendar.MINUTE, mStartMinute);
			if (mDateOn && mStartOn && mEndOn) {
				enableButton();
			}
		} else if (mStartOrEnd == 2){
			mTextEndingTime.setText(String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
			mEndHour = hourOfDay;
			mEndMinute = minute;
			mPreparedMeeting.setEndHour(mEndHour);
			mPreparedMeeting.setEndMin(mEndMinute);
			mPreparedMeeting.setInProgress(true);
			mEndOn = true;
			createEndDate();
			if (mDateOn && mStartOn && mEndOn) {
				enableButton();
			}
		}
	}

	@OnClick(R.id.button)
	void chooseAttendees() {
		MeetingRoom mRoom = null;
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
		mPreparedMeeting.setSubject(mEditSujet.getEditText().getText().toString());
		mPreparedMeeting.setInProgress(true);
		mCheckStop = true;
		AttendeesCheckListFragment fragment = new AttendeesCheckListFragment();
		((MainActivity)getActivity()) .replaceFragment(fragment, "frags");
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

	ServiceMeeting prepareMeeting() {
		MeetingRoom mRoom = null;
		ArrayList<Employee> mAttendees = mApiService.getServiceMeeting().getAttendees();
		createStartDate();
		createEndDate();
		int mDeleteCode = 0000;
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
		mPreparedMeeting.setInProgress(false);
		return new ServiceMeeting(mC, mStartHour, mStartMinute, mEndHour, mEndMinute, mRoom, mEditSujet.getEditText().getText().toString(), mAttendees, mDeleteCode, false);
	}

	@OnClick(R.id.button2)
	void addMeeting() {
		//ServiceMeeting mPreparedMeeting = prepareMeeting();
		Meeting mMeeting = new Meeting(mC, mStartDate, mEndDate, mPreparedMeeting.getRoom(),
				mPreparedMeeting.getSubject(), mPreparedMeeting.getAttendees(),
				mPreparedMeeting.getDeleteCode()
		);
		mApiService.createMeeting(mMeeting);
		mPreparedMeeting.setInProgress(false);
		Fragment fragment = new ListMeetingFragment();
		((MainActivity) getActivity()).replaceFragment(fragment, "frags");
		/*getActivity().getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.container, fragment)
				.addToBackStack(null)
				.commit();*/
	}
}
