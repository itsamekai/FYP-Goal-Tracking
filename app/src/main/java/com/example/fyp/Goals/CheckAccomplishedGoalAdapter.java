package com.example.fyp.Goals;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.R;

import java.util.ArrayList;

public class CheckAccomplishedGoalAdapter extends RecyclerView.Adapter<CheckAccomplishedGoalAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList AccomplishedAchievement;
    private ArrayList AccomplishedDateTime;

    public CheckAccomplishedGoalAdapter(Activity activity, Context context, ArrayList accomplishedAchievement, ArrayList accomplishedDateTime) {
        this.activity = activity;
        this.context = context;
        this.AccomplishedAchievement = accomplishedAchievement;
        this.AccomplishedDateTime = accomplishedDateTime;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_check_accomplished_goals, parent, false);
        return new CheckAccomplishedGoalAdapter.MyViewHolder(view);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onBindViewHolder(@NonNull final CheckAccomplishedGoalAdapter.MyViewHolder holder, int position) {
        holder.accomplishedAchievement.setText(String.valueOf(AccomplishedAchievement.get(position)));
        holder.accomplishedDateTime.setText(String.valueOf(AccomplishedDateTime.get(position)));
    }

    @Override
    public int getItemCount() {
        return AccomplishedAchievement.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView accomplishedAchievement, accomplishedDateTime;
        CardView cardView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            accomplishedAchievement = itemView.findViewById(R.id.accomplishedAchievement);
            accomplishedDateTime = itemView.findViewById(R.id.accomplishedDateTime);
            cardView = itemView.findViewById(R.id.cardView3);

        }
    }
}
