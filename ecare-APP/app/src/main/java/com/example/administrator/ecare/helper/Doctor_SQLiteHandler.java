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



public class Doctor_SQLiteHandler extends SQLiteOpenHelper {

	private static final String TAG = Doctor_SQLiteHandler.class.getSimpleName();

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "ecare";

	// Login table name
	//private static final String TABLE_USER = "patient_registration";

	private static final String TABLE_DOCTOR = "doctor_registration";
	private static final String TABLE_PATHOLOGIST = "pathologist_registration";
	private static final String TABLE_PHARMACIST = "pharmacist_registration";



	// Login Table Columns names
	//private static final String KEY_ID="id";
	private  static final String KEY_Pathologist_ID="Pathologist_ID";
	private  static final String KEY_Pharmacist_ID="Pharmacist_ID";
	private static final String KEY_Patient_ID = "Patient_ID";
	private static final String KEY_Full_Name = "Full_Name";
	private static final String KEY_Email = "Email";
	private static final String KEY_Address = "Address";
	private static final String KEY_City = "City";
	private static final String KEY_DOB = "DOB";
	private static final String KEY_Blood_Group = "Blood_Group";
	private static final String KEY_Mobile_Number = "Mobile_Number";
	private static final String KEY_Password = "Password";
	private static final String KEY_Doctor_ID = "Doctor_ID";
	private static final String KEY_Qualification = "Qualification";
	private static final String KEY_Specialization = "Specialization";
	private static final String KEY_Hospital_Name = "Hospital_Name";
	private static final String KEY_Hospital_Address = "Hospital_Address";
	private static final String KEY_Fees = "Fees";

	public Doctor_SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


    @Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_DLOGIN_TABLE = "CREATE TABLE " + TABLE_DOCTOR + "("
				+ KEY_Doctor_ID+ " INTEGER PRIMARY KEY,"
				+ KEY_Full_Name + " TEXT,"
				+ KEY_Mobile_Number + " TEXT,"
				+ KEY_DOB + " TEXT,"
				+ KEY_Address + " TEXT,"
				+ KEY_City + " TEXT,"
				+ KEY_Email+ " TEXT,"
				+ KEY_Password + " TEXT,"
				+ KEY_Qualification + " TEXT,"
				+ KEY_Specialization + " TEXT,"
				+ KEY_Hospital_Name + " TEXT,"
				+ KEY_Hospital_Address+ " TEXT,"
				+ KEY_Fees + " TEXT"+")";


		db.execSQL(CREATE_DLOGIN_TABLE);

		Log.d(TAG, "Doctor Database tables created");
	}


	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);


		// Create tables again

		onCreate(db);


	}


	public void addUser( String Full_Name, String Mobile_Number, String DOB, String Address,String City,
						String Email,String Password, String Qualification,String Specialization,String Hospital_Name,String Hospital_Address,String Fees) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_Full_Name,Full_Name);
		values.put(KEY_Mobile_Number,Mobile_Number);
		values.put(KEY_DOB,DOB);
		values.put(KEY_Address, Address);
		values.put(KEY_City,City);
		values.put(KEY_Email,Email);
		values.put(KEY_Password, Password);
		values.put(KEY_Qualification, Qualification);
		values.put(KEY_Specialization, Specialization);
		values.put(KEY_Hospital_Name, Hospital_Name);
		values.put(KEY_Hospital_Address, Hospital_Address);
		values.put(KEY_Fees,Fees);


		// Inserting Row

		String Doctor_ID = Long.toString(db.insert(TABLE_DOCTOR, null, values));
		db.close(); // Closing database connection

		Log.d(TAG, "New Doctor inserted into sqlite: " + Doctor_ID);
	}




	/**
	 * Getting user data from database
	 */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> doctor_registration = new HashMap<String, String>();
		String selectQuery = "SELECT  *  FROM " + TABLE_DOCTOR;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			doctor_registration.put("Doctor_ID", cursor.getString(0));
			doctor_registration.put("Full_Name", cursor.getString(1));
			doctor_registration.put("Mobile_Number", cursor.getString(2));
			doctor_registration.put("DOB", cursor.getString(3));
			doctor_registration.put("Address", cursor.getString(4));
			doctor_registration.put("City", cursor.getString(5));
			doctor_registration.put("Email", cursor.getString(6));
			doctor_registration.put("Password", cursor.getString(7));
			doctor_registration.put("Qualification", cursor.getString(8));
			doctor_registration.put("Specialization", cursor.getString(9));
			doctor_registration.put("Hospital_Name", cursor.getString(10));
			doctor_registration.put("Hospital_Address", cursor.getString(11));
			doctor_registration.put("Fess", cursor.getString(12));

		}
		cursor.close();
		db.close();
		// return user
		Log.d(TAG, "Fetching user from Sqlite: " + doctor_registration.toString());

		return doctor_registration;   // This is the method which you must have to call o button press event

	}


	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_DOCTOR, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}



}
