package com.example.administrator.ecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.ecare.app.AppConfig;
import com.example.administrator.ecare.app.AppController;
import com.example.administrator.ecare.helper.SQLiteHandler;
import com.example.administrator.ecare.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main1 extends AppCompatActivity {
    private static final String TAG = Main1.class.getSimpleName();
    private static EditText email, password;
    private Button login_button;
    private static TextView attempt, forgot_Password;
    //   int attempt_counter = 3;
    TextView t1;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        t1 = (TextView) findViewById(R.id.tVNew_Account_Main1);
        email = (EditText) findViewById(R.id.eTID_Main1);
        password = (EditText) findViewById(R.id.eTPassword_Main1);
      //  attempt = (TextView) findViewById(R.id.tVAttempt_Main1);
      //  forgot_Password = (TextView) findViewById(R.id.tVForget_Password_Main1);
        login_button = (Button) findViewById(R.id.btLog_In_Main1);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main1.this, Register_As.class);
                startActivity(i);
            }
        });


        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(Main1.this, Patient_Main.class);
            startActivity(intent);
            finish();
        }

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();

                // Check for empty data in the form
                if (!Email.isEmpty() && !Password.isEmpty()) {
                    // login user
                    checkLogin(Email, Password);
                }else {
                    Toast.makeText(Main1.this, "Please enter the credentials!", Toast.LENGTH_SHORT).show();

                }

            }

        });
    }

    private void checkLogin(final String Email, final String Password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                 //   String usertype=jObj.getString("USER_TYPE");
                    String usertype=jObj.getString("USER_TYPE");

                    // Check for error node in json
                    if (!error) {
                            Log.d("okk","Inside erroe if");
                        //   if(usertype=="Patient"){
                        if (usertype.equals("Patient"))
                        {

                                Log.d("oh","inside if");
                                JSONObject patient_registration = jObj.getJSONObject("patient_registration");
                                String Full_Name= patient_registration.getString("Full_Name");
                                String Mobile_Number = patient_registration.getString("Mobile_Number");
                                String DOB = patient_registration.getString("DOB");
                                String Email = patient_registration.getString("Email");
                                String Address = patient_registration.getString("Address");
                                String Blood_Group = patient_registration.getString("Blood_Group");
                                String Password = patient_registration.getString("PSW");

                                db.addUser(Full_Name, Mobile_Number, DOB, Email, Address, Blood_Group, Password);
                                Intent   intent1 = new Intent(Main1.this, Patient_Main.class);
                                startActivity(intent1);
                                finish();

                        }else if(usertype.equals("Doctor")){
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

                                db.addDoctor(Full_Name,Mobile_Number,DOB,Address,City,Email,Password,Qualification,Specialization,Hospital_Name,Hospital_Address,Fees);
                                Intent  intent2  = new Intent(Main1.this, Doctor_Main.class);
                                startActivity(intent2);
                                finish();

                        }else if (usertype.equals("Pathologist")){
                            JSONObject pathologist_registration = jObj.getJSONObject("pathologist_registration");
                                String Full_Name = pathologist_registration.getString("Full_Name");
                                String Mobile_Number = pathologist_registration.getString("Mobile_Number");
                                String DOB = pathologist_registration.getString("DOB");
                                String Qualification = pathologist_registration.getString("Qualification");
                                String Address = pathologist_registration.getString("Address");
                                String Blood_Group = pathologist_registration.getString("Blood_Group");
                                String Email = pathologist_registration.getString("Email");
                                String Password = pathologist_registration.getString("Password");

                                db.addPathologist(Full_Name,Mobile_Number,DOB,Qualification,Address,Blood_Group,Email,Password);
                                Intent  intent3  = new Intent(Main1.this, Pathologist_Main.class);
                                startActivity(intent3);
                                finish();

                            }
                            else if (usertype.equals("Pharmacist")){
                            JSONObject pharmacist_registration = jObj.getJSONObject("pharmacist_registration");
                                String Full_Name = pharmacist_registration.getString("Full_Name");
                                String Mobile_Number = pharmacist_registration.getString("Mobile_Number");
                                String DOB = pharmacist_registration.getString("DOB");
                                String Qualification = pharmacist_registration.getString("Qualification");
                                String Address = pharmacist_registration.getString("Address");
                                String Blood_Group = pharmacist_registration.getString("Blood_Group");
                                String Email = pharmacist_registration.getString("Email");
                                String Password = pharmacist_registration.getString("Password");

                                db.addPharmacist(Full_Name,Mobile_Number,DOB,Qualification,Address,Blood_Group,Email,Password);
                                Intent  intent4  = new Intent(Main1.this, Pharmacist_Main.class);
                                startActivity(intent4);
                                finish();

                            }

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
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
                params.put("Email", Email);
                params.put("Password", Password);

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



