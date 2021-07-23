package com.example.bikerental.Remote;

import android.content.ClipData;

import com.example.bikerental.Model.BookingDetailsSchema;
import com.example.bikerental.Model.BookingsModel;
import com.example.bikerental.Model.ItemModel;
import com.example.bikerental.Model.LoginResponse;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

//    @FormUrlEncoded
//    @POST("auth/login")
//    Call<ResponseBody> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseBody> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/register")
    Call<ResponseBody> register(@Field("name") String name,@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/category")
    Call<ResponseBody> category(@Field("cat_name") String cat_name,@Field("cat_image") String cat_image, @Field("status") String status);

    @GET("api/bikedetails")
    Call<List<ItemModel>> getBike();

    @GET("api/bikebooking")
    Call<List<BookingsModel>> getBooking();

    @FormUrlEncoded
    @POST("api/insertbikebooking")
    Call<ResponseBody> book(@Field("bd_id") int bd_id,
                            @Field("user_id") int user_id,
                            @Field("from_date") String from_date,
                            @Field("to_date") String to_date,
                            @Field("location") String location,
                            @Field("status") int status
    );

    @GET("api/bikebooking/{id}")
    Call<List<BookingDetailsSchema>> getBookingByUserId(
            @Path("id") int id
    );

    @PUT("api/bikebooking")
    Call<ResponseBody> changeBookingStatus(
            @Query("bookingid") int id,
            @Query("status") int statusid
    );

//    @FormUrlEncoded
//    @POST("api/login")
//    Call<ResponseBody> login(@Field("name") String name,@Field("email") String email, @Field("password") String password);


}
