package com.example.administrator.ecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Patient_Consultation_Status extends AppCompatActivity {
    ListView simpleList;
    TextView error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_consultation_status);
        simpleList = (ListView) findViewById(R.id.ListView_Patient_Consultation_Status);
        error = (TextView) findViewById(R.id.Error_Patient_Consultation_Status);

        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("key");

        final ArrayList<String> cidArray = new ArrayList<String>();
        final ArrayList<String> pidArray = new ArrayList<String>();
        final ArrayList<String> didArray = new ArrayList<String>();
        final ArrayList<String> dnameArray = new ArrayList<String>();
        final ArrayList<String> problemArray = new ArrayList<String>();
        final ArrayList<String> medicine_infoArray = new ArrayList<String>();
        final ArrayList<String> allergy_infoArray = new ArrayList<String>();
        final ArrayList<String> suggetionArray = new ArrayList<String>();
        final ArrayList<String> prescribe_medicineArray = new ArrayList<String>();


        try {

            JSONObject jsonObject = new JSONObject(value);
            Log.d("Hiiii1", jsonObject.toString());

            if (!jsonObject.has("Key0")) {
                Log.d("OK", "Doctor not found");
                error.setText("No Consultation Status");
                Toast.makeText(this, "Try Again...", Toast.LENGTH_SHORT).show();

            } else {


                for (int i = 0; i < jsonObject.length() - 1; i++) {
                    String key = "Key" + String.valueOf(i);
                    Log.d("Hiiii2", key);
                    String cid,pid,did,dname,problem,medicine_info,allergy_info,suggetion,prescribe_medicine;

                    JSONObject obj = jsonObject.getJSONObject(key);
                    Log.d("Hiiii3", obj.toString());
                    cid = obj.getString("Consultation_ID");
                    pid = obj.getString("Patient_ID");
                    did = obj.getString("Doctor_ID");
                    dname = obj.getString("Doctor_Name");
                    problem = obj.getString("Problem");
                    medicine_info = obj.getString("Additional_Info_Medicine");
                    allergy_info = obj.getString("Additional_Info_Allergy");
                    suggetion = obj.getString("Suggetion");
                    prescribe_medicine = obj.getString("Prescribe_Medicine");




                    Log.d("OK", cid);

                    cidArray.add(cid);
                    pidArray.add(pid);
                    didArray.add(did);
                    dnameArray.add(dname);
                    problemArray.add(problem);
                    medicine_infoArray.add(medicine_info);
                    allergy_infoArray.add(allergy_info);
                    suggetionArray.add(suggetion);
                    prescribe_medicineArray.add(prescribe_medicine);


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        CustomAdapter3 customAdapter = new CustomAdapter3(getApplicationContext(), cidArray);
        simpleList.setAdapter(customAdapter);



        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                long v = adapterView.getItemIdAtPosition(i);


//                String str = (String)item; //As you are using Default String Adapter
                //    Toast.makeText(getBaseContext(),str.getTitle(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Patient_Consultation_Status.this, Patient_Consultation_Status_Store.class);

                intent.putExtra("Consultation_ID", cidArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("Patient_ID", pidArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("Doctor_ID", didArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("Doctor_Name", dnameArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("Problem", problemArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("Additional_Info_Medicine", medicine_infoArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("Additional_Info_Allergy", allergy_infoArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("Suggetion", suggetionArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("Prescribe_Medicine", prescribe_medicineArray.get(Integer.parseInt(Long.toString(v))));



                startActivity(intent);
                finish();
            }
        });

    }

}
