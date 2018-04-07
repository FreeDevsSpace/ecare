package com.example.administrator.ecare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Consult_Patient extends AppCompatActivity {
    TextView patient_id,problem,suggetion,medicine,ppatient_id,pproblem;
    EditText write_suggetion, prescribe_medicine;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_patient);

        patient_id=(TextView)findViewById(R.id.tVPatient_ID_Consult_Patient);
        problem=(TextView)findViewById(R.id.tVProblem_Consult_Patient);
        suggetion=(TextView)findViewById(R.id.tVSuggetion_Consult_Patient);
        medicine=(TextView)findViewById(R.id.tVMedicine_Consult_Patient);
        ppatient_id=(TextView)findViewById(R.id.tVPPatient_ID_Consult_Patient);
        pproblem=(TextView)findViewById(R.id.tVPProble_Consult_Patient);

        write_suggetion=(EditText)findViewById(R.id.eTSuggetion_Consult_Patient);
        prescribe_medicine=(EditText)findViewById(R.id.eTMedicine_Consult_Patient);

        send=(Button)findViewById(R.id.btSend_Consult_Patient);
    }
}
