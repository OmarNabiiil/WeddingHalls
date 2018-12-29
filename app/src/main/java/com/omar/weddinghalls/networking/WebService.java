package com.omar.weddinghalls.networking;

import com.omar.weddinghalls.HallItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {

    @GET("halls.json")
    Call<List<HallItem>> getHalls();
}
