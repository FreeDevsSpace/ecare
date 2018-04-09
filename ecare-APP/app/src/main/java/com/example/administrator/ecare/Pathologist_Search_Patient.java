package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Pathologist_Search_Patient extends AppCompatActivity {
    TextView tv1,tv2;
    EditText et1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathologist_search_patient);

      //  tv1=(TextView)findViewById(R.id.tVEnter_Patient_ID_Pathologist_Search_Patient);
        tv2=(TextView)findViewById(R.id.tVLogOut_Pathologist_Search_Patient);
        et1=(EditText)findViewById(R.id.eTEnter_Patient_ID_Pathologist_Search_Patient);
        b1=(Button)findViewById(R.id.btContinue_Pathologist_Search_Patient);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Pathologist_Search_Patient.this,Pharmacist_Patient_Bill_Report_Upload.class);
                startActivity(i1);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2= new Intent(Pathologist_Search_Patient.this,Main1.class);
                startActivity(i2);
            }
        });
    }
}
