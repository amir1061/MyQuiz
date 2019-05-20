package com.parkpoint.ubexquiz;


import com.parkpoint.ubexquiz.Model.ShipmentStatus;
import com.parkpoint.ubexquiz.Model.Shipments;
import com.parkpoint.ubexquiz.Model.User;
import com.parkpoint.ubexquiz.Utilities.Constants;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @POST("login")
    Observable <User> performUserLogin(@Query("username") String UserName, @Query("password") String UserPassword);

    @GET("list")
    Observable  <ShipmentStatus> GetShipments(@Query("token") String token);
}
