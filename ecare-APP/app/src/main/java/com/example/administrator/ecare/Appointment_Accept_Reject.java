package com.example.administrator.ecare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Appointment_Accept_Reject extends AppCompatActivity {
    TextView pid,ppid,date,pdate,timeslote,ptimeslote;
    Button accept,reject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_accept_reject);
        pid=(TextView)findViewById(R.id.tVPatient_ID_Appointment_Accept_Reject);
        ppid=(TextView)findViewById(R.id.tVPPatient_ID_Appointment_Accept_Reject);
        date=(TextView)findViewById(R.id.tVPDate_Appointment_Accept_Reject);
        pdate=(TextView)findViewById(R.id.tVPDate_Appointment_Accept_Reject);
        timeslote=(TextView)findViewById(R.id.tVTimeslote_Appointment_Accept_Reject);
        ptimeslote=(TextView)findViewById(R.id.tVPTimeslote_Appointment_Accept_Reject);

        accept=(Button)findViewById(R.id.btAccept_Appointment_Accept_Reject);
        reject=(Button)findViewById(R.id.btReject_Appointment_Accept_Reject);

    }
}
