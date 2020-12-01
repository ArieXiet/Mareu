package com.ariexiet.mareu.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.di.DI;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.ui.list_meeting.ListMeetingFragment;
import com.ariexiet.mareu.ui.list_meeting.ListMeetingRecyclerViewAdapter;
import com.ariexiet.mareu.ui.new_meeting.AttendeesCheckListFragment;
import com.ariexiet.mareu.ui.new_meeting.DatePickerFragment;
import com.ariexiet.mareu.ui.new_meeting.NewMeetingFragment;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

	private static final String TAG = "MainActivity DEBUG";
	private int mYear;
	private int mMonth;
	private int mDayOfMonth;
	private Calendar mC = Calendar.getInstance();
	private List<Meeting> mMeetingsByDate;

	/**
	 * if there's no saved instance, launch ListMeetingFragment
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (null == savedInstanceState) {
			replaceFragment(ListMeetingFragment.newInstance(), "frags");
		}
	}

	/**
	 * bound the menu with corresponding xml file
	 * @param menu
	 * @return true
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_de_filtrage, menu);
		return true;
	}

	/**
	 * used to change fragment
	 * @param fragment
	 * @param tag
	 */
	public void replaceFragment(Fragment fragment, String tag) {
		Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);

		/*if (currentFragment != null) {
			if(currentFragment.getClass() == fragment.getClass()) {
				return;
			}

			if(getSupportFragmentManager().findFragmentByTag(tag) != null) {
				getSupportFragmentManager()
						.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
		}*/
		getSupportFragmentManager()
				.beginTransaction()
				.addToBackStack(null)
				.replace(R.id.container, fragment)
				.commit();
	}

	/**
	 * open NewMeetingFragment when fab (R.id.floatingActionButton) is clicked
	 * @param view
	 */
	public void newMeeting(View view) {
		replaceFragment(NewMeetingFragment.newInstance(), "frags");
	}


	/**
	 * show or hide fab when needed
	 * @param fragment
	 */
	@Override
	public void onAttachFragment(@NonNull Fragment fragment) {
		Log.d(TAG, "onAttachFragment: " + fragment);
		super.onAttachFragment(fragment);
		if (fragment instanceof ListMeetingFragment) {
			Objects.requireNonNull(this.getSupportActionBar()).show();
			this.findViewById(R.id.floatingActionButton).setVisibility(View.VISIBLE);

		} else {
			Objects.requireNonNull(this.getSupportActionBar()).hide();
			this.findViewById(R.id.floatingActionButton).setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * handle action from menu
	 * @param item
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		ListMeetingRecyclerViewAdapter mAdapter = ((MyApplication)getApplication()).getRefToAdapter();//TODO a changer
		switch(item.getItemId()) {
			case R.id.action_sort:
				DialogFragment datePicker = new DatePickerFragment(this);
				datePicker.show(getSupportFragmentManager(), "date picker");
				//ListMeetingFragment.newInstance().sortItemsByDate();
				//mAdapter.sortItemsByDate();//TODO a changer
				Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();

				return true;
			case R.id.action_sort2:
				mAdapter.sortItemsByRoom();//TODO a changer
				Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		ListMeetingRecyclerViewAdapter mAdapter = ((MyApplication)getApplication()).getRefToAdapter();
		mYear = year;
		mMonth = month;
		mDayOfMonth = dayOfMonth;
		mC.set(Calendar.YEAR, year);
		mC.set(Calendar.MONTH, month);
		mC.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		Log.d(TAG, "onDateSet: DEBUG: mC " + mC.getTime());
		mMeetingsByDate = DI.getMeetingApiService().getMeetingsByDate(mC);
		mAdapter.sortItemsByDate(mMeetingsByDate);
		Objects.requireNonNull(this.getSupportActionBar()).show();
		this.findViewById(R.id.floatingActionButton).setVisibility(View.VISIBLE);
	}
}
