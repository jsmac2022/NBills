package com.nictbills.app.activities.tbo.flight.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.flight.model.FlightSeatModel;

import java.util.ArrayList;

public class FlightSeatAdapter extends RecyclerView.Adapter<FlightSeatAdapter.MyViewHolder> {
    //    ArrayList personNames=;
    ArrayList<FlightSeatModel> list = new ArrayList<>();
    //   ArrayList<Seat> list = new ArrayList<>() ;
    Context context;
    OnItemClickListener listener;
    int click = 0;
    SharedPreferences shared;
    int getadult_count;

    int row_index = -1;
    int clickcount=0;
    double amount=0;
    String seatno="";
    String cama=",";
    public FlightSeatAdapter(Context context, ArrayList list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public FlightSeatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flightseat_adapter_view, parent, false);

        return new FlightSeatAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FlightSeatAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FlightSeatModel flightSeatModel = list.get(position);
        shared = context.getSharedPreferences("nict", MODE_PRIVATE);

        getadult_count = Integer.parseInt(String.valueOf(Integer.parseInt(shared.getString("Adultno", ""))));
        holder.tvseatcode.setText(list.get(position).getSeat_code());
        Log.e("....", "hhhhh" + getadult_count);
//        holder.view.setBackgroundColor(flightSeatModel.isSelected() ? Color.CYAN : Color.WHITE);

        if (list.get(position).getAvilability_type().equals("1")) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getadult_count==1)
                    {
                        row_index = position;
                        notifyDataSetChanged();
                        amount=Double.valueOf(list.get(position).getPrice());
                        seatno=list.get(position).getSeat_code();
                        listener.onItemClick(list.get(position).getSeat_code(), list.get(position).getPrice() , list.get(position).getAvilability_type() ,getadult_count ,amount,seatno);

                    }
                    else
                    {
                        clickcount=clickcount+1;
                        if(clickcount<=getadult_count)
                        {
                            amount=amount+Double.valueOf(list.get(position).getPrice());
                            seatno=seatno+list.get(position).getSeat_code()+cama;
                            Toast.makeText(context,"yes", Toast.LENGTH_LONG).show();
                            holder.layout_seat.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
                            listener.onItemClick(list.get(position).getSeat_code(),list.get(position).getPrice() , list.get(position).getAvilability_type() ,getadult_count ,amount,seatno);
                        }
                        else
                        {
                            Toast.makeText(context,"You can select only by paassenge no", Toast.LENGTH_LONG).show();

                        }

                    }




                }
            });


        }
        else if (list.get(position).getAvilability_type().equals("3")) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.dillog_onclickseat);
                    Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog_click);
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }
            });

        }

        if (getadult_count==1) {
            if (row_index == position) {
                holder.layout_seat.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
            }
            else {
                if (list.get(position).getAvilability_type().equals("1")) {
                    holder.layout_seat.setBackgroundColor(ContextCompat.getColor(context, R.color.light_sky_blue));
                    Log.e("baba1","ty");
                } else {
                    holder.layout_seat.setBackgroundColor(ContextCompat.getColor(context, R.color.colorGray1));
                    Log.e("else454","ty");
                }
            }
        }

        else {
            if (list.get(position).getAvilability_type().equals("1")) {
                holder.layout_seat.setBackgroundColor(ContextCompat.getColor(context, R.color.light_sky_blue));
            } else {
                holder.layout_seat.setBackgroundColor(ContextCompat.getColor(context, R.color.colorGray1));

            }
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvseatcode, tvFlightName, tvFlightNo, tvduration, tvendtime, tvstarttime, tvstartpoint, tvflightsetavilable, tvendpoint, tvofferprice, tvpublishprice;
        RelativeLayout rlBookNow;
        ImageView imageViewseat;
        LinearLayout layout_seat;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

//            imageViewseat = itemView.findViewById(R.id.seat_img);
            tvseatcode = itemView.findViewById(R.id.tv_seatcode);
            layout_seat = itemView.findViewById(R.id.lay_seat);
//            tvFlightNo = itemView.findViewById(R.id.tvFlightNo);
//            tvflightsetavilable = itemView.findViewById(R.id.tvFlightSeats);
//            tvstartpoint = itemView.findViewById(R.id.tvStartpoint);
//            tvstarttime = itemView.findViewById(R.id.tvStartTiming);
//            tvendtime = itemView.findViewById(R.id.tvEntTiming);
//            tvendpoint = itemView.findViewById(R.id.tvEndpoint);
//            tvpublishprice = itemView.findViewById(R.id.tvpubPrice);
//            tvofferprice = itemView.findViewById(R.id.tvofferPrice);
//            tvduration = itemView.findViewById(R.id.tvduration);
//            rlBookNow = itemView.findViewById(R.id.rlBookNow);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String seatcode, String price ,String seatavilability,int getadult_count ,double finalamount ,String seatno );

    }

}

