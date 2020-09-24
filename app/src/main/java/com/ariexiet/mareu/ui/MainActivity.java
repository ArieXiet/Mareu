package com.ariexiet.mareu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (null == savedInstanceState) {
			replaceFragment(ListMeetingFragment.newInstance(), "frags");
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_de_filtrage, menu);
		return true;
	}

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
				.addToBackStack(tag)
				.replace(R.id.container, fragment, tag)
				.commit();
	}

	public void newMeeting(View view) {
		replaceFragment(NewMeetingFragment.newInstance(), "frags");
	}

	@Override
	public void onBackPressed() {
		int fragmentsInStack = getSupportFragmentManager().getBackStackEntryCount();
		if (fragmentsInStack > 1) {
			getSupportFragmentManager().popBackStack("frags", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		} else if (fragmentsInStack == 1) {
			finish();
		} else {
			super.onBackPressed();
		}
	}


	@Override
	public void onAttachFragment(@NonNull Fragment fragment) {
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		ListMeetingRecyclerViewAdapter mAdapter = ((MyApplication)getApplication()).getRefToAdapter();//TODO a virer
		switch(item.getItemId()) {
			case R.id.action_sort:
				mAdapter.sortItemsByDate();//TODO a virer
				Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_sort2:
				mAdapter.sortItemsByRoom();//TODO a virer
				Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
