package com.nictbills.app.activities.tbo.hotel.adapter;

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
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.hotel.HotelRoomGetActivity;
import com.nictbills.app.activities.tbo.hotel.model.hotelgetroom.HotelGeRoomResponse;
import com.nictbills.app.activities.tbo.hotel.model.hotelroomlistresponse.HotelRoomListResponse;

import java.util.ArrayList;

import retrofit2.Callback;

public class HotelRoomListAdapter extends RecyclerView.Adapter<HotelRoomListAdapter.MyViewHolder> {
    SharedPreferences shared;
    int finalroomcount = 0;
    String roomcount;
    int selectedPosition = -1;
    int clickcount = 0;
    int row_index = -1;
    Context context;
    HotelRoomListAdapter.OnItemClickListener listener;
    ArrayList<HotelRoomListResponse> hote_room_list = new ArrayList<HotelRoomListResponse>();
    private RadioButton lastCheckedRB = null;
    HotelRoomGetActivity hotelRoomGetActivity;
    public HotelRoomListAdapter(Context context, ArrayList<HotelRoomListResponse> hote_room_list, HotelRoomListAdapter.OnItemClickListener listener) {
        this.context = context;
        this.hote_room_list = hote_room_list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public HotelRoomListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_adapter_hotelrrom, parent, false);
        return new HotelRoomListAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HotelRoomListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HotelRoomListResponse hotelResultlist = hote_room_list.get(position);
        holder.tvroom_desc.setText(hote_room_list.get(position).getRatePlanName());
        holder.tvroom_rateplan.setText(hote_room_list.get(position).getRoomDescription());
        holder.tvroom_hotelprice.setText("Rs." + hote_room_list.get(position).getHotel_room_price());
        holder.tvroom_avilability.setText(hote_room_list.get(position).getAvailabilityType());
        shared = context.getSharedPreferences("nict", MODE_PRIVATE);
        roomcount = shared.getString("roomnocount", "");
        finalroomcount=Integer.parseInt(roomcount);

//        holder.checkroom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                if(finalroomcount==1)
//                {
//                    if (isChecked) {
//                        Log.e("1", "true");
//                        row_index = position;
//                        notifyDataSetChanged();
//                        listener.onItemClick(hote_room_list.get(position).getRoomDescription(), hote_room_list.get(position).getRoomIndex(), hote_room_list.get(position).getRoomTypeCode(), hote_room_list.get(position).getRoomTypeName(), hote_room_list.get(position).getRatePlanName(), hote_room_list.get(position).getRatePlanCode(), hote_room_list.get(position).getCurrencyCode(), hote_room_list.get(position).getRoomPrice(), hote_room_list.get(position).getTax(), hote_room_list.get(position).getExtraGuestCharge(), hote_room_list.get(position).getChildCharge(), hote_room_list.get(position).getOtherCharges(), hote_room_list.get(position).getPublishedPrice(), hote_room_list.get(position).getOfferedPrice(), hote_room_list.get(position).getPublishedPriceRoundedOff(), hote_room_list.get(position).getOfferedPriceRoundedOff(), hote_room_list.get(position).getServiceTax(), hote_room_list.get(position).getDiscount(), hote_room_list.get(position).getAgentCommission(), hote_room_list.get(position).getAgentMarkUp(), hote_room_list.get(position).getTDS() ,1 ,"checked");
//                    }
//                    else {
//                        Log.e("2", "false");
//                        listener.onItemClick("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""  ,1 ,"unchecked");
//                    }
//
//                }
//                else
//                {
//                    if (isChecked) {
//                        clickcount=clickcount+1;
//                        if(clickcount<=finalroomcount)
//                        {
//                            listener.onItemClick(hote_room_list.get(position).getRoomDescription(), hote_room_list.get(position).getRoomIndex(), hote_room_list.get(position).getRoomTypeCode(), hote_room_list.get(position).getRoomTypeName(), hote_room_list.get(position).getRatePlanName(), hote_room_list.get(position).getRatePlanCode(), hote_room_list.get(position).getCurrencyCode(), hote_room_list.get(position).getRoomPrice(), hote_room_list.get(position).getTax(), hote_room_list.get(position).getExtraGuestCharge(), hote_room_list.get(position).getChildCharge(), hote_room_list.get(position).getOtherCharges(), hote_room_list.get(position).getPublishedPrice(), hote_room_list.get(position).getOfferedPrice(), hote_room_list.get(position).getPublishedPriceRoundedOff(), hote_room_list.get(position).getOfferedPriceRoundedOff(), hote_room_list.get(position).getServiceTax(), hote_room_list.get(position).getDiscount(), hote_room_list.get(position).getAgentCommission(), hote_room_list.get(position).getAgentMarkUp(), hote_room_list.get(position).getTDS() ,clickcount ,"checked");
//                        }
//                        else
//                        {
//                            clickcount=clickcount-1;
//                            holder.checkroom.setChecked(false);
//                            listener.onItemClick("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" ,0 ,"unchecked");
//                            Toast.makeText(context,"You can select only"+finalroomcount+" Room", Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                    else
//                    {
//                        clickcount=clickcount-1;
//                        listener.onItemClick("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" ,0,"unchecked");
//                    }
//
//                }
//
//            }
//
//        });
//
//        if (finalroomcount == 1)
//        {
//            if (row_index == position) {
//                holder.checkroom.setChecked(true);
//            } else {
//                holder.checkroom.setChecked(false);
//            }
//        }
//        else
//        {
//
//        }
//        hote_room_list.get(position).setCheck(false);



        holder.radioButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            CompoundButton compoundButton,
                            boolean b) {

                        if(finalroomcount==1)
                        {
                            if (b)
                            {
                                selectedPosition = holder.getAdapterPosition();

                                listener.onItemClick(hote_room_list.get(position).getRoomDescription(), hote_room_list.get(position).getRoomIndex(), hote_room_list.get(position).getRoomTypeCode(), hote_room_list.get(position).getRoomTypeName(), hote_room_list.get(position).getRatePlanName(), hote_room_list.get(position).getRatePlanCode(), hote_room_list.get(position).getCurrencyCode(), hote_room_list.get(position).getRoomPrice(), hote_room_list.get(position).getTax(), hote_room_list.get(position).getExtraGuestCharge(), hote_room_list.get(position).getChildCharge(), hote_room_list.get(position).getOtherCharges(), hote_room_list.get(position).getPublishedPrice(), hote_room_list.get(position).getOfferedPrice(), hote_room_list.get(position).getPublishedPriceRoundedOff(), hote_room_list.get(position).getOfferedPriceRoundedOff(), hote_room_list.get(position).getServiceTax(), hote_room_list.get(position).getDiscount(), hote_room_list.get(position).getAgentCommission(), hote_room_list.get(position).getAgentMarkUp(), hote_room_list.get(position).getTDS() ,1 ,"checked");

                            }
                        }
                        else
                        {
                            clickcount=clickcount+1;
                            if(clickcount<=finalroomcount)
                            {
                                listener.onItemClick(hote_room_list.get(position).getRoomDescription(), hote_room_list.get(position).getRoomIndex(), hote_room_list.get(position).getRoomTypeCode(), hote_room_list.get(position).getRoomTypeName(), hote_room_list.get(position).getRatePlanName(), hote_room_list.get(position).getRatePlanCode(), hote_room_list.get(position).getCurrencyCode(), hote_room_list.get(position).getRoomPrice(), hote_room_list.get(position).getTax(), hote_room_list.get(position).getExtraGuestCharge(), hote_room_list.get(position).getChildCharge(), hote_room_list.get(position).getOtherCharges(), hote_room_list.get(position).getPublishedPrice(), hote_room_list.get(position).getOfferedPrice(), hote_room_list.get(position).getPublishedPriceRoundedOff(), hote_room_list.get(position).getOfferedPriceRoundedOff(), hote_room_list.get(position).getServiceTax(), hote_room_list.get(position).getDiscount(), hote_room_list.get(position).getAgentCommission(), hote_room_list.get(position).getAgentMarkUp(), hote_room_list.get(position).getTDS() ,1 ,"checked");

                            }
                            else
                            {
                                Toast.makeText(context,"You can select only by no of Room", Toast.LENGTH_LONG).show();
                                holder.radioButton.setChecked(false);
                            }


                        }

                    }
                });

        if(finalroomcount==1)
        {
            holder.radioButton.setChecked(position
                    == selectedPosition);
        }
        else
        {

        }
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
        return hote_room_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvroom_desc, tvroom_rateplan, tvroom_hotelprice, tvroom_avilability;
        RelativeLayout rl_roomselect;
        RatingBar ratingBar;
        ImageView hotel_img;
        RadioButton radioButton;
        CheckBox checkroom;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvroom_desc = itemView.findViewById(R.id.tv_roomdesc);
            tvroom_rateplan = itemView.findViewById(R.id.tv_rateplan_name);
            tvroom_hotelprice = itemView.findViewById(R.id.tv_price_hotelrrom);
            tvroom_avilability = itemView.findViewById(R.id.tv_availability);
            rl_roomselect = itemView.findViewById(R.id.rl_selectroom);
            radioButton = itemView.findViewById(R.id.radio_hotelroom);
            checkroom = itemView.findViewById(R.id.check_room);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String roomdesc, String getroom_index, String roomtypecode, String roomtype_name, String room_planname, String room_plancode, String getcurrencycode, String getroom_price, String gettax, String guestextracharge, String childcharge, String othercharge, String publishprice, String offerprice, String publishroundoffprice, String offerroundoffprice, String servicetax, String discount, String agentcommision, String agentmarkup, String tds ,int clickcount,String checkstatus);
    }
}
