package com.nictbills.app.activities.tbo.flight.adapter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.flight.model.AddFlightPassengermodel.FlightPassengerMemberAdd;

import java.util.ArrayList;
import java.util.Calendar;

public class FlightChildMemberAdapter extends RecyclerView.Adapter<FlightChildMemberAdapter.MyViewHolder> {
    String gendertype="Male";
    View view;
    String getgendertype="M";
    Context context;
    FlightChildMemberAdapter.OnItemClickListener listener;
    ArrayList<FlightPassengerMemberAdd> childlist = new ArrayList<FlightPassengerMemberAdd>();

    public FlightChildMemberAdapter(Context context, ArrayList<FlightPassengerMemberAdd> childlist, FlightChildMemberAdapter.OnItemClickListener listener) {
        this.context = context;
        this.childlist = childlist;
        this.listener = listener;
    }


    @NonNull
    @Override
    public FlightChildMemberAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_adapter_flightchildmember, parent, false);
        return new FlightChildMemberAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FlightChildMemberAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        FlightPassengerMemberAdd flightPassengerMemberAdd = childlist.get(position);
        holder.addpassenger_title.setText("Child Passenger" + childlist.get(position).getPassengernumberall());
        holder.edtfname.setText(childlist.get(position).getFullname());
        holder.edtlastname.setText(childlist.get(position).getLastname());
//
        gendertype = ((RadioButton)view.findViewById(holder.radioGroupgender.getCheckedRadioButtonId()))
                .getText().toString();
        holder.radioGroupgender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gendertype = ((RadioButton) view.findViewById(holder.radioGroupgender.getCheckedRadioButtonId())).getText().toString();

            }
        });

        if (gendertype.equals("Male")) {
            getgendertype = "M";
        } else {
            getgendertype = "F";
        }

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

        holder.edtdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                depart_EDT.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                                departSend = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                String date="1";
                                int finalmonth=monthOfYear+1;
                                String finalmonthsum;
                                if(finalmonth< 10)
                                {
                                    finalmonthsum="0"+finalmonth;
                                    Log.e("final month e",""+finalmonthsum+"");

                                }
                                else {
                                    finalmonthsum= String.valueOf(finalmonth);
                                    Log.e("final month ELESE",""+finalmonthsum+"");

                                }

                                if(dayOfMonth<10)
                                {
                                    date="0"+dayOfMonth;
//                                    depart_EDT.setText(date + "/" + (monthOfYear + 1) + "/" + year);
//                                    binding.edtdob.setText(year + "-" + finalmonthsum + "-" + date);
//                                    binding.edtdob.setText( date+"/"+finalmonthsum+"/"+year+"T00: 00:00");
                                    holder.edtdob.setText( year+"-"+finalmonthsum+"-"+date);

                                }
                                else {
//                                    binding.edtdob.setText(dayOfMonth+"/"+finalmonthsum +"/"+year+"T00: 00:00" );
                                    holder.edtdob.setText(year+"-"+finalmonthsum +"-"+dayOfMonth);
//                                    binding.edtdob.setText(year + "-" + finalmonthsum + "-" + dayOfMonth);

                                }
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.show();

            }
        });

        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onItemClick_child( holder.edtfname.getText().toString().trim(),holder.edtlastname.getText().toString().trim(), holder.edtdob.getText().toString().trim(),getgendertype ,position) ;

            }
        });


    }

    @Override
    public int getItemCount() {
        return childlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView addpassenger_title, tvbus_type, tvbus_dapttime, tvbus_price, tvbus_arrivetime, tvbus_seat, tv_rating_value, hotel_address, tvFlightNo, tvduration, tvendtime, tvstarttime, tvstartpoint, tvflightsetavilable, tvendpoint, tvofferprice, tvpublishprice;
        Button btnadd;
        EditText edtdob ,edtfname, edtlastname ,edtmobile,edtemail,edtage,edtadress;
        RadioGroup radioGroupgender;
        RadioButton radiomale, radiofemale;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            btnadd = itemView.findViewById(R.id.add_childmember);
            addpassenger_title = itemView.findViewById(R.id.tv_heading_child);
            edtfname = itemView.findViewById(R.id.edt_childName_flight);
            edtlastname = itemView.findViewById(R.id.edt_childlastName_flight);
            edtdob= itemView.findViewById(R.id.edt_childdob_flight);
            radioGroupgender = itemView.findViewById(R.id.childradiogroup_flight);
        }
    }

    public interface OnItemClickListener {
        //        void onItemClick(View v,int position,MyViewHolder holder ,String name, String lname,String gendertype ,String mobile,String email,String age,String address ,boolean check);
        void onItemClick_child( String fname, String lname ,String dob ,String getgendertype ,int position);
    }

}

