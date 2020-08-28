package com.ariexiet.mareu.ui.new_meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
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
import androidx.fragment.app.ListFragment;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.di.DI;
import com.ariexiet.mareu.model.Employee;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.model.MeetingRoom;
import com.ariexiet.mareu.service.MeetingApiService;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewMeetingFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

	private static Employee mLea = new Employee("Léa", "lea@lamzone.com");
	private static Employee mLouis = new Employee("Louis", "louis@lamzone.com");
	private static Employee mClara = new Employee("Clara", "clara@lamzone.com");
	public int mStartOrEnd = 0;
	public int mYear;
	public int mMonth;
	public int mDayOfMonth;
	public int mStartHour;
	public int mEndHour;
	public int mStartMinute;
	public int mEndMinute;

	private static NewMeetingFragment mInstance;
	@BindView(R.id.editText_sujet)
	public TextInputLayout mEditSujet;
	@BindView(R.id.button_date)
	public Button mButtonDate;
	@BindView(R.id.text_date)
	public TextView mTextDate;
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

		mApiService = DI.getMeetingApiService();
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

	/**
	 * Init the List of neighbours
	 */
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
	}

	@Override
	public void onResume() {
		super.onResume();
		initList();
	}

	@Override
	public void onStart() {
		super.onStart();
		//EventBus.getDefault().register(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		Fragment fragment = new ListMeetingFragment();
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
	}


	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		mYear = year;
		mMonth = month;
		mDayOfMonth = dayOfMonth;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
		mTextDate.setText(currentDateString);
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		if (mStartOrEnd == 1) {
			mTextStartingTime.setText(String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
			mStartHour = hourOfDay;
			mStartMinute = minute;
		} else if (mStartOrEnd == 2){
			mTextEndingTime.setText(String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
			mEndHour = hourOfDay;
			mEndMinute = minute;
		}
	}

	@OnClick(R.id.button)
	void chooseAttendees() {
		AttendeesCheckListFragment fragment = AttendeesCheckListFragment.newInstance();
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

	}

	@OnClick(R.id.button2)
	void addMeeting() {
		MeetingRoom mRoom = null;
		ArrayList<Employee> mAttendees = new ArrayList<>(Arrays.asList(mLea, mLouis, mClara));
		Calendar mStartDate = Calendar.getInstance();
		mStartDate.set(Calendar.YEAR, mYear);
		mStartDate.set(Calendar.MONTH, mMonth + 1);
		mStartDate.set(Calendar.DAY_OF_MONTH, mDayOfMonth);
		mStartDate.set(Calendar.HOUR, mStartHour);
		mStartDate.set(Calendar.MINUTE, mStartMinute);
		Calendar mEndDate = Calendar.getInstance();
		mEndDate.set(Calendar.YEAR, mYear);
		mEndDate.set(Calendar.MONTH, mMonth);
		mEndDate.set(Calendar.DAY_OF_MONTH, mDayOfMonth);
		mEndDate.set(Calendar.HOUR, mEndHour);
		mEndDate.set(Calendar.MINUTE, mEndMinute);
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
		Meeting mMeeting = new Meeting(mStartDate, mEndDate, mRoom,
				mEditSujet.getEditText().getText().toString(), mAttendees, mDeleteCode
		);
		mApiService.createMeeting(mMeeting);
		Fragment fragment = new ListMeetingFragment();
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
	}

}
