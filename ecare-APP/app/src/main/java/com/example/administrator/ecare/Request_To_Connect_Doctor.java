package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ecare.helper.SQLiteHandler;

import java.util.HashMap;

public class Request_To_Connect_Doctor extends AppCompatActivity {
    ImageView doctor_profile;
    Button book_appointment, online_consultation;
    TextView tvname,tvqualification,tvspecialization,tvhospital_name,
            tvhospital_address,tvfees,tvphospital_name,tvphospital_address,tvpfees,qualification1,specialization1;
    private SQLiteHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_to_connect_doctor);

        doctor_profile=(ImageView)findViewById(R.id.IVProfile_Pic_Request_To_Connect_Doctor);
        book_appointment=(Button)findViewById(R.id.btBook_Appointment_Request_To_Connect_Doctor);
        online_consultation=(Button)findViewById(R.id.btOnline_Consultation_Request_To_Connect_Doctor);
        tvname=(TextView)findViewById(R.id.tVName_Request_To_Connect_Doctor);
        tvqualification=(TextView)findViewById(R.id.tVQualification_Request_To_Connect_Doctor);
        tvspecialization=(TextView)findViewById(R.id.tVSpecialization_Request_To_Connect_Doctor);
        tvhospital_name=(TextView)findViewById(R.id.tVHospital_Address_Request_To_Connect_Doctor);
        tvhospital_address=(TextView)findViewById(R.id.tVHospital_Address_Request_To_Connect_Doctor);
        tvfees=(TextView)findViewById(R.id.tVFees_Request_To_Connect_Doctor);
        tvphospital_name=(TextView)findViewById(R.id.tVPHospital_Name_Request_To_Connect_Doctor);
        tvphospital_address=(TextView)findViewById(R.id.tVPHospital_Address_Request_To_Connect_Doctor);
        tvpfees=(TextView)findViewById(R.id.tVPFees_Request_To_Connect_Doctor);
        qualification1=(TextView)findViewById(R.id.tVQualification1_Request_To_Connect_Doctor);
        specialization1=(TextView)findViewById(R.id.tVSpecialization1_Request_To_Connect_Doctor);



        Bundle b= getIntent().getExtras();

        final String did=b.get("did").toString();
        String name= b.get("name").toString();
        String qualification= b.get("qualification").toString();
        String fees= b.get("fees").toString();
        String specialization= b.get("specialization").toString();
        String hospital_address= b.get("hospital_address").toString();
        String hospital_name= b.get("hospital_name").toString();

        tvname.setText(name);
        tvqualification.setText(qualification);
        tvspecialization.setText(specialization);
        tvphospital_name.setText(hospital_name);
        tvphospital_address.setText(hospital_address);
        tvpfees.setText(fees);

        db = new SQLiteHandler(getApplicationContext());
        book_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Request_To_Connect_Doctor.this,Patient_Book_Appointment.class);
                startActivity(i1);
            }
        });

        online_consultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> patient_registration = db.getUserDetails();
//                HashMap<String,String> doctor_registration = db.getDoctorDetails();
               Intent i2=new Intent(Request_To_Connect_Doctor.this,Patient_Online_Consultation.class);
                //String Doctor_ID = doctor_registration.get("Doctor_ID");
                String Patient_ID = patient_registration.get("Patient_ID");
                //i2.putExtra("Doctor_ID",Doctor_ID);
                i2.putExtra("Patient_ID",Patient_ID);
                i2.putExtra("Doctor_ID",did);


                startActivity(i2);
            }
        });
    }
}
