package com.ariexiet.mareu.service;

import com.ariexiet.mareu.model.Employee;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.model.MeetingRoom;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {
	private final List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();
	private final List<MeetingRoom> mMeetingRooms = DummyMeetingGenerator.generateMeetingRooms();
	private final List<Employee> mEmployees = DummyMeetingGenerator.generateEmployees();

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
	public void deleteMeeting(Meeting meeting) {
		mMeetings.remove(meeting);
	}

	@Override
	public void createMeeting(Meeting meeting) {
		mMeetings.add(meeting);
	}
}
