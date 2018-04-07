package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Patient_Treatment_With_Connected_Doctor extends AppCompatActivity {
    TextView p_id,doc_id,doc_name,symptoms,report,medicine,refdoc_id,refdoc_name,refdoc_suggetion,refreport,refmedicine;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_treatment_with_connected_doctor);
        p_id = (TextView)findViewById(R.id.tVPatient_ID_Patient_Treatment_With_Connected_Doctor);
        doc_id=(TextView)findViewById(R.id.tVDoctor_Name_Patient_Treatment_With_Connected_Doctor);
        doc_name=(TextView)findViewById(R.id.tVDoctor_Name_Patient_Treatment_With_Connected_Doctor);
        symptoms=(TextView)findViewById(R.id.tVSymptoms_Patient_Treatment_With_Connected_Doctor);
        report=(TextView)findViewById(R.id.tVReport_Patient_Treatment_With_Connected_Doctor);
        medicine=(TextView)findViewById(R.id.tVMedicine_Patient_Treatment_With_Connected_Doctor);
        refdoc_id=(TextView)findViewById(R.id.tVRefDoctor_ID_Patient_Treatment_With_Connected_Doctor);
        refdoc_name=(TextView)findViewById(R.id.tVRefDoctor_Name_Patient_Treatment_With_Connected_Doctor);
        refdoc_suggetion=(TextView)findViewById(R.id.tVRefDoctor_Suggetion_Patient_Treatment_With_Connected_Doctor);
        refreport=(TextView)findViewById(R.id.tVRefReport_Patient_Treatment_With_Connected_Doctor);
        refmedicine=(TextView)findViewById(R.id.tVRefMedicine_Patient_Treatment_With_Connected_Doctor);

        save=(Button)findViewById(R.id.btSave_Patient_Treatment_With_Connected_Doctor);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Patient_Treatment_With_Connected_Doctor.this,Shedule_For_Today.class);
                startActivity(i1);
            }
        });
    }
}
