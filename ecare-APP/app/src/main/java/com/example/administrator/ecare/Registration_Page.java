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

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.ecare.app.AppConfig;
import com.example.administrator.ecare.app.AppController;
import com.example.administrator.ecare.helper.SQLiteHandler;
import com.example.administrator.ecare.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Registration_Page extends AppCompatActivity {
    private static final String TAG = Registration_Page.class.getSimpleName();
    EditText Name;
    EditText MN;
    EditText C;
    EditText A;
    EditText Date_Of_Birth;
    EditText Psw;
    EditText E;
    TextView tv1, tv2;
    Button b1;
    Spinner  BG;
    private int mYear, mMonth, mDay;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);


        Name = (EditText) findViewById(R.id.eTName_Registration_Page);
        MN = (EditText) findViewById(R.id.eTMobile_Number_Registration_Page);
        A = (EditText) findViewById(R.id.eTAddress_Registration_Page);
        Psw = (EditText) findViewById(R.id.eTPassword_Main1);
        //  RPsw = (EditText) findViewById(R.id.eTRE_Write_Password_Registration_Page);
        Date_Of_Birth = (EditText) findViewById(R.id.eTDOB_Registration_Page);
        E = (EditText) findViewById(R.id.eTEmail_Registration_Page);
        tv1 = (TextView) findViewById(R.id.tVAddress_Registration_Page);
        tv2 = (TextView) findViewById(R.id.tVBlood_Group_Registration_Page);
        b1 = (Button) findViewById(R.id.bnRegister_Registration_Page);
        //  sp1 = (Spinner) findViewById(R.id.SpinnerName_Start_Registration_Page);
        BG = (Spinner) findViewById(R.id.Spinner_Blood_Group_Registration_Page);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());


        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(Registration_Page.this, Patient_Main.class);
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
        Date_Of_Birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == Date_Of_Birth) {

                    //get current date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(Registration_Page.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    Date_Of_Birth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }

            }

        });



        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String Full_Name = Name.getText().toString();
                String Mobile_Number = MN.getText().toString();
                String DOB = Date_Of_Birth.getText().toString();
                String Email = E.getText().toString();
                String Address = A.getText().toString();
                String Blood_Group = BG.getSelectedItem().toString();
                String Password = Psw.getText().toString();


                if (!Full_Name.isEmpty() && !Mobile_Number.isEmpty() && !DOB.isEmpty() && !Email.isEmpty()
                        && !Address.isEmpty() &&  !Blood_Group.isEmpty() && !Password.isEmpty()) {
                    registerUser(Full_Name, Mobile_Number, DOB, Email, Address, Blood_Group, Password);
                } else {

                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG).show();
                }
                // Link to Login Screen

            }

        });

    }
    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser(final String Full_Name ,final String Mobile_Number, final String DOB, final String Email,
                              final String Address, final String Blood_Group, final String Password){
        //   final String Re_Password
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST, AppConfig.URL_REGISTER, new Response.Listener<String>() {

            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                     //   String Patient_ID = jObj.getString("Patient_ID");

                        JSONObject patient_registration = jObj.getJSONObject("patient_registration");
                        String Full_Name= patient_registration.getString("Full_Name");
                        String Mobile_Number = patient_registration.getString("Mobile_Number");
                        String DOB = patient_registration.getString("DOB");
                        String Email = patient_registration.getString("Email");
                        String Address = patient_registration.getString("Address");
                        String Blood_Group = patient_registration.getString("Blood_Group");
                        String Password = patient_registration.getString("PSW");

                        db.addUser(Full_Name,Mobile_Number,DOB,Email,Address,Blood_Group,Password);

                        Toast.makeText(Registration_Page.this, "User successfully registered. Try login now!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Registration_Page.this, Main1.class);
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
                Map<String, String> params = new HashMap<String, String>();
                params.put("Full_Name",Full_Name);
                params.put("Mobile_Number",Mobile_Number);
                params.put("DOB",DOB);
                params.put("Email",Email);
                params.put("Address",Address);
                params.put("Blood_Group",Blood_Group);
                //  params.put("Re_Password",Re_Password);
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
