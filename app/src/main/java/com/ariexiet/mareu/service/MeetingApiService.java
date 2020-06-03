package com.ariexiet.mareu.service;

import com.ariexiet.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {
	List<Meeting> getMeetings();

	void deleteMeeting(Meeting meeting);

	void createMeeting(Meeting meeting);
}
