package com.nictbills.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.covid_genders_dto.Gender;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class CovidGendersAdapter extends RecyclerView.Adapter<CovidGendersAdapter.MyViewHolder> {
    Context context;

    private List<Gender> genderList;
    private OnClickRecyclerViewInterface myListener;
    private int selectedPosition=-1;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private RadioButton genderRadioButton;



        public MyViewHolder(View itemView) {
            super(itemView);
            this.genderRadioButton = (RadioButton) itemView.findViewById(R.id.genderRadioButton);
        }
    }

    public CovidGendersAdapter(Context context, List<Gender> data, OnClickRecyclerViewInterface listener) {
        this.genderList = data;
        this.context =  context;
        this.myListener = listener;
    }

    @Override
    public CovidGendersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.covid_genders_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        CovidGendersAdapter.MyViewHolder myViewHolder = new CovidGendersAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CovidGendersAdapter.MyViewHolder holder, final int listPosition) {

        if (selectedPosition==listPosition){
          holder.genderRadioButton.setChecked(true);
        } else {

            holder.genderRadioButton.setChecked(false);
        }

        holder.genderRadioButton.setText(genderList.get(listPosition).getGender());
        holder.genderRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = listPosition;
                myListener.onListItem(v, listPosition);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return genderList.size();
        //return mDataset.size();


    }

}
