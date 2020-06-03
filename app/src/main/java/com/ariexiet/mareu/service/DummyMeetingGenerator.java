package com.ariexiet.mareu.service;

import com.ariexiet.mareu.model.Employee;
import com.ariexiet.mareu.model.Meeting;
import com.ariexiet.mareu.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class DummyMeetingGenerator {

	private static Employee mJean = new Employee("Jean");
	private static Employee mYann = new Employee("Yann");
	private static Employee mArthur = new Employee("Arthur");
	private static Employee mPierre = new Employee("Pierre");
	private static Employee mStephane = new Employee("Stéphane");
	private static Employee mMaude = new Employee("Maude");
	private static Employee mValerie =	new Employee("Valérie");
	private static Employee mLea = new Employee("Léa");
	private static Employee mChloe = new Employee("Chloé");
	private static Employee mClara = new Employee("Clara");
	private static Employee mEmma = new Employee("Emma");
	private static Employee mJade = new Employee("Jade");
	private static Employee mLouise = new Employee("Louise");
	private static Employee mAlice = new Employee("Alice");
	private static Employee mJulie = new Employee("Julie");
	private static Employee mLouis = new Employee("Louis");
	private static Employee mSimon = new Employee("Simon");
	private static Employee mSebastien = new Employee("Sébastien");
	private static Employee mAntoine = new Employee("Antoine");
	private static Employee mMarc = new Employee("Marc");

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


	private static final List<Meeting> DUMMY_MeetingS = Arrays.asList(
			new Meeting("13h","13h45mn", mMeetingRoom1, "Blah", new ArrayList<>(Arrays.asList(mJade, mAlice, mAntoine)), 1234),
			new Meeting("14h","14h50mn", mMeetingRoom2, "Blih", new ArrayList<>(Arrays.asList(mSebastien, mMarc, mMaude)), 2345),
			new Meeting("09h","09h30mn", mMeetingRoom3, "Bleh", new ArrayList<>(Arrays.asList(mPierre, mLouis, mJulie)), 1234),
			new Meeting("11h15","12h", mMeetingRoom4, "Bloh", new ArrayList<>(Arrays.asList(mSimon, mEmma, mValerie)), 1234),
			new Meeting("14h20","14h55mn", mMeetingRoom5, "Bluh", new ArrayList<>(Arrays.asList(mClara, mLouise, mArthur)), 1234),
			new Meeting("13h10","13h45mn", mMeetingRoom6, "Blauh", new ArrayList<>(Arrays.asList(mYann, mJean, mStephane)), 1234),
			new Meeting("15h15","16h", mMeetingRoom7, "Blouh", new ArrayList<>(Arrays.asList(mLea, mChloe, mAntoine)), 1234),
			new Meeting("10h30","11h10mn", mMeetingRoom8, "Bleih", new ArrayList<>(Arrays.asList(mJade, mSebastien, mJulie)), 1234),
			new Meeting("13h","13h55mn", mMeetingRoom9, "Blinh", new ArrayList<>(Arrays.asList(mLea, mLouis, mClara)), 1234),
			new Meeting("14h45","15h40mn", mMeetingRoom10, "Blanh", new ArrayList<>(Arrays.asList(mChloe, mSimon, mEmma)), 1234)
	);

	static List<Employee> generateEmployees() { return new ArrayList<>(DUMMY_EmployeeS); }
	static List<Meeting> generateMeetings() {return new ArrayList<>(DUMMY_MeetingS); }
}
