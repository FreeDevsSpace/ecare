package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Patient_Tretment_From_Doctor extends AppCompatActivity {
    TextView p_id,patient_name,mobile_number,blood_group,doctor_name,symptoms,medicine,report,suggetions,prescribe_medicine
    ,upload_report,upload_medicine_bill,connect_to_other_doctor,upload_report_clickhere
            ,prescribe_medicine_clickhere,upload_medicine_bill_clickhere;

    EditText symptoms_write_here,prescribe_medicine_here,write_report,write_suggetions;
    Button save;
    RadioGroup rg_report;
    RadioButton yes,no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_tretment_from_doctor);

        p_id=(TextView)findViewById(R.id.tVPatient_ID_Patient_Tretment_From_Doctor);
        patient_name=(TextView)findViewById(R.id.tVPatient_Name_Patient_Tretment_From_Doctor);
        mobile_number=(TextView)findViewById(R.id.tVMobile_Number_Patient_Tretment_From_Doctor);
        blood_group=(TextView)findViewById(R.id.tVBlood_Group_Patient_Tretment_From_Doctor);
        doctor_name=(TextView)findViewById(R.id.tVDoctor_Name_Patient_Tretment_From_Doctor);
        symptoms=(TextView)findViewById(R.id.tVSymptoms_Patient_Tretment_From_Doctor);
        medicine=(TextView)findViewById(R.id.tVMedicine_Patient_Tretment_From_Doctor);
        report=(TextView)findViewById(R.id.tVReport_Patient_Tretment_From_Doctor);
        suggetions=(TextView)findViewById(R.id.tVSuggetions_Patient_Tretment_From_Doctor);
        prescribe_medicine=(TextView)findViewById(R.id.tVPrescribe_Medicine_Image_Patient_Tretment_From_Doctor);
        upload_report=(TextView)findViewById(R.id.tVUpload_Report_Patient_Tretment_From_Doctor);
        upload_medicine_bill=(TextView)findViewById(R.id.tVUpload_Medicine_Bill_Patient_Tretment_From_Doctor);
        connect_to_other_doctor=(TextView)findViewById(R.id.tVConnect_To_Other_Doctor_Patient_Tretment_From_Doctor);
        upload_report_clickhere=(TextView)findViewById(R.id.tVClick_Here_Upload_Report_Patient_Tretment_From_Doctor);
        prescribe_medicine_clickhere=(TextView)findViewById(R.id.tVClick_Here_Prescribe_Medicine_Patient_Tretment_From_Doctor);
        upload_medicine_bill_clickhere=(TextView)findViewById(R.id.tVClick_Here_Upload_Medicine_Bill_Patient_Tretment_From_Doctor);

        symptoms_write_here = (EditText)findViewById(R.id.eTSymptoms_Patient_Tretment_From_Doctor);
        prescribe_medicine_here=(EditText)findViewById(R.id.eTMedicine_Prescribe_Patient_Tretment_From_Doctor);
        write_report=(EditText)findViewById(R.id.eTWrite_Report_Patient_Tretment_From_Doctor);
        write_suggetions=(EditText)findViewById(R.id.eTWrite_Suggetions_Patient_Tretment_From_Doctor);

        save=(Button)findViewById(R.id.btSave_Patient_Tretment_From_Doctor);
        rg_report=(RadioGroup)findViewById(R.id.RadioGroup_Patient_Tretment_From_Doctor);
        yes=(RadioButton)findViewById(R.id.RadioButton_No_Patient_Tretment_From_Doctor);
        no=(RadioButton)findViewById(R.id.RadioButton_No_Patient_Tretment_From_Doctor);


        connect_to_other_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Patient_Tretment_From_Doctor.this,Connect_To_Other_Doctor.class);
                startActivity(i1);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(Patient_Tretment_From_Doctor.this,Patient_Feedback_Rating.class);
                startActivity(i2);
            }
        });
    }
}
