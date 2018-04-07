package com.example.administrator.ecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register_As extends AppCompatActivity {
    TextView tv1;
    Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_as);

        tv1=(TextView)findViewById(R.id.tVRegister_As);
        b1=(Button)findViewById(R.id.btPatient);
        b2=(Button)findViewById(R.id.btDoctor);
        b3=(Button)findViewById(R.id.btPathologist);
        b4=(Button)findViewById(R.id.btPharmacist);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register_As.this,Registration_Page.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Register_As.this,Doctor_Registration_Page.class);
                startActivity(i1);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(Register_As.this,Pathologist_Registration_Page.class);
                startActivity(i2);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(Register_As.this,Pharmacist_Registration_Page.class);
                startActivity(i3);
            }
        });

    }
}
