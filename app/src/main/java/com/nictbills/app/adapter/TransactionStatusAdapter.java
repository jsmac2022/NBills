package com.nictbills.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.transaction_history.UserTxnList;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionStatusAdapter extends RecyclerView.Adapter<TransactionStatusAdapter.MyViewHolder> {
    Context context;

    private List<UserTxnList> txnLists;
    private OnClickRecyclerViewInterface myListener;
    private String transaction_date;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView ivrs_number_Tv,txn_amount_TV,transaction_Date_TV;
        private CardView transaction_details_cardView;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.ivrs_number_Tv = (TextView) itemView.findViewById(R.id.ivrs_number_Tv);
            this.txn_amount_TV = (TextView) itemView.findViewById(R.id.txn_amount_TV);
            this.transaction_Date_TV = (TextView) itemView.findViewById(R.id.transaction_Date_TV);
            this.transaction_details_cardView = (CardView) itemView.findViewById(R.id.transaction_details_cardView);

        }
    }

    public TransactionStatusAdapter(Context context, List<UserTxnList> data, OnClickRecyclerViewInterface listener) {
        this.txnLists = data;
        this.context =  context;
        this.myListener = listener;
    }

    @Override
    public TransactionStatusAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_history_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        TransactionStatusAdapter.MyViewHolder myViewHolder = new TransactionStatusAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final TransactionStatusAdapter.MyViewHolder holder, final int listPosition) {

        TextView ivrs_number_Tv = holder.ivrs_number_Tv;
        TextView txn_amount_TV = holder.txn_amount_TV;
        TextView transaction_Date_TV = holder.transaction_Date_TV;
        CardView transaction_details_cardView = holder.transaction_details_cardView;

        //final CardView Product_Description_Card_View = holder.Product_Description_Card_View;

        transaction_details_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myListener.onListItem(v, listPosition);
            }
        });



      if (txnLists.get(listPosition).getStatus().equalsIgnoreCase("captured")||txnLists.get(listPosition).getStatus().equalsIgnoreCase("success")){

          ivrs_number_Tv.setText(txnLists.get(listPosition).getNotesServiceId());
          txn_amount_TV.setTextColor(Color.parseColor("#228B22"));
          txn_amount_TV.setText("₹ "+txnLists.get(listPosition).getAmount()+", "+"Your transactions is successful");


      }else if (txnLists.get(listPosition).getStatus().toLowerCase().startsWith("fail")){

          ivrs_number_Tv.setText(txnLists.get(listPosition).getNotesServiceId());
          txn_amount_TV.setTextColor(Color.parseColor("#FF0000"));
          txn_amount_TV.setText("₹ "+txnLists.get(listPosition).getAmount()+", "+"Your transactions has failed");

        } else if (txnLists.get(listPosition).getStatus().toLowerCase().startsWith("pending")){

          ivrs_number_Tv.setText(txnLists.get(listPosition).getNotesServiceId());
          txn_amount_TV.setTextColor(Color.parseColor("#FFA500"));
          txn_amount_TV.setText("₹ "+txnLists.get(listPosition).getAmount()+", "+"Your transactions has pending");

      }

        transaction_date = txnLists.get(listPosition).getCreatedAt()+"";

        Date tr_date = new Date(Long.parseLong((transaction_date+"")) * 1000);

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");


        String dtStartOK = format.format(tr_date);
        transaction_Date_TV.setText(dtStartOK);
        //String stringDate = DateFormat.getDateTimeInstance().format(dtStartOK);


    }

    @Override
    public int getItemCount() {
        return txnLists.size();
        //return mDataset.size();

    }

}
