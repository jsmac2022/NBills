package com.nictbills.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.get_shops.Datum;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class Dawai4UShopsAdapter extends RecyclerView.Adapter<Dawai4UShopsAdapter.MyViewHolder>{
    Context context;

    private List<Datum> data;
    private OnClickRecyclerViewInterface myListener;



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView chemist_shop_name_TV, chemist_location_Tv;
        private Button  continue_BTN;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.chemist_shop_name_TV = (TextView) itemView.findViewById(R.id.chemist_shop_name_TV);
            this.chemist_location_Tv = (TextView) itemView.findViewById(R.id.chemist_location_Tv);
            this.continue_BTN = (Button) itemView.findViewById(R.id.continue_BTN);

        }

    }

    public Dawai4UShopsAdapter(Context context, List<Datum> data, OnClickRecyclerViewInterface listener) {
        this.data = data;
        this.context = context;
        this.myListener = listener;
    }

    @Override
    public Dawai4UShopsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dawai_for_u_shops_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        Dawai4UShopsAdapter.MyViewHolder myViewHolder = new Dawai4UShopsAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final Dawai4UShopsAdapter.MyViewHolder holder, final int listPosition) {

        //CardView transaction_details_cardView = holder.transaction_details_cardView;

        //final CardView Product_Description_Card_View = holder.Product_Description_Card_View;

       holder.continue_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });


        holder.chemist_shop_name_TV.setText(data.get(listPosition).getStoreName());
        holder.chemist_location_Tv.setText(data.get(listPosition).getLocation());


    }

    @Override
    public int getItemCount() {
        return data.size();
        //return mDataset.size();

    }
}
