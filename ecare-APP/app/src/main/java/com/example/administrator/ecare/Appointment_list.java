package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Appointment_list extends AppCompatActivity {

    Button view_appointment_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);


        view_appointment_list=(Button)findViewById(R.id.btView_Appointment_List_);

        view_appointment_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Appointment_list.this,View_Appointment_List.class);
                startActivity(i1);
            }
        });
    }


}