package com.nictbills.app.activities.tbo.bus.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.bus.fragment.BusBoradingMainActivity;
import com.nictbills.app.activities.tbo.bus.model.bordingpointmodel.BoardingPointsDetailsModelResponse;
import com.nictbills.app.activities.tbo.bus.model.bordingpointmodel.DropingPointsDetailsModelResponse;
import com.nictbills.app.activities.tbo.hotel.HotelRoomGetActivity;

import java.util.ArrayList;

public class DroppingPointBusAdapter extends RecyclerView.Adapter<DroppingPointBusAdapter.MyViewHolder> {
    Context context;
    SharedPreferences shared;

    ArrayList<DropingPointsDetailsModelResponse> droplist = new ArrayList<DropingPointsDetailsModelResponse>();
    private RadioButton lastCheckedRB = null;
    HotelRoomGetActivity hotelRoomGetActivity;
    int selectedPosition = -1;
    BusBoradingMainActivity busBoradingMainActivity;

    public DroppingPointBusAdapter(Context context, ArrayList<DropingPointsDetailsModelResponse> droplist) {
        this.context = context;
        this.droplist = droplist;
    }


    @NonNull
    @Override
    public DroppingPointBusAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_adapter_droppingpointbus, parent, false);
        return new DroppingPointBusAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DroppingPointBusAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DropingPointsDetailsModelResponse dropingPointsDetailsModelResponse = droplist.get(position);
        shared = context.getSharedPreferences("nict", MODE_PRIVATE);

        holder.tvdropping_desc.setText(droplist.get(position).getDroping_pointname());
        holder.tvdropping_location.setText(droplist.get(position).getDroping_PointLocation());
        holder.tvdropping_time.setText(droplist.get(position).getDroping_PointTime());
        Log.e("sam...","around"+droplist.size());

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
                            editor.putString("Dropingindex", droplist.get(position).getDroping_pointindex());
                            editor.putString("Dropinname", droplist.get(position).getDroping_pointname());
                            editor.apply();
                            busBoradingMainActivity.btncontinue.setVisibility(View.VISIBLE);
                            notifyDataSetChanged();

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
        return droplist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvdropping_desc, tvdropping_location, tvdropping_time, tvroom_avilability;
        RelativeLayout rl_roomselect;
        RatingBar ratingBar;
        ImageView hotel_img;
        RadioButton radioButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvdropping_desc = itemView.findViewById(R.id.tv_dropaddress);
            tvdropping_location = itemView.findViewById(R.id.tv_droplocation);
            tvdropping_time = itemView.findViewById(R.id.tv_droptime);
            radioButton = itemView.findViewById(R.id.radio_dropping);
        }
    }

//    public interface OnItemClickListener {
//        void onItemClick();
//    }
}


