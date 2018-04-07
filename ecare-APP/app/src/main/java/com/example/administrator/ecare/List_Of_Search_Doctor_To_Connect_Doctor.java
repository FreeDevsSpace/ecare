package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

public class List_Of_Search_Doctor_To_Connect_Doctor extends AppCompatActivity {
    ListView search_doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_search_doctor_to_connect_doctor);

        search_doctor=(ListView)findViewById(R.id.ListView_Search_Doctor_To_Connect_Doctor);

        search_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(List_Of_Search_Doctor_To_Connect_Doctor.this,Connect_To_Other_Doctor.class);
                startActivity(i1);
            }
        });
    }
}
