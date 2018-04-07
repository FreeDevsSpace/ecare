package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.ecare.Patient_Treatment_With_Connected_Doctor;

public class Connect_To_Other_Doctor extends AppCompatActivity {
    TextView p_id,doc_id,doctor_name,symptoms,medicine,report,ref_doctor_name,suggetion,ref_report,ref_medicine,attechment,attechmentclick_here;
    EditText write_suggetion,write_report,prescribe_medicine;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_other_doctor);
        p_id = (TextView)findViewById(R.id.tVPatient_ID_Connect_To_Other_Doctor);
        doc_id=(TextView)findViewById(R.id.tVDoctor_ID_Connect_To_Other_Doctor);
        doctor_name=(TextView)findViewById(R.id.tVDoctor_Name_Connect_To_Other_Doctor);
        symptoms=(TextView)findViewById(R.id.tVSymptoms_Connect_To_Other_Doctor);
        medicine=(TextView)findViewById(R.id.tVMedicine_Connect_To_Other_Doctor);
        report=(TextView)findViewById(R.id.tVReport_Connect_To_Other_Doctor);
        ref_doctor_name=(TextView)findViewById(R.id.tVRefDoctor_Name_Connect_To_Other_Doctor);
        suggetion=(TextView)findViewById(R.id.tVSuggetion_Connect_To_Other_Doctor);
        ref_report=(TextView)findViewById(R.id.tVRefReport_Connect_To_Other_Doctor);
        ref_medicine=(TextView)findViewById(R.id.tVRefMedicine_Connect_To_Other_Doctor);
        attechment=(TextView)findViewById(R.id.tVAttechment_Connect_To_Other_Doctor);
        attechmentclick_here=(TextView)findViewById(R.id.tVClick_Here_Attechment_Connect_To_Other_Doctor);

        write_suggetion=(EditText)findViewById(R.id.eTSuggetion_Connect_To_Other_Doctor);
        write_report=(EditText)findViewById(R.id.eTRefReport_Connect_To_Other_Doctor);
        prescribe_medicine=(EditText)findViewById(R.id.eTRefMedicine_Connect_To_Other_Doctor);

        send=(Button)findViewById(R.id.btSend_Connect_To_Other_Doctor);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Connect_To_Other_Doctor.this,Patient_Treatment_With_Connected_Doctor.class);
                startActivity(i1);
            }
        });

    }
}
