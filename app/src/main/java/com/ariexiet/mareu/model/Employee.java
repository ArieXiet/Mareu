package com.ariexiet.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {

	private String mName;
	private String mEmail;

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String email) {
		mEmail = email;
	}

	public Employee(String name, String email) {
		mName = name;
		mEmail = email;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

	}
}
