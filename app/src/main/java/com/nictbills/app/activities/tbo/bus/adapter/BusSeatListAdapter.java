package com.nictbills.app.activities.tbo.bus.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.bus.model.busseatadd.SeatAdd;
import com.nictbills.app.activities.tbo.bus.model.busseatresponsemode.BusSeatResponseListModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BusSeatListAdapter extends RecyclerView.Adapter<BusSeatListAdapter.MyViewHolder> {
    List<BusSeatResponseListModel> listbusseat = new ArrayList<BusSeatResponseListModel>();
    Context context;
    BusSeatListAdapter.OnItemClickListener listener;
    int clickcount = 0;
    double amount = 0;
//    int amount = 0;
    String finalamount;
    String seatno = "";
    int seatno1 = 0;
//    int cama = ,;
    String cama = ",";
    int getadult_count = 6;
    int row_index = -1;
    String type = "";
   public static ArrayList<SeatAdd> seatadd = new ArrayList<>();

    public BusSeatListAdapter(Context context, ArrayList listbusseat, BusSeatListAdapter.OnItemClickListener listener) {
        this.context = context;
        this.listbusseat = listbusseat;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BusSeatListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.busseat_adapter_layout, parent, false);
        return new BusSeatListAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BusSeatListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BusSeatResponseListModel busSeatResponseListModel = listbusseat.get(position);
//        holder.tvseatname.setText(listbusseat.get(position).getSeatname());
//        holder.tvseatcode.setText(listbusseat.get(position).getColumnNo());
        holder.tvseatcode.setText(listbusseat.get(position).getSeatname());


        if(listbusseat.get(position).getSeatstatus() == true){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SeatAdd seatAdd = new SeatAdd();

                    if (listbusseat.get(position).isCheck() == false)
                    {
                        listbusseat.get(position).setCheck(true);
                        clickcount = clickcount + 1;
                        if (clickcount <= getadult_count) {
//
                            amount = amount + Double.parseDouble(listbusseat.get(position).getPublishedPriceRoundedOff());
                            seatno = seatno + listbusseat.get(position).getSeatname()+cama;
                            listener.onItemClick(amount, seatno, clickcount, listbusseat.get(position).getColumnNo(), listbusseat.get(position).getHeight(), listbusseat.get(position).IsLadiesSeat, listbusseat.get(position).IsMalesSeat, listbusseat.get(position).IsUpper, listbusseat.get(position).getSeatrowno(), listbusseat.get(position).getSeatprice(), listbusseat.get(position).getSeatindex(), listbusseat.get(position).getSeatname(), listbusseat.get(position).getSeatstatus(), listbusseat.get(position).getSeattype(), listbusseat.get(position).getWidth(), listbusseat.get(position).getCurrencyCode(), listbusseat.get(position).getBasePrice(), listbusseat.get(position).getTax(), listbusseat.get(position).getOtherCharges(), listbusseat.get(position).getDiscount(), listbusseat.get(position).getSeatpublishedprice(), listbusseat.get(position).getPublishedPriceRoundedOff(), listbusseat.get(position).getOfferedPrice(), listbusseat.get(position).getOfferedPriceRoundedOff(), listbusseat.get(position).getAgentCommission(), listbusseat.get(position).getAgentMarkUp(), listbusseat.get(position).getTDS(), listbusseat.get(position).getCGSTAmount(), listbusseat.get(position).getCGSTRate(), listbusseat.get(position).getCessAmount(), listbusseat.get(position).getCessRate(), listbusseat.get(position).getIGSTAmount(), listbusseat.get(position).getIGSTRate(), listbusseat.get(position).getSGSTAmount(), listbusseat.get(position).getIGSTRate(), listbusseat.get(position).getTaxableAmount(),"ADD" ,position);

                            holder.imageViewseat.setImageResource(R.drawable.selectedseat);
                            seatAdd.setColumnNo(listbusseat.get(position).getColumnNo());
                            seatAdd.setHeight(listbusseat.get(position).getHeight());
                            seatAdd.setIsLadiesSeat(String.valueOf(listbusseat.get(position).IsLadiesSeat));
                            seatAdd.setIsMalesSeat(String.valueOf(listbusseat.get(position).IsMalesSeat));
                            seatAdd.setIsUpper(String.valueOf(listbusseat.get(position).IsUpper));
                            seatAdd.setRowNo(listbusseat.get(position).getSeatrowno());
                            seatAdd.setSeatprice(listbusseat.get(position).getSeatprice());
                            seatAdd.setSeatName(listbusseat.get(position).getSeatname());
                            seatAdd.setSeatIndex(listbusseat.get(position).getSeatindex());
                            seatAdd.setSeatStatus(String.valueOf(listbusseat.get(position).getSeatstatus()));
                            seatAdd.setSeatType(listbusseat.get(position).getSeattype());
                            seatAdd.setWidth(listbusseat.get(position).getWidth());
                            Log.e("seatname", "add" + listbusseat.get(position).getSeatname());
                            //price
                            seatAdd.setCurrencyCode(listbusseat.get(position).getCurrencyCode());
                            seatAdd.setBasePrice(listbusseat.get(position).getBasePrice());
                            seatAdd.setTax(listbusseat.get(position).getTax());
                            seatAdd.setOtherCharges(listbusseat.get(position).getOtherCharges());
                            seatAdd.setDiscount(listbusseat.get(position).getDiscount());
                            seatAdd.setPublishedPrice(listbusseat.get(position).getSeatpublishedprice());
                            seatAdd.setPublishedPriceRoundedOff(listbusseat.get(position).getPublishedPriceRoundedOff());
                            seatAdd.setOfferedPrice(listbusseat.get(position).getOfferedPrice());
                            seatAdd.setOfferedPriceRoundedOff(listbusseat.get(position).getOfferedPriceRoundedOff());
                            seatAdd.setAgentCommission(listbusseat.get(position).getAgentCommission());
                            seatAdd.setAgentMarkUp(listbusseat.get(position).getAgentMarkUp());
                            seatAdd.setTDS(listbusseat.get(position).getTDS());

                            //gst
                            seatAdd.setCGSTAmount(listbusseat.get(position).getCGSTAmount());
                            seatAdd.setCGSTRate(listbusseat.get(position).getCGSTRate());
                            seatAdd.setCessAmount(listbusseat.get(position).getCessAmount());
                            seatAdd.setCessRate(listbusseat.get(position).getCessRate());
                            seatAdd.setIGSTAmount(listbusseat.get(position).getIGSTAmount());
                            seatAdd.setIGSTRate(listbusseat.get(position).getIGSTRate());
                            seatAdd.setSGSTAmount(listbusseat.get(position).getSGSTAmount());
                            seatAdd.setSGSTRate(listbusseat.get(position).getSGSTRate());
                            seatAdd.setTaxableAmount(listbusseat.get(position).getTaxableAmount());
                            seatadd.add(seatAdd);
//                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "You can select only max 6 seat", Toast.LENGTH_LONG).show();

                        }
                    }
                    else {

//                        clickcount = clickcount - 1;
//                        holder.imageViewseat.setImageResource(R.drawable.selectseaton);
//                        listbusseat.get(position).setCheck(false);
//                        amount = amount - Double.parseDouble(listbusseat.get(position).getPublishedPriceRoundedOff());
//                        seatno = seatno.replaceAll(listbusseat.get(position).getSeatname()+cama, "");
//                        listener.onItemClick(amount, seatno, clickcount, listbusseat.get(position).getColumnNo(), listbusseat.get(position).getHeight(), listbusseat.get(position).IsLadiesSeat, listbusseat.get(position).IsMalesSeat, listbusseat.get(position).IsUpper, listbusseat.get(position).getSeatrowno(), listbusseat.get(position).getSeatprice(), listbusseat.get(position).getSeatindex(), listbusseat.get(position).getSeatname(), listbusseat.get(position).getSeatstatus(), listbusseat.get(position).getSeattype(), listbusseat.get(position).getWidth(), listbusseat.get(position).getCurrencyCode(), listbusseat.get(position).getBasePrice(), listbusseat.get(position).getTax(), listbusseat.get(position).getOtherCharges(), listbusseat.get(position).getDiscount(), listbusseat.get(position).getSeatpublishedprice(), listbusseat.get(position).getPublishedPriceRoundedOff(), listbusseat.get(position).getOfferedPrice(), listbusseat.get(position).getOfferedPriceRoundedOff(), listbusseat.get(position).getAgentCommission(), listbusseat.get(position).getAgentMarkUp(), listbusseat.get(position).getTDS(), listbusseat.get(position).getCGSTAmount(), listbusseat.get(position).getCGSTRate(), listbusseat.get(position).getCessAmount(), listbusseat.get(position).getCessRate(), listbusseat.get(position).getIGSTAmount(), listbusseat.get(position).getIGSTRate(), listbusseat.get(position).getSGSTAmount(), listbusseat.get(position).getIGSTRate(), listbusseat.get(position).getTaxableAmount() ,"REMOVE" ,position);
//                        seatAdd.setColumnNo(listbusseat.get(position).getColumnNo());
//                        seatAdd.setHeight(listbusseat.get(position).getHeight());
//                        seatAdd.setIsLadiesSeat(String.valueOf(listbusseat.get(position).IsLadiesSeat));
//                        seatAdd.setIsMalesSeat(String.valueOf(listbusseat.get(position).IsMalesSeat));
//                        seatAdd.setIsUpper(String.valueOf(listbusseat.get(position).IsUpper));
//                        seatAdd.setRowNo(listbusseat.get(position).getSeatrowno());
//                        seatAdd.setSeatprice(listbusseat.get(position).getSeatprice());
//                        seatAdd.setSeatName(listbusseat.get(position).getSeatname());
//                        seatAdd.setSeatIndex(listbusseat.get(position).getSeatindex());
//                        seatAdd.setSeatStatus(String.valueOf(listbusseat.get(position).getSeatstatus()));
//                        seatAdd.setSeatType(listbusseat.get(position).getSeattype());
//                        seatAdd.setWidth(listbusseat.get(position).getWidth());
//                        Log.e("seatname", "remove" + listbusseat.get(position).getSeatname());
//
//                        //price
//                        seatAdd.setCurrencyCode(listbusseat.get(position).getCurrencyCode());
//                        seatAdd.setBasePrice(listbusseat.get(position).getBasePrice());
//                        seatAdd.setTax(listbusseat.get(position).getTax());
//                        seatAdd.setOtherCharges(listbusseat.get(position).getOtherCharges());
//                        seatAdd.setDiscount(listbusseat.get(position).getDiscount());
//                        seatAdd.setPublishedPrice(listbusseat.get(position).getSeatpublishedprice());
//                        seatAdd.setPublishedPriceRoundedOff(listbusseat.get(position).getPublishedPriceRoundedOff());
//                        seatAdd.setOfferedPrice(listbusseat.get(position).getOfferedPrice());
//                        seatAdd.setOfferedPriceRoundedOff(listbusseat.get(position).getOfferedPriceRoundedOff());
//                        seatAdd.setAgentCommission(listbusseat.get(position).getAgentCommission());
//                        seatAdd.setAgentMarkUp(listbusseat.get(position).getAgentMarkUp());
//                        seatAdd.setTDS(listbusseat.get(position).getTDS());
//
//                        //gst
//                        seatAdd.setCGSTAmount(listbusseat.get(position).getCGSTAmount());
//                        seatAdd.setCGSTRate(listbusseat.get(position).getCGSTRate());
//                        seatAdd.setCessAmount(listbusseat.get(position).getCessAmount());
//                        seatAdd.setCessRate(listbusseat.get(position).getCessRate());
//                        seatAdd.setIGSTAmount(listbusseat.get(position).getIGSTAmount());
//                        seatAdd.setIGSTRate(listbusseat.get(position).getIGSTRate());
//                        seatAdd.setSGSTAmount(listbusseat.get(position).getSGSTAmount());
//                        seatAdd.setSGSTRate(listbusseat.get(position).getSGSTRate());
//                        seatAdd.setTaxableAmount(listbusseat.get(position).getTaxableAmount());
//                        seatadd.remove(seatAdd);

                        Toast.makeText(context, "Already Select", Toast.LENGTH_LONG).show();

                    }


                }
            });

        }
        else
        {
            holder.imageViewseat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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

        listbusseat.get(position).setCheck(false);
        if (listbusseat.get(position).getSeatstatus() == true)
        {
            if(listbusseat.get(position).isLadiesSeat() == true)
            {
                holder.imageViewseat.setImageResource(R.drawable.femaleseat);
            }
            else{
                holder.imageViewseat.setImageResource(R.drawable.selectseaton);

            }
        }
        else if (listbusseat.get(position).getSeatstatus() == false) {
            holder.imageViewseat.setImageResource(R.drawable.bookedseat);

        }

    }

    @Override
    public int getItemCount() {
        return listbusseat.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvseatname, tvseatcode, tv_upperlower, tvFlightName, tvFlightNo, tvduration, tvendtime, tvstarttime, tvstartpoint, tvflightsetavilable, tvendpoint, tvofferprice, tvpublishprice;
        RelativeLayout rlBookNow;
        ImageView imageViewseat;
        LinearLayout layout_seat;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.setIsRecyclable(false);
            tvseatcode = itemView.findViewById(R.id.tv_seatcode);
            imageViewseat = itemView.findViewById(R.id.tv_seatimg);

        }

        public void bind(final BusSeatResponseListModel listbusseat, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    listener.onItemClick(listbusseat);
                }
            });
        }
    }

    public interface OnItemClickListener {
        //        void onItemClick(, ,,String seatrow, ,String getpublished ,double finalamount, String finalseatno ,int noofseat,String seatprice ,String seatheight);
        void onItemClick(double finalamount, String finalseatno, int noofseat, String columnno, String seatheight, boolean isladis, boolean ismale, boolean isupper, String seatrow, String seatprice, String seatindex, String seatname, boolean seatstatus, String seattype, String seatwidth, String currencycode, String baseprice, String tax, String othercharge, String discount, String publishedprice, String publishedroundoff, String offerprice, String opfferroundof, String agentcommission, String agentmarkup, String tds, String cgstamount, String cgstrate, String cessamount, String cessrate, String igstamount, String igstrate, String sgsamount, String sgstrate, String amounttaxable,String addtype ,int finalpostion);
        void onItemClick1(double finalamount ,String finalseatno);

    }


}
