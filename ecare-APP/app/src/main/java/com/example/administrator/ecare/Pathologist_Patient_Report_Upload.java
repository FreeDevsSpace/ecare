package com.example.administrator.ecare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class Pathologist_Patient_Report_Upload extends AppCompatActivity {
    TextView p_id,upload_report,clickhere_upload_report,payment;
    Button bycash,online,done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathologist_patient_report_upload);

        p_id=(TextView)findViewById(R.id.tVPatient_ID_Pathologist_Patient_Report_Upload);
        upload_report=(TextView)findViewById(R.id.tVUpload_Report_Pathologist_Patient_Report_Upload);
        clickhere_upload_report=(TextView)findViewById(R.id.tVClickHere_Upload_Report_Pathologist_Patient_Report_Upload);
        payment=(TextView)findViewById(R.id.tVPayment_Pathologist_Patient_Report_Upload);

        bycash=(Button)findViewById(R.id.btCash_Pathologist_Patient_Report_Upload);
        online=(Button)findViewById(R.id.btOnline_Pathologist_Patient_Report_Upload);
        done=(Button)findViewById(R.id.btDone_Pathologist_Patient_Report_Upload);
    }
}
