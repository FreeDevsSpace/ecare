package com.example.administrator.ecare;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.ecare.app.AppConfig;
import com.example.administrator.ecare.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Patient_Book_Appointment extends AppCompatActivity {

    private static final String TAG = Patient_Book_Appointment.class.getSimpleName();

    TextView p_id, appointment, choose_time,selected_date,ppid;
    Button save,select_date;

    private ProgressDialog pDialog;
    private Spinner spinnertime;

    public String Patient_ID,Doctor_ID;

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
        ppid = (TextView) findViewById(R.id.tVPPatient_ID_Patient_Book_Appointment);


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Bundle b= getIntent().getExtras();

        Patient_ID=b.get("Patient_ID").toString().trim();
        Doctor_ID=b.get("Doctor_ID").toString().trim();

        final String pid = b.getString("Patient_ID");
        final String did=b.getString("Doctor_ID");
        ppid.setText(pid);


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

                String Date = selected_date.getText().toString().trim();
                String Timeslote = spinnertime.getSelectedItem().toString().trim();

                Log.d("Patient_ID",Patient_ID);
                Log.d("Doctor_ID",Doctor_ID);
                Log.d("Date",Date);
                Log.d("Timeslote",Timeslote);

                // Check for empty data in the form
                if (!Patient_ID.isEmpty() && !Doctor_ID.isEmpty() && !Date.isEmpty() && !Timeslote.isEmpty()) {
                    // Book An Appointment
                    bookAppointment(Patient_ID,Doctor_ID,Date,Timeslote);
                }
                else
                {
                    Toast.makeText(Patient_Book_Appointment.this, "Please fill All Details Correctly!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void bookAppointment(final String Patient_ID, final String Doctor_ID, final String Date, final String Timeslote)
    {
        // Tag used to cancel the request
        String tag_string_req = "req_book";

        pDialog.setMessage("Booking Appointment ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_BOOK_APPOINTMENT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        Toast.makeText(getApplicationContext(),
                                "Appointment Booked Successfully", Toast.LENGTH_LONG).show();

                        Intent i1= new Intent(Patient_Book_Appointment.this,Main1.class);
                        startActivity(i1);

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("Patient_ID", Patient_ID);
                params.put("Doctor_ID", Doctor_ID);
                params.put("Date", Date);
                params.put("Timeslote", Timeslote);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}