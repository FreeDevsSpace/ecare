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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Patient_Online_Consultation extends AppCompatActivity {
    private static final String TAG = Patient_Online_Consultation.class.getSimpleName();
    TextView p_id, basic_info, take_medicine, any_allergy, describe_issue,ppatient_id,doctor_id,pdoctor_id;
    Button proceed;
    EditText issue, medicine, allergy;
    private ProgressDialog pDialog;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_online_consultation);
        p_id = (TextView) findViewById(R.id.tVPatient_ID_Patient_Online_Consultation);
        basic_info = (TextView) findViewById(R.id.tVDescribe_Issue_In_Details_Patient_Online_Consultation);
        take_medicine = (TextView) findViewById(R.id.tVTake_Extra_Medicine_Patient_Online_Consultation);
        any_allergy = (TextView) findViewById(R.id.tVAllergy_Patient_Online_Consultation);
        describe_issue = (TextView) findViewById(R.id.tVDescribe_Issue_In_Details_Patient_Online_Consultation);
        ppatient_id = (TextView) findViewById(R.id.eTPPatient_ID_Patient_Online_Consultation);
        issue = (EditText) findViewById(R.id.eTDescribe_Issue_In_Details_Patient_Online_Consultation);
        medicine = (EditText) findViewById(R.id.eTTake_Extra_Medicine_Patient_Online_Consultation);
        allergy = (EditText) findViewById(R.id.eTAllergy_Patient_Online_Consultation);
        proceed = (Button) findViewById(R.id.btProceeds_Patient_Online_Consultation);
        doctor_id=(TextView)findViewById(R.id.tVDoctor_ID_Patient_Online_Consultation);
        pdoctor_id=(TextView)findViewById(R.id.tVPDoctor_ID_Patient_Online_Consultation);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        Bundle b = getIntent().getExtras();
        final String did=b.getString("Doctor_ID");
       final String pid = b.getString("Patient_ID");
        ppatient_id.setText(pid);
        pdoctor_id.setText(did);
        db = new SQLiteHandler(getApplicationContext());


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Patient_ID =ppatient_id .getText().toString();
                String Doctor_ID = pdoctor_id.getText().toString();
                String Problem = issue.getText().toString();
                String Medicine = medicine.getText().toString();
                String Allergy = allergy.getText().toString();

                if (!Patient_ID.isEmpty() &&!Doctor_ID.isEmpty() &&!Problem.isEmpty() && !Medicine.isEmpty() && !Allergy.isEmpty()) {
                    patientConsultation(Patient_ID,Doctor_ID,Problem, Medicine, Allergy);
                } else {

                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void patientConsultation(final String Patient_ID,final String Doctor_ID,final String Problem, final String Medicine, final String Allergy) {

        String tag_string_req = "req_consultation";

        pDialog.setMessage("Send Consultation ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_CONSULTATION, new Response.Listener<String>() {

            public void onResponse(String response) {
                Log.d(TAG, "Register Consultation: " + response.toString());
                hideDialog();

                try {

                    JSONObject jObj = new JSONObject(response);

                 //   JSONObject obj = new JSONObject(id);
                    boolean error = jObj.getBoolean("error");
                    Toast.makeText(Patient_Online_Consultation.this, "Your Consultation is send!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Patient_Online_Consultation.this, Patient_Main.class);
                    startActivity(intent);
                    finish();
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
        }){

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("Patient_ID", Patient_ID);
                params.put("Doctor_ID", Doctor_ID);
                params.put("Problem", Problem);
                params.put("Additional_Info_Medicine", Medicine);
                params.put("Additional_Info_Allergy", Allergy);

                return params;
            }

        };


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




