package com.nictbills.app.activities.tbo.flight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.flight.model.citylist.Data;
import com.nictbills.app.activities.tbo.flight.model.search.FlightSearchList;

import org.json.JSONArray;

import java.util.ArrayList;

public class FlitListAdapter extends RecyclerView.Adapter<FlitListAdapter.MyViewHolder> {

    ArrayList personNames;
//    FlightSearchModel flightSearchModel;
    ArrayList<FlightSearchList>  flightlist = new ArrayList<>();
    Context context;
    OnItemClickListener listener;
    JSONArray jsonArray;



//    public FlitListAdapter(Context context, JSONArray jsonArray, OnItemClickListener listener) {
//        this.context = context;
////        this.flightSearchModelslist = flightSearchModelslist;
//        this.jsonArray = jsonArray;
//        this.listener = listener;
//    }
    public FlitListAdapter(Context context, ArrayList flightlist, OnItemClickListener listener) {
        this.context = context;
//        this.flightSearchModelslist = flightSearchModelslist;
        this.flightlist = flightlist;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flit_list, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FlightSearchList flightSearchList=flightlist.get(position);

        Log.e("onadapter", "1" +flightlist.size());
        Log.e("onadapter", "1" +flightlist.get(position).getAirlineCode());

        holder.tvpublishprice.setText("Rs. "+flightlist.get(position).getPublishFared());
        holder.tvofferprice.setText("offer Price "+"Rs. "+flightlist.get(position).getOfferfared());
        holder.tvFlightName.setText(flightlist.get(position).getFlightname());
        holder.tvstartpoint.setText(flightlist.get(position).getCityCode_origin());
        holder.tvstarttime.setText("("+flightlist.get(position).getDepartTime()+")");
        holder.tvendtime.setText("("+flightlist.get(position).getArriveTime()+")");
        holder.tvduration.setText("Duration :"+flightlist.get(position).getDuration());
        holder.tvINDEX.setText(flightlist.get(position).getResulrIndex());
        holder.tvTRUE.setText(flightlist.get(position).getIsLLC());
        holder.tvendpoint.setText(flightlist.get(position).getCityCode_destination());
        holder.tvflightsetavilable.setText(flightlist.get(position).getFlighseatavilable()+" "+"Seat(s) left");
        holder.tvFlightNo.setText(flightlist.get(position).getAirlineCode() +" - "+flightlist.get(position).getFlightno());

        holder.rlBookNow.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(flightlist.get(position).getPublishFared(),flightlist.get(position).getOfferfared(),flightlist.get(position).getFlightname() ,flightlist.get(position).getCityCode_origin(),flightlist.get(position).getCityCode_destination() ,flightlist.get(position).getFlighseatavilable(),flightlist.get(position).getAirlineCode(),flightlist.get(position).getFlightno(),flightlist.get(position).getFulldepttime() ,flightlist.get(position).getFullarrtime() ,flightlist.get(position).getResulrIndex() ,flightlist.get(position).getIsLLC());


            }
        });


    }

    @Override
    public int getItemCount() {
        return flightlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvFlightName ,tvINDEX ,tvTRUE ,tvFlightNo ,tvduration , tvendtime ,tvstarttime ,tvstartpoint ,tvflightsetavilable ,tvendpoint ,tvofferprice ,tvpublishprice;
        RelativeLayout rlBookNow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFlightName = itemView.findViewById(R.id.tvFlightName);
            tvFlightNo = itemView.findViewById(R.id.tvFlightNo);
            tvflightsetavilable = itemView.findViewById(R.id.tvFlightSeats);
            tvstartpoint = itemView.findViewById(R.id.tvStartpoint);
            tvstarttime = itemView.findViewById(R.id.tvStartTiming);
            tvendtime = itemView.findViewById(R.id.tvEntTiming);
            tvendpoint = itemView.findViewById(R.id.tvEndpoint);
            tvpublishprice = itemView.findViewById(R.id.tvpubPrice);
            tvofferprice = itemView.findViewById(R.id.tvofferPrice);
            tvduration = itemView.findViewById(R.id.tvduration);
            tvINDEX = itemView.findViewById(R.id.tvINDEX);
            tvTRUE = itemView.findViewById(R.id.tvTRUE);
            rlBookNow = itemView.findViewById(R.id.rlBookNow);
        }
    }

    public void updateList( ArrayList<FlightSearchList>  list) {

        flightlist = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(String publishedfared ,String offerfared ,String flightname ,String origincode ,String destinationcode,String seatavilable,String airlinecode,String flightno ,String fuldepartdatetime  ,String fularrdatetime ,String getresultindex,String getLlcType);

    }



}
