package com.example.administrator.ecare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Patient_Thank_You extends AppCompatActivity {
    TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_thank_you);
        tv1=(TextView)findViewById(R.id.tVThank_You_Patient_Thank_You);
        tv2=(TextView)findViewById(R.id.tVQuote_Patient_Thank_You);
    }
}
