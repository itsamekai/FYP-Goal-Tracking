package com.example.fyp.GeneralMainPage.OrgHelp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Goals.TrackingGoal;
import com.example.fyp.Goals.TrackingGoalAdapter;
import com.example.fyp.Goals.ViewUncompletedGoal;
import com.example.fyp.R;

import java.util.ArrayList;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList goalName, goalDesc, goalStart;

    public HelpAdapter(Activity activity, Context context, ArrayList goalName, ArrayList goalDesc, ArrayList goalStart) {
        this.activity = activity;
        this.context = context;
        this.goalName = goalName;
        this.goalDesc = goalDesc;
        this.goalStart = goalStart;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_select_help_goal, parent, false);
        return new HelpAdapter.MyViewHolder(view);

    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.GoalName.setText(String.valueOf(goalName.get(position)));
        holder.GoalDesc.setText(String.valueOf(goalDesc.get(position)));
        holder.GoalStart.setText(String.valueOf(goalStart.get(position)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ConfirmHelpGoal.class);
                i.putExtra("goal_name", String.valueOf(goalName.get(position)));
                i.putExtra("username", SelectHelpGoal.uniqueString);
                activity.startActivityForResult(i, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return goalName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView GoalName, GoalDesc, GoalStart;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            GoalName = itemView.findViewById(R.id.helpGoalName);
            GoalDesc = itemView.findViewById(R.id.helpGoalDesc);
            GoalStart = itemView.findViewById(R.id.helpDateTime);
            cardView = itemView.findViewById(R.id.cardView4);
        }
    }
}
