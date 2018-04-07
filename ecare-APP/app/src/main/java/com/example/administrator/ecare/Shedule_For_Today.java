package com.example.administrator.ecare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class Shedule_For_Today extends AppCompatActivity {
    ListView shedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule_for_today);

        shedule = (ListView)findViewById(R.id.ListView_Shedule_For_Today);

/*        shedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Shedule_For_Today.this,Patient_Tretment_From_Doctor.class);
                startActivity(i1);
            }
        }); */
    }
}
