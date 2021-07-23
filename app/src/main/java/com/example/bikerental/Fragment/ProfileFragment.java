package com.example.bikerental.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bikerental.AboutUsActivity;
import com.example.bikerental.ContactInfoActivity;
import com.example.bikerental.LoginActivity;
import com.example.bikerental.R;
import com.example.bikerental.UserInformationActivity;

public class ProfileFragment extends Fragment {

    private static final int GALLERY_REQUEST_CODE = 123;
    private static final int RESULT_OK = 1;

    TextView myInformation;
    TextView aboutUs;
    TextView contactUs;
    ImageView ivUserPhoto;
    TextView tvLogout, tvEmail;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME ="UserInfo";
    private static final String KEY_EMAIL ="email";
    private static final String KEY_PASSWORD ="password";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        tvEmail = view.findViewById(R.id.tvEmail);
        tvLogout = view.findViewById(R.id.logout);

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL,null);
        if(email != null ){
            tvEmail.setText(email);
        }
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getActivity(),"Logout Successfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();


            }
        });



        ivUserPhoto = view.findViewById(R.id.ivUserPhoto);
        ivUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Gallery"),GALLERY_REQUEST_CODE);
            }
        });

        myInformation = view.findViewById(R.id.myInformation);
        myInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserInformationActivity.class));
            }
        });

        aboutUs = view.findViewById(R.id.aboutUs);
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
            }
        });

        contactUs = view.findViewById(R.id.contactUs);
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ContactInfoActivity.class));
            }
        });
        return view;




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data!= null){
            Uri imageData = data.getData();
            ivUserPhoto.setImageURI(imageData);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}