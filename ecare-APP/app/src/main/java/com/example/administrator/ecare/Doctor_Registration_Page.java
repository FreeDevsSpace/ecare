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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.ecare.app.AppConfig;
import com.example.administrator.ecare.app.AppController;
import com.example.administrator.ecare.helper.Doctor_SQLiteHandler;
import com.example.administrator.ecare.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Doctor_Registration_Page extends AppCompatActivity {

    private static final String TAG = Doctor_Registration_Page.class.getSimpleName();
    EditText name,mobile_number,city,password,hospital_name,hospital_address,email,fees;
    TextView date_of_birth,tvdob;
    TextView address,specialization,qualification,workplace;
    Button b1;
    Spinner sp1qualification,sp2specialization,sp3;
    boolean invalid = false;
    private ProgressDialog pDialog;
    private SessionManager session;
    private Doctor_SQLiteHandler db1;


    private int mYear, mMonth, mDay;
    //boolean invalid = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration_page);

        name=(EditText)findViewById(R.id.eTName_Doctor_Registration_Page);
        mobile_number=(EditText)findViewById(R.id.eTMobile_Number_Doctor_Registration_Page);
        password=(EditText)findViewById(R.id.eTPassword_Doctor_Registration_Page);
        date_of_birth=(TextView) findViewById(R.id.eTDate_Of_Birth_Doctor_Registration_Page);
        email=(EditText)findViewById(R.id.eTEmail_Doctor_Registration_Page);
      //  address=(TextView)findViewById(R.id.tVAddress_Doctor_Registration_Page);
        city=(EditText)findViewById(R.id.eTCity_Doctor_Registration_Page);
        specialization=(TextView)findViewById(R.id.tVSpecialization_Doctor_Registration_Page);
       // workplace=(TextView)findViewById(R.id.tVWorkplace_Doctor_Registration_Page);
        qualification=(TextView)findViewById(R.id.tVQualification_Doctor_Registration_Page);
        b1=(Button)findViewById(R.id.bnRegister_Doctor_Registration_Page);
        sp1qualification=(Spinner)findViewById(R.id.Spinner_Qualification_Doctor_Registration_Page);
        sp2specialization=(Spinner)findViewById(R.id.Spinner_Specialization_Doctor_Registration_Page);
        hospital_name=(EditText)findViewById(R.id.eTHospital_Name_Doctor_Registration_Page);
        hospital_address=(EditText)findViewById(R.id.eTHospital_Address_Doctor_Registration_page);
        fees=(EditText)findViewById(R.id.eTFees_Doctor_Registration_Page);
        tvdob=(TextView)findViewById(R.id.tVDOB_Doctor_Registration_Page);


        date_of_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == date_of_birth) {

                    //get current date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(Doctor_Registration_Page.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    date_of_birth.setText(dayOfMonth + "-" + (monthOfYear + 0) + "-" + year);   //+1

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }

            }

        });

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db1 = new Doctor_SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(Doctor_Registration_Page.this,Main1.class);
            startActivity(intent);
            finish();
        }


        final boolean invalid = false;
        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String Full_Name = name.getText().toString();
                String Mobile_Number = mobile_number.getText().toString();
                String DOB = date_of_birth.getText().toString();
                String Address = address.getText().toString();
                String City = city.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String Qualification = sp1qualification.getSelectedItem().toString();
                String Specialization = sp2specialization.getSelectedItem().toString();
                String Hospital_Name = hospital_name.getText().toString();
                String Hospital_Address = hospital_address.getText().toString();
                String Fees = fees.getText().toString();

                if (!Full_Name.isEmpty() && !Mobile_Number.isEmpty() && !DOB.isEmpty() && !Email.isEmpty()
                        && !Address.isEmpty() && !City.isEmpty() && !Password.isEmpty() && !Qualification.isEmpty()&& !Specialization.isEmpty()&& !Hospital_Name.isEmpty()&& !Hospital_Address.isEmpty()&& !Fees.isEmpty()) {
                    registerDoctor(Full_Name, Mobile_Number, DOB,Address,City,Email, Password,Qualification,Specialization,Hospital_Name,Hospital_Address,Fees);
                } else {

                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG).show();
                }
                // Link to Login Screen

            }

        });

    }

    private void registerDoctor(final String Full_Name, final String Mobile_Number, final String DOB, final String Address, final String City,final String Email,
                                final String Password, final String Qualification, final String Specialization,
                                final String Hospital_Name, final String Hospital_Address,final String Fees) {
        //   final String Re_Password
        // Tag used to cancel the request
        final String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        final StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_DOCREGISTER, new Response.Listener<String>() {
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        // String Patient_ID = jObj.getString("Patient_ID");

                        JSONObject doctor_registration = jObj.getJSONObject("doctor_registration");
                        String Full_Name = doctor_registration.getString("Full_Name");
                        String Mobile_Number = doctor_registration.getString("Mobile_Number");
                        String DOB = doctor_registration.getString("DOB");
                        String Address = doctor_registration.getString("Address");
                        String City = doctor_registration.getString("City");
                        String Email = doctor_registration.getString("Email");
                        String Password = doctor_registration.getString("Password");
                        String Qualification = doctor_registration.getString("Qualification");
                        String Specialization = doctor_registration.getString("Specialization");
                        String Hospital_Name = doctor_registration.getString("Hospital_Name");
                        String Hospital_Address = doctor_registration.getString("Hospital_Address");
                        String Fees = doctor_registration.getString("Fees");

                       // db1.addDoctor(Full_Name, Mobile_Number, DOB, Address,City, Email, Password,
                         //       Qualification, Specialization, Hospital_Name, Hospital_Address,Fees);

                        db1.addDoctor(Full_Name, Mobile_Number, DOB, Address,City, Email, Password,
                                Qualification, Specialization, Hospital_Name, Hospital_Address,Fees);
                        Toast.makeText(Doctor_Registration_Page.this, "User successfully registered. Try login now!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Doctor_Registration_Page.this, Main1.class);
                        startActivity(intent);
                        finish();

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Full_Name", Full_Name);
                params.put("Mobile_Number", Mobile_Number);
                params.put("DOB", DOB);
                params.put("Address", Address);
                params.put("City", City);
                params.put("Email", Email);
                params.put("Password", Password);
                params.put("Qualification", Qualification);
                params.put("Specialization", Specialization);
                params.put("Hospital_Name", Hospital_Name);
                params.put("Hospital_Address", Hospital_Address);
                params.put("Fees", Fees);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(tag_string_req, strReq);
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






















