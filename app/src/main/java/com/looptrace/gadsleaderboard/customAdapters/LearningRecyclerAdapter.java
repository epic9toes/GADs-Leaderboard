package com.looptrace.gadsleaderboard.customAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.looptrace.gadsleaderboard.R;
import com.looptrace.gadsleaderboard.models.Learning;

import java.util.List;

public class LearningRecyclerAdapter extends RecyclerView.Adapter<LearningRecyclerAdapter.ViewHolder> {
    private final List<Learning> mLearnings;

    public LearningRecyclerAdapter(List<Learning> learnings) {
        mLearnings = learnings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learning_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Learning learning = mLearnings.get(position);
        holder.username.setText(learning.getName());
        holder.hours.setText(learning.getHours() + " learning hours, " + learning.getCountry());
    }

    @Override
    public int getItemCount() {
        return mLearnings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView username, hours;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            hours = itemView.findViewById(R.id.hours);
        }
    }
}
