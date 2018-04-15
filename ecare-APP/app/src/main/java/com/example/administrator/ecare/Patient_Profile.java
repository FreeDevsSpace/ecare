package com.example.administrator.ecare;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ecare.helper.SessionManager;

import java.io.Serializable;

public class Patient_Profile extends AppCompatActivity {

    TextView tvname, tvmobile_number, tvDOB, tvaddress, tvblood_group, tvpatient_id;
    TextView wname,wmobile_number,wdob,waddress,wblood_group,wpatient_id;
    ImageView iv1;


    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        Bundle b=getIntent().getExtras();

        String id = b.getString("Patient_ID");
        String name=b.getString("Full_Name");
        String mobile_number = b.getString("Mobile_Number");
        String dob = b.getString("DOB");
        String address = b.getString("Address");
        String blood_group = b.getString("Blood_Group");


        tvname = (TextView) findViewById(R.id.tVName_Patient_Profile);
        tvmobile_number = (TextView) findViewById(R.id.tVMobile_Number_Patient_Profile);
        tvDOB = (TextView) findViewById(R.id.tVDOB_Patient_Profile);
        tvaddress = (TextView) findViewById(R.id.tVAddress_Patient_Profile);
        tvblood_group = (TextView) findViewById(R.id.tVBlood_Group_Patient_Profile);
        tvpatient_id = (TextView) findViewById(R.id.tVPatient_ID_Patient_Profile);
     //   iv1 = (ImageView) findViewById(R.id.IVProfile_icon_Patient_Profile);
        wpatient_id=(TextView)findViewById(R.id.tVWPatient_ID_Patient_Profile);
        wname=(TextView)findViewById(R.id.tVWPatient_Name_Patient_Profile);
        wmobile_number=(TextView)findViewById(R.id.tVWMobile_Number_Patient_Profile);
        wdob=(TextView)findViewById(R.id.tVWDOB_Patient_Profile);
        waddress=(TextView)findViewById(R.id.tVWAddress_Patient_Profile);
        wblood_group=(TextView)findViewById(R.id.tVWBlood_Group_Patient_Profile);

        wpatient_id.setText(id);
        wname.setText(name);
        wmobile_number.setText(mobile_number);
        wdob.setText(dob);
        waddress.setText(address);
        wblood_group.setText(blood_group);


        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


    }

}