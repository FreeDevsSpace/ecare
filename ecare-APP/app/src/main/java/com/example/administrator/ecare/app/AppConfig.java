package com.example.administrator.ecare.app;

public class AppConfig {

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Server user login url
	public static String URL_LOGIN = "http://192.168.43.164/ecare/ecare-API/login.php";

	// Server user register url
	public static String URL_REGISTER ="http://192.168.43.164/ecare/ecare-API/register.php";
	public static String URL_PATHOLOGISTREGISTER ="http://192.168.43.164/ecare/ecare-API/pathologist_registration.php";
	public static String URL_PHARMACISTREGISTER ="http://192.168.43.164/ecare/ecare-API/pharmacist_registration.php";

	public static String URL_GETCITY = "http://192.168.43.164/ecare/ecare-API/getcity.php";
	public static String URL_GETQUALIFICATION = "http://192.168.43.164/ecare/ecare-API/getqualification.php";
	public static String URL_GETSPECIALIZATION = "http://192.168.43.164/ecare/ecare-API/getspecialization.php";
	public static String URL_GETSEARCH_DETAILS = "http://192.168.43.164/ecare/ecare-API/getsearch_details.php";
	public static  String URL_CONSULTATION="http://192.168.43.164/ecare/ecare-API/consultation.php";

	public static  String URL_DOCTOR_CONSULTATION="http://192.168.43.164/ecare/ecare-API/doctor_consultation.php";
	public static  String URL_DOCTOR_SUGGETION="http://192.168.43.164/ecare/ecare-API/doctor_suggetion.php";

	public static  String URL_DOCREGISTER="http://192.168.43.164/ecare/ecare-API/dregistration.php";
	public static  String URL_GETAPPOINTMENT_DETAILS="http://192.168.43.164/ecare/ecare-API/getappointment_details.php";

    public static String URL_BOOK_APPOINTMENT="http://192.168.43.164/ecare/ecare-API/book_appointment.php";
	public static String URL_GETPATIENT_TREATMENT_DETAILS="http://192.168.43.164/ecare/ecare-API/getpatient_treatment_details.php";
		public static  String URL_GETPATIENT_DETAILS="http://192.168.43.164/ecare/ecare-API/getpatient_details.php";
	public static  String URL_PETIENT_CONSULTATION_STATUS="http://192.168.43.164/ecare/ecare-API/patient_consultation_status.php";



	////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Server user login url
/*	public static String URL_LOGIN = "https://140120107185.000webhostapp.com/login.php";

	// Server user register url
	public static String URL_REGISTER ="https://140120107185.000webhostapp.com/register.php";

	public static String URL_GETCITY = "https://140120107185.000webhostapp.com/getcity.php";
	public static String URL_GETQUALIFICATION = "https://140120107185.000webhostapp.com/getqualification.php";
	public static String URL_GETSPECIALIZATION = "https://140120107185.000webhostapp.com/getspecialization.php";
	public static String URL_GETSEARCH_DETAILS = "https://140120107185.000webhostapp.com/getsearch_details.php";

	public static  String URL_DOCREGISTER="https://140120107185.000webhostapp.com/dregistration.php"; */

	///////////////////////////////////////////////////////////////////////////////////////////////////////


	//JSON array name
	public static final String JSON_ARRAY = "result";

	//Tags used in the JSON String
	public static final String TAG_NAME = "City";
}
