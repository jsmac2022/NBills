package com.nictbills.app.activities.farmequipment.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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

import com.bumptech.glide.Glide;
import com.nictbills.app.R;
import com.nictbills.app.activities.farmequipment.model.farmlistresponsemodel.FarmEquipmentListResponseModel;

import java.util.ArrayList;

public class FarmEquipmentListAdapter  extends RecyclerView.Adapter<FarmEquipmentListAdapter.MyViewHolder> {
    SharedPreferences shared;

    Context context;
    FarmEquipmentListAdapter.OnItemClickListener listener;
    ArrayList<FarmEquipmentListResponseModel> farmlist = new ArrayList<FarmEquipmentListResponseModel>();

    public FarmEquipmentListAdapter(Context context, ArrayList<FarmEquipmentListResponseModel> farmlist, FarmEquipmentListAdapter.OnItemClickListener listener) {
        this.context = context;
        this.farmlist = farmlist;
        this.listener = listener;
    }


    @NonNull
    @Override
    public FarmEquipmentListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_adapter_farmlist, parent, false);
        return new FarmEquipmentListAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FarmEquipmentListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FarmEquipmentListResponseModel farmEquipmentListResponseModel = farmlist.get(position);
        shared = context.getSharedPreferences("nict", MODE_PRIVATE);

        holder.farm_title.setText(farmlist.get(position).getName());
        holder.farm_desc.setText(farmlist.get(position).getDescription());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SharedPreferences.Editor editor = shared.edit();
//                editor.putString("Title", farmlist.get(position).getName());
//                editor.putString("Description", farmlist.get(position).getDescription());
//                editor.putString("img", farmlist.get(position).getImagePath());
//                editor.apply();
                listener.onItemClick(farmlist.get(position).getName(),farmlist.get(position).getDescription() ,farmlist.get(position).getRent_amount() ,farmlist.get(position).getId() ,farmlist.get(position).getImagePath() ,farmlist.get(position).getAvailableLocation());

            }
        });
        Glide.with(holder.itemView)
                .load(farmlist.get(position).getImagePath())
                .placeholder(R.drawable.hotel)
                .into(holder.hotel_img);


    }

    @Override
    public int getItemCount() {
        return farmlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView farm_title ,farm_desc;
        ImageView hotel_img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            farm_title= itemView.findViewById(R.id.tv_titlefarmlist);
            farm_desc = itemView.findViewById(R.id.tv_descfarmlist);
            hotel_img = itemView.findViewById(R.id.imgView_icon);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(String title ,String description ,String rent_amount,String productid ,String img ,String location);

    }

}
