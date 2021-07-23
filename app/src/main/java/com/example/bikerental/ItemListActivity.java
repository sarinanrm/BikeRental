package com.example.bikerental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.bikerental.Adapter.ItemAdapter;
import com.example.bikerental.Adapter.interfaceItemClick;
import com.example.bikerental.Model.ItemModel;
import com.example.bikerental.Remote.ApiService;
import com.example.bikerental.Remote.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ItemModel> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        getSupportActionBar().setTitle("Items List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.recyclerItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        getBikeList();
//        ArrayList<ItemModel> itemList = new ArrayList<>();

//        itemList.add(new ItemModel(R.drawable.bike_image, "Bajaj Pulsar", "Bajaj", "Rs.1500 / day"));
//        itemList.add(new ItemModel(R.drawable.bike_image, "Bajaj Pulsar", "Bajaj", "Rs.1500 / day"));
//        itemList.add(new ItemModel(R.drawable.bike_image, "Bajaj Pulsar", "Bajaj", "Rs.1500 / day"));
//

//        ItemAdapter adapter = new ItemAdapter(itemList, this);
//        recyclerView.setAdapter(adapter);

    }

    private void getBikeList(){
        ApiService apiService = ApiUtil.getApiService();
        apiService.getBike().enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                Log.e("ItemListActivity","status code =" +response.code());
                Log.v("Drvfs", response.body().toString());
                if (response.code() == 200) {
                    Log.v("Drvfs", response.body().toString());
                    itemList = response.body();
                    ItemAdapter adapter = new ItemAdapter(itemList, ItemListActivity.this);
                    recyclerView.setAdapter(adapter);

//                List<ItemModel>itemList =response.body();

//                ItemAdapter adapter= new ItemAdapter(itemList);

//                for (ItemModel itemModel:itemList){
//                    int k =itemModel.getBd_id();
//                    String price = itemModel.getPrice();

//                    itemList.add(itemModel);
//                }

                }
            }


            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                Log.v("Svfd",t.getMessage().toString());
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }

//    @Override
//    public void ItemClick(int position) {
//        Log.d("ItemListActivity", "Obtained Position" +  ""+ position);
//        ItemModel model = itemList.get(position);
//        int id = model.getBdId();
//
//        Intent intent = new Intent(this, ItemDetailsActivity.class);
//        intent.putExtra("id", id);
//        intent.putExtra("bike_name", model.getBikeName());
//        intent.putExtra("brand_name", model.getBrandName());
//        intent.putExtra("Price", model.getPrice());
//        intent.putExtra("Mileage", model.getMileage());
//        intent.putExtra("Weight", model.getWeight());
//        intent.putExtra("year", model.getYear());
////        intent.putExtra("Description", model.getDescription());
//

//
//        startActivity(intent);
    }

//}
