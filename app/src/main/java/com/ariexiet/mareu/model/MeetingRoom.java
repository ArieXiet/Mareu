package com.ariexiet.mareu.model;

import android.graphics.Color;

import com.ariexiet.mareu.R;

public class MeetingRoom {
	private int mRoomNumber;
	private int mRoomLogo;
	private int mColor;

	public MeetingRoom(int roomNumber) {
		mRoomNumber = roomNumber;
	}

	public int getRoomNumber() {
		return mRoomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		mRoomNumber = roomNumber;
	}
	public int getRoomLogo() {
		switch (mRoomNumber) {
			case 1:
				mRoomLogo = R.mipmap.ic_salle1;
				break;
			case 2:
				mRoomLogo = R.mipmap.ic_salle2;
				break;
			case 3:
				mRoomLogo = R.mipmap.ic_salle3;
				break;
			case 4:
				mRoomLogo = R.mipmap.ic_salle4;
				break;
			case 5:
				mRoomLogo = R.mipmap.ic_salle5;
				break;
			case 6:
				mRoomLogo = R.mipmap.ic_salle6;
				break;
			case 7:
				mRoomLogo = R.mipmap.ic_salle7;
				break;
			case 8:
				mRoomLogo = R.mipmap.ic_salle8;
				break;
			case 9:
				mRoomLogo = R.mipmap.ic_salle9;
				break;
			case 10:
				mRoomLogo = R.mipmap.ic_salle10;
				break;
		}
		return mRoomLogo;
	}
	public int getRoomColor() {
		switch (mRoomNumber) {
			case 1:
				mColor = Color.rgb(255, 83, 85);
				break;
			case 2:
				mColor = Color.rgb(95, 193, 204);
				break;
			case 3:
				mColor = Color.rgb(140, 225, 168);
				break;
			case 4:
				mColor = Color.rgb(226, 139, 218);
				break;
			case 5:
				mColor = Color.rgb(208, 166, 56);
				break;
			case 6:
				mColor = Color.rgb(9, 255, 24);
				break;
			case 7:
				mColor = Color.rgb(82, 75, 241);
				break;
			case 8:
				mColor = Color.rgb(176, 22, 232);
				break;
			case 9:
				mColor = Color.rgb(155, 155, 155);
				break;
			case 10:
				mColor = Color.rgb(255, 255, 85);
				break;
		}
		return mColor;
	}
}
