
package com.example.administrator.ecare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Doctor_Profile extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;
    TextView PID, PName, PMobileNumber, PDateOfBirth, PQualification, PAddress, PBloodGroup;

    ImageView iv1;
    ImageButton ib_camera;
    ImageView ivselectimage;


    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    private String userChoosenTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        Bundle b = getIntent().getExtras();
        String id = b.getString("Doctor_ID");
        String name = b.getString("Full_Name");
        String mobile_number = b.getString("Mobile_Number");
        String dob = b.getString("DOB");
        String qualification = b.getString("Qualification");
      //  String address = b.getString("Address");
        String blood_group = b.getString("Blood_Group");


        //  tv1 = (TextView) findViewById(R.id.tVLogOut_Doctor_Profile);
        tv2 = (TextView) findViewById(R.id.tVName_Doctor_Profile);
        tv3 = (TextView) findViewById(R.id.tVMobile_Number_Doctor_Profile);
        tv5 = (TextView) findViewById(R.id.tVDOB_Doctor_Profile);
        tv6 = (TextView) findViewById(R.id.tVQualification_Doctor_Profile);
      //  tv7 = (TextView) findViewById(R.id.tVAddress_Doctor_Profile);
        //tv8 = (TextView) findViewById(R.id.tVBlood_Group_Doctor_Profile);
        tv9 = (TextView) findViewById(R.id.tVDoctor_ID_Doctor_Profile);
     //   iv1 = (ImageView) findViewById(R.id.IVProfile_Icon_Doctor_Profile);
        //ib_camera = (ImageButton) findViewById(R.id.IBComera_Doctor_Profile);

        PID = (TextView) findViewById(R.id.tVP_ID_Doctor_Profile);
        PName = (TextView) findViewById(R.id.tVP_Name_Doctor_Profile);
        PMobileNumber = (TextView) findViewById(R.id.tVP_MobileNumber_Doctor_Profile);
        PDateOfBirth = (TextView) findViewById(R.id.tVP_DateOfBirth_Doctor_Profile);
        PQualification = (TextView) findViewById(R.id.tVP_Qualification_Doctor_Profile);
      //  PAddress = (TextView) findViewById(R.id.tVP_Address_Doctor_Profile);
        //   PBloodGroup=(TextView)findViewById(R.id.tVP_BloodGroup_Doctor_Profile);


        PID.setText(id);
        PName.setText(name);
        PMobileNumber.setText(mobile_number);
        PDateOfBirth.setText(dob);
        PQualification.setText(qualification);
      //  PAddress.setText(address);
        //  PBloodGroup.setText(blood_group);

        //  ivselectimage = (ImageView) findViewById(R.id.IVProfile_Icon_Doctor_Profile);


/*        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Doctor_Profile.this, Main1.class);
                startActivity(i1);
            }
        }); */
    }
}

    /*    ib_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();





            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(Doctor_Profile.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(Doctor_Profile.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//

        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ivselectimage.setImageBitmap(thumbnail);
        ib_camera.setVisibility(View.INVISIBLE);

    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ivselectimage.setImageBitmap(bm);
        ib_camera.setVisibility(View.INVISIBLE);


    }
}

*/
