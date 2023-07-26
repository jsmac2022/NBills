package com.nictbills.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.reward_points.reward_ledger.Ledger;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class RewardPointsAdapter extends RecyclerView.Adapter<RewardPointsAdapter.MyViewHolder>{
        Context context;

private List<Ledger> ledgers;
private OnClickRecyclerViewInterface myListener;



public static class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView status_Tv, amount_Tv, date_TV;
    private LinearLayout reward_point_LL;

    public MyViewHolder(View itemView) {
        super(itemView);

        this.status_Tv = (TextView) itemView.findViewById(R.id.status_Tv);
        this.amount_Tv = (TextView) itemView.findViewById(R.id.amount_Tv);
        this.date_TV = (TextView) itemView.findViewById(R.id.date_TV);
        this.reward_point_LL = (LinearLayout) itemView.findViewById(R.id.reward_point_LL);

    }

}

    public RewardPointsAdapter(Context context, List<Ledger> ledgers, OnClickRecyclerViewInterface listener) {
        this.ledgers = ledgers;
        this.context = context;
        this.myListener = listener;
    }

    @Override
    public RewardPointsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reward_point_card_view, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        RewardPointsAdapter.MyViewHolder myViewHolder = new RewardPointsAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RewardPointsAdapter.MyViewHolder holder, final int listPosition) {

        TextView status_Tv = holder.status_Tv;
        TextView amount_Tv = holder.amount_Tv;
        TextView date_TV = holder.date_TV;
        //CardView transaction_details_cardView = holder.transaction_details_cardView;

        //final CardView Product_Description_Card_View = holder.Product_Description_Card_View;

        holder.reward_point_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });


        if (ledgers.get(listPosition).getTtype().equalsIgnoreCase("ADD")) {

            status_Tv.setText(R.string.added);
            status_Tv.setTextColor(Color.parseColor("#228B22"));
            amount_Tv.setTextColor(Color.parseColor("#228B22"));
            amount_Tv.setText("+"+" "+ledgers.get(listPosition).getAmount()+" "+ context.getResources().getString(R.string.point));


        } else if (ledgers.get(listPosition).getTtype().equalsIgnoreCase("RED")) {

            status_Tv.setText(R.string.paid);
            status_Tv.setTextColor(Color.parseColor("#FF0000"));
            amount_Tv.setTextColor(Color.parseColor("#FF0000"));
            amount_Tv.setText("-"+" "+ ledgers.get(listPosition).getAmount()+" "+ context.getResources().getString(R.string.point));

        }

     /*   transaction_date = ledgers.get(listPosition).getCreatedAt() + "";
        Date tr_date = new Date(Long.parseLong((transaction_date + "")) * 1000);
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String dtStartOK = format.format(tr_date);*/
        date_TV.setText(ledgers.get(listPosition).getEdt());
        //String stringDate = DateFormat.getDateTimeInstance().format(dtStartOK);


    }

    @Override
    public int getItemCount() {
        return ledgers.size();
        //return mDataset.size();

    }
}