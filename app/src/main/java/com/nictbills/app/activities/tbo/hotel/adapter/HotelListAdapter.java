package com.nictbills.app.activities.tbo.hotel.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.hotel.HotelDashBoardActivity;
import com.nictbills.app.activities.tbo.hotel.model.hotellistresponse.HotelListResponseModel;

import java.util.ArrayList;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.MyViewHolder> {

    Context context;
    HotelListAdapter.OnItemClickListener listener;
    ArrayList<HotelListResponseModel> hotellist = new ArrayList<HotelListResponseModel>();

    public HotelListAdapter(Context context, ArrayList<HotelListResponseModel> hotellist, HotelListAdapter.OnItemClickListener listener) {
        this.context = context;
        this.hotellist = hotellist;
        this.listener = listener;
    }


    @NonNull
    @Override
    public HotelListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_listhotelsearch, parent, false);
        return new HotelListAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HotelListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HotelListResponseModel hotelResultlist = hotellist.get(position);
        holder.tbhotel_name.setText(hotellist.get(position).getHotel_name());
        holder.tv_rating_value.setText("(" + hotellist.get(position).getHotel_ratingvalue() + ")" + "Star");
//        holder.ratingBar.setRating(hotellist.get(position).getStarRating());
        holder.hotel_address.setText(hotellist.get(position).getHoteladdress());
        holder.hotel_amount.setText("Rs."+hotellist.get(position).getHotel_price());
        Glide.with(holder.itemView)
                .load(hotellist.get(position).getHotel_img())
                .placeholder(R.drawable.hotel)
                .into(holder.hotel_img);


        holder.rlBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(hotellist.get(position).getHotle_CategoryId().equals("NOCategoryId"))
                {
                    Toast.makeText(context, "CategoryId is Null ", Toast.LENGTH_SHORT).show();

                }
                else {
                    listener.onItemClick(hotellist.get(position).getHotel_index(),hotellist.get(position).getHotel_code() ,hotellist.get(position).getHotel_name() ,hotellist.get(position).getHotle_CategoryId());

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotellist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tbhotel_name,hotel_amount , tv_rating_value, hotel_address ,tvFlightNo, tvduration, tvendtime, tvstarttime, tvstartpoint, tvflightsetavilable, tvendpoint, tvofferprice, tvpublishprice;
        RelativeLayout rlBookNow;
        RatingBar ratingBar;
        ImageView hotel_img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tbhotel_name = itemView.findViewById(R.id.tv_hotelname);
            tv_rating_value = itemView.findViewById(R.id.tv_ratingvalue);
            hotel_address = itemView.findViewById(R.id.tv_hotel_address);
            hotel_amount = itemView.findViewById(R.id.amount);
            hotel_img = itemView.findViewById(R.id.hotel_img);
            rlBookNow = itemView.findViewById(R.id.rlBookNow);
//            ratingBar = itemView.findViewById(R.id.rating);
        }
    }

    public void updateList( ArrayList<HotelListResponseModel>  list) {
        hotellist = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(String gethotel_index,String gethotel_code ,String gethotel_name,String catgeory);

    }

}
