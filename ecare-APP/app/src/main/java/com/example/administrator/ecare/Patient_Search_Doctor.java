package com.example.administrator.ecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Patient_Search_Doctor extends AppCompatActivity {

    private static final String TAG = Patient_Search_Doctor.class.getSimpleName();

    TextView tv1, tv2, tv3, tv4;
    Spinner spinner_city, spinner_qualification, spinner_specialization;
    Button b1;
    private SQLiteHandler db;
    private SessionManager session;

    private ProgressDialog pDialog;

    ArrayList<String> CityName;
    ArrayList<String>QualificationName;
    ArrayList<String>SpecializationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search_doctor);


        tv1 = (TextView) findViewById(R.id.tVSearch_Doctor_Patient_search_Doctor);
        tv2 = (TextView) findViewById(R.id.tVCity_Patient_Search_Doctor);
        tv3 = (TextView) findViewById(R.id.tVQualification_Patient_Search_Doctor);
        tv4 = (TextView) findViewById(R.id.tVSpecialization_Patient_Search_Doctor);

        spinner_city = (Spinner) findViewById(R.id.SpinnerCity_Patient_Search_Doctor);
        spinner_qualification = (Spinner) findViewById(R.id.SpinnerQualification_Patient_Search_Doctor);
        spinner_specialization = (Spinner) findViewById(R.id.SpinnerSpecialization_Patient_Search_Doctor);
        b1 = (Button) findViewById(R.id.btSearch_Patient_Search_Doctor);
        CityName = new ArrayList<>();
        QualificationName=new ArrayList<>();
        SpecializationName = new ArrayList<>();

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        db = new SQLiteHandler(getApplicationContext());


        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    String City = spinner_city.getSelectedItem().toString();
                    String Qualification = spinner_qualification.getSelectedItem().toString();
                    String Specialization = spinner_specialization.getSelectedItem().toString();

                        chechResult(City,Qualification,Specialization);
            }

        });
                loadSpinnerCityData();

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        String city = spinner_city.getItemAtPosition(spinner_city.getSelectedItemPosition()).toString();
                     //  Toast.makeText(getApplicationContext(), city, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), city, Toast.LENGTH_SHORT);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        // DO Nothing here
                    }
                });

                loadSpinnerQualificatonData();

                spinner_qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        String qualification = spinner_qualification.getItemAtPosition(spinner_qualification.getSelectedItemPosition()).toString();
                     //   Toast.makeText(getApplicationContext(), qualification, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), qualification, Toast.LENGTH_SHORT);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        // DO Nothing here
                    }
                });

                loadSpinnerSpecializationData();

                spinner_specialization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        String specialization = spinner_specialization.getItemAtPosition(spinner_specialization.getSelectedItemPosition()).toString();
                     //   Toast.makeText(getApplicationContext(), specialization, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), specialization, Toast.LENGTH_SHORT);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        // DO Nothing here
                    }
                });
            }

    private void chechResult( final String City, final String Qualification, final String Specialization) {

        String tag_string_req = "req_login";
        pDialog.setMessage("Loading  ...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                AppConfig.URL_GETSEARCH_DETAILS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean error = jsonObject.getBoolean("error");
                    if (!error) {

                       // jsonObject.getString(City);
                        //jsonObject.getString(Qualification);
                        //jsonObject.getString(Specialization);
                        String obj = jsonObject.toString();

                        Intent i = new Intent(Patient_Search_Doctor.this, Patient_Search_List_Action.class);
                        i.putExtra("data",obj);

                        startActivity(i);
                    }else {
                        String errorMsg = jsonObject.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            } ,  new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
              //  hideDialog();
            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                // map method signature
                Map<String, String> params = new HashMap<>();
                params.put("City", City);
                params.put("Qualification", Qualification);
                params.put("Specialization", Specialization);

                return params;
            }

        };


        AppController.getInstance().addToRequestQueue(tag_string_req, stringRequest);

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    private void loadSpinnerCityData() {
                pDialog.setMessage("Loding Cities...");
                pDialog.show();
                String tag_string_req = "req_getcity";
                //   RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_GETCITY, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            //   JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray = new JSONArray(response);

                            //  jsonArray = jsonObject.getJSONArray("City");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String city = jsonObject1.getString("City");
                                CityName.add(city);
                            }
                            spinner_city.setAdapter(new ArrayAdapter<String>(Patient_Search_Doctor.this, android.R.layout.simple_spinner_dropdown_item, CityName));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                pDialog.hide();
                        //simple_spinner_dropdown_item
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                AppController.getInstance().addToRequestQueue(tag_string_req, stringRequest);

            }


            private void loadSpinnerQualificatonData() {
                pDialog.setMessage("Loding Qualifications...");
                pDialog.show();

                String tag_string_req = "req_getQualification";
                //   RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_GETQUALIFICATION, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONArray jsonArray = new JSONArray(response);


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String qualification = jsonObject1.getString("Qualification");
                                QualificationName.add(qualification);
                            }

                            spinner_qualification.setAdapter(new ArrayAdapter<String>(Patient_Search_Doctor.this, android.R.layout.simple_spinner_dropdown_item, QualificationName));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pDialog.hide();
                        //simple_spinner_dropdown_item
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                AppController.getInstance().addToRequestQueue(tag_string_req, stringRequest);

            }


            private void loadSpinnerSpecializationData() {

                pDialog.setMessage("Loding Specializations...");
                pDialog.show();
                String tag_string_req = "req_Specialization";
                //   RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_GETSPECIALIZATION, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            //   JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray = new JSONArray(response);

                            //  jsonArray = jsonObject.getJSONArray("City");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String specialization = jsonObject1.getString("Specialization");
                                SpecializationName.add(specialization);
                            }

                            spinner_specialization.setAdapter(new ArrayAdapter<String>(Patient_Search_Doctor.this, android.R.layout.simple_spinner_dropdown_item, SpecializationName));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pDialog.hide();
                        }  //simple_spinner_dropdown_item
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                AppController.getInstance().addToRequestQueue(tag_string_req, stringRequest);

            }
}
















