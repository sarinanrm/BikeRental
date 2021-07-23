package com.example.bikerental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bikerental.Model.LoginResponse;
import com.example.bikerental.Remote.ApiService;
import com.example.bikerental.Remote.ApiUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView tvSignup;
    Button btLogin;
    TextView tvForgotPassword;
    EditText etEmail, etPassword;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME ="mypref";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_EMAIL ="email";
    private static final String KEY_PASSWORD ="password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //remove action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        Boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false);
        if (isLoggedIn){
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }

        tvSignup = findViewById(R.id.tvLogin);
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String password =etPassword.getText().toString();
                if(email.isEmpty()){
                    etEmail.requestFocus();
                    etEmail.setError("Enter your email first");
                    return;
                }
                if(password.isEmpty()){
                    etPassword.requestFocus();
                    etPassword.setError("Enter your password first");
                    return;
                }

                login();
//                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });

        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });

    }
    private void login(){
//        Toast.makeText(LoginActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
        ApiService apiService = ApiUtil.getApiService();
        apiService.login(etEmail.getText().toString(),etPassword.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("LOGINACTIVITY", "Status code = " + response.code());

                if (response.code() == 201 || response.code()== 200) {

                    String s = null;
                    try {
                        s = response.body().string();

                        //convert data string to json object
                        JSONObject responseBody = new JSONObject(s);
                        //get message
                        String user = responseBody.getString("user");
                        String token = responseBody.getString("token");
//
//                        Log.v("SDc",user.toString());
//                        Log.v("SDc",token.toString());

                        //get user object
                        JSONObject u = new JSONObject(user);

                        // Storing data into SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putBoolean("isLoggedIn", true);
                        myEdit.putInt("userid", u.getInt("id"));
                        myEdit.putString("email", u.getString("email"));
                        myEdit.putString("name", u.getString("name"));

                        myEdit.commit();


                       // String token = responseBody.getString("token");
//                        //get result object
//                        JSONObject result = new JSONObject(responseBody.getString("result"));




                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
//                    // Storing data into SharedPreferences
//                    SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
//                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
//

//
//                    myEdit.putBoolean("isLoggedIn", true);
//                    myEdit.putString("email", LoginResponse);
//                    myEdit.putString("token", token);
//                    myEdit.commit();
//
//
//                            editor.putString(KEY_USER_ID, response.body().getUser().getId().toString());
//                            editor.putString(KEY_EMAIL, response.body().getUser().getEmail());
//                            editor.putString(KEY_PASSWORD, etPassword.getText().toString());
//                            editor.apply();

//                            editor.putString(KEY_USER_ID, response.body().getUser().getId().toString());
//                            editor.putString(KEY_EMAIL,etEmail.getText().toString());
//                            editor.putString(KEY_PASSWORD,etPassword.getText().toString());
//                            JSONObject object=null;

//                                Log.d("LoginActivity", response.toString());


                      //      editor.apply();


                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                            finish();


                        }else{
                            Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();

                        }


                    }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


