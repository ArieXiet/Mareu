package com.ariexiet.mareu.model;

import java.util.ArrayList;

public class Meeting {
	private String mStartingTime;
	private String mEndingTime;
	private MeetingRoom mRoom;
	private String mSubject;
	private ArrayList<Employee> mAttendees;
	private int mDeleteCode;

	public Meeting(String startingTime, String endingTime, MeetingRoom room, String subject, ArrayList<Employee> attendees, int deleteCode) {
		mStartingTime = startingTime;
		mEndingTime = endingTime;
		mRoom = room;
		mSubject = subject;
		mAttendees = attendees;
		mDeleteCode = deleteCode;
	}

	public String getStartingTime() {
		return mStartingTime;
	}

	public void setStartingTime(String startingTime) {
		this.mStartingTime = startingTime;
	}

	public String getEndingTime() {
		return mEndingTime;
	}

	public void setEndingTime(String endingTime) {
		this.mEndingTime = endingTime;
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

	public ArrayList getAttendees() {
		return mAttendees;
	}

	public void setAttendees(ArrayList attendees) {
		this.mAttendees = attendees;
	}

	public int getDeleteCode() {
		return mDeleteCode;
	}

	public void setDeleteCode(int deleteCode) {
		this.mDeleteCode = deleteCode;
	}

}
