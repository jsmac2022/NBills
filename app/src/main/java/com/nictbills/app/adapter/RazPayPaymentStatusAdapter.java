package com.nictbills.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.update_order.PaymentEntity;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class RazPayPaymentStatusAdapter extends RecyclerView.Adapter<RazPayPaymentStatusAdapter.MyViewHolder> {
    Context context;

    private List<PaymentEntity> paymentEntities;
    private OnClickRecyclerViewInterface myListener;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView ivrs_number_TV,bill_amount_TV,due_date_Tv,customer_name_TV;
        private ImageView delete_ivrs_IV;
        private CardView ivrs_number_card_view;
        private Button pay_now_Button;




        public MyViewHolder(View itemView) {
            super(itemView);

            this.ivrs_number_TV = (TextView) itemView.findViewById(R.id.ivrs_number_TV);
            this.bill_amount_TV = (TextView) itemView.findViewById(R.id.bill_amount_TV);
            this.due_date_Tv = (TextView) itemView.findViewById(R.id.due_date_Tv);
            this.customer_name_TV = (TextView) itemView.findViewById(R.id.customer_name_TV);
            this.delete_ivrs_IV = (ImageView) itemView.findViewById(R.id.delete_ivrs_IV);
            this.ivrs_number_card_view = (CardView) itemView.findViewById(R.id.ivrs_number_card_view);
            this.pay_now_Button = (Button) itemView.findViewById(R.id.pay_now_Button);




        }
    }

    public RazPayPaymentStatusAdapter(Context context, List<PaymentEntity> data, OnClickRecyclerViewInterface listener) {
        this.paymentEntities = data;
        this.context =  context;
        this.myListener = listener;
    }

    @Override
    public RazPayPaymentStatusAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ivrs_added_number_card_view, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        RazPayPaymentStatusAdapter.MyViewHolder myViewHolder = new RazPayPaymentStatusAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RazPayPaymentStatusAdapter.MyViewHolder holder, final int listPosition) {

        TextView ivrs_number_TV = holder.ivrs_number_TV;
        TextView bill_amount_TV = holder.bill_amount_TV;
        TextView due_date_Tv = holder.due_date_Tv;
        TextView customer_name_TV = holder.customer_name_TV;
        ImageView delete_ivrs_IV = holder.delete_ivrs_IV;
        CardView ivrs_number_card_view = holder.ivrs_number_card_view;
        Button pay_now_Button = holder.pay_now_Button;

        //final CardView Product_Description_Card_View = holder.Product_Description_Card_View;

        pay_now_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myListener.onListItem(v, listPosition);
            }
        });


        delete_ivrs_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myListener.onListItem(v, listPosition);

            }
        });



        ivrs_number_card_view.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });


    /*    ivrs_number_TV.setText(paymentEntities.get(listPosition).getIvrsNo());
        bill_amount_TV.setText("₹ "+IvrsResultList.get(listPosition).getNetBill());
        customer_name_TV.setText(IvrsResultList.get(listPosition).getConsName());
        dueDate = IvrsResultList.get(listPosition).getDueDate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dtStartOK = format.parse(dueDate);
            //String stringDate = DateFormat.getDateTimeInstance().format(dtStartOK);
            due_date_Tv.setText(DateFormat.getDateInstance().format(dtStartOK));
            //   transaction_Date_amount_TV.setText(DateFormat.getTimeInstance().format(dtStartOK));

        } catch (ParseException e) {
            //Handle exception here, most of the time you will just log it.
            e.printStackTrace();

        }*/




    }

    @Override
    public int getItemCount() {
        return paymentEntities.size();
        //return mDataset.size();


    }



}
