package com.nictbills.app.activities.tbo.bus.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.bus.model.bussearchresmodel.BusAdapterResponseModel;

import java.util.ArrayList;

public class BusListAdapter extends RecyclerView.Adapter<BusListAdapter.MyViewHolder> {

    //    ArrayList<HotelResult> hotellist = new ArrayList<HotelResult>();
    Context context;
    BusListAdapter.OnItemClickListener listener;
    ArrayList<BusAdapterResponseModel> busllist = new ArrayList<BusAdapterResponseModel>();

    public BusListAdapter(Context context, ArrayList<BusAdapterResponseModel> busllist, BusListAdapter.OnItemClickListener listener) {
        this.context = context;
        this.busllist = busllist;
        this.listener = listener;
    }


    @NonNull
    @Override
    public BusListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_layout_adapter, parent, false);
        return new BusListAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BusListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BusAdapterResponseModel busAdapterResponseModel = busllist.get(position);
        holder.tvbus_name.setText(busllist.get(position).getBus_name());
        holder.tvbus_type.setText(busllist.get(position).getBus_type());
        holder.tvbus_dapttime.setText(busllist.get(position).getBus_departuretime());
        holder.tvbus_arrivetime.setText(busllist.get(position).getBus_arrivetime());
        holder.tvbus_seat.setText(busllist.get(position).getBus_seatavilability()+ " "+"Seat Left");

//        String s = String.format("%.1f",busllist.get(position).getBus_published_price());
        double number1 = Double.parseDouble(busllist.get(position).getBus_published_price());
        double number2 = (int)(Math.round(number1 * 100))/100.0;
        System.out.println(number2);
                    Log.e("number2","on adapter"+number2);
        holder.tvbus_price.setText("Rs."+busllist.get(position).getBus_published_price_roundoff());
//        holder.tvbus_price.setText("Rs."+s);
//        Log.e("..samadapter","tr"+busllist.get(position).getBus_arrivetime());
//        holder.tv_rating_value.setText("(" + hotellist.get(position).getHotel_ratingvalue() + ")" + "Star");
//        holder.hotel_address.setText(hotellist.get(position).getHoteladdress());
//        holder.hotel_amount.setText("Rs."+hotellist.get(position).getHotel_price());
//        Log.e("hotel", "size...." + hotellist.size());
////        Log.e("hotel", "name...." + hotellist.get(position).getHotelName());
//        Glide.with(holder.itemView)
//                .load(hotellist.get(position).getHotel_img())
//                .placeholder(R.drawable.hotel)
//                .into(holder.hotel_img);
//        Log.e("hotel", "img...." + hotellist.get(position).getHotel_img());
//        Log.e("hotel", "index...." + hotellist.get(position).getHotel_index());
//        Log.e("hotel", "code...." + hotellist.get(position).getHotel_code());
//
//
        holder.btnseatview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(busllist.get(position).getBus_resultindex() ,busllist.get(position).getBus_name() ,busllist.get(position).getBus_published_price_roundoff());


            }
        });
    }

    @Override
    public int getItemCount() {
        return busllist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvbus_name,tvbus_type ,tvbus_dapttime ,tvbus_price , tvbus_arrivetime ,tvbus_seat ,tv_rating_value, hotel_address ,tvFlightNo, tvduration, tvendtime, tvstarttime, tvstartpoint, tvflightsetavilable, tvendpoint, tvofferprice, tvpublishprice;
        RelativeLayout rlBookNow;
        RatingBar ratingBar;
        ImageView hotel_img;
        Button btnseatview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvbus_name = itemView.findViewById(R.id.bus_name);
            tvbus_type = itemView.findViewById(R.id.bus_type);
            tvbus_seat = itemView.findViewById(R.id.bus_seatavilable);
            tvbus_price = itemView.findViewById(R.id.price_bus);
            tvbus_dapttime= itemView.findViewById(R.id.tv_daprttime);
            tvbus_arrivetime = itemView.findViewById(R.id.tv_arrivetime);
            btnseatview = itemView.findViewById(R.id.btn_viewseat);


        }
    }

    public interface OnItemClickListener {
        void onItemClick(String getbus_index ,String bustavles,String publishedprice);

    }

}
