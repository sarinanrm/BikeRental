package com.example.bikerental.Remote;

public class ApiUtil {
//  /  public static String baseUrl="http://bikerent.oxfordcollege.edu.np/";
    public static String baseUrl="http://192.168.10.104:8000/";

//private static final String baseUrl ="https://bca-ecommerce-project.herokuapp.com/api/v1/";
    public static ApiService getApiService(){
        return RetrofitClient.getRetrofitClient(baseUrl).create(ApiService.class);
    }
}
