package com.example.bikerental;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class UserInformationActivity extends AppCompatActivity {

private static final int GALLERY_REQUEST_CODE =123;

    ImageView ivLicenseFront;
    ImageView ivLicenseBack;
    ImageView ivIdFront;
    ImageView ivIdBack;

    EditText etFullName, etPhone, etAddress, etRelation, etEmergencyPhone;
    Button btUpdate;

    Spinner spnGender;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME ="mypref";
    private static final String KEY_FULL_NAME ="fullName";
    private static final String KEY_PHONE ="phone";
    private static final String KEY_ADDRESS ="address";
    private static final String KEY_RELATION ="relation";
    private static final String KEY_EMERGENCY_PHONE ="emergencyPhone";
    private static final String KEY_GENDER ="gender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        getSupportActionBar().setTitle("My Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        spnGender= findViewById(R.id.etGender);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.genderList,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnGender.setAdapter(adapter);

        etFullName = findViewById(R.id.etFullName);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        etRelation = findViewById(R.id.etRelation);
        etEmergencyPhone = findViewById(R.id.etEmergencyPhone);
        btUpdate = findViewById(R.id.btUpdate);


        ivLicenseFront = findViewById(R.id.ivLicenseFront);
        ivLicenseFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Gallery"),GALLERY_REQUEST_CODE);
            }
        });

        ivLicenseBack = findViewById(R.id.ivLicenseBack);
        ivLicenseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto,1);

            }
        });

        ivIdFront = findViewById(R.id.ivIdFront);
        ivIdFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto,1);

            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                validateUserInformation();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_FULL_NAME,etFullName.getText().toString());
                editor.putString(KEY_ADDRESS,etAddress.getText().toString());
                editor.putString(KEY_PHONE,etPhone.getText().toString());
                editor.putString(KEY_RELATION,etRelation.getText().toString());
                editor.putString(KEY_EMERGENCY_PHONE,etEmergencyPhone.getText().toString());

                editor.apply();
                Toast.makeText(UserInformationActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();


            }
        });

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String fullName = sharedPreferences.getString(KEY_FULL_NAME,null);
        String address = sharedPreferences.getString(KEY_ADDRESS,null);
        String phone = sharedPreferences.getString(KEY_PHONE,null);
        String relation = sharedPreferences.getString(KEY_RELATION,null);
        String emergencyPhone = sharedPreferences.getString(KEY_EMERGENCY_PHONE,null);
        etFullName.setText(fullName);
        etAddress.setText(address);
        etPhone.setText(phone);
        etRelation.setText(relation);
        etEmergencyPhone.setText(emergencyPhone);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data!= null){
            Uri imageData = data.getData();
            ivLicenseFront.setImageURI(imageData);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

private void validateUserInformation(){
    String fullName = etFullName.getText().toString();
    String phone = etPhone.getText().toString();
    String address = etAddress.getText().toString();
    String relation = etRelation.getText().toString();
    String emergencyPhone = etEmergencyPhone.getText().toString();

    if(fullName.isEmpty()){
        etFullName.requestFocus();
        etFullName.setError("Enter your name");
        return;
    }
    if(phone.isEmpty()){
        etPhone.requestFocus();
        etPhone.setError("Enter your phone");
        return;
    }
    if(address.isEmpty()){
        etAddress.requestFocus();
        etAddress.setError("Enter your address");
        return;
    }
    if(relation.isEmpty()){
        etRelation.requestFocus();
        etRelation.setError("Enter your relation with emergency contact");
        return;
    }
    if(emergencyPhone.isEmpty()){
        etEmergencyPhone.requestFocus();
        etEmergencyPhone.setError("Enter your emergency phone number");
        return;
    }



}

}