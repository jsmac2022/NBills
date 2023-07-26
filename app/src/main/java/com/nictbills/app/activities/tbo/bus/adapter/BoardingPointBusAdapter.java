package com.nictbills.app.activities.tbo.bus.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.bus.fragment.BusBoradingMainActivity;
import com.nictbills.app.activities.tbo.bus.model.bordingpointmodel.BoardingPointsDetailsModelResponse;
import com.nictbills.app.activities.tbo.hotel.HotelRoomGetActivity;

import java.util.ArrayList;

public class BoardingPointBusAdapter extends RecyclerView.Adapter<BoardingPointBusAdapter.MyViewHolder> {
    Context context;
    SharedPreferences shared;

    //    BoardingPointBusAdapter.OnItemClickListener listener;
    ArrayList<BoardingPointsDetailsModelResponse> pickuplist = new ArrayList<BoardingPointsDetailsModelResponse>();
    int selectedPosition = -1;
    BusBoradingMainActivity busBoradingMainActivity;
    public BoardingPointBusAdapter(Context context, ArrayList<BoardingPointsDetailsModelResponse> pickuplist) {
        this.context = context;
        this.pickuplist = pickuplist;
    }


    @NonNull
    @Override
    public BoardingPointBusAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_adapter_busboardingpoint, parent, false);
        return new BoardingPointBusAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BoardingPointBusAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BoardingPointsDetailsModelResponse boardingPointsDetailsModelResponse = pickuplist.get(position);
        shared = context.getSharedPreferences("nict", MODE_PRIVATE);

        holder.tvpickup_desc.setText(pickuplist.get(position).getBoarding_pointname());
        holder.tvpickup_location.setText(pickuplist.get(position).getBoarding_PointLocation());
        holder.tvpickup_ptime.setText(pickuplist.get(position).getBoarding_pointtime());
        holder.tvpickup_pcontact.setText(pickuplist.get(position).getBoarding_pointcontactno());
        holder.radioButton.setChecked(position
                == selectedPosition);

        holder.radioButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            CompoundButton compoundButton,
                            boolean b) {

                        if (b)
                        {
                            selectedPosition = holder.getAdapterPosition();

                            SharedPreferences.Editor editor = shared.edit();
                            editor.putString("Bordingindex", pickuplist.get(position).getBoarding_pointindex());
                            editor.putString("originname", pickuplist.get(position).getBoarding_pointname());
                            editor.apply();
                            notifyDataSetChanged();

                            busBoradingMainActivity.viewPager.setCurrentItem(2);


                        }

                    }
                });



    }

    @Override
    public long getItemId(int position) {
        // pass position
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        // pass position
        return position;
    }

    @Override
    public int getItemCount() {
        return pickuplist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvpickup_desc, tvpickup_location, tvpickup_ptime, tvpickup_pcontact;
        RelativeLayout rl_roomselect;
        RatingBar ratingBar;
        ImageView hotel_img;
        RadioButton radioButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvpickup_desc = itemView.findViewById(R.id.tv_pickupaddress);
            tvpickup_location = itemView.findViewById(R.id.tv_puplocation);
            tvpickup_ptime = itemView.findViewById(R.id.tv_puptime);
            tvpickup_pcontact = itemView.findViewById(R.id.tv_pcontact);
            radioButton = itemView.findViewById(R.id.radio_pickup);
        }
    }

//    public interface OnItemClickListener {
//        void onItemClick();
//    }
}

