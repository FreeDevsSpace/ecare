package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Consultation extends AppCompatActivity {

    ListView simpleList;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        simpleList=(ListView)findViewById(R.id.ListView_Consultation);
        tv1=(TextView)findViewById(R.id.tVError_Consultation);



        final ArrayList<String> cid_Array = new ArrayList<String>();
        final ArrayList<String> pid_Array = new ArrayList<String>();
        final ArrayList<String> did_Array = new ArrayList<String>();
        final ArrayList<String> patientnameArray = new ArrayList<String>();
        final ArrayList<String> problemArray = new ArrayList<String>();
        final ArrayList<String> medicineArray = new ArrayList<String>();
        final ArrayList<String> allergyArray = new ArrayList<String>();
        final ArrayList<String> suggetionArray = new ArrayList<String>();


        Bundle bundle = getIntent().getExtras();
        String doctor_id = bundle.getString("Doctor_ID");

        try {

            JSONObject jsonObject = new JSONObject(doctor_id);



            if (!jsonObject.has("Key0")) {
                Log.d("OK", "Doctor not found");
                //error.setText("Doctor not Found.... Try Again");
                Toast.makeText(this, "Try Again...", Toast.LENGTH_SHORT).show();

            } else {


                for (int i = 0; i < jsonObject.length() - 1; i++) {
                    String key = "Key" + String.valueOf(i);
                    Log.d("Hiiii2", key);
                    String cid,pid, patient_name,did,problem, medicine, suggetion,allergy;

                    JSONObject obj = jsonObject.getJSONObject(key);
                    Log.d("Hiiii3", obj.toString());

                    cid=obj.getString("Consultation_ID");
                    pid = obj.getString("Patient_ID");
                    patient_name = obj.getString("Patient_Name");
                    did = obj.getString("Doctor_ID");
                    problem = obj.getString("Problem");
                    medicine = obj.getString("Additional_Info_Medicine");
                    allergy = obj.getString("Additional_Info_Allergy");
                    suggetion = obj.getString("Suggetion");

                   // Log.d("OK", name + qualification + fees);
                    cid_Array.add(cid);
                    pid_Array.add(pid);
                    patientnameArray.add(patient_name);
                    did_Array.add(did);
                    problemArray.add(problem);
                    medicineArray.add(medicine);
                    allergyArray.add(allergy);
                    suggetionArray.add(suggetion);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        CustomAdapter1 customAdapter1 = new CustomAdapter1(getApplicationContext(), patientnameArray,problemArray);
        // specializationArray, hospital_addressArray, hospital_nameArray);
        simpleList.setAdapter(customAdapter1);


        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //  Object item = adapterView.getItemAtPosition(i);
                //  Log.d("ok hiiiiii", item.toString());

                long v = adapterView.getItemIdAtPosition(i);

                Log.d("ok hiiiiii", Long.toString(v));
                Log.d("cid", cid_Array.get(Integer.parseInt(Long.toString(v))));
                Log.d("pid", pid_Array.get(Integer.parseInt(Long.toString(v))));
                Log.d("pname", patientnameArray.get(Integer.parseInt(Long.toString(v))));
                Log.d("did", did_Array.get(Integer.parseInt(Long.toString(v))));
                Log.d("problem", problemArray.get(Integer.parseInt(Long.toString(v))));
                Log.d("medicine", medicineArray.get(Integer.parseInt(Long.toString(v))));
                Log.d("allergy", allergyArray.get(Integer.parseInt(Long.toString(v))));
                Log.d("suggetion", suggetionArray.get(Integer.parseInt(Long.toString(v))));



//                String str = (String)item; //As you are using Default String Adapter
                //    Toast.makeText(getBaseContext(),str.getTitle(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Consultation.this, Consult_Patient.class);

                intent.putExtra("cid", cid_Array.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("pid", pid_Array.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("pname", patientnameArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("did", did_Array.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("problem", problemArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("medicine", medicineArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("allergy", allergyArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("suggetion", suggetionArray.get(Integer.parseInt(Long.toString(v))));

                startActivity(intent);
                finish();
            }
        });

    }
}
