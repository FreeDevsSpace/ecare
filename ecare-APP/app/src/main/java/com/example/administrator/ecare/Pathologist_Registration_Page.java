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
import com.example.administrator.ecare.helper.Pathologist_SQLiteHandler;
import com.example.administrator.ecare.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Pathologist_Registration_Page extends AppCompatActivity {
    private static final String TAG = Pathologist_SQLiteHandler.class.getSimpleName();
    EditText name,mobile_number,email,address,password;
    Spinner spqualification,spblood_group;
    TextView tvdob,tvqualification,tvaddress,tvblood_group,dob;
    Button save;
    private int mYear, mMonth, mDay;
    private ProgressDialog pDialog;
    private SessionManager session;
    private Pathologist_SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathologist_registration_page);

        name=(EditText)findViewById(R.id.eTName_Pathologist_Registration_Page);
        mobile_number=(EditText)findViewById(R.id.eTMobile_Number_Pathologist_Registration_Page);
        email=(EditText)findViewById(R.id.eTEmail_Pathologist_Registration_Page);
        dob=(TextView)findViewById(R.id.eTDOB_Pathologist_Registration_Page);
        address=(EditText)findViewById(R.id.eTAddress_Pathologist_Registration_Page);
        password=(EditText)findViewById(R.id.eTPassword_Pathologist_Registration_Page);
        spqualification=(Spinner)findViewById(R.id.SpinnerQualification_Pathologist_Registration_Page);
        spblood_group=(Spinner)findViewById(R.id.SpinnerBlood_Group_Pathologist_Registration_Page);
        tvdob=(TextView)findViewById(R.id.tVDOB_Pathologist_Registration_Page);
        tvqualification=(TextView)findViewById(R.id.tVQualification_Pathologist_Registration_Page);
     //   tvaddress=(TextView)findViewById(R.id.tVAddress_Pathologist_Registration_Page);
        tvblood_group=(TextView)findViewById(R.id.tVBloodGroup_Pathologist_Registration_Page);
        save=(Button)findViewById(R.id.btSave_Pathologist_Registration_Page);

        // Progress dialog
        pDialog= new ProgressDialog(this);
        pDialog.setCancelable(false);

// Session manager
        session = new SessionManager(getApplicationContext());

// SQLite database handler
        db= new Pathologist_SQLiteHandler(getApplicationContext());


// Check if user is already logged in or not
        if (session.isLoggedIn()) {
// User is already logged in. Take him to main activity
            Intent intent = new Intent(Pathologist_Registration_Page.this, Main1.class);
            startActivity(intent);
            finish();
        }
/*  String email = E.getText().toString();

        if (Name.getText().toString().length() <= 3) {
Name.setError("Please enter your Name");
Name.requestFocus();
        } else if (MN.getText().toString().length() <= 9) {
MN.setError("Please enter 10 digit Mobile Number");
MN.requestFocus();
        } else if (C.getText().toString().length() == 0) {
C.setError("Please enter City Name");
C.requestFocus();
        } else if (A.getText().toString().length() == 0) {
A.setError("Please Enter your Street Name");
A.requestFocus();

        } else if (C.getText().toString().length() == 0) {
C.setError("Please Enter City Name");
C.requestFocus();

        } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
E.setError("Please Enter an Valid Email ID");
E.requestFocus();

        } else if (Date_Of_Birth.getText().toString().length() <= 7) {
Date_Of_Birth.setError("Please Enter Date of Birth in Correct Format");
Date_Of_Birth.requestFocus();
        } else if (Psw.getText().toString().length() < 5) {
Psw.setError("Password Should be at least 5 character");
Psw.requestFocus();

        }  else {
            Toast.makeText(getApplicationContext(),"sending.....",Toast.LENGTH_LONG).show();

        } */
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == dob) {

                    //get current date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(Pathologist_Registration_Page.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }

            }

        });
        save.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String Full_Name = name.getText().toString();
                String Mobile_Number = mobile_number.getText().toString();
                String DOB = dob.getText().toString();
                String Email = email.getText().toString();
                String Qualification = spqualification.getSelectedItem().toString();
                String Address = address.getText().toString();
                String Blood_Group = spblood_group.getSelectedItem().toString();
                String Password = password.getText().toString();

                if (!Full_Name.isEmpty() && !Mobile_Number.isEmpty() && !DOB.isEmpty() && !Email.isEmpty() && !Qualification.isEmpty()
                        && !Address.isEmpty() &&  !Blood_Group.isEmpty() && !Password.isEmpty())
                {
                    registerPathologist(Full_Name,Mobile_Number,DOB,Qualification,Address,Blood_Group,Email,Password);

                }
                else {

                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG).show();
                }
            }

        });

    }
    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerPathologist(final String Full_Name ,final String Mobile_Number, final String DOB,final String Qualification,final String Address, final String Blood_Group , final String Email, final String Password){
//   final String Re_Password
        // Tag used to cancel the request
        String tag_string_req = "req_register";
        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_PATHOLOGISTREGISTER, new Response.Listener<String>() {

            public void onResponse(String response) {
                Log.d(TAG, "Response Received: " + response.toString());
                hideDialog();
                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite

                        JSONObject pathologist_registration = jObj.getJSONObject("pathologist_registration");
                        String Full_Name= pathologist_registration.getString("Full_Name");
                        String Mobile_Number = pathologist_registration.getString("Mobile_Number");
                        String DOB = pathologist_registration.getString("DOB");
                        String Qualification = pathologist_registration.getString("Qualification");
                        String Address = pathologist_registration.getString("Address");
                        String Blood_Group = pathologist_registration.getString("Blood_Group");
                        String Email = pathologist_registration.getString("Email");
                        String Password = pathologist_registration.getString("Password");

                        db.addUser(Full_Name,Mobile_Number,DOB,Qualification,Address,Blood_Group,Email,Password);

                        Toast.makeText(Pathologist_Registration_Page.this, "User successfully registered. Try login now!",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Pathologist_Registration_Page.this, Main1.class);
                        startActivity(intent);
                        finish();

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),errorMsg, Toast.LENGTH_LONG).show();
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
                Map<String, String>params = new HashMap<String, String>();
                params.put("Full_Name",Full_Name);
                params.put("Mobile_Number",Mobile_Number);
                params.put("DOB",DOB);
                params.put("Qualification",Qualification);
                params.put("Address",Address);
                params.put("Blood_Group",Blood_Group);
                params.put("Email",Email);
                params.put("Password", Password);

                return params;
            }

        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(tag_string_req,strReq );
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
