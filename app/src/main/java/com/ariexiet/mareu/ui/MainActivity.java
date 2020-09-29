package com.ariexiet.mareu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.ui.list_meeting.ListMeetingFragment;
import com.ariexiet.mareu.ui.list_meeting.ListMeetingRecyclerViewAdapter;
import com.ariexiet.mareu.ui.new_meeting.AttendeesCheckListFragment;
import com.ariexiet.mareu.ui.new_meeting.NewMeetingFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity  {

	private static final String TAG = "MainActivity DEBUG";

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

		if (currentFragment != null) {
			if(currentFragment.getClass() == fragment.getClass()) {
				return;
			}

			if(getSupportFragmentManager().findFragmentByTag(tag) != null) {
				getSupportFragmentManager()
						.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
		}
		getSupportFragmentManager()
				.beginTransaction()
				.addToBackStack(tag)
				.replace(R.id.container, fragment, tag)
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
	 * handle click on the back button
	 */
	@Override
	public void onBackPressed() {
		int fragmentsInStack = getSupportFragmentManager().getBackStackEntryCount();
		Log.d(TAG, "onBackPressed: DEBUG " + fragmentsInStack);
		if (fragmentsInStack > 1) {
			getSupportFragmentManager().popBackStack("frags", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		} else {
			super.onBackPressed();
		}
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
			this.findViewById(R.id.floatingActionButton).setVisibility(View.VISIBLE);
		} else if (fragment instanceof AttendeesCheckListFragment) {
			Objects.requireNonNull(this.getSupportActionBar()).hide();
			this.findViewById(R.id.floatingActionButton).setVisibility(View.INVISIBLE);
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
				//ListMeetingFragment.newInstance().sortItemsByDate();
				mAdapter.sortItemsByDate();//TODO a changer
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
}
