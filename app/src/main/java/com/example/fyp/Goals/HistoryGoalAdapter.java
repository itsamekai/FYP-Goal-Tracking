package com.example.fyp.Goals;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.R;

import java.util.ArrayList;

public class HistoryGoalAdapter extends RecyclerView.Adapter<HistoryGoalAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList goalName, goalDesc, goalDate;

    public HistoryGoalAdapter(Activity a, Context c, ArrayList goalName, ArrayList goalDesc, ArrayList goalDate) {
        this.activity = a;
        this.context = c;
        this.goalName = goalName;
        this.goalDesc = goalDesc;
        this.goalDate = goalDate;
    }

    @NonNull
    @Override
    public HistoryGoalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_history_goals, parent, false);
        return new HistoryGoalAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.HistoryGoalName.setText(String.valueOf(goalName.get(position)));
        holder.HistoryGoalDesc.setText(String.valueOf(goalDesc.get(position)));
        holder.HistoryStartDate.setText(String.valueOf(goalDate.get(position)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewCompletedGoal.class);
                i.putExtra("goal_name", String.valueOf(goalName.get(position)));
                i.putExtra("username", HistoryGoals.uniqueString);
                activity.startActivityForResult(i, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return goalName.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView HistoryGoalName, HistoryGoalDesc, HistoryStartDate;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            HistoryGoalName = itemView.findViewById(R.id.HistoryGoalName);
            HistoryGoalDesc = itemView.findViewById(R.id.HistoryGoalDesc);
            HistoryStartDate = itemView.findViewById(R.id.HistoryStartDate);
            cardView = itemView.findViewById(R.id.cardView4);
        }
    }





}
