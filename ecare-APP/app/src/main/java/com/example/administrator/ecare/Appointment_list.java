package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Appointment_list extends AppCompatActivity {

    ListView simpleList;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);

        simpleList = (ListView) findViewById(R.id.ListView_Appointment_List);
        error = (TextView) findViewById(R.id.Error_Appointment_List);

        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("key");

        final ArrayList<String> appidArray = new ArrayList<String>();
        final ArrayList<String> pidArray = new ArrayList<String>();
        final ArrayList<String> didArray = new ArrayList<String>();
        final ArrayList<String> dateArray = new ArrayList<String>();
        final ArrayList<String> timesloteArray = new ArrayList<String>();


        try {

            JSONObject jsonObject = new JSONObject(value);
            Log.d("Hiiii1", jsonObject.toString());

            if (!jsonObject.has("Key0")) {
                Log.d("OK", "Doctor not found");
                error.setText("No Appointment Request");
                Toast.makeText(this, "Try Again...", Toast.LENGTH_SHORT).show();

            } else {


                for (int i = 0; i < jsonObject.length() - 1; i++) {
                    String key = "Key" + String.valueOf(i);
                    Log.d("Hiiii2", key);
                    String appid, pid, did, date, timeslote;

                    JSONObject obj = jsonObject.getJSONObject(key);
                    Log.d("Hiiii3", obj.toString());
                    appid = obj.getString("Appointment_ID");
                    pid = obj.getString("Patient_ID");
                    did = obj.getString("Doctor_ID");
                    date = obj.getString("Date");
                    timeslote = obj.getString("Timeslote");


                    Log.d("OK", appid + pid + did + date + timeslote);

                    appidArray.add(appid);
                    pidArray.add(pid);
                    didArray.add(did);
                    dateArray.add(date);
                    timesloteArray.add(timeslote);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        CustomAdapter2 customAdapter = new CustomAdapter2(getApplicationContext(), pidArray, dateArray, timesloteArray);
        simpleList.setAdapter(customAdapter);


        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                long v = adapterView.getItemIdAtPosition(i);


//                String str = (String)item; //As you are using Default String Adapter
                //    Toast.makeText(getBaseContext(),str.getTitle(),Toast.LENGTH_SHORT).show();

           /*     Intent intent = new Intent(Appointment_list.this, Appointment_Accept_Reject.class);

                intent.putExtra("appid", appidArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("pid", pidArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("did", didArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("date", dateArray.get(Integer.parseInt(Long.toString(v))));
                intent.putExtra("timeslote", timesloteArray.get(Integer.parseInt(Long.toString(v))));

                startActivity(intent);
                finish(); */
            }
        });
    }
}

