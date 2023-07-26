package com.nictbills.app.adapter.paniwala;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.water_category.Result;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class PaniwalaWaterCategoryAdapter extends RecyclerView.Adapter<PaniwalaWaterCategoryAdapter.MyViewHolder>{
    Context context;

    private List<Result> resultList;
    private OnClickRecyclerViewInterface myListener;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView water_category_TV;
        private LinearLayout water_category_LL;
       // private Button water_category_BTN;

        public MyViewHolder(View itemView) {
            super(itemView);

       //     this.water_type_TV = (TextView) itemView.findViewById(R.id.water_type_TV);
            this.water_category_TV = itemView.findViewById(R.id.water_category_TV);
            this.water_category_LL = itemView.findViewById(R.id.water_category_LL);


        }

    }

    public PaniwalaWaterCategoryAdapter(Context context, List<Result> resultList, OnClickRecyclerViewInterface listener) {
        this.resultList = resultList;
        this.context = context;
        this.myListener = listener;
    }

    @Override
    public PaniwalaWaterCategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.paniwala_water_category_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        PaniwalaWaterCategoryAdapter.MyViewHolder myViewHolder = new PaniwalaWaterCategoryAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final PaniwalaWaterCategoryAdapter.MyViewHolder holder, final int listPosition) {

        //TextView water_type_TV = holder.water_type_TV;

        //CardView transaction_details_cardView = holder.transaction_details_cardView;

        //final CardView Product_Description_Card_View = holder.Product_Description_Card_View;

       holder.water_category_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });


     /*   transaction_date = ledgers.get(listPosition).getCreatedAt() + "";
        Date tr_date = new Date(Long.parseLong((transaction_date + "")) * 1000);
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String dtStartOK = format.format(tr_date);*/
        holder.water_category_TV.setText(resultList.get(listPosition).getSubServ());
        //String stringDate = DateFormat.getDateTimeInstance().format(dtStartOK);


    }

    @Override
    public int getItemCount() {
        return resultList.size();
        //return mDataset.size();

    }
}