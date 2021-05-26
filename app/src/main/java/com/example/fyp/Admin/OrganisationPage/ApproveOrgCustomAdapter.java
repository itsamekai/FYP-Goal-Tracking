package com.example.fyp.Admin.OrganisationPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.R;

import java.util.ArrayList;

public class ApproveOrgCustomAdapter extends RecyclerView.Adapter<ApproveOrgCustomAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList org_name;


    public ApproveOrgCustomAdapter(Activity activity, Context context, ArrayList org_name) {
        this.activity = activity;
        this.context = context;
        this.org_name = org_name;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_approve_rows, parent, false);
        return new MyViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.approveOrgName.setText(String.valueOf(org_name.get(position)));
        holder.mainLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ApproveOrganisation.class);
                i.putExtra("name", String.valueOf(org_name.get(position)));
                //Toast.makeText(context, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
                activity.startActivityForResult(i, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return org_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView approveOrgName;
        public LinearLayout mainLayout2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            approveOrgName = itemView.findViewById(R.id.approveOrgName);
            mainLayout2 = itemView.findViewById(R.id.mainLayout2);

        }
    }
}
