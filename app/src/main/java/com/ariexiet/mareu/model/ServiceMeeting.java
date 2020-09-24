package com.ariexiet.mareu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class ServiceMeeting implements Serializable {
	private Calendar mDate;
	private int mStartHour;
	private int mStartMin;
	private int mEndHour;
	private int mEndMin;

	private MeetingRoom mRoom;
	private String mSubject;
	private ArrayList<Employee> mAttendees;
	private int mDeleteCode;
	private boolean mInProgress;

	public ServiceMeeting(Calendar date, Integer startHour, Integer startMin, Integer endHour, Integer endMin, MeetingRoom room, String subject, ArrayList<Employee> attendees, int deleteCode, boolean inProgress) {
		mDate = date;
		mStartHour = startHour;
		mStartMin = startMin;
		mEndHour = endHour;
		mEndMin = endMin;
		mRoom = room;
		mSubject = subject;
		mAttendees = attendees;
		mDeleteCode = deleteCode;
		mInProgress = inProgress;
	}

	public boolean isInProgress() {
		return mInProgress;
	}

	public void setInProgress(boolean inProgress) {
		mInProgress = inProgress;
	}

	public Calendar getDate() {
		return mDate;
	}

	public void setDate(Calendar date) {
		mDate = date;
	}

	public int getStartHour() {
		return mStartHour;
	}

	public void setStartHour(int startHour) {
		mStartHour = startHour;
	}

	public int getStartMin() {
		return mStartMin;
	}

	public void setStartMin(int startMin) {
		mStartMin = startMin;
	}

	public int getEndHour() {
		return mEndHour;
	}

	public void setEndHour(int endHour) {
		mEndHour = endHour;
	}

	public int getEndMin() {
		return mEndMin;
	}

	public void setEndMin(int endMin) {
		mEndMin = endMin;
	}

	public MeetingRoom getRoom() {
		return mRoom;
	}

	public void setRoom(MeetingRoom room) {
		this.mRoom = room;
	}

	public String getSubject() {
		return mSubject;
	}

	public void setSubject(String subject) {
		this.mSubject = subject;
	}

	public ArrayList<Employee> getAttendees() {
		return mAttendees;
	}

	public void setAttendees(ArrayList<Employee> attendees) {
		this.mAttendees = attendees;
	}

	public int getDeleteCode() {
		return mDeleteCode;
	}

	public void setDeleteCode(int deleteCode) {
		this.mDeleteCode = deleteCode;
	}

}
