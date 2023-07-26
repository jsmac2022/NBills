package com.nictbills.app.activities.tbo.flight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.flight.FlitListAdapter;


import java.util.List;

public class SearchCityListAdapter extends RecyclerView.Adapter<SearchCityListAdapter.MyViewHolder> {
    Context context;
   // List<CityData> cityList;

   /* public SearchCityListAdapter(List<CityData> cityList, Context context) {
        this.cityList = cityList;
        this.context = context;
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_citi_list, parent, false);

        return new SearchCityListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       // holder.tvCityName.setText(cityList.get(position).getCityName());
    }

    @Override
    public int getItemCount() {
        //       return cityList.size();
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCityName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCityName =  itemView.findViewById(R.id.tvCityName);
        }
    }
}
