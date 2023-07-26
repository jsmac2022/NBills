package com.nictbills.app.adapter.paniwala;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.water_type.Result;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class PaniwalaWaterTypeAdapter extends RecyclerView.Adapter<PaniwalaWaterTypeAdapter.MyViewHolder>{
    Context context;

    private List<Result> resultList;
    private OnClickRecyclerViewInterface myListener;



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView water_type_TV;
        private Button continue_water_type_BTN;
        private ImageView paniwala_water_type_IMG;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.water_type_TV = (TextView) itemView.findViewById(R.id.water_type_TV);
            this.continue_water_type_BTN = (Button) itemView.findViewById(R.id.continue_water_type_BTN);
            this.paniwala_water_type_IMG = (ImageView) itemView.findViewById(R.id.paniwala_water_type_IMG);


        }

    }

    public PaniwalaWaterTypeAdapter(Context context, List<Result> resultList, OnClickRecyclerViewInterface listener) {
        this.resultList = resultList;
        this.context = context;
        this.myListener = listener;
    }

    @Override
    public PaniwalaWaterTypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.paniwala_water_type_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        PaniwalaWaterTypeAdapter.MyViewHolder myViewHolder = new PaniwalaWaterTypeAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final PaniwalaWaterTypeAdapter.MyViewHolder holder, final int listPosition) {

        TextView water_type_TV = holder.water_type_TV;

        //CardView transaction_details_cardView = holder.transaction_details_cardView;

        //final CardView Product_Description_Card_View = holder.Product_Description_Card_View;

       holder.continue_water_type_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });


     /*   transaction_date = ledgers.get(listPosition).getCreatedAt() + "";
        Date tr_date = new Date(Long.parseLong((transaction_date + "")) * 1000);
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String dtStartOK = format.format(tr_date);*/
        water_type_TV.setText(resultList.get(listPosition).getMainServ());
        //String stringDate = DateFormat.getDateTimeInstance().format(dtStartOK);

        Log.e("vinod", "onBindViewHolder: "+ resultList.get(listPosition).getLogo());
        Glide.with(context).load(resultList.get(listPosition).getLogo())
                .placeholder(R.drawable.paniwala_logo)
                .error(R.drawable.paniwala_logo)
                .into(holder.paniwala_water_type_IMG);

    }

    @Override
    public int getItemCount() {
        return resultList.size();
        //return mDataset.size();

    }
}