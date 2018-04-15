package com.example.administrator.ecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Patient_Consultation_Status_Store extends AppCompatActivity {

    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_consultation_status_store);

        TextView consultation_id = (TextView) findViewById(R.id.tVConsultation_ID_Listview_Consultation_Status_Store);
        TextView pconsultation_id = (TextView) findViewById(R.id.tVPConsultation_ID_Listview_Consultation_Status_Store);
        TextView doctor_name = (TextView) findViewById(R.id.tVDoctor_Name_Listview_Consultation_Status_Store);
        TextView pdoctor_name = (TextView) findViewById(R.id.tVPDoctor_Name_Listview_Consultation_Status_Store);
        TextView problem = (TextView) findViewById(R.id.tVProblem_Listview_Consultation_Status);
        TextView pproblem = (TextView) findViewById(R.id.tVPProblem_Listview_Consultation_Status);
        TextView addi_info_medicine = (TextView) findViewById(R.id.tVAdditional_Info_Medicine_Listview_Consultation_Status_Store);
        TextView paddi_info_medicine = (TextView) findViewById(R.id.tVPAdditional_Info_Medicine_Listview_Consultation_Status_Store);
        TextView addi_info_allergy = (TextView) findViewById(R.id.tVAdditional_Info_Allergy_Listview_Consultation_Status_Store);
        TextView paddi_info_allergy = (TextView) findViewById(R.id.tVPAdditional_Info_Allergy_Listview_Consultation_Status_Store);
        TextView suggetion = (TextView) findViewById(R.id.tVSuggetion_Listview_Consultation_Status_Store);
        TextView psuggetion = (TextView) findViewById(R.id.tVPSuggetion_Listview_Consultation_Status_Store);
        TextView prescribe_medicine = (TextView) findViewById(R.id.tVPrescribe_Medicine_Listview_Consultation_Status_Store);
        TextView pprescibe_medicine = (TextView) findViewById(R.id.tVPPrescribe_Medicine_Listview_Consultation_Status_Store);


        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        Bundle b = getIntent().getExtras();

        String cid = b.get("Consultation_ID").toString();
        String dname = b.get("Doctor_Name").toString();
        String problem1 = b.get("Problem").toString();
        String addi_medi = b.get("Additional_Info_Medicine").toString();
        String addi_aller = b.get("Additional_Info_Allergy").toString();
        String suggetion1 = b.get("Suggetion").toString();
        String pre_medi = b.get("Prescribe_Medicine").toString();

        pconsultation_id.setText(cid);
        pdoctor_name.setText(dname);
        pproblem.setText(problem1);
        paddi_info_medicine.setText(addi_medi);
        paddi_info_allergy.setText(addi_aller);
        psuggetion.setText(suggetion1);
        pconsultation_id.setText(cid);
        pprescibe_medicine.setText(pre_medi);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(Patient_Consultation_Status_Store.this, Patient_Main.class);
        startActivity(i);
        finish();


    }
}
