package com.example.proyectofinal.api;

import com.example.proyectofinal.network.UserEntry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {

    @GET("users")
    Call<List<UserEntry>> getUsers();

    @GET("users/{id}")
    Call<List<UserEntry>> getUser(@Path("id") int userId);
    //Probar con UserEntry o con List<UserEntry>
}
