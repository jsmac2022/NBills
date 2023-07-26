package com.nictbills.app.adapter.paniwala;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.vendor_profile.Result;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PaniwalaVendorProfileAdapter extends RecyclerView.Adapter<PaniwalaVendorProfileAdapter.MyViewHolder>{
    Context context;
    private String payStatus;
    private List<Result> vendorProfileList;
    private OnClickRecyclerViewInterface myListener;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView address_TV,service_TV,service_title_ID_TV,vendor_Name,city_TV,capacity_TV,container_type_TV,ratting_TV,contact_no_TV;
        private LinearLayout vendor_more_details_LL,mobile_number_LL,call_vendor_LL;
        private ImageView profile_image_IV;
        // private Button water_category_BTN;

        public MyViewHolder(View itemView) {
            super(itemView);

       //     this.water_type_TV = (TextView) itemView.findViewById(R.id.water_type_TV);
            this.service_title_ID_TV = itemView.findViewById(R.id.service_title_ID_TV);
            this.vendor_Name = itemView.findViewById(R.id.vendor_Name);
            this.city_TV = itemView.findViewById(R.id.city_TV);
            this.capacity_TV = itemView.findViewById(R.id.capacity_TV);
            this.container_type_TV = itemView.findViewById(R.id.container_type_TV);
            this.ratting_TV = itemView.findViewById(R.id.ratting_TV);
            this.vendor_more_details_LL = itemView.findViewById(R.id.vendor_more_details_LL);
            this.mobile_number_LL = itemView.findViewById(R.id.mobile_number_LL);
            this.call_vendor_LL = itemView.findViewById(R.id.call_vendor_LL);
            this.contact_no_TV = itemView.findViewById(R.id.contact_no_TV);
            this.profile_image_IV = itemView.findViewById(R.id.profile_image_IV);
            this.service_TV = itemView.findViewById(R.id.service_TV);
            this.address_TV = itemView.findViewById(R.id.address_TV);


        }

    }

    public PaniwalaVendorProfileAdapter(Context context, List<Result> vendorProfileList,String payStatus, OnClickRecyclerViewInterface listener) {
        this.vendorProfileList = vendorProfileList;
        this.context = context;
        this.myListener = listener;
        this.payStatus = payStatus;
    }

    @Override
    public PaniwalaVendorProfileAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.paniwala_vendor_profile_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        PaniwalaVendorProfileAdapter.MyViewHolder myViewHolder = new PaniwalaVendorProfileAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final PaniwalaVendorProfileAdapter.MyViewHolder holder, final int listPosition) {


       holder.vendor_more_details_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });


        holder.call_vendor_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });


        if (payStatus.equalsIgnoreCase("Paid")){
            holder.vendor_more_details_LL.setVisibility(View.GONE);
            holder.call_vendor_LL.setVisibility(View.VISIBLE);
            holder.service_title_ID_TV.setText(vendorProfileList.get(listPosition).getServTitle());
            holder.vendor_Name.setText(vendorProfileList.get(listPosition).getName());
            holder.city_TV.setText(vendorProfileList.get(listPosition).getCity());
            holder.capacity_TV.setText(vendorProfileList.get(listPosition).getCapacity());
            holder.container_type_TV.setText(vendorProfileList.get(listPosition).getVendorType());
            //holder.ratting_TV.setText(vendorProfileList.get(listPosition).getRating());
            holder.contact_no_TV.setText(vendorProfileList.get(listPosition).getContact());
            holder.service_TV.setText(vendorProfileList.get(listPosition).getVendorType());
            holder.address_TV.setText(vendorProfileList.get(listPosition).getAddress());
            holder.mobile_number_LL.setVisibility(View.VISIBLE);
            //String stringDate = DateFormat.getDateTimeInstance().format(dtStartOK);
            Picasso.with(context).load(vendorProfileList.get(listPosition).getProfilePic()).into(holder.profile_image_IV);

        } else {
            holder.mobile_number_LL.setVisibility(View.GONE);
            holder.call_vendor_LL.setVisibility(View.GONE);
            holder.vendor_more_details_LL.setVisibility(View.VISIBLE);
            holder.service_title_ID_TV.setText(vendorProfileList.get(listPosition).getServTitle());
            holder.vendor_Name.setText(vendorProfileList.get(listPosition).getName());
            holder.city_TV.setText(vendorProfileList.get(listPosition).getCity());
            holder.capacity_TV.setText(vendorProfileList.get(listPosition).getCapacity());
            holder.container_type_TV.setText(vendorProfileList.get(listPosition).getVendorType());
          //  holder.ratting_TV.setText(vendorProfileList.get(listPosition).getRating());
            //String stringDate = DateFormat.getDateTimeInstance().format(dtStartOK);
            holder.address_TV.setText(vendorProfileList.get(listPosition).getAddress());
        }




    }

    @Override
    public int getItemCount() {
        return vendorProfileList.size();
        //return mDataset.size();

    }
}