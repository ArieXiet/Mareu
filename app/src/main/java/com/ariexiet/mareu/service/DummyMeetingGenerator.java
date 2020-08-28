package com.ariexiet.mareu.service;

import com.ariexiet.mareu.model.Employee;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.model.MeetingRoom;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

abstract class DummyMeetingGenerator {

	private static Employee mJean = new Employee("Jean", "jean@lamzone.com");
	private static Employee mYann = new Employee("Yann", "yann@lamzone.com");
	private static Employee mArthur = new Employee("Arthur", "arthur@lamzone.com");
	private static Employee mPierre = new Employee("Pierre", "pierre@lamzone.com");
	private static Employee mStephane = new Employee("Stéphane", "stephane@lamzone.com");
	private static Employee mMaude = new Employee("Maude", "maude@lamzone.com");
	private static Employee mValerie =	new Employee("Valérie", "valerie@lamzone.com");
	private static Employee mLea = new Employee("Léa", "lea@lamzone.com");
	private static Employee mChloe = new Employee("Chloé", "chloe@lamzone.com");
	private static Employee mClara = new Employee("Clara", "clara@lamzone.com");
	private static Employee mEmma = new Employee("Emma", "emma@lamzone.com");
	private static Employee mJade = new Employee("Jade", "jade@lamzone.com");
	private static Employee mLouise = new Employee("Louise", "louise@lamzone.com");
	private static Employee mAlice = new Employee("Alice", "alise@lamzone.com");
	private static Employee mJulie = new Employee("Julie", "julie@lamzone.com");
	private static Employee mLouis = new Employee("Louis", "louis@lamzone.com");
	private static Employee mSimon = new Employee("Simon", "simon@lamzone.com");
	private static Employee mSebastien = new Employee("Sébastien", "sebastien@lamzone.com");
	private static Employee mAntoine = new Employee("Antoine", "antoine@lamzone.com");
	private static Employee mMarc = new Employee("Marc", "marc@lamzone.com");

	private static final List<Employee> DUMMY_EmployeeS = Arrays.asList(
			mJean, mYann, mArthur, mPierre, mStephane, mMaude, mValerie, mLea, mChloe, mClara, mEmma, mJade, mLouise, mAlice, mJulie, mLouis, mSimon, mSebastien, mAntoine, mMarc
	);

	private static MeetingRoom mMeetingRoom1 = new MeetingRoom(1);
	private static MeetingRoom mMeetingRoom2 = new MeetingRoom(2);
	private static MeetingRoom mMeetingRoom3 = new MeetingRoom(3);
	private static MeetingRoom mMeetingRoom4 = new MeetingRoom(4);
	private static MeetingRoom mMeetingRoom5 = new MeetingRoom(5);
	private static MeetingRoom mMeetingRoom6 = new MeetingRoom(6);
	private static MeetingRoom mMeetingRoom7 = new MeetingRoom(7);
	private static MeetingRoom mMeetingRoom8 = new MeetingRoom(8);
	private static MeetingRoom mMeetingRoom9 = new MeetingRoom(9);
	private static MeetingRoom mMeetingRoom10 = new MeetingRoom(10);


	private static final List<MeetingRoom> DUMMY_MEETING_ROOMS = Arrays.asList(
			mMeetingRoom1, mMeetingRoom2, mMeetingRoom3, mMeetingRoom4, mMeetingRoom5, mMeetingRoom6, mMeetingRoom7, mMeetingRoom8, mMeetingRoom9, mMeetingRoom10
	);
	private static final List<Meeting> DUMMY_MEETINGS = Arrays.asList(
			new Meeting(/*new LocalDate(2020, 7, 7),
					new LocalTime(13, 0),
					new LocalTime(13, 45),*/new GregorianCalendar(2020, 7, 7, 13, 0),
					new GregorianCalendar(2020, 7, 7, 13, 45),
					mMeetingRoom1, "Blah",
					new ArrayList<>(Arrays.asList(mJade, mAlice, mAntoine)), 1234),
			new Meeting(/*new LocalDate(2020, 7, 7),
					new LocalTime(14, 0),
					new LocalTime(14,50),*/new GregorianCalendar(2020, 7, 7, 14, 0),
					new GregorianCalendar(2020, 7, 7, 14, 50),
					mMeetingRoom2, "Blih",
					new ArrayList<>(Arrays.asList(mSebastien, mMarc, mMaude)), 2345),
			new Meeting(/*new LocalDate(2020, 7, 7),
					new LocalTime(9, 0),
					new LocalTime(9,30),*/new GregorianCalendar(2020, 7, 7, 9, 0),
					new GregorianCalendar(2020, 7, 7, 9, 30),
					mMeetingRoom3, "Bleh",
					new ArrayList<>(Arrays.asList(mPierre, mLouis, mJulie)), 3456),
			new Meeting(/*new LocalDate(2020, 7, 7),
					new LocalTime(11,15),
					new LocalTime(12, 0),*/new GregorianCalendar(2020, 7, 7, 11, 15),
					new GregorianCalendar(2020, 7, 7, 12, 0),
					mMeetingRoom4, "Bloh",
					new ArrayList<>(Arrays.asList(mSimon, mEmma, mValerie)), 4567),
			new Meeting(/*new LocalDate(2020, 7, 7),
					new LocalTime(14,20),
					new LocalTime(14,55),*/new GregorianCalendar(2020, 7, 7, 14, 20),
					new GregorianCalendar(2020, 7, 7, 14, 55),
					mMeetingRoom5, "Bluh",
					new ArrayList<>(Arrays.asList(mClara, mLouise, mArthur)), 5678),
			new Meeting(/*new LocalDate(2020, 7, 7),
					new LocalTime(13,10),
					new LocalTime(13,45),*/new GregorianCalendar(2020, 7, 7, 13, 10),
					new GregorianCalendar(2020, 7, 7, 13, 45),
					mMeetingRoom6, "Blauh",
					new ArrayList<>(Arrays.asList(mYann, mJean, mStephane)), 6789),
			new Meeting(/*new LocalDate(2020, 7, 7),
					new LocalTime(15,15),
					new LocalTime(16, 0),*/new GregorianCalendar(2020, 7, 7, 15, 15),
					new GregorianCalendar(2020, 7, 7, 16, 0),
					mMeetingRoom7, "Blouh",
					new ArrayList<>(Arrays.asList(mLea, mChloe, mAntoine)), 7890),
			new Meeting(/*new LocalDate(2020, 7, 7),
					new LocalTime(10,30),
					new LocalTime(11,10),*/new GregorianCalendar(2020, 7, 7, 10, 30),
					new GregorianCalendar(2020, 7, 7, 11, 10),
					mMeetingRoom8, "Bleih",
					new ArrayList<>(Arrays.asList(mJade, mSebastien, mJulie)), 8901),
			new Meeting(/*new LocalDate(2020, 7, 7),
					new LocalTime(13, 0),
					new LocalTime(13,55),*/new GregorianCalendar(2020, 7, 7, 13, 0),
					new GregorianCalendar(2020, 7, 7, 13, 55),
					mMeetingRoom9, "Blinh",
					new ArrayList<>(Arrays.asList(mLea, mLouis, mClara)), 9012),
			new Meeting(/*new LocalDate(2020, 7, 7),
					new LocalTime(14,45),
					new LocalTime(15,40),*/new GregorianCalendar(2020, 7, 7, 14, 45),
					new GregorianCalendar(2020, 7, 7, 15, 40),
					mMeetingRoom10, "Blanh",
					new ArrayList<>(Arrays.asList(mChloe, mSimon, mEmma)), 83)
	);

	static List<Employee> generateEmployees() { return new ArrayList<>(DUMMY_EmployeeS) ; }
	static List<Meeting> generateMeetings() {return new ArrayList<>(DUMMY_MEETINGS); }
	static List<MeetingRoom> generateMeetingRooms() {return new ArrayList<>(DUMMY_MEETING_ROOMS);}
}
