package com.nictbills.app.activities.farmequipment.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nictbills.app.R;
import com.nictbills.app.activities.farmequipment.model.farmlistresponsemodel.FarmEquipmentListResponseModel;
import com.nictbills.app.activities.farmequipment.model.orderhistory.OrderHistoryFarmEquipment;

import java.util.ArrayList;

public class FarmOrderHistoryAdapter extends RecyclerView.Adapter<FarmOrderHistoryAdapter.MyViewHolder> {
    SharedPreferences shared;
    Context context;
    FarmOrderHistoryAdapter.OnItemClickListener listener;
    ArrayList<OrderHistoryFarmEquipment> orderlist = new ArrayList<OrderHistoryFarmEquipment>();

    public FarmOrderHistoryAdapter(Context context, ArrayList<OrderHistoryFarmEquipment> orderlist, FarmOrderHistoryAdapter.OnItemClickListener listener) {
        this.context = context;
        this.orderlist = orderlist;
        this.listener = listener;
    }


    @NonNull
    @Override
    public FarmOrderHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_orderfarm_adapter, parent, false);
        return new FarmOrderHistoryAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FarmOrderHistoryAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        OrderHistoryFarmEquipment orderHistoryFarmEquipment = orderlist.get(position);
        holder.tvorder_id.setText(orderlist.get(position).getOrderNo());
        holder.tvbooking_id.setText(orderlist.get(position).getBooking_id());
        holder.tvproduct_id.setText(orderlist.get(position).getProduct_id());
        holder.tvproductrental_name.setText(orderlist.get(position).getRental_time());
        holder.tvorder_date.setText(orderlist.get(position).getOrder_date());
        holder.tvorder_status.setText(orderlist.get(position).getOrder_status());
        holder.tvproduct_name.setText(orderlist.get(position).getEquipment_name());
//        tvproduct_name

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                listener.onItemClick();
//
//            }
//        });

//        Glide.with(holder.itemView)
//                .load(farmlist.get(position).getImagePath())
//                .placeholder(R.drawable.hotel)
//                .into(holder.hotel_img);


    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvorder_id ,tvorder_date ,tvorder_status ,tvbooking_id  ,tvproduct_id ,tvproductrental_name ,tvproduct_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvorder_id= itemView.findViewById(R.id.tv_orderid);
            tvbooking_id= itemView.findViewById(R.id.tv_bookingid);
            tvproduct_name= itemView.findViewById(R.id.product_name);
            tvproductrental_name= itemView.findViewById(R.id.tv_rentaltime);
            tvproduct_id= itemView.findViewById(R.id.tv_productid);
            tvorder_date= itemView.findViewById(R.id.tv_orderdate);
            tvorder_status= itemView.findViewById(R.id.tv_orderstatus);

        }
    }

    public interface OnItemClickListener {
        void onItemClick();

    }

}

