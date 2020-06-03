package com.ariexiet.mareu.service;

import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.model.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {
	private final List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();

	@Override
	public List<Meeting> getMeetings() {
		return mMeetings;
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