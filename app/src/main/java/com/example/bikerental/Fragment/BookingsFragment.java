package com.example.bikerental.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bikerental.Adapter.BookingsAdapter;
import com.example.bikerental.Adapter.ItemAdapter;
import com.example.bikerental.ItemListActivity;
import com.example.bikerental.Model.BookingDetailsSchema;
import com.example.bikerental.Model.BookingsModel;
import com.example.bikerental.Model.ItemModel;
import com.example.bikerental.R;
import com.example.bikerental.Remote.ApiService;
import com.example.bikerental.Remote.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class BookingsFragment extends Fragment {

    RecyclerView recyclerView;
    List<BookingDetailsSchema> bookingList;
    private ProgressDialog mProgress;
    Context cntx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bookings, container, false);
        recyclerView = view.findViewById(R.id.recyclerBookings);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SharedPreferences sharedPreferences = cntx.getSharedPreferences("UserInfo", MODE_PRIVATE);
        int uid = sharedPreferences.getInt("userid", 0);
        getBooking(uid);

        //progress bar
        mProgress = new ProgressDialog(cntx);
        mProgress.setTitle("Loading...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);
        mProgress.show();
//        recyclerView.setAdapter(new BookingsAdapter(initData(), cntx));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.cntx = context;
    }


    private void getBooking(int id) {
        ApiService apiService = ApiUtil.getApiService();
        apiService.getBookingByUserId(id).enqueue(new Callback<List<BookingDetailsSchema>>() {
            @Override
            public void onResponse(Call<List<BookingDetailsSchema>> call, Response<List<BookingDetailsSchema>> response) {
                Log.e("ItemListActivity", "status code =" + response.code());
                mProgress.dismiss();
                if (response.code() == 200) {
                    bookingList = response.body();
                    BookingsAdapter adapter = new BookingsAdapter(bookingList, cntx);
                    recyclerView.setAdapter(adapter);


                }
            }


            @Override
            public void onFailure(Call<List<BookingDetailsSchema>> call, Throwable t) {
                mProgress.dismiss();
                Toast.makeText(getContext(), "Error while fetching bike", Toast.LENGTH_SHORT).show();

            }
        });
    }

}