package com.example.fyp.Goals;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Admin.OrganisationPage.ViewOrgCustomAdapter;
import com.example.fyp.R;

import java.util.ArrayList;

public class TrackingGoalAdapter extends RecyclerView.Adapter<TrackingGoalAdapter.MyViewHolder> {

   private Context context;
   private Activity activity;
   private ArrayList goalName, goalDesc;
   private CardView cv;



    public TrackingGoalAdapter(Activity activity, Context context, ArrayList goalName, ArrayList goalDesc) {
        this.activity = activity;
        this.context = context;
        this.goalName = goalName;
        this.goalDesc = goalDesc;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_track_goals, parent, false);
        return new TrackingGoalAdapter.MyViewHolder(view);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.GoalName.setText(String.valueOf(goalName.get(position)));
        holder.GoalDesc.setText(String.valueOf(goalDesc.get(position)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewUncompletedGoal.class);
                i.putExtra("goal_name", String.valueOf(goalName.get(position)));
                i.putExtra("username", TrackingGoal.uniqueString);
                activity.startActivityForResult(i, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return goalName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView GoalName, GoalDesc;
        CardView cardView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            GoalName = itemView.findViewById(R.id.goalName);
            GoalDesc = itemView.findViewById(R.id.goalDesc);
            cardView = itemView.findViewById(R.id.cardView3);

        }
    }
}
