package com.nictbills.app.activities.tbo.flight.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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
import com.nictbills.app.activities.tbo.flight.model.getpassenger.AddPassenger;

import java.util.ArrayList;

public class GetPassengerAdapter extends RecyclerView.Adapter<GetPassengerAdapter.MyViewHolder> {

    Context context;
    ArrayList<AddPassenger>  getpasengerlist = new ArrayList();

    int row_index = -1;
    static String getvalue;
    int selectedItemPosition = 0;

    public GetPassengerAdapter(Context context, ArrayList<AddPassenger> getpasengerlist) {
        this.context = context;
        this.getpasengerlist = getpasengerlist;
    }


    @NonNull
    @Override
    public GetPassengerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.getpassenger_layoutadpter, parent, false);
        return new GetPassengerAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull GetPassengerAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AddPassenger getpassenger = getpasengerlist.get(position);
        holder.getfullname.setText(getpassenger.getFullName());

        if(getpassenger.getGender().equals("M"))
        {
            holder.getgender.setText("Male");

        }
        else{
            holder.getgender.setText("Female");

        }


        if(getpassenger.getEmailId().equals(""))
        {
            holder.getemail.setVisibility(View.GONE);
        }
        else
        {
            holder.getemail.setText(getpassenger.getEmailId());

        }



    }

    @Override
    public int getItemCount() {
        return getpasengerlist.size();
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
