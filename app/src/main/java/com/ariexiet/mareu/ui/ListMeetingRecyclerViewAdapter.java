package com.ariexiet.mareu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.events.DeleteMeetingEvent;
import com.ariexiet.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMeetingRecyclerViewAdapter extends RecyclerView.Adapter<ListMeetingRecyclerViewAdapter.ViewHolder> {

	private final List<Meeting> mMeetings;

	public ListMeetingRecyclerViewAdapter(List<Meeting> meetings) {
		mMeetings = meetings;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.room_logo)
		public ImageView mRoomLogo;
		@BindView(R.id.item_list_name)
		public TextView mMeetingName;
		@BindView(R.id.item_list_delete_button)
		public ImageButton mDeleteButton;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
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
		holder.mMeetingName.setText(mMeeting.getSubject());
		holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EventBus.getDefault().post(new DeleteMeetingEvent(mMeeting));
			}
		});
	}

	@Override
	public int getItemCount() {
		return mMeetings.size();
	}

}
