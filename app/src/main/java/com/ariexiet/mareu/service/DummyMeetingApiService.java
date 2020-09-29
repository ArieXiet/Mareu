package com.ariexiet.mareu.service;

import com.ariexiet.mareu.model.Employee;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.model.MeetingRoom;
import com.ariexiet.mareu.model.ServiceMeeting;

import java.util.Calendar;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {
	private final List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();
	private List<Meeting> mMeetingsByDate = null;
	private final List<MeetingRoom> mMeetingRooms = DummyMeetingGenerator.generateMeetingRooms();
	private final List<Employee> mEmployees = DummyMeetingGenerator.generateEmployees();
	private ServiceMeeting mServiceMeeting = DummyMeetingGenerator.serviceMeeting();

	@Override
	public List<Meeting> getMeetings() {
		return mMeetings;
	}

	@Override
	public List<MeetingRoom> getMeetingRooms() {
		return mMeetingRooms;
	}

	@Override
	public List<Employee> getEmployees() {
		return mEmployees;
	}

	@Override
	public ServiceMeeting getServiceMeeting() { return mServiceMeeting; }

	@Override
	public void deleteMeeting(Meeting meeting) {
		mMeetings.remove(meeting);
	}

	@Override
	public void createMeeting(Meeting meeting) {
		mMeetings.add(meeting);
	}

	@Override
	public void createPreparedMeeting(ServiceMeeting serviceMeeting) {
		mServiceMeeting = serviceMeeting;
	}

	@Override
	public List<Meeting> getMeetingsByDate(Calendar date) {
		Calendar mTestStart = null;
		Calendar mTestEnd = null;
		mTestStart.set(date.YEAR, date.MONTH, date.DAY_OF_MONTH, 0, 1);
		mTestEnd.set(date.YEAR, date.MONTH, date.DAY_OF_MONTH, 23, 59);
		mMeetingsByDate = null;
		for (Meeting in : mMeetings) {
			if (in.getDate().after(mTestStart) && in.getStart().before(mTestEnd)) {
				mMeetingsByDate.add(in);
			}
		}
		return mMeetingsByDate;
	}
}
