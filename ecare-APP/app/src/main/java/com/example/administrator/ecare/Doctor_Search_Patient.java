package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.ecare.Patient_Tretment_From_Doctor;
import com.example.administrator.ecare.R;

public class Doctor_Search_Patient extends AppCompatActivity {
    EditText e1;
    TextView t1;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search_patient);

        e1=(EditText)findViewById(R.id.eTPatient_ID_Doctor_Search_Patient);
        t1=(TextView)findViewById(R.id.tVPatient_ID_Doctor_Search_Patient);
        search=(Button)findViewById(R.id.btContinue_Doctor_Search_Patient);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Doctor_Search_Patient.this,Patient_Tretment_From_Doctor.class);
                startActivity(i);
            }
        });
    }
}
