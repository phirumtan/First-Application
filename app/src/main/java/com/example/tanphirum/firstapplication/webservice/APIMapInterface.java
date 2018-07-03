package com.example.tanphirum.firstapplication.webservice;

import com.example.tanphirum.firstapplication.bean.map.DirectionsResultItem;
import com.example.tanphirum.firstapplication.bean.map_api.MapItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIMapInterface {

    //https://maps.googleapis.com/maps/api/directions/json?origin=11.57730710,104.92037940
    // &destination=11.57742590,104.91693970&
    // &waypoints=optimize:true|Providence, ACLEDA Bank Plc. Headquarters (PhnomPenh, Cambodia)
    // &key=AIzaSyC4nlrVmoeTxp25te3KwjuZ6HqCyT5ADOM

    @GET("/maps/api/directions/json")
    Call<MapItem> getMapDirection(@Query("origin") String origin, @Query("destination") String des,
                                  @Query("waypoints") String waypoints,
                                  @Query("key") String key);
}
