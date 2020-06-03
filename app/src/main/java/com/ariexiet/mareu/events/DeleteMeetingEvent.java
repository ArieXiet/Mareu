package com.ariexiet.mareu.events;

import com.ariexiet.mareu.model.Meeting;

public class DeleteMeetingEvent {
	public final Meeting mMeeting;

	public DeleteMeetingEvent(Meeting meeting) {
		mMeeting = meeting;
	}
}
