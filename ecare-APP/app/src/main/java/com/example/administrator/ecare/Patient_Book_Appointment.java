package com.example.administrator.ecare;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.ecare.R;

import java.util.Calendar;

public class Patient_Book_Appointment extends AppCompatActivity {
    TextView p_id, appointment, available_time_slot;
    Button save, select_date, find_available_time_slot;
    EditText date;

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_book_appointment);
        p_id = (TextView) findViewById(R.id.tVPatient_ID_Patient_Book_Appointment);
        appointment = (TextView) findViewById(R.id.tVAppointment_Patient_Book_Appointment);
        available_time_slot = (TextView) findViewById(R.id.tVAvailable_Time_Slot_Patient_Book_Appointment);

        date = (EditText) findViewById(R.id.eTDate_Patient_Book_Appointment);

        save = (Button) findViewById(R.id.btSave_Patient_Book_Appointment);
        select_date = (Button) findViewById(R.id.btSelect_Date_Patient_Book_Appointment);
        find_available_time_slot = (Button) findViewById(R.id.btFind_Appointment_Time_Slot_Patient_Book_Appointment);

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

                                        date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

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

