package com.example.apitest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface PersonInterface {
    @GET("person")
    Call<List<Person>> getPersons();

    @GET("person/{id}")
    Call<Person> getPersonById(@Path("id") int id);

    @POST("person")
    Call<Void> createPerson(@Body Person person);

    @PUT("person")
    Call<Void> updatePerson(@Body Person person);

    @DELETE("person/{id}")
    Call<Void> deletePerson(@Path("id") int id);
}
