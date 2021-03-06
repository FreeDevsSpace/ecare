/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package com.example.administrator.ecare.helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class Patient_SQLiteHandler extends SQLiteOpenHelper {

	private static final String TAG = Patient_SQLiteHandler.class.getSimpleName();

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "ecare_patient";

	// Login table name
	private static final String TABLE_USER = "patient_registration";




	// Login Table Columns names

	private static final String KEY_Patient_ID = "Patient_ID";
	private static final String KEY_Full_Name = "Full_Name";
	private static final String KEY_Email = "Email";
	private static final String KEY_Address = "Address";
	private static final String KEY_City = "City";
	private static final String KEY_DOB = "DOB";
	private static final String KEY_Blood_Group = "Blood_Group";
	private static final String KEY_Mobile_Number = "Mobile_Number";
	private static final String KEY_Password = "Password";

	public Patient_SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
				+ KEY_Patient_ID + " INTEGER PRIMARY KEY,"
				+ KEY_Mobile_Number + " TEXT,"
				+ KEY_Full_Name + " TEXT,"
				+ KEY_DOB + " TEXT,"
				+ KEY_Email + " TEXT,"
				+ KEY_Address + " TEXT,"
				+ KEY_Blood_Group + " TEXT,"
				+ KEY_Password + " TEXT " + ")";

		db.execSQL(CREATE_LOGIN_TABLE);

		Log.d(TAG, "Patient Database tables created");
	}


	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

		// Create tables again
		onCreate(db);

	}
	/**
	 * Storing user details in database
	 */
	public void addUser(String Patient_ID, String Full_Name, String Mobile_Number, String DOB, String Email,
						 String Address,String Blood_Group, String Password) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_Patient_ID,Patient_ID);
		values.put(KEY_Full_Name,Full_Name);
		values.put(KEY_Mobile_Number,Mobile_Number);
		values.put(KEY_DOB,DOB);
		values.put(KEY_Email, Email);
		values.put(KEY_Address,Address);
		values.put(KEY_Blood_Group,Blood_Group);
		values.put(KEY_Password, Password);


		// Inserting Row
		long id = db.insert(TABLE_USER,null,values);
		db.close(); // Closing database connection
		Log.d(TAG, "New Patient inserted into sqlite: " + id);
	}


	/**
	 * Getting user data from database
	 */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> patient = new HashMap<String, String>();
		String selectQuery = "SELECT  Patient_ID, Full_Name, Mobile_Number, DOB, Address, Blood_Group FROM " + TABLE_USER;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			patient.put("Patient_ID",cursor.getString(0));
			patient.put("Full_Name", cursor.getString(1));
			patient.put("Mobile_Number", cursor.getString(2));
			patient.put("DOB", cursor.getString(3));
		//	patient.put("Email", cursor.getString(5));
			patient.put("Address", cursor.getString(4));
			patient.put("Blood_Group", cursor.getString(5));
//     		patient.put("Password", cursor.getString(8));

		}

		cursor.close();
		db.close();
		Log.d(TAG, "Fetching Patient data from Sqlite: " + patient.toString());

		return patient;
	}

	/**
	 * Re crate database Delete all tables and create them again
	 */
	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_USER, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}


}
