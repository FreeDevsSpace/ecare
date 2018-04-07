package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Pathologist_Profile extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
    ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathologist_profile);

        tv1=(TextView)findViewById(R.id.tVLogOut_Pathologist_Profile);
        tv2=(TextView)findViewById(R.id.tVName_Pathologist_Profile);
        tv3=(TextView)findViewById(R.id.tVMobile_Number_Pathologist_Profile);
        tv5=(TextView)findViewById(R.id.tVDOB_Pathologist_Profile);
        tv6=(TextView)findViewById(R.id.tVQualification_Pathologist_Profile);
        tv7=(TextView)findViewById(R.id.tVAddress_Pathologist_Profile);
        tv8=(TextView)findViewById(R.id.tVBlood_Group_Pathologist_Profile);
        tv9=(TextView)findViewById(R.id.tVPathologist_ID_Pathologist_Profile);

        iv1=(ImageView)findViewById(R.id.IVProfile_Icon_Pathologist_Profile);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Pathologist_Profile.this,Main1.class);
                startActivity(i1);
            }
        });
    }
}
