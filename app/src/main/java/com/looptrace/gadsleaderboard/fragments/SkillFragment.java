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
import com.looptrace.gadsleaderboard.customAdapters.SkillRecyclerAdapter;
import com.looptrace.gadsleaderboard.models.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkillFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Skill> mSkills = new ArrayList<>();

    public SkillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill, container, false);
        mRecyclerView = view.findViewById(R.id.skill_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PopulateModel();
        SkillRecyclerAdapter adapter = new SkillRecyclerAdapter(mSkills);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        // Inflate the layout for this fragment
        return view;
    }

    private void PopulateModel() {
        for (int i = 0; i < 15; i++) {
            Skill skill = new Skill("Onwe Chukwumaobim C", 204, "Nigeria", "");
            mSkills.add(skill);
        }
    }
}
