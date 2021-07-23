package com.example.bikerental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bikerental.Model.ItemModel;
import com.example.bikerental.Remote.ApiService;
import com.example.bikerental.Remote.ApiUtil;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DateTimeActivity extends AppCompatActivity {

    Button btContinue;
    EditText etPickupDate;
    EditText etLocation;
    EditText etReturnDate;
    DatePickerDialog.OnDateSetListener listener;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        getSupportActionBar().setTitle("Date and Time");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //get user id
        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        int uid = sharedPreferences.getInt("userid",0);

        int bikeid = getIntent().getIntExtra("BikeId",0);

//        Log.v("Sdcx", String.valueOf(bikeid));



        //progress bar
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Booking...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        etLocation = findViewById(R.id.etLocation);
        etReturnDate = findViewById(R.id.etReturnDate);
        etPickupDate = findViewById(R.id.etPickupDate);
        Calendar calendar= Calendar.getInstance();
        final int year= calendar.get(Calendar.YEAR);
        final int month= calendar.get(Calendar.MONTH);
        final int day= calendar.get(Calendar.DAY_OF_MONTH);

        btContinue = findViewById(R.id.btContinue);
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Sd",etPickupDate.getText().toString());
                int status  = 0;
                mProgress.show();
                ApiService apiService = ApiUtil.getApiService();
                apiService.book(bikeid,uid,etPickupDate.getText().toString(),etReturnDate.getText().toString(),etLocation.getText().toString(),status).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        mProgress.dismiss();
                        Log.e("DateTimeActivity", "Status code = " + response.code());

                        if (response.code() == 201 || response.code()== 200) {


                                Toast.makeText(DateTimeActivity.this, "Bike Booked Successfully", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(DateTimeActivity.this, "Error while booking", Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DateTimeActivity.this, "Failed to book bike", Toast.LENGTH_SHORT).show();
                    }
                });


                //startActivity(new Intent(DateTimeActivity.this, BookingDetailsActivity.class));
            }
        });


        etPickupDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(DateTimeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month +"/" +year;
                        etPickupDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });



        etReturnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(DateTimeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" +month+"/" + year;
                        etReturnDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });




    }

}