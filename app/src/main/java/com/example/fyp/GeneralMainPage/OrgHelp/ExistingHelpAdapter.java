package com.example.fyp.GeneralMainPage.OrgHelp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.R;

import java.util.ArrayList;

public class ExistingHelpAdapter extends RecyclerView.Adapter<ExistingHelpAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList goalName, goalDesc, goalStart, orgName, orgPhone;

    public ExistingHelpAdapter(Context context, Activity activity, ArrayList goalName, ArrayList goalDesc, ArrayList goalStart, ArrayList orgName, ArrayList orgPhone) {
        this.context = context;
        this.activity = activity;
        this.goalName = goalName;
        this.goalDesc = goalDesc;
        this.goalStart = goalStart;
        this.orgName = orgName;
        this.orgPhone = orgPhone;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_existing_help, parent, false);
        return new ExistingHelpAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.GoalName.setText(String.valueOf(goalName.get(position)));
        holder.GoalDesc.setText(String.valueOf(goalDesc.get(position)));
        holder.GoalStart.setText(String.valueOf(goalStart.get(position)));
        holder.OrgName.setText("Organisation: \n" + String.valueOf(orgName.get(position)));
        holder.OrgPhone.setText(String.valueOf(orgPhone.get(position)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // test
                Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return goalName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView GoalName, GoalDesc, GoalStart, OrgName, OrgPhone;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            GoalName = itemView.findViewById(R.id.existingGoalName);
            GoalDesc = itemView.findViewById(R.id.existingGoalDesc);
            GoalStart = itemView.findViewById(R.id.existingDateTime);
            OrgName = itemView.findViewById(R.id.existingOrgName);
            OrgPhone = itemView.findViewById(R.id.existingOrgPhone);
            cardView = itemView.findViewById(R.id.cardView8);

        }

    }

}
