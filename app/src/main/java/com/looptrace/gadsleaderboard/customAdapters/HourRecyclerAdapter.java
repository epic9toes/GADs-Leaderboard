package com.looptrace.gadsleaderboard.customAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.looptrace.gadsleaderboard.R;
import com.looptrace.gadsleaderboard.models.Hour;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HourRecyclerAdapter extends RecyclerView.Adapter<HourRecyclerAdapter.ViewHolder> {
    private List<Hour> mHours;

    public HourRecyclerAdapter(List<Hour> hours) {
        mHours = hours;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hour hour = mHours.get(position);
        holder.username.setText(hour.getName());
        holder.hours.setText(hour.getHours() + " learning hours, " + hour.getCountry());
        Picasso.get().load(hour.getBadgeUrl()).into(holder.mImageView);
    }

    public void setHours(List<Hour> hours) {
        mHours = hours;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView username, hours;
        private ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            hours = itemView.findViewById(R.id.value);
            mImageView = itemView.findViewById(R.id.image);
        }
    }
}
