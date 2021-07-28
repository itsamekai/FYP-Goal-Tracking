package com.example.fyp.Admin.OrganisationPage;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.R;

import java.util.ArrayList;

public class ViewOrgCustomAdapter extends RecyclerView.Adapter<ViewOrgCustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList org_name, org_incharge, org_phone, org_service;

    public ViewOrgCustomAdapter(Context context, ArrayList org_name, ArrayList org_incharge, ArrayList org_phone) {
        this.context = context;
        this.org_name = org_name;
        this.org_incharge = org_incharge;
        this.org_phone = org_phone;
      //  this.org_service = org_service;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_show_orgs_rows, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.organisationName.setText(String.valueOf(org_name.get(position)));
        holder.personInCharge.setText(String.valueOf(org_incharge.get(position)));
        holder.OrganisationPhoneNo.setText(String.valueOf(org_phone.get(position)));
       // holder.OrganisationServices.setText(String.valueOf(org_service.get(position)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set intent

            }
        });
    }

    @Override
    public int getItemCount() {
        return org_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView organisationName, personInCharge, OrganisationPhoneNo, OrganisationServices;
        CardView cardView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            organisationName = itemView.findViewById(R.id.organisationName);
            personInCharge = itemView.findViewById(R.id.personInCharge);
            OrganisationPhoneNo = itemView.findViewById(R.id.OrganisationPhoneNo);
            //OrganisationServices = itemView.findViewById(R.id.OrgService);
            cardView = itemView.findViewById(R.id.cardView5);

        }
    }
}
