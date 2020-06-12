package com.ariexiet.mareu.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.di.DI;
import com.ariexiet.mareu.events.DeleteMeetingEvent;
import com.ariexiet.mareu.model.Employee;
import com.ariexiet.mareu.model.Meeting;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMeetingRecyclerViewAdapter extends RecyclerView.Adapter<ListMeetingRecyclerViewAdapter.ViewHolder> {

	private static List<Meeting> mMeetings;
	private static Context mContext;
	private static final String TAG = "ListMeetingRecyclerView";

	public ListMeetingRecyclerViewAdapter(List<Meeting> meetings, Context context) {
		mMeetings = meetings;
		mContext = context;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.room_logo)
		public ImageView mRoomLogo;
		@BindView(R.id.item_list_name)
		public TextView mMeetingName;
		@BindView(R.id.item_list_participants)
		public TextView mParticipants;
		@BindView(R.id.item_list_delete_button)
		public ImageButton mDeleteButton;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(v -> {

				DetailsMeetingFragment fragment = DetailsMeetingFragment.newInstance(mMeetings.get(getAdapterPosition()), mContext);
				((FragmentActivity)mContext).getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.container, fragment)
						.addToBackStack("ListMeetingFragment")
						.commit();
			});
		}
	}
	@NonNull
	@Override
	public ListMeetingRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_meeting,parent,false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ListMeetingRecyclerViewAdapter.ViewHolder holder, int position) {
		final Meeting mMeeting = mMeetings.get(position);
		String mAttendeesToText = "";
		ArrayList<Employee> mEmployeeToText = mMeeting.getAttendees();
		for(Employee in : mEmployeeToText) {
			mAttendeesToText += in.getName();
			if(in != (mEmployeeToText.get(mEmployeeToText.size() - 1))) {
				mAttendeesToText += ", ";
			}
		}
		holder.mMeetingName.setText(mMeeting.getSubject());
		holder.mParticipants.setText(mAttendeesToText);
		holder.mRoomLogo.setImageResource(mMeeting.getRoom().getRoomLogo());
		//holder.mDeleteButton.setOnClickListener(v -> EventBus.getDefault().post(new DeleteMeetingEvent(mMeeting)));
		holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "DEBUG: onBindViewHolder: ");
				DI.getMeetingApiService().deleteMeeting(mMeeting);
				notifyItemRemoved(position);
				//EventBus.getDefault().post(new DeleteMeetingEvent(mMeeting));
			}
		});
	}


	@Override
	public int getItemCount() {
		return mMeetings.size();
	}

}
