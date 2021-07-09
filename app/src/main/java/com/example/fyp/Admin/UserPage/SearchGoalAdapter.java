package com.example.fyp.Admin.UserPage;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Admin.OrganisationPage.ViewOrgCustomAdapter;
import com.example.fyp.ObjectClass.FilteredUsersGoal;
import com.example.fyp.R;

import java.util.ArrayList;

public class SearchGoalAdapter extends RecyclerView.Adapter<SearchGoalAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<FilteredUsersGoal> goals;

    public SearchGoalAdapter(Context context, ArrayList<FilteredUsersGoal> goals) {
        this.context = context;
        this.goals = goals;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_show_goals_rows, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull SearchGoalAdapter.MyViewHolder holder, int position) {
        holder.viewGoalUser.setText(goals.get(position).getName());
        holder.viewGoalCategory.setText(goals.get(position).getCategory());
        holder.viewGoalName.setText(goals.get(position).getGoalName());
        holder.Accomplished.setText(goals.get(position).getAccomplished());

    }

    //This method will filter the list
    //here we are passing the filtered data
    //and assigning it to the list with notifydatasetchanged method
    public void filterList(ArrayList<FilteredUsersGoal> filteredNames) {
        goals = filteredNames;
        notifyDataSetChanged();
    }




    @Override
    public int getItemCount() {
        return goals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView viewGoalUser, viewGoalCategory, viewGoalName, Accomplished;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewGoalUser = itemView.findViewById(R.id.viewGoalUser);
            viewGoalCategory = itemView.findViewById(R.id.viewGoalCategory);
            viewGoalName = itemView.findViewById(R.id.viewGoalName);
            Accomplished = itemView.findViewById(R.id.Accomplished);
            //mainLayout = itemView.findViewById(R.id.mainLayout3);
        }
    }
}
