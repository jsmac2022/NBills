package com.nictbills.app.activities.tbo.hotel.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.hotel.model.addhotelmemberlist.AddHotelPassengerModel;

import java.util.ArrayList;

public class HotelPassengerChildAdapter extends RecyclerView.Adapter<HotelPassengerChildAdapter.MyViewHolder> {
    Context context;
    HotelPassengerChildAdapter.OnItemClickListener listener;
    ArrayList<AddHotelPassengerModel> add_childpassengerlisthoteladapter = new ArrayList<AddHotelPassengerModel>();

    public HotelPassengerChildAdapter(Context context, ArrayList<AddHotelPassengerModel> add_childpassengerlisthoteladapter, HotelPassengerChildAdapter.OnItemClickListener listener) {
        this.context = context;
        this.add_childpassengerlisthoteladapter = add_childpassengerlisthoteladapter;
        this.listener = listener;
    }


    @NonNull
    @Override
    public HotelPassengerChildAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_adapter_childpassenger, parent, false);
        return new HotelPassengerChildAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelPassengerChildAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        AddHotelPassengerModel addHotelPassengerModel = add_childpassengerlisthoteladapter.get(position);
        holder.addpassenger_title.setText("Child Passenger" + add_childpassengerlisthoteladapter.get(position).getPassengernumberall());
        holder.edtfname.setText(add_childpassengerlisthoteladapter.get(position).getFname());
        holder.edtlastname.setText(add_childpassengerlisthoteladapter.get(position).getLname());

        holder.btnadd_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick_child(holder.edtfname.getText().toString().trim(),holder.edtlastname.getText().toString().trim() ,position) ;

            }
        });


    }

    @Override
    public int getItemCount() {
        return add_childpassengerlisthoteladapter.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView addpassenger_title, tvbus_type, tvbus_dapttime, tvbus_price, tvbus_arrivetime, tvbus_seat, tv_rating_value, hotel_address, tvFlightNo, tvduration, tvendtime, tvstarttime, tvstartpoint, tvflightsetavilable, tvendpoint, tvofferprice, tvpublishprice;
        Button btnadd_child;
        EditText  edtfname, edtlastname ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnadd_child= itemView.findViewById(R.id.btnadd_child);
            addpassenger_title = itemView.findViewById(R.id.tvaddp_child);
            edtfname = itemView.findViewById(R.id.etFirstName_hotelp_child);
            edtlastname = itemView.findViewById(R.id.etLastName_hotelp_child);
        }
    }

    public interface OnItemClickListener {
        //        void onItemClick(View v,int position,MyViewHolder holder ,String name, String lname,String gendertype ,String mobile,String email,String age,String address ,boolean check);
        void onItemClick_child(String child_fname ,String child_lname ,int position);
    }

}

