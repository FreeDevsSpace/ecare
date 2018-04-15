package com.example.administrator.ecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.ecare.app.AppConfig;
import com.example.administrator.ecare.app.AppController;
import com.example.administrator.ecare.helper.Patient_SQLiteHandler;
import com.example.administrator.ecare.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Patient_Main extends AppCompatActivity {
    private static final String TAG = Patient_Main.class.getSimpleName();
    ImageView  profile, search,payment,consultation_status;
    TextView logout,tvprofile,tvsearch,tvpayment,tvconsultationstatus;

    private Patient_SQLiteHandler db; ////////////////////////////////////////
    private SessionManager session;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);

        profile = (ImageView) findViewById(R.id.IVProfile_Patient_Main);
        search = (ImageView) findViewById(R.id.IVSearch_Patient_Main);
        payment=(ImageView)findViewById(R.id.IVPayment_Patient_Main);
        consultation_status=(ImageView)findViewById(R.id.IVConsultation_Status_Patient_Main);
        logout = (TextView) findViewById(R.id.tVLogOut_Patient_Main);
        tvprofile=(TextView)findViewById(R.id.tVProfile_Patient_Main);
        tvsearch=(TextView)findViewById(R.id.tVSearch_Patient_Main);
        tvconsultationstatus=(TextView)findViewById(R.id.tVConsultation_Status_Patient_Main);
        tvpayment=(TextView)findViewById(R.id.tVPayment_Patient_Main);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        db = new Patient_SQLiteHandler(getApplicationContext()); ////////////////////////////////

        // session manager
        session = new SessionManager(getApplicationContext());

       if (!session.isLoggedIn()) {
            logoutUser();
       }


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Fetching user details from SQLite
                HashMap<String, String> patient_registration = db.getUserDetails();

                String Patient_ID = patient_registration.get("Patient_ID");
                String Full_name = patient_registration.get("Full_Name");
                String Mobile_Number = patient_registration.get("Mobile_Number");
                String DOB = patient_registration.get("DOB");
                String Address = patient_registration.get("Address");
                String Blood_Group = patient_registration.get("Blood_Group");

                Intent i1 = new Intent(Patient_Main.this, Patient_Profile.class);

                i1.putExtra("Patient_ID", Patient_ID);
                i1.putExtra("Full_Name", Full_name);
                i1.putExtra("Mobile_Number", Mobile_Number);
                i1.putExtra("DOB", DOB);
                i1.putExtra("Address", Address);
                i1.putExtra("Blood_Group", Blood_Group);

                startActivity(i1);

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pDialog.setMessage("Loading data ...");
                //  showDialog();
                Intent i2 = new Intent(Patient_Main.this, Patient_Search_Doctor.class);
                startActivity(i2);
                // hideDialog();
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent shareIntent =  new Intent(Intent.ACTION_VIEW);

                shareIntent.setType("*/*");

                startActivity(Intent.createChooser(shareIntent,"Payment via Paytm"));

            }
        });

        consultation_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> patient_registration = db.getUserDetails();
              String  Patient_ID = patient_registration.get("Patient_ID");

                getid(Patient_ID);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               logoutUser();
            }
        });


    }




    private void getid(final String Patient_ID) {

        Log.d("okk2","inside getid");
        String tag_string_req = "req_register";

        pDialog.setMessage("Loading ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_PETIENT_CONSULTATION_STATUS, new Response.Listener<String>() {

            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {

                    JSONObject jObj = new JSONObject(response);

                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        String obj = jObj.toString();

                        Intent i = new Intent(Patient_Main.this, Patient_Consultation_Status.class);
                        i.putExtra("key",obj);

                        startActivity(i);
                        finish();

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("message");
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
                params.put("Patient_ID",Patient_ID);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(tag_string_req,strReq );
    }



    private void logoutUser() {
        session.setLogin(false,"");
        String status = String.valueOf(session.isLoggedOut());
        Log.d("Logged Out..." , status);
        db.deleteUsers();
        // Launching the login activity
        Intent intent = new Intent(Patient_Main.this, Main1.class);
        startActivity(intent);
        finish();
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