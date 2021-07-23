package com.example.bikerental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bikerental.Model.LoginResponse;
import com.example.bikerental.Remote.ApiService;
import com.example.bikerental.Remote.ApiUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    TextView btLogNow;
    Button btRegister;
    EditText etFullName, etEmail, etPassword;

//    SharedPreferences sharedPreferences;
//    private static final String SHARED_PREF_NAME ="mypref";
//    private static final String KEY_USER_ID = "userId";
//    private static final String KEY_NAME ="name";
//    private static final String KEY_EMAIL ="email";
//    private static final String KEY_PASSWORD ="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //remove action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btLogNow = findViewById(R.id.tvLogin);

        btLogNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

//        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
//        String email = sharedPreferences.getString(KEY_EMAIL,null);
//        if (email!= null){
//            Intent intent = new Intent(SignupActivity.this,HomeActivity.class);
//            startActivity(intent);
//            finish();
//        }



        etEmail = findViewById(R.id.etEmail);
        etFullName = findViewById(R.id.etFullName);
        etPassword = findViewById(R.id.etPassword);

        btRegister = findViewById(R.id.btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etFullName.getText().toString();
                String email = etEmail.getText().toString();
                String password =etPassword.getText().toString();

                if(name.isEmpty()){
                    etFullName.requestFocus();
                    etFullName.setError("Enter your name");
                    return;
                }
                if(email.isEmpty()){
                    etEmail.requestFocus();
                    etEmail.setError("Enter your email");
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etEmail.requestFocus();
                    etEmail.setError("Enter correct email");
                    return;
                }
                if(password.isEmpty()){
                    etPassword.requestFocus();
                    etPassword.setError("Enter your password");
                    return;
                }
                if(password.length()<8){
                    etPassword.requestFocus();
                    etPassword.setError("Password must be of eight character");
                    return;
                }
                registerUser();


            }
        });


    }
    private  void registerUser(){
        ApiService apiService = ApiUtil.getApiService();
        apiService.register(etFullName.getText().toString(),etEmail.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("SignupActivity","status code =" +response.code());

                if (response.code() == 201 || response.code()== 200) {

                Toast.makeText(SignupActivity.this, "User Created Successfully and Logged In", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                }else{
                    Toast.makeText(SignupActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(SignupActivity.this,"Failed to create user account",Toast.LENGTH_SHORT).show();


            }
        });

    }
}