package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Pathologist_Profile extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
    TextView PID,PName,PMobileNumber,PDateOfBirth,PQualification,PAddress,PBloodGroup;
    ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pathologist_profile);

        Bundle b=getIntent().getExtras();
        String id = b.getString("Pathologist_ID");
        String name=b.getString("Full_Name");
        String mobile_number = b.getString("Mobile_Number");
        String dob = b.getString("DOB");
        String qualification = b.getString("Qualification");
        String address = b.getString("Address");
        String blood_group = b.getString("Blood_Group");


      //  tv1=(TextView)findViewById(R.id.tVLogOut_Pathologist_Profile);
        tv2=(TextView)findViewById(R.id.tVName_Pathologist_Profile);
        tv3=(TextView)findViewById(R.id.tVMobile_Number_Pathologist_Profile);
        tv5=(TextView)findViewById(R.id.tVDOB_Pathologist_Profile);
        tv6=(TextView)findViewById(R.id.tVQualification_Pathologist_Profile);
        tv7=(TextView)findViewById(R.id.tVAddress_Pathologist_Profile);
        tv8=(TextView)findViewById(R.id.tVBlood_Group_Pathologist_Profile);
        tv9=(TextView)findViewById(R.id.tVPathologist_ID_Pathologist_Profile);
        PID=(TextView)findViewById(R.id.tVP_ID_Pathologist_Profile);
        PName=(TextView)findViewById(R.id.tVP_Name_Pathologist_Profile);
        PMobileNumber=(TextView)findViewById(R.id.tVP_MobileNumber_Pathologist_Profile);
        PDateOfBirth=(TextView)findViewById(R.id.tVP_DateOfBirth_Pathologist_Profile);
        PQualification=(TextView)findViewById(R.id.tVP_Qualification_Pathologist_Profile);
        PAddress=(TextView)findViewById(R.id.tVP_Address_Pathologist_Profile);
        PBloodGroup=(TextView)findViewById(R.id.tVP_BloodGroup_Pathologist_Profile);


        PID.setText(id);
        PName.setText(name);
        PMobileNumber.setText(mobile_number);
        PDateOfBirth.setText(dob);
        PQualification.setText(qualification);
        PAddress.setText(address);
        PBloodGroup.setText(blood_group);


     //   iv1=(ImageView)findViewById(R.id.IVProfile_Icon_Pathologist_Profile);

       /* tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Pathologist_Profile.this,Main1.class);
                startActivity(i1);
            }
        }); */
    }
}
