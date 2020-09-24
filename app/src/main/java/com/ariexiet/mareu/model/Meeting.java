package com.ariexiet.mareu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Meeting implements Serializable {
	//TODO remettre la date a part
	private Calendar mDate;
	private Calendar mStart;
	private Calendar mEnd;
	private MeetingRoom mRoom;
	private String mSubject;
	private ArrayList<Employee> mAttendees;
	private int mDeleteCode;

	public Meeting(Calendar date, Calendar start, Calendar end, MeetingRoom room, String subject, ArrayList<Employee> attendees, int deleteCode) {
		mDate = date;
		mStart = start;
		mEnd = end;
		mRoom = room;
		mSubject = subject;
		mAttendees = attendees;
		mDeleteCode = deleteCode;
	}

	public Calendar getDate() {
		return mDate;
	}

	public void setDate(Calendar date) {
		mDate = date;
	}

	public Calendar getStart() {
		return mStart;
	}

	public void setStart(Calendar start) {
		mStart = start;
	}

	public Calendar getEnd() {
		return mEnd;
	}

	public void setEnd(Calendar end) {
		mEnd = end;
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
