package com.omar.weddinghalls;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.omar.weddinghalls.networking.WebClient;
import com.omar.weddinghalls.networking.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HallsListRepository {
    private static final String TAG = HallsListRepository.class.getSimpleName();

    private WebService webClient;

    public HallsListRepository() {
        webClient = WebClient.getClient().create(WebService.class);
    }

    public LiveData<List<HallItem>> getHalls() {
        final MutableLiveData<List<HallItem>> data = new MutableLiveData<>();

        Call<List<HallItem>> call = webClient.getHalls();
        call.enqueue(new Callback<List<HallItem>>() {
            @Override
            public void onResponse(Call<List<HallItem>> call, Response<List<HallItem>> response) {
                data.setValue(response.body());
            }

            // TODO - handle error
            // https://stackoverflow.com/questions/44208618/how-to-handle-error-states-with-livedata
            @Override
            public void onFailure(Call<List<HallItem>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return data;
    }
}
