package com.example.administrator.ecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ecare.helper.Pharmacist_SQLiteHandler;
import com.example.administrator.ecare.helper.SessionManager;

import java.util.HashMap;

public class Pharmacist_Main extends AppCompatActivity {
    ImageView profile,search_patient,patient_history;
    TextView logout,tvprofile,tvhistory,tvsearch;
    private Pharmacist_SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;
    // Progress dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_main);

        profile=(ImageView)findViewById(R.id.IVProfile_Pharmacist_Main);
        search_patient=(ImageView)findViewById(R.id.IVSearch_Pharmacist_Main);
      //  patient_history=(ImageView)findViewById(R.id.IVHistory_Pharmacist_Main);
        logout=(TextView)findViewById(R.id.tVLogout_Pharmacist_Main);
       // tvhistory=(TextView)findViewById(R.id.tVHistory_Pharmacist_Main);
        tvprofile=(TextView)findViewById(R.id.tVProfile_Pharmacist_Main);
        tvsearch=(TextView)findViewById(R.id.tVSearch_Pharmacist_Main);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        db = new Pharmacist_SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> pharmacist_registration = db.getUserDetails();
                String Pharmacist_ID = pharmacist_registration.get("Pharmacist_ID");
                String Full_name = pharmacist_registration.get("Full_Name");
                String Mobile_Number = pharmacist_registration.get("Mobile_Number");
                String DOB = pharmacist_registration.get("DOB");
                String Qualification = pharmacist_registration.get("Qualification");
                String Address = pharmacist_registration.get("Address");
                String Blood_Group = pharmacist_registration.get("Blood_Group");

                Intent i1= new Intent(Pharmacist_Main.this,Pharmacist_Profile.class);

                i1.putExtra("Pharmacist_ID", Pharmacist_ID);
                i1.putExtra("Full_Name", Full_name);
                i1.putExtra("Mobile_Number", Mobile_Number);
                i1.putExtra("DOB", DOB);
                i1.putExtra("Qualification", Qualification);
                i1.putExtra("Address", Address);
                i1.putExtra("Blood_Group", Blood_Group);
                startActivity(i1);
            }
        });

        search_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2= new Intent(Pharmacist_Main.this,Pharmacist_Search_Patient.class);
                startActivity(i2);
            }
        });

    /*    patient_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(Pharmacist_Main.this,Pharmacist_Patient_History.class);
                startActivity(i3);
            }
        }); */
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
        Log.d("Pharma Logged Out..." , status);
        db.deleteUsers();
        Intent i4= new Intent(Pharmacist_Main.this,Main1.class);
        startActivity(i4);

    }
}
