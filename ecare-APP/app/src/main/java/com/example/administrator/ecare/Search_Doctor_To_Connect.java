package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Search_Doctor_To_Connect extends AppCompatActivity {

    TextView city,specialization,qualification;
    Spinner search_city,search_specialization,search_qualification;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor_to_connect);

        city=(TextView)findViewById(R.id.tVCity_Search_Doctor_To_Connect);
        specialization=(TextView)findViewById(R.id.tVSpecialization_Search_Doctor_To_Connect);
        qualification=(TextView)findViewById(R.id.tVQualification_Search_Doctor_To_Connect);

        search_city=(Spinner)findViewById(R.id.SpinnerCity_Search_Doctor_To_Connect);
        search_specialization=(Spinner)findViewById(R.id.SpinnerSpecialization_Search_Doctor_To_Connect);
        search_qualification=(Spinner)findViewById(R.id.SpinnerQualification_Search_Doctor_To_Connect);

        search = (Button)findViewById(R.id.btSearch_Search_Doctor_To_Connect);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Search_Doctor_To_Connect.this,List_Of_Search_Doctor_To_Connect_Doctor.class);
                startActivity(i1);
            }
        });
    }
}
