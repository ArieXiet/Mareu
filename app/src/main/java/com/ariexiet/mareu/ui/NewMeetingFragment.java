package com.ariexiet.mareu.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.di.DI;
import com.ariexiet.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewMeetingFragment extends Fragment {

	@BindView(R.id.editText_sujet)
	public EditText mEditSujet;
	@BindView(R.id.radioGroup)
	public RadioGroup mRadioGroup1;
	@BindView(R.id.radioGroup2)
	public RadioGroup mRadioGroup2;
	@BindView(R.id.radioButton2)
	public RadioButton mRadioButton1;
	@BindView(R.id.radioButton3)
	public RadioButton mRadioButton2;
	@BindView(R.id.radioButton4)
	public RadioButton mRadioButton3;
	@BindView(R.id.radioButton5)
	public RadioButton mRadioButton4;
	@BindView(R.id.radioButton6)
	public RadioButton mRadioButton5;
	@BindView(R.id.radioButton7)
	public RadioButton mRadioButton6;
	@BindView(R.id.radioButton8)
	public RadioButton mRadioButton7;
	@BindView(R.id.radioButton9)
	public RadioButton mRadioButton8;
	@BindView(R.id.radioButton10)
	public RadioButton mRadioButton9;
	@BindView(R.id.radioButton11)
	public RadioButton mRadioButton10;
	@BindView(R.id.editTextDate)
	public EditText mEditStart;
	@BindView(R.id.editTextTime)
	public EditText mEditEnd;

	private MeetingApiService mApiService;

	public static NewMeetingFragment newInstance() {
		return new NewMeetingFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_new_meeting, container, false);
		Context context = view.getContext();
		ButterKnife.bind(this, view);
		mApiService = DI.getMeetingApiService();
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
		//EventBus.getDefault().unregister(this);
	}

}
