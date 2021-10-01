package com.example.apitest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
//    private static final String URL = "http://10.0.2.2:8080/TECJavaAPI/api/";
    private static final String URL = "http://10.0.2.2:8080/TECJavaAPI/api/";
    private static final Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory((GsonConverterFactory.create())).build();
    public static <S> S buildService(Class <S> serviceType) {
        return retrofit.create(serviceType);
    }

}
