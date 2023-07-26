package com.nictbills.app.activities.tbo.hotel.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.flight.adapter.GetPassengerAdapter;
import com.nictbills.app.activities.tbo.flight.model.getpassenger.AddPassenger;

import java.util.ArrayList;

public class HotelPassengerListAdapter extends RecyclerView.Adapter<HotelPassengerListAdapter.MyViewHolder> {

    Context context;
    ArrayList<com.nictbills.app.activities.tbo.hotel.model.getpasengerlistres.AddPassenger> hotelplist= new ArrayList<com.nictbills.app.activities.tbo.hotel.model.getpasengerlistres.AddPassenger>();

    int row_index = -1;
    static String getvalue;
    int selectedItemPosition = 0;

    public HotelPassengerListAdapter(Context context, ArrayList<com.nictbills.app.activities.tbo.hotel.model.getpasengerlistres.AddPassenger> hotelplist) {
        this.context = context;
        this.hotelplist = hotelplist;
    }


    @NonNull
    @Override
    public HotelPassengerListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout_hotel_passengerlist, parent, false);
        return new HotelPassengerListAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HotelPassengerListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        com.nictbills.app.activities.tbo.hotel.model.getpasengerlistres.AddPassenger getpassenger = hotelplist.get(position);
        holder.getfullname.setText(getpassenger.getFirstName());
        holder.getgender.setText(getpassenger.getAge());

        if(getpassenger.getEmail().equals(""))
        {
            holder.getemail.setVisibility(View.GONE);
        }
        else
        {
            holder.getemail.setText(getpassenger.getEmail());

        }





    }

    @Override
    public int getItemCount() {
        return hotelplist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView getfullname, getemail, getgender, tvbus_price, tvbus_arrivetime, tvbus_seat, tv_rating_value, hotel_address, tvFlightNo, tvduration, tvendtime, tvstarttime, tvstartpoint, tvflightsetavilable, tvendpoint, tvofferprice, tvpublishprice;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            getfullname = itemView.findViewById(R.id.get_fullname);
            getemail = itemView.findViewById(R.id.get_email);
            getgender = itemView.findViewById(R.id.get_gender);


        }
    }


}
