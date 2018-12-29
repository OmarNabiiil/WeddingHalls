package com.omar.weddinghalls.ui.hallslist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omar.weddinghalls.HallItem;
import com.omar.weddinghalls.HallsListAdapter;
import com.omar.weddinghalls.R;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HallsListFragment extends Fragment {

    private static final String TAG = HallsListFragment.class.getSimpleName();

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private HallsListViewModel mViewModel;

    Unbinder unbinder;
    HallsListAdapter mAdapter;

    public static HallsListFragment newInstance() {
        return new HallsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.halls_list_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mAdapter = new HallsListAdapter(getActivity());

        // getting ViewModel instance
        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(HallsListViewModel.class);

        // preparing RecyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HallsListViewModel.class);
        // TODO: Use the ViewModel

        // Observing to list items
        mViewModel.getHallsItems().observe(this, new Observer<List<HallItem>>() {
            @Override
            public void onChanged(@Nullable List<HallItem> hallItems) {
                // displaying item in recycler view
                mAdapter.setItems(hallItems);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

}
