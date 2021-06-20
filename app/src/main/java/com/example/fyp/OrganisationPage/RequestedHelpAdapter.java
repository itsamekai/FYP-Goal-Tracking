package com.example.fyp.OrganisationPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class RequestedHelpAdapter extends RecyclerView.Adapter<RequestedHelpAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList category, full_name, phone_no, goal_name, goal_desc, goal_created_time;

    public RequestedHelpAdapter(Context context, Activity activity, ArrayList category, ArrayList full_name, ArrayList phone_no, ArrayList goal_name, ArrayList goal_desc, ArrayList goal_created_time) {
        this.context = context;
        this.activity = activity;
        this.category = category;
        this.full_name = full_name;
        this.phone_no = phone_no;
        this.goal_name = goal_name;
        this.goal_desc = goal_desc;
        this.goal_created_time = goal_created_time;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_requested_help, parent, false);
        return new RequestedHelpAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.category_name.setText(String.valueOf(category.get(position)));
        holder.full_name.setText(String.valueOf(full_name.get(position)));
        holder.phone_no.setText(String.valueOf(phone_no.get(position)));
        holder.goal_name.setText(String.valueOf(goal_name.get(position)));
        holder.goal_desc.setText(String.valueOf(goal_desc.get(position)));
        holder.goal_created_time.setText(String.valueOf(goal_created_time.get(position)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, OrgConfirmUserHelp.class);
                i.putExtra("username", RequestedHelp.uniqueString);
                i.putExtra("category_name", String.valueOf(category.get(position)));
                i.putExtra("full_name", String.valueOf(full_name.get(position)));
                i.putExtra("phone_no", String.valueOf(phone_no.get(position)));
                i.putExtra("goal_name", String.valueOf(goal_name.get(position)));
                i.putExtra("goal_description", String.valueOf(goal_desc.get(position)));
                i.putExtra("goal_created", String.valueOf(goal_created_time.get(position)));
                activity.startActivityForResult(i, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return full_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView category_name, full_name, phone_no, goal_name, goal_desc, goal_created_time;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            category_name = itemView.findViewById(R.id.requestedCategory);
            full_name = itemView.findViewById(R.id.requestedFullName);
            phone_no = itemView.findViewById(R.id.requestedUserNo);
            goal_name = itemView.findViewById(R.id.requestedGoalName);
            goal_desc = itemView.findViewById(R.id.requestedGoalDesc);
            goal_created_time = itemView.findViewById(R.id.requestedGoalCreatedTime);
            cardView = itemView.findViewById(R.id.cardView6);

        }
    }


}
