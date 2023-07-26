package com.nictbills.app.activities.tbo.hotel.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.bus.adapter.BusBookingHistoryAdapter;
import com.nictbills.app.activities.tbo.flight.adapter.FlightViewHistoryAdapter;
import com.nictbills.app.activities.tbo.flight.model.flightmodelviewhistory.Data;

import java.util.ArrayList;

public class HotelViewHistoryAdapter  extends RecyclerView.Adapter<HotelViewHistoryAdapter.MyViewHolder> {
    Context context;
    ArrayList<com.nictbills.app.activities.tbo.hotel.model.hotelhistoryresmodel.Data> viewhisotylist = new ArrayList();
    int row_index = -1;
    static String getvalue;
    int selectedItemPosition = 0;
    HotelViewHistoryAdapter.OnItemClickListener listener;

    public HotelViewHistoryAdapter(Context context, ArrayList<com.nictbills.app.activities.tbo.hotel.model.hotelhistoryresmodel.Data> viewhisotylist , HotelViewHistoryAdapter.OnItemClickListener listener) {
        this.context = context;
        this.viewhisotylist = viewhisotylist;
        this.listener = listener;
    }


    @NonNull
    @Override
    public HotelViewHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotelview_history_layadapter, parent, false);
        return new HotelViewHistoryAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HotelViewHistoryAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        com.nictbills.app.activities.tbo.hotel.model.hotelhistoryresmodel.Data data = viewhisotylist.get(position);
        holder.tvhotel_bookingid.setText(viewhisotylist.get(position).getBookingId());
        holder.tvhotel_hotelcode.setText(viewhisotylist.get(position).getHotelCode());
        holder.tvhotel_orderid.setText(viewhisotylist.get(position).getOrderId());
        holder.tvhotel_name.setText(viewhisotylist.get(position).getHotelName());
        holder.tvhotel_confrefno.setText(viewhisotylist.get(position).getConfirmationNo());
        holder.tvhotel_price.setText("Rs."+viewhisotylist.get(position).getPublishedPrice());
        holder.tvhotel_status.setText(viewhisotylist.get(position).getHotelBookingStatus());


        if(viewhisotylist.get(position).isIscanceled()==true)
        {
            holder.btncancel.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.btncancel.setVisibility(View.GONE);

        }
        holder.btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(viewhisotylist.get(position).getBookingId(),viewhisotylist.get(position).getId() ,viewhisotylist.get(position).getTokenid(),viewhisotylist.get(position).getEdnuser_ip());

            }
        });

    }

    @Override
    public int getItemCount() {
        return viewhisotylist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvhotel_name  ,tvhotel_status ,tvhotel_bookingid, tvhotel_price ,tvhotel_orderid ,tvhotel_confrefno, tvhotel_hotelcode, tvhotel_hotelname, tvbus_arrivetime, tvbus_seat, tv_rating_value, hotel_address, tvFlightNo, tvduration, tvendtime, tvstarttime, tvstartpoint, tvflightsetavilable, tvendpoint, tvofferprice, tvpublishprice;
        Button btncancel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvhotel_bookingid = itemView.findViewById(R.id.tv_hotelbooking_id);
            tvhotel_confrefno = itemView.findViewById(R.id.tv_hconfrefid);
            tvhotel_hotelcode= itemView.findViewById(R.id.tv_hotelcode);
            tvhotel_orderid= itemView.findViewById(R.id.tv_order_id);
            tvhotel_price= itemView.findViewById(R.id.tv_price);
            tvhotel_name= itemView.findViewById(R.id.hotel_name);
            tvhotel_status= itemView.findViewById(R.id.tv_hotelstatus);
            btncancel= itemView.findViewById(R.id.btn_ticketcancel_hotel);
        }
    }

    public  interface OnItemClickListener
    {
        void onItemClick(String bookingid, String id ,String token_id ,String enduser_ip);

    }
}

