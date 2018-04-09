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

public class Pathologist_SQLiteHandler extends SQLiteOpenHelper {

	private static final String TAG = Patient_SQLiteHandler.class.getSimpleName();

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "ecare";

	// Login table name

	private static final String TABLE_PATHOLOGIST = "pathologist_registration";


	// Login Table Columns names

	private  static final String KEY_Pathologist_ID="Pathologist_ID";
	private static final String KEY_Full_Name = "Full_Name";
	private static final String KEY_Email = "Email";
	private static final String KEY_Address = "Address";
	private static final String KEY_City = "City";
	private static final String KEY_DOB = "DOB";
	private static final String KEY_Blood_Group = "Blood_Group";
	private static final String KEY_Mobile_Number = "Mobile_Number";
	private static final String KEY_Password = "Password";
	private static final String KEY_Qualification = "Qualification";


	public Pathologist_SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_PLOGIN_TABLE = "CREATE TABLE " + TABLE_PATHOLOGIST + "(" //+KEY_ID + "INTEGER PRIMARY KEY,"
				+ KEY_Pathologist_ID + " INTEGER PRIMARY KEY,"
				+ KEY_Full_Name + " TEXT ,"
				+ KEY_Mobile_Number + " TEXT,"
				+ KEY_DOB + " TEXT,"
				+ KEY_Qualification + " TEXT,"
				+ KEY_Address + " TEXT,"
				+ KEY_Blood_Group + " TEXT,"
				+ KEY_Email + " TEXT,"
				+ KEY_Password + " TEXT " + " TEXT " + ")";

		db.execSQL(CREATE_PLOGIN_TABLE);
		Log.d(TAG, "Database tables created");


	}
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATHOLOGIST);


		// Create tables again
		onCreate(db);

	}


	/**
	 * Storing user details in database
	 */


	public void addPathologist( String Full_Name, String Mobile_Number, String DOB,String Qualification,String Address, String Blood_Group, String Email, String Password) {
		SQLiteDatabase db3 = this.getWritableDatabase();

		ContentValues values = new ContentValues();
//values.put(KEY_Pathologist_ID,Pathologist_ID);
		values.put(KEY_Full_Name,Full_Name);
		values.put(KEY_Mobile_Number,Mobile_Number);
		values.put(KEY_DOB,DOB);
		values.put(KEY_Qualification,Qualification);
		values.put(KEY_Address,Address);
		values.put(KEY_Blood_Group,Blood_Group);
		values.put(KEY_Email,Email);
		values.put(KEY_Password, Password);


		String Pathologist_ID = Long.toString(db3.insert(TABLE_PATHOLOGIST, null, values));

		db3.close(); // Closing database connection

		Log.d(TAG, "New Pathologist inserted into sqlite: " + Pathologist_ID);
	}


	/**
	 * Getting user data from database
	 */

	public HashMap<String, String>getPathologistDetails() {
		HashMap<String, String>pathologist_registration = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_PATHOLOGIST;

		SQLiteDatabase db2 = this.getReadableDatabase();
		Cursor cursor = db2.rawQuery(selectQuery, null);
// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() >0) {
			pathologist_registration.put("Pathologist_ID", cursor.getString(0));
			pathologist_registration.put("Full_Name", cursor.getString(1));
			pathologist_registration.put("Mobile_Number", cursor.getString(2));
			pathologist_registration.put("DOB", cursor.getString(3));
			pathologist_registration.put("Qualification", cursor.getString(4));
			pathologist_registration.put("Address", cursor.getString(5));
			pathologist_registration.put("Blood_Group", cursor.getString(6));
			pathologist_registration.put("Email", cursor.getString(7));
			pathologist_registration.put("Password", cursor.getString(8));
		}
		cursor.close();
		db2.close();
// return user
		Log.d(TAG, "Fetching user from Sqlite: " + pathologist_registration.toString());

		return pathologist_registration;
	}

	/**
	 * Re crate database Delete all tables and create them again
	 */

	public void deletePathologist() {
		SQLiteDatabase db2 = this.getWritableDatabase();
		db2.delete(TABLE_PATHOLOGIST, null, null);
		db2.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}


}
