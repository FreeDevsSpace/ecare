package com.example.administrator.ecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.ecare.app.AppConfig;
import com.example.administrator.ecare.app.AppController;
import com.example.administrator.ecare.helper.Doctor_SQLiteHandler;
import com.example.administrator.ecare.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Doctor_Main extends AppCompatActivity {
    private static final String TAG = Doctor_Main.class.getSimpleName();
    ImageView profile,shedule,search,consultation,appointment,history;
    TextView logout,tvprofile,tvsearch,tvappointment,tvhistory,tvshedule,tvconsultation;
    private Doctor_SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;
    private String Doctor_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
        profile = (ImageView) findViewById(R.id.IVProfile_Doctor_Main);
      //  shedule = (ImageView) findViewById(R.id.IVShedule_Doctor_Main);
        search = (ImageView) findViewById(R.id.IVSearch_Doctor_Main);
        consultation = (ImageView) findViewById(R.id.IVConsultation_Doctor_Main);
        appointment = (ImageView) findViewById(R.id.IVAppointment_Doctor_Main);
       // history = (ImageView) findViewById(R.id.IVHistory_Doctor_Main);

        logout = (TextView) findViewById(R.id.tVLogOut_Doctor_Main);
        tvprofile=(TextView)findViewById(R.id.tVProfile_Doctor_Main);
        tvsearch=(TextView)findViewById(R.id.tVSearch_Doctor_Main);
        tvappointment=(TextView)findViewById(R.id.tVAppointment_Doctor_Main);
       // tvhistory=(TextView)findViewById(R.id.tVHistory_Doctor_Main);
       // tvshedule=(TextView)findViewById(R.id.tVSchedule_Doctor_Main);
        tvconsultation=(TextView)findViewById(R.id.tVConsultion_Doctor_Main);


        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        db = new Doctor_SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());


        if (!session.isLoggedIn()) {
            logoutUser();
        }

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, String> doctor_registration = db.getUserDetails();

                    String Doctor_ID = doctor_registration.get("Doctor_ID");
                    String Full_name = doctor_registration.get("Full_Name");
                    String Mobile_Number = doctor_registration.get("Mobile_Number");
                    String DOB = doctor_registration.get("DOB");
                    String Qualification = doctor_registration.get("Qualification");
                    String Address = doctor_registration.get("Address");
                    String Blood_Group = doctor_registration.get("Blood_Group");

                    Intent i1 = new Intent(Doctor_Main.this, Doctor_Profile.class);

                    i1.putExtra("Doctor_ID", Doctor_ID);
                    i1.putExtra("Full_Name", Full_name);
                    i1.putExtra("Mobile_Number", Mobile_Number);
                    i1.putExtra("DOB", DOB);
                    i1.putExtra("Qualification", Qualification);
                    i1.putExtra("Address", Address);
                    i1.putExtra("Blood_Group", Blood_Group);

                    startActivity(i1);
                }
            });

            consultation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    HashMap<String, String> doctor_registration = db.getUserDetails();

                  String  Doctor_ID = doctor_registration.get("Doctor_ID");
                    Log.d("Doctor_ID", Doctor_ID);
                    Log.d("okk1", "before getid");
                    Log.d("Doctor_ID", Doctor_ID);
                    getid(Doctor_ID);

                }
            });


            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i4 = new Intent(Doctor_Main.this, Doctor_Search_Patient.class);
                    startActivity(i4);
                }
            });

            appointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, String> doctor_registration = db.getUserDetails();

                    Doctor_ID = doctor_registration.get("Doctor_ID");
                    Log.d("Doctor_ID", Doctor_ID);
                    Log.d("okk1", "before getid");
                    Log.d("Doctor_ID", Doctor_ID);
                    getappointment(Doctor_ID);

                }
            });


            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    logoutUser();
                }
            });


        }

    private void getappointment(final String Doctor_ID) {
        String tag_string_req = "req_login";
        pDialog.setMessage("Loading  ...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                AppConfig.URL_GETAPPOINTMENT_DETAILS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean error = jsonObject.getBoolean("error");

                    if (!error) {

                        String obj = jsonObject.toString();
                     //   String obj = jsonObject.getString(response);

                        Intent i = new Intent(Doctor_Main.this, Appointment_list.class);

                        i.putExtra("key",obj);
                      //  i.putExtra("Doctor_ID",Doctor_ID);

                        startActivity(i);
                    }else {
                        String errorMsg = jsonObject.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } ,  new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                //  hideDialog();
            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                // map method signature
                Map<String, String> params = new HashMap<>();
                params.put("Doctor_ID", Doctor_ID);
                return params;
            }

        };


        AppController.getInstance().addToRequestQueue(tag_string_req, stringRequest);

    }


    private void getid(final String Doctor_ID) {
        Log.d("okk2","inside getid");
        String tag_string_req = "req_register";

        pDialog.setMessage("Loading ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_DOCTOR_CONSULTATION, new Response.Listener<String>() {

            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {

                    JSONObject jObj = new JSONObject(response);

                       boolean error = jObj.getBoolean("error");
                    if (!error) {
                        String obj = jObj.toString();

                        Intent i = new Intent(Doctor_Main.this, Consultation.class);
                        i.putExtra("data",obj);

                        startActivity(i);
                        finish();

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Doctor_ID",Doctor_ID);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(tag_string_req,strReq );
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    private void logoutUser() {
                session.setLogin(false,"");
                String status = String.valueOf(session.isLoggedOut());
                Log.d("Doctor Logged Out..." , status);
                db.deleteUsers();
                // Launching the login activity
                Intent intent = new Intent(Doctor_Main.this, Main1.class);
                startActivity(intent);
                finish();
            }
        }





