package com.example.bikerental;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bikerental.Adapter.BookingsAdapter;
import com.example.bikerental.Model.BookingDetailsSchema;
import com.example.bikerental.Model.ItemModel;
import com.example.bikerental.Remote.ApiService;
import com.example.bikerental.Remote.ApiUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingDetailsActivity extends AppCompatActivity {
    Button btCancel;
    BookingDetailsSchema bookingItem;
    TextView tvPickupDate, tvReturnDate, tvLocation, tvPrice, tvMileage, tvDetailName, tvDetailBrand, tvRegYearData, tvBikeDescription, tvWeightData, tvCcData;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        bookingItem = (BookingDetailsSchema) getIntent().getSerializableExtra("BookingItem");

        getSupportActionBar().setTitle("Booking Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvPickupDate = findViewById(R.id.tvPickupDate);
        tvPrice = findViewById(R.id.tvPrice);
        tvDetailBrand = findViewById(R.id.tvDetailBrand);
        tvDetailName = findViewById(R.id.tvDetailName);
        tvMileage = findViewById(R.id.tvMileageData);
        tvRegYearData = findViewById(R.id.tvRegYearData);
        tvBikeDescription = findViewById(R.id.tvBikeDescription);
        tvWeightData = findViewById(R.id.tvWeightData);
        tvCcData = findViewById(R.id.tvCcData);
        tvReturnDate = findViewById(R.id.tvReturnDate);
        tvLocation = findViewById(R.id.tvLocation);

        //     id = item.getBdId();
        tvPrice.setText("Rs " + bookingItem.getPrice().toString() + " / Per Day");
        tvDetailName.setText(bookingItem.getBikeName());
        tvDetailBrand.setText(bookingItem.getBrandName());
        tvMileage.setText(bookingItem.getBrandName());
        tvPickupDate.setText(bookingItem.getFromDate());
        tvRegYearData.setText(bookingItem.getYear().toString());
        tvWeightData.setText(bookingItem.getWeight().toString());
        tvCcData.setText(bookingItem.getCc().toString());
        tvBikeDescription.setText(bookingItem.getDescription());
        tvReturnDate.setText(bookingItem.getToDate());
        tvLocation.setText(bookingItem.getLocation());

        //progress bar
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Canceling...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);


        btCancel = findViewById(R.id.btCancel);

        if (bookingItem.getStatus() != 0) {
            btCancel.setText("Booked");
            btCancel.setBackgroundResource(R.drawable.rounded_button_green);
        }

        if (bookingItem.getStatus() == 0) {
            btCancel.setText("Cancel");
        } else if (bookingItem.getStatus() == 1) {
            btCancel.setText("Booked");
            btCancel.setBackgroundResource(R.drawable.rounded_button_green);
        } else if (bookingItem.getStatus() == 2) {
            btCancel.setText("Completed");
            btCancel.setBackgroundResource(R.drawable.rounded_button_accent);
        } else {
            btCancel.setText("Canceled");
            btCancel.setBackgroundResource(R.drawable.rounded_button_red);
        }

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bookingItem.getStatus() == 0) {
                    mProgress.show();
                    changeBookingStatus(bookingItem.getBookingId(), 3);
                }

                //       Toast.makeText(BookingDetailsActivity.this, "Booking Cancelled", Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void changeBookingStatus(int bookingid, int status) {
        ApiService apiService = ApiUtil.getApiService();
        apiService.changeBookingStatus(bookingid, status).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("ItemListActivity", "status code =" + response.code());
                mProgress.dismiss();
                if (response.code() == 200) {
                    String s = null;
                    try {
                        s = response.body().string();
                        JSONObject a = new JSONObject(s);
                        Boolean success = a.getBoolean("success");
                        if (success) {
                            Toast.makeText(BookingDetailsActivity.this, "Booking Cancelled", Toast.LENGTH_SHORT).show();
                            btCancel.setText("Canceled");
                            btCancel.setBackgroundResource(R.drawable.rounded_button_red);
                        }else{
                            Toast.makeText(BookingDetailsActivity.this, "Error canceling booking please contact our support", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mProgress.dismiss();
                    Toast.makeText(BookingDetailsActivity.this, "Error canceling booking on failure", Toast.LENGTH_SHORT).show();

            }
        });
    }


}