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


public class Pharmacist_SQLiteHandler extends SQLiteOpenHelper {

	private static final String TAG = Pharmacist_SQLiteHandler.class.getSimpleName();

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "ecare_pharmacist";

	// Login table name

	private static final String TABLE_PHARMACIST = "pharmacist_registration";



	// Login Table Columns names

	private  static final String KEY_Pharmacist_ID="Pharmacist_ID";
	private static final String KEY_Patient_ID = "Patient_ID";
	private static final String KEY_Full_Name = "Full_Name";
	private static final String KEY_Email = "Email";
	private static final String KEY_Address = "Address";
	//private static final String KEY_City = "City";
	private static final String KEY_DOB = "DOB";
	private static final String KEY_Blood_Group = "Blood_Group";
	private static final String KEY_Mobile_Number = "Mobile_Number";
	private static final String KEY_Password = "Password";
	//private static final String KEY_Doctor_ID = "Doctor_ID";
	private static final String KEY_Qualification = "Qualification";


	public Pharmacist_SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_PHARMACIST + "(" //+KEY_ID + "INTEGER PRIMARY KEY,"
				+ KEY_Pharmacist_ID + " INTEGER PRIMARY KEY,"
				+ KEY_Full_Name + " TEXT ,"
				+ KEY_Mobile_Number + " TEXT,"
				+ KEY_DOB + " TEXT,"
				+ KEY_Qualification + "TEXT,"
				+ KEY_Address + " TEXT,"
				+ KEY_Blood_Group + " TEXT,"
				+ KEY_Email + " TEXT,"
				+ KEY_Password + " TEXT " + ")";

		db.execSQL(CREATE_LOGIN_TABLE);
		Log.d(TAG,"Database tables created");

	}




	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHARMACIST);


		onCreate(db);

	}



	public void addUser(String Pharmacist_ID, String Full_Name, String Mobile_Number, String DOB, String Email,String Qualification,String Address, String Blood_Group, String Password) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_Pharmacist_ID,Pharmacist_ID);
		values.put(KEY_Full_Name,Full_Name);
		values.put(KEY_Mobile_Number,Mobile_Number);
		values.put(KEY_DOB,DOB);
		values.put(KEY_Email, Email);
		values.put(KEY_Address,Address);
		values.put(KEY_Qualification,Qualification);
		values.put(KEY_Blood_Group,Blood_Group);
		values.put(KEY_Password, Password);


		long id =db.insert(TABLE_PHARMACIST, null, values);
		db.close(); // Closing database connection
		Log.d(TAG, "New Pharmacist inserted into sqlite: " + id);

	}




	public HashMap<String, String>getUserDetails() {
		HashMap<String, String>pharmacist_registration = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_PHARMACIST;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() >0) {
			pharmacist_registration.put("Pharmacist_ID", cursor.getString(0));
			pharmacist_registration.put("Full_Name", cursor.getString(1));
			pharmacist_registration.put("Mobile_Number", cursor.getString(2));
			pharmacist_registration.put("DOB", cursor.getString(3));
			pharmacist_registration.put("Qualification", cursor.getString(4));
			pharmacist_registration.put("Address", cursor.getString(5));
			pharmacist_registration.put("Blood_Group", cursor.getString(6));
			pharmacist_registration.put("Email", cursor.getString(7));
			pharmacist_registration.put("Password", cursor.getString(8));

		}
		cursor.close();
		db.close();
	// return user
		Log.d(TAG, "Fetching user from Sqlite: " + pharmacist_registration.toString());

		return pharmacist_registration;
	}


	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_PHARMACIST, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");


	}

}
