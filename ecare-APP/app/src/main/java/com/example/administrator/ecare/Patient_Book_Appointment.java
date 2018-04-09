package com.example.administrator.ecare;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Patient_Book_Appointment extends AppCompatActivity {
    TextView p_id, appointment, choose_time,selected_date;
    Button save,select_date;
    private Spinner spinnertime;


    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_book_appointment);
        appointment=(TextView)findViewById(R.id.tVAppointment_Patient_Book_Appointment);
        p_id = (TextView) findViewById(R.id.tVPatient_ID_Patient_Book_Appointment);
        selected_date = (TextView) findViewById(R.id.tVSelect_Date_Patient_Book_Appointment);
        choose_time = (TextView) findViewById(R.id.tVChoose_Time_Patient_Book_Appointment);
        spinnertime = (Spinner) findViewById(R.id.spinner_time_Patient_Book_Appointment);



        save = (Button) findViewById(R.id.btSave_Patient_Book_Appointment);
        select_date=(Button)findViewById(R.id.btSelect_Date_Patient_Book_Appointment);

        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (view == select_date) {

                        //get current date
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(Patient_Book_Appointment.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        selected_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }

                }

        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Appointment done successfully", Toast.LENGTH_LONG).show();
                Intent i1= new Intent(Patient_Book_Appointment.this,Patient_Treatment_History.class);
                startActivity(i1);
            }
        });
    }
}

