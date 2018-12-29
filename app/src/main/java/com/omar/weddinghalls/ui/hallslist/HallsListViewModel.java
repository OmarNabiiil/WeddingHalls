package com.omar.weddinghalls.ui.hallslist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.omar.weddinghalls.HallItem;
import com.omar.weddinghalls.HallsListRepository;

import java.util.List;

public class HallsListViewModel extends ViewModel {

    private HallsListRepository mRepository;
    private LiveData<List<HallItem>> hallsItems;

    public HallsListViewModel() {
        mRepository = new HallsListRepository();
    }

    public LiveData<List<HallItem>> getHallsItems() {

        if(hallsItems == null){
            hallsItems = mRepository.getHalls();
        }
        return hallsItems;
    }
}
