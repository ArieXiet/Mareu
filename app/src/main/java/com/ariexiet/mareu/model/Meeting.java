package com.ariexiet.mareu.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Meeting implements Serializable {
	private GregorianCalendar mStartingTime;
	private GregorianCalendar mEndDateTime;
	private MeetingRoom mRoom;
	private String mSubject;
	private ArrayList<Employee> mAttendees;
	private int mDeleteCode;

	public Meeting(GregorianCalendar startingTime, GregorianCalendar endingTime, MeetingRoom room, String subject, ArrayList<Employee> attendees, int deleteCode) {
		mStartingTime = startingTime;
		mEndDateTime = endingTime;
		mRoom = room;
		mSubject = subject;
		mAttendees = attendees;
		mDeleteCode = deleteCode;
	}

	public GregorianCalendar getStartingTime() {
		return mStartingTime;
	}

	public void setStartingTime(GregorianCalendar startingTime) {
		mStartingTime = startingTime;
	}

	public GregorianCalendar getEndDateTime() {
		return mEndDateTime;
	}

	public void setEndDateTime(GregorianCalendar endDateTime) {
		mEndDateTime = endDateTime;
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
