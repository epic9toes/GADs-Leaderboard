package com.looptrace.gadsleaderboard.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.looptrace.gadsleaderboard.R;
import com.looptrace.gadsleaderboard.customAdapters.LearningRecyclerAdapter;
import com.looptrace.gadsleaderboard.models.Learning;

import java.util.ArrayList;
import java.util.List;

public class LearningFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private List<Learning> mLearningList = new ArrayList<>();

    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning, container, false);

        mRecyclerView = view.findViewById(R.id.learning_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        PopulateModel();
        LearningRecyclerAdapter adapter = new LearningRecyclerAdapter(mLearningList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        // Inflate the layout for this fragment
        return view;
    }

    private void PopulateModel() {
        for (int i = 0; i < 15; i++) {
            Learning learning = new Learning("Onwe Chukwumaobim C", 114, "Nigeria", "");
            mLearningList.add(learning);
        }
    }
}
