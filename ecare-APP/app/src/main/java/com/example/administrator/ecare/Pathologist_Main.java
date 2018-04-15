package com.example.administrator.ecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ecare.helper.Pathologist_SQLiteHandler;
import com.example.administrator.ecare.helper.SessionManager;

import java.util.HashMap;

public class Pathologist_Main extends AppCompatActivity {
    ImageView profile,search_patient,patient_history;
    TextView logout,tvprofile,tvsearch,tvhistory;
    private Pathologist_SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathologist_main);

        profile=(ImageView)findViewById(R.id.IVProfile_Pathologist_Main);
        search_patient=(ImageView)findViewById(R.id.IVSearch_Pathologist_Main);
       // patient_history=(ImageView)findViewById(R.id.IVHistory_Pathologist_Main);
        logout=(TextView)findViewById(R.id.tVLogout_Pathologist_Main);
        tvprofile=(TextView)findViewById(R.id.tVProfile_Patient_Main);
        tvsearch=(TextView)findViewById(R.id.tVSearch_Pathologist_Main);
       // tvhistory=(TextView)findViewById(R.id.tVHistory_Pathologist_Main);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        db = new Pathologist_SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> pathologist_registration = db.getUserDetails();

                String Pathologist_ID = pathologist_registration.get("Pathologist_ID");
                String Full_name = pathologist_registration.get("Full_Name");
                String Mobile_Number = pathologist_registration.get("Mobile_Number");
                String DOB = pathologist_registration.get("DOB");
                String Qualification = pathologist_registration.get("Qualification");
                String Address = pathologist_registration.get("Address");
                String Blood_Group = pathologist_registration.get("Blood_Group");

                Intent i1= new Intent(Pathologist_Main.this,Pathologist_Profile.class);

                i1.putExtra("Pathologist_ID", Pathologist_ID);
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
                Intent i2= new Intent(Pathologist_Main.this,Pathologist_Search_Patient.class);
                startActivity(i2);
            }
        });

      /*  patient_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(Pathologist_Main.this,Pathologist_Patient_History.class);
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
        Log.d("Patho Logged Out..." , status);
        db.deleteUsers();
        Intent i4=new Intent(Pathologist_Main.this,Main1.class);
        startActivity(i4);
    }
}
