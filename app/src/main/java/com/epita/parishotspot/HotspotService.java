package com.epita.parishotspot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lyfing on 04/06/2016.
 */
public interface HotspotService {

    public static final String ENDPOINT = "http://www.tutos-android.com/MTI/2017";

    @GET("/list_hot_spots.json")
    Call<Hotspot> loadHotspots();
}
