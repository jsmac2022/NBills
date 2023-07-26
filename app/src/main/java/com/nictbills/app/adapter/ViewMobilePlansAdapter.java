package com.nictbills.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.view_plans_dto.ViewPlansDto;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class ViewMobilePlansAdapter extends RecyclerView.Adapter<ViewMobilePlansAdapter.MyViewHolder> {
    Context context;

    private List<String> planTitleList;
    private List<ViewPlansDto> viewPlansDtoslist;
    private OnClickRecyclerViewInterface myListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mobile_plan_name_TV;
        private LinearLayout planTitle_LL;

        public MyViewHolder(View itemView) {
            super(itemView);

           this.mobile_plan_name_TV = (TextView) itemView.findViewById(R.id.mobile_plan_name_TV);
           this.planTitle_LL = (LinearLayout) itemView.findViewById(R.id.planTitle_LL);
          //  this.mobileRechargeRateList = (RecyclerView) itemView.findViewById(R.id.mobileRechargeRateList);

        }
    }

    public ViewMobilePlansAdapter(Context context, List<String> planTitleList,List<ViewPlansDto> data, OnClickRecyclerViewInterface listener) {
        this.planTitleList = planTitleList;
        this.viewPlansDtoslist = data;
        this.context = context;
        this.myListener = listener;
    }

    @Override
    public ViewMobilePlansAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_mobile_plan_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        ViewMobilePlansAdapter.MyViewHolder myViewHolder = new ViewMobilePlansAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewMobilePlansAdapter.MyViewHolder holder, final int listPosition) {

        holder.mobile_plan_name_TV.setText(planTitleList.get(listPosition).trim());

        holder.planTitle_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myListener.onListItem(v, listPosition);

            }
        });

    }

    @Override
    public int getItemCount() {
        return planTitleList.size();
        //return mDataset.size();

    }
}
