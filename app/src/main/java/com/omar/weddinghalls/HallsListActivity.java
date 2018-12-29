package com.omar.weddinghalls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.omar.weddinghalls.ui.hallslist.HallsListFragment;

public class HallsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halls_list_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, HallsListFragment.newInstance())
                    .commitNow();
        }
    }
}
