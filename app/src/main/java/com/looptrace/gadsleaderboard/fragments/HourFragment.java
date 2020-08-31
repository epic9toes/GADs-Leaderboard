package com.looptrace.gadsleaderboard.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.looptrace.gadsleaderboard.R;
import com.looptrace.gadsleaderboard.customAdapters.HourRecyclerAdapter;
import com.looptrace.gadsleaderboard.models.Hour;
import com.looptrace.gadsleaderboard.Repositories.HourRepo;
import com.looptrace.gadsleaderboard.viewmodels.HourViewModel;
import com.looptrace.gadsleaderboard.views.HoursView;

import java.util.ArrayList;
import java.util.List;

public class HourFragment extends Fragment {

    private List<Hour> mHourList = new ArrayList<>();
    private HourRecyclerAdapter mAdapter;
    private HourViewModel mHourViewModel;
    private ProgressBar mProgressBar;

    public HourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning, container, false);
        mProgressBar = view.findViewById(R.id.progress_circular);

        mHourViewModel = ViewModelProviders.of(getActivity()).get(HourViewModel.class);
        mHourViewModel.init();

        getHours();
        getThrowableError();
        getErrorCode();

        RecyclerView recyclerView = view.findViewById(R.id.learning_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new HourRecyclerAdapter(mHourList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        // Inflate the layout for this fragment
        return view;
    }

    private void getErrorCode() {
        mHourViewModel.getErrorCode().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void getThrowableError() {
        mHourViewModel.getThrowableError().observe(getActivity(), new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                Toast.makeText(getActivity(), "ErrorMsg: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getHours() {
        mHourViewModel.getHours().observe(getActivity(), new Observer<List<Hour>>() {
            @Override
            public void onChanged(List<Hour> hours) {
                mAdapter.setHours(hours);
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
