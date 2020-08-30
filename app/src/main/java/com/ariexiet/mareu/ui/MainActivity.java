package com.ariexiet.mareu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.ui.list_meeting.ListMeetingFragment;
import com.ariexiet.mareu.ui.list_meeting.ListMeetingRecyclerViewAdapter;
import com.ariexiet.mareu.ui.new_meeting.NewMeetingFragment;

import java.util.Collections;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
	ListMeetingRecyclerViewAdapter mAdapter;


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
			Objects.requireNonNull(this.getSupportActionBar()).hide();
			this.findViewById(R.id.floatingActionButton).setVisibility(View.INVISIBLE);
		}
	}
/*	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_sort:
				mAdapter.sortItemsByDate();
				Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_sort2:
				Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}*/
}
