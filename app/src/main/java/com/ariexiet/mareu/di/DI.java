package com.ariexiet.mareu.di;

import com.ariexiet.mareu.service.DummyMeetingApiService;
import com.ariexiet.mareu.service.MeetingApiService;

public class DI {

	private static final MeetingApiService service = new DummyMeetingApiService();

	public static MeetingApiService getMeetingApiService() {
		return service;
	}

}
