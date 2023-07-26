package com.nictbills.app.activities.tbo.hotel.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.hotel.model.hotelinfoimg.HotelinfoImageModel;

import java.util.ArrayList;

public class HotelInfoImageAdapter extends RecyclerView.Adapter<HotelInfoImageAdapter.MyViewHolder> {

    Context context;
    ArrayList<HotelinfoImageModel> hotelimglist = new ArrayList<HotelinfoImageModel>();

    int row_index = -1;
    static String getvalue;
    int selectedItemPosition = 0;

//    public HotelInfoImageAdapter(Context context, ArrayList<HotelinfoImageModel> hotelimglist, HotelPassengerNumberAdapter.OnItemClickListener listener) {
//        this.context = context;
//        this.hotelimglist = hotelimglist;
//        this.listener = listener;
//    }

    public HotelInfoImageAdapter(Context context, ArrayList<HotelinfoImageModel> hotelimglist) {
        this.context = context;
        this.hotelimglist = hotelimglist;
    }

    @NonNull
    @Override
    public HotelInfoImageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotelinfo_adapterimg, parent, false);
        return new HotelInfoImageAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HotelInfoImageAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HotelinfoImageModel hotelinfoImageModel = hotelimglist.get(position);
        Log.e("img",".ui"+hotelinfoImageModel.getHotelimg());
        Log.e("img",".ui12"+hotelimglist.get(position).getHotelimg());

//        holder.addedname.setText("Passenger" + addedpassengerlist.get(position).getPassangerno());


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedItemPosition = position;
//                notifyDataSetChanged();
//                listener.onItemClick(addedpassengerlist.get(position).getPassangerno());
//
//            }
//        });
//
//        if (selectedItemPosition == position) {
//            holder.itemView.setBackgroundColor(Color.parseColor("#4CAF50"));
//            Log.e("ytyif", ":ytyif" + addedpassengerlist.get(position).getPassangerno());
////            holder.addedname.setTextColor(Color.parseColor("#DC746C"));
//        } else {
//            holder.itemView.setBackgroundColor(Color.parseColor("#F6F7FF"));
//            Log.e("ytyelse", ":ytyelse" + addedpassengerlist.get(position).getPassangerno());
////            holder.addedname.setTextColor(Color.parseColor("#F6F7FF"));
//
//        }

//        Glide.with(context)
//                .asBitmap()
//                .load(hotelimglist.get(position).getHotelimg())
//                .error(R.drawable.hotel)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(new CustomTarget<Bitmap>() {
//
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        holder.hotel_img.setImageBitmap(resource);
//                        holder.hotel_img.buildDrawingCache();
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//                    }
//                });

        Glide.with(holder.itemView)
                .load(hotelimglist.get(position).getHotelimg())
                .placeholder(R.drawable.hotel)
                .into(holder.hotel_img);


    }

    @Override
    public int getItemCount() {
        return hotelimglist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ;
        ImageView hotel_img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hotel_img = itemView.findViewById(R.id.hotel_img);


        }
    }

    public interface OnItemClickListener {
        void onItemClick(String getdata);

    }

}
