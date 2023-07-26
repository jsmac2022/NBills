package com.nictbills.app.activities.tbo.bus.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.bus.model.busbookinghistorymodel.BusBookingHistoryModel;

import java.util.ArrayList;

import retrofit2.Callback;

public class BusBookingHistoryAdapter extends RecyclerView.Adapter<BusBookingHistoryAdapter.MyViewHolder> {
    Context context;
    BusBookingHistoryAdapter.OnItemClickListener listener;

    ArrayList<com.nictbills.app.activities.tbo.bus.model.busbookinghistorymodel.Data> viewhisotylist = new ArrayList<com.nictbills.app.activities.tbo.bus.model.busbookinghistorymodel.Data>();

    int row_index = -1;
    static String getvalue;
    int selectedItemPosition = 0;

    public BusBookingHistoryAdapter(Context context, ArrayList viewhisotylist , OnItemClickListener listener ) {
        this.context = context;
        this.viewhisotylist = viewhisotylist;
        this.listener = listener;
    }



    @NonNull
    @Override
    public BusBookingHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_adapter_busbookinghistory, parent, false);
        return new BusBookingHistoryAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BusBookingHistoryAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        com.nictbills.app.activities.tbo.bus.model.busbookinghistorymodel.Data data = viewhisotylist.get(position);
        holder.tv_ticketid.setText(viewhisotylist.get(position).getTicketNo());
        holder.tvhotel_orderid.setText(viewhisotylist.get(position).getOrderId());
        holder.tvhotel_price.setText("Rs." + viewhisotylist.get(position).getBusAmount());
        holder.tvbus_status.setText(viewhisotylist.get(position).getBusBookingStatus());
        holder.tvbus_id.setText(viewhisotylist.get(position).getBusId());
        holder.tvinvoiceno.setText(viewhisotylist.get(position).getInvoiceNumber());
        holder.tvbustravelname.setText(viewhisotylist.get(position).getTravellname());
        holder.tvbordingname.setText(viewhisotylist.get(position).getBoardingname());
        holder.tvdroppingname.setText(viewhisotylist.get(position).getDroppingname());
        Log.e("bookedid","idfinal"+viewhisotylist.get(position).getBusbookid());

//

        if(viewhisotylist.get(position).getBusBookingStatus().equals("confirm"))
        {
            holder.btncancelticket.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.btncancelticket.setVisibility(View.GONE);

        }

        holder.btncancelticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bookedid","idfinal"+viewhisotylist.get(position).getBusbookid());
                Log.e("getBusId","idfinal"+viewhisotylist.get(position).getBusId());
                listener.onItemClick(viewhisotylist.get(position).getBusId() ,viewhisotylist.get(position).getBusbookid() ,viewhisotylist.get(position).getTokenId() ,viewhisotylist.get(position).getTraceId());

            }
        });

    }

    @Override
    public int getItemCount() {
        return viewhisotylist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvbustravelname ,tvbordingname ,tvdroppingname ,tv_ticketid, tvbus_status, tvbus_id, tvinvoiceno, tvhotel_price, tvhotel_orderid, tvhotel_confrefno, tvhotel_hotelcode, tvhotel_hotelname, tvbus_arrivetime, tvbus_seat, tv_rating_value, hotel_address, tvFlightNo, tvduration, tvendtime, tvstarttime, tvstartpoint, tvflightsetavilable, tvendpoint, tvofferprice, tvpublishprice;
        Button btncancelticket;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ticketid = itemView.findViewById(R.id.tv_ticket_id);
            tvhotel_orderid = itemView.findViewById(R.id.tv_order_id);
            tvhotel_price = itemView.findViewById(R.id.tv_price);
            tvbus_status = itemView.findViewById(R.id.tv_bustatus);
            tvbus_id = itemView.findViewById(R.id.tv_busid);
            tvinvoiceno = itemView.findViewById(R.id.tv_invoice);
            tvbustravelname = itemView.findViewById(R.id.bustravname_name);
            tvbordingname = itemView.findViewById(R.id.tv_bordingname);
            tvdroppingname = itemView.findViewById(R.id.tv_droppingname);
            btncancelticket = itemView.findViewById(R.id.btn_ticketcancel);
        }
    }

    public  interface OnItemClickListener
    {
        void onItemClick(String busid ,String  bookdid ,String tokenid, String traceid);

    }

}
