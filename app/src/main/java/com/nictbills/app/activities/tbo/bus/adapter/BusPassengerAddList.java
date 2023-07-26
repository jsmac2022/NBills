package com.nictbills.app.activities.tbo.bus.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
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
import com.nictbills.app.activities.tbo.bus.model.addbuspassengermodel.PassengerListValue;

import java.util.ArrayList;

public class BusPassengerAddList extends RecyclerView.Adapter<BusPassengerAddList.MyViewHolder> {
    boolean isAllFieldsChecked = false;
    String gendertype;
    View view;
    String getgendertype="1";
    Context context;
    boolean termchecked = false;
    BusPassengerAddList.OnItemClickListener listener;
    ArrayList<PassengerListValue> addedpassengerlist = new ArrayList<PassengerListValue>();

    public BusPassengerAddList(Context context, ArrayList<PassengerListValue> addedpassengerlist, BusPassengerAddList.OnItemClickListener listener) {
        this.context = context;
        this.addedpassengerlist = addedpassengerlist;
        this.listener = listener;
    }


    @NonNull
    @Override
    public BusPassengerAddList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.busadapteraddlist_layout, parent, false);
        return new BusPassengerAddList.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BusPassengerAddList.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PassengerListValue passengerListValue = addedpassengerlist.get(position);
        holder.addpassenger_title.setText("Passenger" + addedpassengerlist.get(position).getPassangerno());
        holder.edtfname.setText(addedpassengerlist.get(position).getEdtname());
        holder.edtlastname.setText(addedpassengerlist.get(position).getEdtlname());
        holder.edtmobile.setText(addedpassengerlist.get(position).getEdtmobile());
        holder.edtemail.setText(addedpassengerlist.get(position).getEdtemail());
        holder.edtadress.setText(addedpassengerlist.get(position).getEdtaddress());
        holder.edtage.setText(addedpassengerlist.get(position).getEdtage());

        InputFilter letterFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String filtered = "";
                for (int i = start; i < end; i++) {
                    char character = source.charAt(i);
                    if (!Character.isWhitespace(character)&&Character.isLetter(character)) {
                        filtered += character;
                    }
                }

                return filtered;            }


        };
        holder.edtfname.setFilters(new InputFilter[]{letterFilter});
        holder.edtlastname.setFilters(new InputFilter[]{letterFilter});
//        holder.checkBox.se
        holder.checkBox.setChecked (addedpassengerlist.get(position).isLeadpassengertype());

        gendertype = ((RadioButton)view.findViewById(holder.radioGroupgender.getCheckedRadioButtonId()))
                .getText().toString();
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
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    termchecked = true;
                } else {
                    termchecked = false;
                }

            }
        });

        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onItemClick(v ,holder,position ,holder.edtfname.getText().toString(), holder.edtlastname.getText().toString().trim() ,getgendertype,holder.edtmobile.getText().toString().trim(),holder.edtemail.getText().toString().trim(),holder.edtage.getText().toString().trim(),holder.edtadress.getText().toString().trim()) ;

            }
        });


    }

    @Override
    public int getItemCount() {
        return addedpassengerlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView addpassenger_title, tvbus_type, tvbus_dapttime, tvbus_price, tvbus_arrivetime, tvbus_seat, tv_rating_value, hotel_address, tvFlightNo, tvduration, tvendtime, tvstarttime, tvstartpoint, tvflightsetavilable, tvendpoint, tvofferprice, tvpublishprice;
        Button btnadd;
        EditText edtfname, edtlastname ,edtmobile,edtemail,edtage,edtadress;
        RadioGroup radioGroupgender;
        RadioButton radiomale, radiofemale;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            addpassenger_title = itemView.findViewById(R.id.tv_passengerbustitle);
            btnadd = itemView.findViewById(R.id.btnadd);
            edtfname = itemView.findViewById(R.id.etFirstName);
            edtlastname = itemView.findViewById(R.id.etLastName);
            edtmobile = itemView.findViewById(R.id.etMobile);
            edtemail= itemView.findViewById(R.id.etEmail);
            edtage= itemView.findViewById(R.id.etage);
            edtadress= itemView.findViewById(R.id.etAdress);
            checkBox= itemView.findViewById(R.id.check_term);
            radioGroupgender = itemView.findViewById(R.id.radio_group);


        }


    }

    public interface OnItemClickListener {
//        void onItemClick(View v,int position,MyViewHolder holder ,String name, String lname,String gendertype ,String mobile,String email,String age,String address ,boolean check);
        void onItemClick(View v ,MyViewHolder holder,int position ,String name, String lname,String gendertype ,String mobile,String email,String age,String address);

    }

    private boolean CheckAllFields(String name) {

//        if (binding.etFirstName.getText().toString().length() == 0) {
//            binding.etFirstName.setError("This field is required");
//            return false;
//        }

//        if (binding.etLastName.getText().toString().length() == 0) {
//            binding.etLastName.setError("This field is required");
//            return false;
//        }
//
//        if (binding.etMobile.getText().toString().length() == 0) {
//            binding.etMobile.setError("Mobile no is required");
//            return false;
//        }
//
//
//        if (binding.etEmail.getText().toString().length() == 0) {
//            binding.etEmail.setError("Email is required");
//            return false;
//
//        }
//        if (binding.etage.getText().toString().length() == 0) {
//            binding.etage.setError("Age is required");
//            return false;
//        }
//
//        if (binding.etAdress.getText().toString().length() == 0) {
//            binding.etAdress.setError("Fill Address");
//            return false;
//        }
//        binding.etEmail.getText().toString().matches(emailPattern) && binding.etEmail.getText().toString().length() > 0
        // after all validation return true.
        return true;
    }


}

