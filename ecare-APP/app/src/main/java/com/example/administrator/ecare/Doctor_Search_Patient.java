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
import com.example.administrator.ecare.Patient_Tretment_From_Doctor;
import com.example.administrator.ecare.R;
import com.example.administrator.ecare.app.AppConfig;
import com.example.administrator.ecare.app.AppController;
import com.example.administrator.ecare.helper.Patient_SQLiteHandler;
import com.example.administrator.ecare.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Doctor_Search_Patient extends AppCompatActivity {
    private static final String TAG = Doctor_Search_Patient.class.getSimpleName();
    EditText e1;
    TextView t1;
    Button search;
    private ProgressDialog pDialog;
    private SessionManager session;
    private Patient_SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search_patient);

        e1=(EditText)findViewById(R.id.eTPatient_ID_Doctor_Search_Patient);

        search=(Button)findViewById(R.id.btContinue_Doctor_Search_Patient);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String Patient_ID=e1.getText().toString();
                if (!Patient_ID.isEmpty()){
                    getdetails(Patient_ID);
                }else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter Patient ID!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void getdetails(final String Patient_ID) {
        String tag_string_req = "req_register";

//        pDialog.setMessage("Loading ...");
  //      showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_GETPATIENT_DETAILS, new Response.Listener<String>() {

            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
//                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");


                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String Patient_ID=jObj.getString("Patient_ID");
                        String Patient_Name= jObj.getString("Patient_Name");
                        String Blood_Group=jObj.getString("Blood_Group");

                        Log.d(TAG,"data is here");


                         Intent intent = new Intent(Doctor_Search_Patient.this, Patient_Tretment_From_Doctor.class);

                        intent.putExtra("Patient_ID",Patient_ID);
                        intent.putExtra("Patient_Name",Patient_Name);
                        intent.putExtra("Blood_Group",Blood_Group);

                        Log.d(TAG,"Inside intent");
                        startActivity(intent);
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

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
//        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
