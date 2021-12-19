package com.example.organizeupv3.adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizeupv3.R;
import com.example.organizeupv3.models.Plan;

import java.util.List;

public class PlansRecyclerAdapter extends RecyclerView.Adapter<PlansRecyclerAdapter.PlansViewHolder> {
    private List<Plan> listPlans;

    public PlansRecyclerAdapter(List<Plan> listPlans){
        this.listPlans = listPlans;
    }

    @NonNull
    @Override
    public PlansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan_recycler, parent, false);

        return new PlansViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlansViewHolder holder, int position) {
        holder.tvPlanID.setText(listPlans.get(position).getPlanID());
        holder.tvTime.setText(listPlans.get(position).getTime());
        holder.tvDate.setText(listPlans.get(position).getDate());
    }

    @Override
    public int getItemCount() {
       Log.v(PlansRecyclerAdapter.class.getSimpleName()," "+listPlans.size());
       return listPlans.size();
    }

    public class PlansViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPlanID;
        public TextView tvTime;
        public TextView tvDate;

        public PlansViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlanID = itemView.findViewById(R.id.tvPlanID);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
