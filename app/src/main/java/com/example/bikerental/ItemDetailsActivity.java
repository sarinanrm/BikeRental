package com.example.bikerental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bikerental.Adapter.ItemAdapter;
import com.example.bikerental.Model.ItemModel;

import java.util.List;

public class ItemDetailsActivity extends AppCompatActivity {
    Button btBook;
    ItemModel item;
    TextView tvPrice, tvMileage,tvDetailName, tvDetailBrand, tvRegYearData,tvBikeDescription,tvWeightData,tvCcData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        Intent intent = getIntent();
        int id;
        getSupportActionBar().setTitle("Item Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        item = (ItemModel) getIntent().getSerializableExtra("Item");


        tvPrice= findViewById(R.id.tvPrice);
        tvDetailBrand= findViewById(R.id.tvDetailBrand);
        tvDetailName= findViewById(R.id.tvDetailName);
        tvMileage=findViewById(R.id.tvMileageData);
        tvRegYearData=findViewById(R.id.tvRegYearData1);
        tvBikeDescription=findViewById(R.id.tvBikeDescription);
        tvWeightData=findViewById(R.id.tvWeightData1);
        tvCcData=findViewById(R.id.tvCcData);

        id = item.getBdId();
        tvPrice.setText("Rs "+item.getPrice().toString() +" / Day");

//        tvPrice.setText(item.getPrice().toString());
        tvDetailName.setText(item.getBikeName());
        tvDetailBrand.setText(item.getBrandName());
        tvMileage.setText(item.getBrandName());
        tvRegYearData.setText(item.getYear().toString());
        tvWeightData.setText(item.getWeight().toString());
        tvCcData.setText(item.getCc().toString());
        tvBikeDescription.setText(item.getDescription());


        btBook = findViewById(R.id.btBook);
        btBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.v("sfved", String.valueOf(uid));
               Intent intent= new Intent(ItemDetailsActivity.this, DateTimeActivity.class);
               intent.putExtra("BikeId", id);
                startActivity(intent);
//                dialog.show();
            }
        });
    }
}