package com.example.administrator.ecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ecare.helper.SQLiteHandler;
import com.example.administrator.ecare.helper.SessionManager;

import java.util.HashMap;

public class Patient_Main extends AppCompatActivity {
  //  private TextView id,Name, mobile_number,dob,address,blood_group;

    ImageView history,profile,search;
    TextView logout;
    private SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;
    // Progress dialog



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);

        profile = (ImageView)findViewById(R.id.IVProfile_Patient_Main);
        search=(ImageView)findViewById(R.id.IVSearch_Patient_Main);
        history=(ImageView)findViewById(R.id.IVHistory_Patient_Main);
        logout=(TextView)findViewById(R.id.tVLogOut_Patient_Main);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

    /*   if (!session.isLoggedIn()) {
            logoutUser();
        } */


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Fetching user details from SQLite
                HashMap<String, String> patient_registration = db.getUserDetails();
                Intent i1 = new Intent(Patient_Main.this,Patient_Profile.class);

                String Patient_ID = patient_registration.get("Patient_ID");
                String Full_name = patient_registration.get("Full_Name");
                String Mobile_Number = patient_registration.get("Mobile_Number");
                String DOB = patient_registration.get("DOB");
                String Address = patient_registration.get("Address");
                String Blood_Group = patient_registration.get("Blood_Group");

                i1.putExtra("Patient_ID",Patient_ID);
                i1.putExtra("Full_Name",Full_name);
                i1.putExtra("Mobile_Number",Mobile_Number);
                i1.putExtra("DOB",DOB);
                i1.putExtra("Address",Address);
                i1.putExtra("Blood_Group",Blood_Group);

                startActivity(i1);

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog.setMessage("Loading data ...");
             //  showDialog();
                Intent i2 = new Intent(Patient_Main.this,Patient_Search_Doctor.class);
                startActivity(i2);
               // hideDialog();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(Patient_Main.this,Patient_Treatment_History.class);
                startActivity(i3);
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
        session.setLogin(false);

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
