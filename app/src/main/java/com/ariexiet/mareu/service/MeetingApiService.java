package com.ariexiet.mareu.service;

import com.ariexiet.mareu.model.Employee;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.model.MeetingRoom;
import com.ariexiet.mareu.model.ServiceMeeting;

import java.util.Calendar;
import java.util.List;

public interface MeetingApiService {
	List<Meeting> getMeetings();

	List<Meeting> getMeetingsByDate(Calendar date);

	List<MeetingRoom> getMeetingRooms();

	List<Employee> getEmployees();

	ServiceMeeting getServiceMeeting();

	void deleteMeeting(Meeting meeting);

	void createMeeting(Meeting meeting);

	void createPreparedMeeting(ServiceMeeting serviceMeeting);

}
