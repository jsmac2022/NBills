package com.nictbills.app.activities.tbo.hotel.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.bus.adapter.BusPassengerAddList;
import com.nictbills.app.activities.tbo.bus.model.addbuspassengermodel.PassengerListValue;
import com.nictbills.app.activities.tbo.hotel.model.addhotelmemberlist.AddHotelPassengerModel;

import java.util.ArrayList;

public class HotelPassengerAdultAdapter extends RecyclerView.Adapter<HotelPassengerAdultAdapter.MyViewHolder> {
    String gendertype;
    View view;
    String getgendertype="1";
    Context context;
    HotelPassengerAdultAdapter.OnItemClickListener listener;
    ArrayList<AddHotelPassengerModel> addpassengerlisthoteladapter = new ArrayList<AddHotelPassengerModel>();

    public HotelPassengerAdultAdapter(Context context, ArrayList<AddHotelPassengerModel> addpassengerlisthoteladapter, HotelPassengerAdultAdapter.OnItemClickListener listener) {
        this.context = context;
        this.addpassengerlisthoteladapter = addpassengerlisthoteladapter;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HotelPassengerAdultAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_layouthoteladult, parent, false);
        return new HotelPassengerAdultAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HotelPassengerAdultAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        AddHotelPassengerModel addHotelPassengerModel = addpassengerlisthoteladapter.get(position);
        holder.addpassenger_title.setText("AdultPassenger" + addpassengerlisthoteladapter.get(position).getPassengernumberall());
        holder.edtfname.setText(addpassengerlisthoteladapter.get(position).getFname());
        holder.edtlastname.setText(addpassengerlisthoteladapter.get(position).getLname());
        holder.edtmobile.setText(addpassengerlisthoteladapter.get(position).getMobile());
        holder.edtemail.setText(addpassengerlisthoteladapter.get(position).getEmail());
        holder.edtadress.setText(addpassengerlisthoteladapter.get(position).getAddress());
        holder.edtage.setText(addpassengerlisthoteladapter.get(position).getAge());
        holder.edthotelpancard.setText(addpassengerlisthoteladapter.get(position).getPancard());
        holder.edthotelpancard.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        int maxLength = 10;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        holder.edthotelpancard.setFilters(fArray);
        holder.edthotelpancard.setFilters(new InputFilter[]{new InputFilter.AllCaps() ,new InputFilter.LengthFilter(maxLength)});
//        edt.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(10)});
        gendertype = ((RadioButton) view.findViewById(holder.radioGroupgender.getCheckedRadioButtonId())).getText().toString();

        holder.radioGroupgender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gendertype = ((RadioButton) view.findViewById(holder.radioGroupgender.getCheckedRadioButtonId())).getText().toString();

            }
        });

        if (gendertype.equals("Male")) {
            getgendertype = "1";
        } else {
            getgendertype = "2";
        }
        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onItemClick(holder.edtfname.getText().toString().trim() ,holder.edtlastname.getText().toString().trim() ,holder.edtmobile.getText().toString().trim() ,holder.edtemail.getText().toString().trim(),holder.edtage.getText().toString().trim(),holder.edthotelpancard.getText().toString().trim(),getgendertype ,position) ;

            }
        });


    }

    @Override
    public int getItemCount() {
        return addpassengerlisthoteladapter.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView addpassenger_title, tvbus_type, tvbus_dapttime, tvbus_price, tvbus_arrivetime, tvbus_seat, tv_rating_value, hotel_address, tvFlightNo, tvduration, tvendtime, tvstarttime, tvstartpoint, tvflightsetavilable, tvendpoint, tvofferprice, tvpublishprice;
        Button btnadd;
        EditText edthotelpancard ,edtfname, edtlastname ,edtmobile,edtemail,edtage,edtadress;
        RadioGroup radioGroupgender;
        RadioButton radiomale, radiofemale;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            btnadd = itemView.findViewById(R.id.btnadd_Adult);
            addpassenger_title = itemView.findViewById(R.id.tvaddp);
            edtfname = itemView.findViewById(R.id.etFirstName_hotelp);
            edtlastname = itemView.findViewById(R.id.etLastName_hotelp);
            edtmobile = itemView.findViewById(R.id.etMobile_hotelp);
            edtemail= itemView.findViewById(R.id.etEmail_hotelp);
            edtadress= itemView.findViewById(R.id.etAdress_hotelp);
            edtage= itemView.findViewById(R.id.etage_hotelp);
            edthotelpancard= itemView.findViewById(R.id.edtpanno_hotelpancard);
            radioGroupgender = itemView.findViewById(R.id.radio_group_hotelp);
        }
    }

    public interface OnItemClickListener {
        //        void onItemClick(View v,int position,MyViewHolder holder ,String name, String lname,String gendertype ,String mobile,String email,String age,String address ,boolean check);
        void onItemClick(String fname, String lname ,String mobile ,String email ,String age ,String pancard ,String  gender ,int position);
    }

}
