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

public class Pathologist_Main extends AppCompatActivity {
    ImageView profile,search_patient,patient_history;
    TextView logout;
    private SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathologist_main);

        profile=(ImageView)findViewById(R.id.IVProfile_Pathologist_Main);
        search_patient=(ImageView)findViewById(R.id.IVSearch_Pathologist_Main);
        patient_history=(ImageView)findViewById(R.id.IVHistory_Pathologist_Main);
        logout=(TextView)findViewById(R.id.tVLogout_Pathologist_Main);
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
                Intent i1= new Intent(Pathologist_Main.this,Pathologist_Profile.class);
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

        patient_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(Pathologist_Main.this,Pathologist_Patient_History.class);
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

//        db.deletePathologist();
        Intent i4=new Intent(Pathologist_Main.this,Main1.class);
        startActivity(i4);
    }
}
