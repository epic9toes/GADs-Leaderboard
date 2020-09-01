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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.looptrace.gadsleaderboard.R;
import com.looptrace.gadsleaderboard.customAdapters.HourRecyclerAdapter;
import com.looptrace.gadsleaderboard.models.Hour;
import com.looptrace.gadsleaderboard.viewmodels.HourViewModel;

import java.util.ArrayList;
import java.util.List;

public class HourFragment extends Fragment {

    private List<Hour> mHourList = new ArrayList<>();
    private HourRecyclerAdapter mAdapter;
    private HourViewModel mHourViewModel;
    private ProgressBar mProgressBar;
    private View mErrorPage;
    private Button ErrBtn;
    private TextView ErrMsg;

    public HourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning, container, false);

        mErrorPage = view.findViewById(R.id.error_page);
        ErrBtn = mErrorPage.findViewById(R.id.refresh_btn);
        ErrMsg = mErrorPage.findViewById(R.id.error_msg);
        ErrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mErrorPage.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                mHourViewModel.Retry();
                getHours();
            }
        });

        mHourViewModel = ViewModelProviders.of(getActivity()).get(HourViewModel.class);
        mHourViewModel.init();
        getHours();

        mProgressBar = view.findViewById(R.id.progress_circular);

        RecyclerView recyclerView = view.findViewById(R.id.learning_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new HourRecyclerAdapter(mHourList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getErrorCode();
        getThrowableError();

        // Inflate the layout for this fragment
        return view;
    }

    private void getErrorCode() {

        mHourViewModel.getErrorCode().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ErrMsg.setText(s);
                mErrorPage.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getThrowableError() {
        mHourViewModel.getThrowableError().observe(getActivity(), new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                mProgressBar.setVisibility(View.INVISIBLE);
                ErrMsg.setText(getActivity().getResources().getString(R.string.network_error));
                mErrorPage.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getHours() {
        mHourViewModel.getHours().observe(getActivity(), new Observer<List<Hour>>() {
            @Override
            public void onChanged(List<Hour> hours) {
                mAdapter.setHours(hours);
                mErrorPage.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

}
