package com.ariexiet.mareu.service;

import com.ariexiet.mareu.model.Employee;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.model.MeetingRoom;

import java.util.List;

public interface MeetingApiService {
	List<Meeting> getMeetings();

	List<MeetingRoom> getMeetingRooms();

	List<Employee> getEmployees();

	void deleteMeeting(Meeting meeting);

	void createMeeting(Meeting meeting);
}
