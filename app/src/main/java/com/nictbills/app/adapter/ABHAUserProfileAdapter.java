package com.nictbills.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.abha_profile_details.MobileLinkedHid;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class ABHAUserProfileAdapter extends RecyclerView.Adapter<ABHAUserProfileAdapter.MyViewHolder>{
    Context context;

    private List<MobileLinkedHid> mobileLinkedHids;
    private OnClickRecyclerViewInterface myListener;



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView abha_user_name_TV, health_id_number_TV,health_id_TV;
        private LinearLayout user_abhaDetails_LL;
        private Button more_details_BTN;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.abha_user_name_TV = (TextView) itemView.findViewById(R.id.abha_user_name_TV);
            this.health_id_number_TV = (TextView) itemView.findViewById(R.id.health_id_number_TV);
            this.health_id_TV = (TextView) itemView.findViewById(R.id.health_id_TV);
            this.user_abhaDetails_LL = (LinearLayout) itemView.findViewById(R.id.user_abhaDetails_LL);
            this.more_details_BTN = (Button) itemView.findViewById(R.id.more_details_BTN);

        }

    }

    public ABHAUserProfileAdapter(Context context, List<MobileLinkedHid> mobileLinkedHids, OnClickRecyclerViewInterface listener) {
        this.mobileLinkedHids = mobileLinkedHids;
        this.context = context;
        this.myListener = listener;
    }

    @Override
    public ABHAUserProfileAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.abha_user_profile_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        ABHAUserProfileAdapter.MyViewHolder myViewHolder = new ABHAUserProfileAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ABHAUserProfileAdapter.MyViewHolder holder, final int listPosition) {

       /* TextView status_Tv = holder.status_Tv;
        TextView amount_Tv = holder.amount_Tv;
        TextView date_TV = holder.date_TV;*/
        //CardView transaction_details_cardView = holder.transaction_details_cardView;

        //final CardView Product_Description_Card_View = holder.Product_Description_Card_View;

        holder.more_details_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });




        holder.abha_user_name_TV.setText(mobileLinkedHids.get(listPosition).getName());
        holder.health_id_number_TV.setText(mobileLinkedHids.get(listPosition).getHealthIdNumber());
        holder.health_id_TV.setText(mobileLinkedHids.get(listPosition).getHealthId());

    }

    @Override
    public int getItemCount() {
        return mobileLinkedHids.size();
        //return mDataset.size();

    }
}

