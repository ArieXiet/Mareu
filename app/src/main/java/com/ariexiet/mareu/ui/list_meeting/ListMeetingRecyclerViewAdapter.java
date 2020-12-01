package com.ariexiet.mareu.ui.list_meeting;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.di.DI;
import com.ariexiet.mareu.model.Employee;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.ui.MainActivity;
import com.ariexiet.mareu.ui.details_meeting.DetailsMeetingFragment;
import com.ariexiet.mareu.ui.new_meeting.DatePickerFragment;
import com.ariexiet.mareu.ui.new_meeting.NewMeetingFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMeetingRecyclerViewAdapter extends RecyclerView.Adapter<ListMeetingRecyclerViewAdapter.ViewHolder> implements DatePickerDialog.OnDateSetListener {

	private static List<Meeting> mMeetings;
	private static List<Meeting> mMeetingsByDate;
	private static List<Meeting> mMeetingsToShow;
	private static Context mContext;
	private static final String TAG = "ListMeetingRecyclerView";
	public int mYear;
	public int mMonth;
	public int mDayOfMonth;
	public Calendar mC = Calendar.getInstance();

	private static ListMeetingRecyclerViewAdapter mInstance;

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
				if (((mContext)) != null) {
					((MainActivity) mContext).replaceFragment(fragment, "frags");
				} else {
					Log.d(TAG, "DEBUG: ViewHolder: fragment.getActivity null");
				}
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
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			holder.mMeetingName.setText(mMeeting.getSubject() + ", " + dateFormat.format(mMeeting.getStart().getTime()));
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


	public void sortItemsByDate(List<Meeting> meetingsByDate){
		//mMeetings = new ArrayList<Meeting>();
		mMeetings = meetingsByDate;
		this.notifyDataSetChanged();

	}

	public void sortItemsByRoom(){
		Collections.sort(mMeetings, new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				int num1 = o1.getRoom().getRoomNumber();
				int num2 = o2.getRoom().getRoomNumber();
				return Integer.compare(num1, num2);
			}
		});
		this.notifyDataSetChanged();
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		mYear = year;
		mMonth = month;
		mDayOfMonth = dayOfMonth;
		mC.set(Calendar.YEAR, year);
		mC.set(Calendar.MONTH, month);
		mC.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		mMeetingsByDate = DI.getMeetingApiService().getMeetingsByDate(mC);

	}

	@Override
	public int getItemCount() {
		if (mMeetings == null) {
			return 0;
		} else {
			return mMeetings.size();
		}
	}

}
