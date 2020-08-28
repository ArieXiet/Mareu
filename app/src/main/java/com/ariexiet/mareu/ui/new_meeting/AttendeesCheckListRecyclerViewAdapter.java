package com.ariexiet.mareu.ui.new_meeting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.mareu.R;
import com.ariexiet.mareu.model.Employee;

import java.util.List;

import butterknife.BindView;

public class AttendeesCheckListRecyclerViewAdapter extends RecyclerView.Adapter<AttendeesCheckListRecyclerViewAdapter.ViewHolder> {

	private static List<Employee> mEmployees;
	private static Context mContext;
	private static final String TAG = "AttendeesCheckListRecyclerView";

	public AttendeesCheckListRecyclerViewAdapter(List<Employee> employees, Context context) {
		mEmployees = employees;
		mContext = context;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.checkbox)
		public CheckBox mCheckBox;
		@BindView(R.id.attendee_name_check)
		public TextView mAttendeeName;
		@BindView(R.id.attendee_email_check)
		public TextView mAttendeeEmail;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
		}
	}
	@NonNull
	@Override
	public AttendeesCheckListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_attendees_chek_list,parent,false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull AttendeesCheckListRecyclerViewAdapter.ViewHolder holder, int position) {
		final Employee mEmployee = mEmployees.get(position);
		holder.mAttendeeName.setText(mEmployee.getName());
		holder.mAttendeeEmail.setText(mEmployee.getEmail());
	}


	@Override
	public int getItemCount() {
		return mEmployees.size();
	}

}
