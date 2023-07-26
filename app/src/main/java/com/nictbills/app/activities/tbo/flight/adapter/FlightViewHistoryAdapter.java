package com.nictbills.app.activities.tbo.flight.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.flight.model.flightmodelviewhistory.Data;
import com.nictbills.app.activities.tbo.flight.model.getpassenger.AddPassenger;

import java.util.ArrayList;

public class FlightViewHistoryAdapter extends RecyclerView.Adapter<FlightViewHistoryAdapter.MyViewHolder> {

    Context context;
    ArrayList<Data> viewhisotylist = new ArrayList();
    int row_index = -1;
    static String getvalue;
    int selectedItemPosition = 0;

    public FlightViewHistoryAdapter(Context context, ArrayList<Data> viewhisotylist) {
        this.context = context;
        this.viewhisotylist = viewhisotylist;
    }


    @NonNull
    @Override
    public FlightViewHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_viewhistory_layoutadapter, parent, false);
        return new FlightViewHistoryAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FlightViewHistoryAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Data data = viewhisotylist.get(position);
        holder.tvflightblookingid.setText(viewhisotylist.get(position).getBookingId());
        holder.tv_pnrno.setText(viewhisotylist.get(position).getPnr());
        holder.tv_ordewrid.setText(viewhisotylist.get(position).getOrderId());
        holder.tv_price.setText("Rs. "+viewhisotylist.get(position).getPublishedFare());
        holder.tv_flightno.setText(viewhisotylist.get(position).getFlightNumber());
        holder.tv_airlinecode.setText(viewhisotylist.get(position).getAirlineCode());
        holder.tvflightname.setText(viewhisotylist.get(position).getFlightname());

//


    }

    @Override
    public int getItemCount() {
        return viewhisotylist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvflightname ,tvflightblookingid,tv_airlinecode , tv_pnrno, tv_ordewrid, tv_price, tv_flightname, tv_flightno, tv_rating_value, hotel_address, tvFlightNo, tvduration, tvendtime, tvstarttime, tvstartpoint, tvflightsetavilable, tvendpoint, tvofferprice, tvpublishprice;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvflightblookingid = itemView.findViewById(R.id.tv_flightbookingid);
            tv_pnrno = itemView.findViewById(R.id.tv_pnrid);
            tv_ordewrid = itemView.findViewById(R.id.tv_orderid);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_flightno = itemView.findViewById(R.id.tv_flightno);
            tv_airlinecode = itemView.findViewById(R.id.tv_airlinecode);
            tvflightname = itemView.findViewById(R.id.tv_flightname);


        }
    }


}
