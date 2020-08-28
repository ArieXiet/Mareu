package com.ariexiet.mareu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.ui.list_meeting.ListMeetingFragment;
import com.ariexiet.mareu.ui.new_meeting.NewMeetingFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListMeetingFragment fragment = ListMeetingFragment.newInstance();
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.container, fragment)
				.addToBackStack("ListMeetingFragment")
				.commit();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_de_filtrage, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		boolean mDisplay = false;
		switch (item.getItemId()) {
			case R.id.action_sort:
				MenuFragment fragment = MenuFragment.newInstance();
				if (mDisplay == false) {
					getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.container_menu, fragment)
							.addToBackStack("ListMeetingFragment")
							.commit();
					mDisplay = true;
				}else {
					getSupportFragmentManager().beginTransaction().remove(fragment).commit();
					mDisplay = false;
				}
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void newMeeting(View view) {
		NewMeetingFragment fragment = NewMeetingFragment.newInstance();
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.container, fragment)
				.addToBackStack("ListMeetingFragment")
				.commit();
	}

	@Override
	public void onAttachFragment(@NonNull Fragment fragment) {
		super.onAttachFragment(fragment);
		if (fragment instanceof ListMeetingFragment) {
			this.findViewById(R.id.floatingActionButton).setVisibility(View.VISIBLE);
		} else {
			this.getSupportActionBar().hide();
			this.findViewById(R.id.floatingActionButton).setVisibility(View.INVISIBLE);
		}
	}
}
