package com.nictbills.app.adapter.mobile_recharge_paysprint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.getRechargePlan.PlanDetailsList;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class PlanDescriptionPaySprintAdapter extends RecyclerView.Adapter<PlanDescriptionPaySprintAdapter.MyViewHolder> {
    Context context;

    private String planTitle;
    List<String> planHead;
    private List<PlanDetailsList> planDetailsLists  ;
    private OnClickRecyclerViewInterface myListener;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView validity_TV,rate_TV,description_TV;
        private LinearLayout amount_LinearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.validity_TV = (TextView) itemView.findViewById(R.id.validity_TV);
            this.rate_TV = (TextView) itemView.findViewById(R.id.rate_TV);
            this.description_TV = (TextView) itemView.findViewById(R.id.description_TV);
            this.amount_LinearLayout = (LinearLayout) itemView.findViewById(R.id.amount_LinearLayout);

        }
    }

    public PlanDescriptionPaySprintAdapter(Context context, List<String> planHead, List<PlanDetailsList> planDetailsLists, OnClickRecyclerViewInterface listener) {

        this.planHead = planHead;
        this.planDetailsLists = planDetailsLists;
        this.context = context;
        this.myListener = listener;
    }

    @Override
    public PlanDescriptionPaySprintAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_price_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        PlanDescriptionPaySprintAdapter.MyViewHolder myViewHolder = new PlanDescriptionPaySprintAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final PlanDescriptionPaySprintAdapter.MyViewHolder holder, final int listPosition) {

        //fina CardView Product_Description_Card_View = holder.Product_Description_Card_View;

        // for ()



            holder.validity_TV.setText("Validity: "+planDetailsLists.get(listPosition).getValidity());
            holder.rate_TV.setText("₹ "+planDetailsLists.get(listPosition).getRs());
            holder.description_TV.setText(planDetailsLists.get(listPosition).getDesc());


     /*   for (int i = 0 ; i<=viewPlansDtoslist.size() ; i++){

            if (viewPlansDtoslist.get(listPosition).getType().trim().equalsIgnoreCase("mobile")){
                holder.mobile_plan_name_TV.setText(operators.get(listPosition).getName().trim());
            }

        }*/


        holder.amount_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myListener.onListItem(v, listPosition);

            }
        });

    }

    @Override
    public int getItemCount() {

       /* if (planTitle.trim().equalsIgnoreCase("Filter")){
            Log.e("sssss+++","Filter");
            return filterData.size();
        }else {
            Log.e("sssss+++","All");
            return allViewPlansList.size();
        }*/

        return planDetailsLists.size();

    }
}
