package com.looptrace.gadsleaderboard.customAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.looptrace.gadsleaderboard.R;
import com.looptrace.gadsleaderboard.models.Skill;

import java.util.List;

public class SkillRecyclerAdapter extends RecyclerView.Adapter<SkillRecyclerAdapter.ViewHolder> {
    private final List<Skill> mSkills;

    public SkillRecyclerAdapter(List<Skill> skills) {
        mSkills = skills;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Skill skill = mSkills.get(position);
        holder.username.setText(skill.getName());
        holder.skill.setText(skill.getScore() + " skill IQ score, " + skill.getCountry());
    }

    @Override
    public int getItemCount() {
        return mSkills.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView username, skill;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            skill = itemView.findViewById(R.id.skill_iq);
        }
    }
}
