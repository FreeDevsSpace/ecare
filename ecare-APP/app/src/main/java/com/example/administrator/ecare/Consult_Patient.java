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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Consult_Patient extends AppCompatActivity {
    private static final String TAG = Consult_Patient.class.getSimpleName();
    TextView patient_id,problem,suggetion,medicine,ppatient_id,pproblem,Consultation_ID,takeany_medicine,any_allegry,Ptakeany_medicine,Pany_allegry;
    EditText write_suggetion, prescribe_medicine;
    Button send;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_patient);

        patient_id=(TextView)findViewById(R.id.tVPatient_ID_Consult_Patient);
        problem=(TextView)findViewById(R.id.tVProblem_Consult_Patient);
        suggetion=(TextView)findViewById(R.id.tVSuggetion_Consult_Patient);
      //  medicine=(TextView)findViewById(R.id.tVMedicine_Consult_Patient);
        ppatient_id=(TextView)findViewById(R.id.tVPPatient_ID_Consult_Patient);
        Consultation_ID=(TextView)findViewById(R.id.tVPConsultation_ID);
        pproblem=(TextView)findViewById(R.id.tVPProble_Consult_Patient);
        write_suggetion=(EditText)findViewById(R.id.eTSuggetion_Consult_Patient);
        prescribe_medicine=(EditText)findViewById(R.id.eTMedicine_Consult_Patient);
        takeany_medicine=(TextView)findViewById(R.id.tVPTake_Any_Medicine_Consult_Patient);
        any_allegry=(TextView)findViewById(R.id.tVIs_Any_Allergy_Consult_Patient);
        Ptakeany_medicine=(TextView)findViewById(R.id.tVPTake_Any_Medicine_Consult_Patient);
        Pany_allegry=(TextView)findViewById(R.id.tVPIs_Any_Allergy_Consult_Patient);

        send=(Button)findViewById(R.id.btSend_Consult_Patient);//// on cick listner

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        Bundle b= getIntent().getExtras();

        final String cid =b.get("cid").toString();
        String pid= b.get("pid").toString();
        String pname= b.get("pname").toString();
        String did= b.get("did").toString();
        String problem= b.get("problem").toString();
        final String medicine= b.get("medicine").toString();
        String allergy= b.get("allergy").toString();


       ppatient_id.setText(pid);
       pproblem.setText(problem);
       Consultation_ID.setText(cid);
        Ptakeany_medicine.setText(medicine);
        Pany_allegry.setText(allergy);


       send.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String Patient_ID =ppatient_id .getText().toString();
               String Consult_ID =Consultation_ID.getText().toString();
               String Problem = pproblem.getText().toString();
               String Suggetion = write_suggetion.getText().toString();
               String Medicine =prescribe_medicine.getText().toString();


               if (!Suggetion.isEmpty() && !Medicine.isEmpty()) {
                   consultpatient(Patient_ID,Consult_ID,Problem,Suggetion, Medicine);
               } else {

                   Toast.makeText(getApplicationContext(),
                           "Please enter your details!", Toast.LENGTH_LONG).show();
               }


           }
       });

    }

    private void consultpatient(final String Patient_ID,final String Consult_ID, final String Problem, final String Suggetion,final String Medicine) {
        String tag_string_req = "req_consultation";

        pDialog.setMessage("Send Consultation ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_DOCTOR_SUGGETION, new Response.Listener<String>() {

            public void onResponse(String response) {
                Log.d(TAG, "Responce of  Consultation: " + response.toString());

                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);

                    boolean error = jObj.getBoolean("error");
                    String msg = jObj.getString("error_msg");

                    if (!error) {

                        Toast.makeText(Consult_Patient.this, msg, Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(Consult_Patient.this, Doctor_Main.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
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
        }){

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("Consultation_ID",Consult_ID);
                params.put("Suggetion", Suggetion);
                params.put("Prescribe_Medicine", Medicine);


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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(Consult_Patient.this, Doctor_Main.class);
        startActivity(i);
        finish();


    }
}
