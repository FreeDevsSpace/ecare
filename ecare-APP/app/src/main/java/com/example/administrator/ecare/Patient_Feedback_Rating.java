package com.example.administrator.ecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class Patient_Feedback_Rating extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    RadioGroup radioGroup1;
    RadioButton r1,r2,r3;
    RatingBar rb1;
    EditText et1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_feedback_rating);
        tv1=(TextView)findViewById(R.id.tVRating_Recommendation_Patient_Feedback_Rating);
        tv2=(TextView)findViewById(R.id.tVFeedback_Patient_Feedback_Rating);
        tv3=(TextView)findViewById(R.id.tVRate_Our_Service_Patient_Feedback_Rating);
        radioGroup1 = (RadioGroup)findViewById(R.id.rGRecommendation_to_Somebody_else_Patient_Feedback_Rating);
        r1=(RadioButton)findViewById(R.id.RBNo_Patient_Feedback_Rating);
        r2=(RadioButton)findViewById(R.id.RBNot_Sure_Patient_Feedback_Rating);
        r3=(RadioButton)findViewById(R.id.RBYes_Patient_Feedback_Rating);
        rb1=(RatingBar)findViewById(R.id.rBSatr_Rate_Our_Service_Patient_Feedback_Rating);
        et1=(EditText)findViewById(R.id.eTFeedback_Write_Patient_Feedback_Rating);
        b1=(Button)findViewById(R.id.btSubmit_Patient_Feedback_Rating);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Patient_Feedback_Rating.this,Patient_Thank_You.class);
                startActivity(i1);
            }
        });


    }
}
