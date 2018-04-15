package com.example.administrator.ecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.ecare.app.AppConfig;
import com.example.administrator.ecare.app.AppController;
import com.example.administrator.ecare.helper.Doctor_SQLiteHandler;
import com.example.administrator.ecare.helper.Patient_SQLiteHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Patient_Tretment_From_Doctor extends AppCompatActivity {
    private static final String TAG = Patient_Tretment_From_Doctor.class.getSimpleName();
    TextView p_id,patient_name,mobile_number,blood_group,doctor_name,symptoms,medicine,report,suggetions,prescribe_medicine
    ,upload_report,upload_report_clickhere,
               tvppatientid,tvppatientname,tvpbloodgroup;

    EditText symptoms_write_here,prescribe_medicine_here,write_report,write_suggetions;
    Button save;
    private Patient_SQLiteHandler db;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_tretment_from_doctor);

        p_id=(TextView)findViewById(R.id.tVPatient_ID_Patient_Tretment_From_Doctor);
        patient_name=(TextView)findViewById(R.id.tVPatient_Name_Patient_Tretment_From_Doctor);
        blood_group=(TextView)findViewById(R.id.tVBlood_Group_Patient_Tretment_From_Doctor);
       // upload_report=(TextView)findViewById(R.id.tVUpload_Report_Patient_Tretment_From_Doctor);
     //   upload_report_clickhere=(TextView)findViewById(R.id.tVClick_Here_Upload_Report_Patient_Tretment_From_Doctor);

        tvppatientid=(TextView)findViewById(R.id.tVP_Patient_ID_Patient_Treatment_From_Doctor);
        tvppatientname=(TextView)findViewById(R.id.tVP_Patient_Name_Patient_Treatment_From_Doctor);
        tvpbloodgroup=(TextView)findViewById(R.id.tVP_Blood_Group_Patient_Treatment_From_Doctor);

        symptoms_write_here = (EditText)findViewById(R.id.eTSymptoms_Patient_Tretment_From_Doctor);
        prescribe_medicine_here=(EditText)findViewById(R.id.eTMedicine_Prescribe_Patient_Tretment_From_Doctor);
        write_report=(EditText)findViewById(R.id.eTWrite_Report_Patient_Tretment_From_Doctor);
        write_suggetions=(EditText)findViewById(R.id.eTWrite_Suggetions_Patient_Tretment_From_Doctor);

        save=(Button)findViewById(R.id.btSave_Patient_Tretment_From_Doctor);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        db = new Patient_SQLiteHandler(getApplicationContext());

        Bundle b = getIntent().getExtras();
        String pid=b.getString("Patient_ID");
        String pname=b.getString("Patient_Name");
        String blood_group=b.getString("Blood_Group");

        tvppatientid.setText(pid);
        tvppatientname.setText(pname);
        tvpbloodgroup.setText(blood_group);


        save.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Patient_ID =tvppatientid .getText().toString();
                String Patient_Name =tvppatientname .getText().toString();
                String Blood_Group =tvpbloodgroup .getText().toString();
                String Symptoms =symptoms_write_here .getText().toString();
                String Prescription =prescribe_medicine_here .getText().toString();
                String Report =write_report .getText().toString();
                String Suggetion =write_suggetions .getText().toString();


                if (!Patient_ID.isEmpty() &&!Patient_Name.isEmpty() &&!Blood_Group.isEmpty() && !Symptoms.isEmpty() &&
                        !Prescription.isEmpty() &&!Report.isEmpty() &&!Suggetion.isEmpty()) {
                    patient_treatment(Patient_ID,Patient_Name,Blood_Group, Symptoms, Prescription,Report,Suggetion);
                } else {

                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG).show();
                }
            }
                });
    }


    private void patient_treatment(final String Patient_ID, final String Patient_Name,final String Blood_Group,final String Symptoms,final String Prescription,final String Report, final String Suggetion) {

        String tag_string_req = "req_consultation";

        pDialog.setMessage("Loading ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_GETPATIENT_TREATMENT_DETAILS, new Response.Listener<String>() {

            public void onResponse(String response) {
                Log.d(TAG, "Patient Treatment: " + response.toString());
                hideDialog();

                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    Toast.makeText(Patient_Tretment_From_Doctor.this, "Your Patient Data is send!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Patient_Tretment_From_Doctor.this, Doctor_Main.class);
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
                params.put("Patient_Name", Patient_Name);
                params.put("Blood_Group", Blood_Group);
                params.put("Symptoms",Symptoms );
                params.put("Prescription", Prescription);
                params.put("Report", Report);
                params.put("Suggetion", Suggetion);

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




