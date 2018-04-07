package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ecare.helper.SQLiteHandler;
import com.example.administrator.ecare.helper.SessionManager;

public class Doctor_Main extends AppCompatActivity {
    ImageView profile,shedule,search,consultation,appointment,history;
    TextView logout;
    private SQLiteHandler db;
    private SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
        profile=(ImageView)findViewById(R.id.IVProfile_Doctor_Main);
        shedule=(ImageView)findViewById(R.id.IVShedule_Doctor_Main);
        search=(ImageView)findViewById(R.id.IVSearch_Doctor_Main);
        consultation=(ImageView)findViewById(R.id.IVConsultation_Doctor_Main);
        appointment=(ImageView)findViewById(R.id.IVAppointment_Doctor_Main);
        history=(ImageView)findViewById(R.id.IVHistory_Doctor_Main);
        logout=(TextView)findViewById(R.id.tVLogOut_Doctor_Main);

        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
//            logoutUser();
        }

        // Fetching user details from SQLite
//        HashMap<String, String> doctor_registration = db.getDoctorDetails();



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Doctor_Main.this,Doctor_Profile.class);
                startActivity(i1);
            }
        });

        consultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(Doctor_Main.this,Consultation.class);
                startActivity(i2);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(Doctor_Main.this,Patient_Treatment_History.class);
                startActivity(i3);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(Doctor_Main.this,Doctor_Search_Patient.class);
                startActivity(i4);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      Intent i5 = new Intent(Doctor_Main.this,Appointment_list.class);
                                      startActivity(i5);
                                  }
        });


        shedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i7 = new Intent(Doctor_Main.this,Shedule_For_Today.class);
                startActivity(i7);
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

//       db.deleteDoctor();
        // Launching the login activity
        Intent intent = new Intent(Doctor_Main.this, Main1.class);
        startActivity(intent);
        finish();
    }
}
