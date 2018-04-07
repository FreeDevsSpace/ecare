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

public class Patient_Search_List_Action extends AppCompatActivity {

    ListView simpleList;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search_list_action);

        simpleList = (ListView) findViewById(R.id.ListView_Patient_Search_List_Action);
        error = (TextView) findViewById(R.id.Error_Patient_Search_list_Action);


        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("data");

        //  ArrayList<String[]> stringArray = new ArrayList<String[]>();

        final ArrayList<String> didArray = new ArrayList<String>();
        final ArrayList<String> nameArray = new ArrayList<String>();
        final ArrayList<String> qualificationArray = new ArrayList<String>();
        final ArrayList<String> feesArray = new ArrayList<String>();
        final ArrayList<String> specializationArray = new ArrayList<String>();
        final ArrayList<String> hospital_nameArray = new ArrayList<String>();
        final ArrayList<String> hospital_addressArray = new ArrayList<String>();

        try {
            JSONObject jsonObject = new JSONObject(value);
            Log.d("Hiiii1", jsonObject.toString());

            if (!jsonObject.has("Key0")) {
                Log.d("OK", "Doctor not found");
                error.setText("Doctor not Found.... Try Again");
                Toast.makeText(this, "Try Again...", Toast.LENGTH_SHORT).show();

            } else {


                for (int i = 0; i < jsonObject.length() - 1; i++) {
                    String key = "Key" + String.valueOf(i);
                    Log.d("Hiiii2", key);
                    String did,name, qualification, fees, specialization, hospital_address, hospital_name;

                    JSONObject obj = jsonObject.getJSONObject(key);
                    Log.d("Hiiii3", obj.toString());
                    did=obj.getString("Doctor_ID");
                    name = obj.getString("Full_Name");
                    qualification = obj.getString("Qualification");
                    fees = obj.getString("Fees");
                    specialization = obj.getString("Specialization");
                    hospital_address = obj.getString("Hospital_Address");
                    hospital_name = obj.getString("Hospital_Name");

                    Log.d("OK", name + qualification + fees);
                    didArray.add(did);
                    nameArray.add(name);
                    qualificationArray.add(qualification);
                    feesArray.add(fees);
                    specializationArray.add(specialization);
                    hospital_addressArray.add(hospital_address);
                    hospital_nameArray.add(hospital_name);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), nameArray, qualificationArray, feesArray);
               // specializationArray, hospital_addressArray, hospital_nameArray);
        simpleList.setAdapter(customAdapter);


        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

              //  Object item = adapterView.getItemAtPosition(i);
              //  Log.d("ok hiiiiii", item.toString());

                long v = adapterView.getItemIdAtPosition(i);

                Log.d("ok hiiiiii", Long.toString(v));
                Log.d("name", nameArray.get(Integer.parseInt(Long.toString(v))));
                Log.d("qualification", qualificationArray.get(Integer.parseInt(Long.toString(v))));
                Log.d("fees", feesArray.get(Integer.parseInt(Long.toString(v))));
                Log.d("specialization", specializationArray.get(Integer.parseInt(Long.toString(v))));
                Log.d("hospital_address", hospital_addressArray.get(Integer.parseInt(Long.toString(v))));
                Log.d("hospital_name", hospital_nameArray.get(Integer.parseInt(Long.toString(v))));



//                String str = (String)item; //As you are using Default String Adapter
                //    Toast.makeText(getBaseContext(),str.getTitle(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Patient_Search_List_Action.this, Request_To_Connect_Doctor.class);

                intent.putExtra("did", didArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("name", nameArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("qualification", qualificationArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("fees", feesArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("specialization", specializationArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("hospital_address", hospital_addressArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("hospital_name", hospital_nameArray.get(Integer.parseInt(Long.toString(v))));

                startActivity(intent);
                finish();
            }
        });
    }

}



 /*   b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Patient_Search_List_Action.this,Request_To_Connect_Doctor.class);
                startActivity(i1);
            }
        }); */

