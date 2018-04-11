package com.example.administrator.ecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ecare.helper.Patient_SQLiteHandler;
import com.example.administrator.ecare.helper.SessionManager;

import java.util.HashMap;

public class Patient_Main extends AppCompatActivity {

    ImageView  profile, search,appointment_status,consultation_status;
    TextView logout,tvprofile,tvsearch,tvappointmentstatus,tvconsultationstatus;
    private Patient_SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);

        profile = (ImageView) findViewById(R.id.IVProfile_Patient_Main);
        search = (ImageView) findViewById(R.id.IVSearch_Patient_Main);
        appointment_status=(ImageView)findViewById(R.id.IVAppointment_Status_Patient_Main);
        consultation_status=(ImageView)findViewById(R.id.IVConsultation_Status_Patient_Main);
        logout = (TextView) findViewById(R.id.tVLogOut_Patient_Main);
        tvprofile=(TextView)findViewById(R.id.tVProfile_Patient_Main);
        tvsearch=(TextView)findViewById(R.id.tVSearch_Patient_Main);
        tvconsultationstatus=(TextView)findViewById(R.id.tVConsultation_Status_Patient_Main);
        tvappointmentstatus=(TextView)findViewById(R.id.tVAppointment_Status_Patient_Main);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        db = new Patient_SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

       if (!session.isLoggedIn()) {
            logoutUser();
       }


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Fetching user details from SQLite
                HashMap<String, String> patient_registration = db.getUserDetails();

                String Patient_ID = patient_registration.get("Patient_ID");
                String Full_name = patient_registration.get("Full_Name");
                String Mobile_Number = patient_registration.get("Mobile_Number");
                String DOB = patient_registration.get("DOB");
                String Address = patient_registration.get("Address");
                String Blood_Group = patient_registration.get("Blood_Group");

                Intent i1 = new Intent(Patient_Main.this, Patient_Profile.class);

                i1.putExtra("Patient_ID", Patient_ID);
                i1.putExtra("Full_Name", Full_name);
                i1.putExtra("Mobile_Number", Mobile_Number);
                i1.putExtra("DOB", DOB);
                i1.putExtra("Address", Address);
                i1.putExtra("Blood_Group", Blood_Group);

                startActivity(i1);

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pDialog.setMessage("Loading data ...");
                //  showDialog();
                Intent i2 = new Intent(Patient_Main.this, Patient_Search_Doctor.class);
                startActivity(i2);
                // hideDialog();
            }
        });

        appointment_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(Patient_Main.this, Patient_Appointment_Status.class);
                startActivity(i3);
            }
        });

        consultation_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(Patient_Main.this,Patient_Consultation_Status.class);
                startActivity(i4);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               logoutUser();
            }
        });


    }

    private void logoutUser() {
        session.setLogin(false,"");
        String status = String.valueOf(session.isLoggedOut());
        Log.d("Logged Out..." , status);
        db.deleteUsers();
        // Launching the login activity
        Intent intent = new Intent(Patient_Main.this, Main1.class);
        startActivity(intent);
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}