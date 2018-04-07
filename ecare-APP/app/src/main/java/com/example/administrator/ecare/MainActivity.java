package com.example.administrator.ecare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView t1,t2;
    ImageView i1;
    public ProgressDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=(TextView)findViewById(R.id.tVMain_Quote);
        //t2=(TextView)findViewById(R.id.tVClick_Hear);
        i1=(ImageView)findViewById(R.id.iVMain_Page);


       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               myDialog=ProgressDialog.show(MainActivity.this,"","Loading",true);
               Intent i = new Intent(MainActivity.this,Main1.class);
               startActivity(i);
               myDialog.dismiss();
               MainActivity.this.finish();
           }
       },3000);


    }
    }

