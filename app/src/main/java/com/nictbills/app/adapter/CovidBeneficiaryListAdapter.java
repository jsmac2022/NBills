package com.nictbills.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.beneficiary_dto.Beneficiary;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class CovidBeneficiaryListAdapter extends RecyclerView.Adapter<CovidBeneficiaryListAdapter.MyViewHolder> {
    Context context;

    private List<Beneficiary> beneficiaryList;
    private OnClickRecyclerViewInterface myListener;



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView dose_appointment_certificate_TV,dose_certificate_TV,name_tv,ref_id_TV,dose_TV,appointment_schedule_TV,schedule_now_TV,vaccine_name_TV,appointment_schedule_hospital_TV,appointment_schedule_city_TV,reSchedule_now_TV,cancel_TV;
        private LinearLayout dose_color_linear_layout,dose_certificate_download_Linear,dose_appointment_download_Linear;
        private ImageView delete_beneficiary_IMG;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.name_tv = (TextView) itemView.findViewById(R.id.name_tv);
            this.ref_id_TV = (TextView) itemView.findViewById(R.id.ref_id_TV);
            this.dose_TV = (TextView) itemView.findViewById(R.id.dose_TV);
            this.appointment_schedule_TV = (TextView) itemView.findViewById(R.id.appointment_schedule_TV);
            this.schedule_now_TV = (TextView) itemView.findViewById(R.id.schedule_now_TV);
            this.vaccine_name_TV = (TextView) itemView.findViewById(R.id.vaccine_name_TV);
            this.appointment_schedule_city_TV = (TextView) itemView.findViewById(R.id.appointment_schedule_city_TV);
            this.appointment_schedule_hospital_TV = (TextView) itemView.findViewById(R.id.appointment_schedule_hospital_TV);
            this.cancel_TV = (TextView) itemView.findViewById(R.id.cancel_TV);
            this.reSchedule_now_TV = (TextView) itemView.findViewById(R.id.reSchedule_now_TV);
            this.dose_certificate_TV = (TextView) itemView.findViewById(R.id.dose_certificate_TV);
            this.dose_appointment_certificate_TV = (TextView) itemView.findViewById(R.id.dose_appointment_certificate_TV);
            this.dose_color_linear_layout = (LinearLayout) itemView.findViewById(R.id.dose_color_linear_layout);
            this.dose_certificate_download_Linear = (LinearLayout) itemView.findViewById(R.id.dose_certificate_download_Linear);
            this.dose_appointment_download_Linear = (LinearLayout) itemView.findViewById(R.id.dose_appointment_download_Linear);
            this.delete_beneficiary_IMG = (ImageView) itemView.findViewById(R.id.delete_beneficiary_IMG);

        }
    }

    public CovidBeneficiaryListAdapter(Context context, List<Beneficiary> data, OnClickRecyclerViewInterface listener) {
        this.beneficiaryList = data;
        this.context =  context;
        this.myListener = listener;
    }

    @Override
    public CovidBeneficiaryListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.covid_beneficiary_list_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        CovidBeneficiaryListAdapter.MyViewHolder myViewHolder = new CovidBeneficiaryListAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CovidBeneficiaryListAdapter.MyViewHolder holder, final int listPosition) {

        holder.name_tv.setText(beneficiaryList.get(listPosition).getName());
        holder.ref_id_TV.setText("REF ID: "+beneficiaryList.get(listPosition).getBeneficiaryReferenceId());
        holder.vaccine_name_TV.setText(beneficiaryList.get(listPosition).getVaccinationStatus());


        if (beneficiaryList.get(listPosition).getVaccinationStatus().trim().equalsIgnoreCase("Not Vaccinated")){


            if (beneficiaryList.get(listPosition).getAppointments().size()==0){

                holder.delete_beneficiary_IMG.setVisibility(View.VISIBLE);
                holder.appointment_schedule_TV.setText("Appointment not scheduled");
                holder.dose_certificate_download_Linear.setVisibility(View.GONE);
                holder.dose_appointment_download_Linear.setVisibility(View.GONE);
                holder.dose_appointment_certificate_TV.setVisibility(View.GONE);
                holder.dose_TV.setText("Dose 1");
                holder.vaccine_name_TV.setTextColor(Color.parseColor("#E80303"));
                holder.dose_color_linear_layout.setBackgroundColor(Color.parseColor("#FFCFCF"));
                holder.appointment_schedule_hospital_TV.setVisibility(View.GONE);
                holder.appointment_schedule_city_TV.setVisibility(View.GONE);
                holder.cancel_TV.setVisibility(View.GONE);
                holder.reSchedule_now_TV.setVisibility(View.GONE);

            } else {

                    holder.delete_beneficiary_IMG.setVisibility(View.GONE);
                    holder.appointment_schedule_TV.setText("Appointment schedule on "+beneficiaryList.get(listPosition).getAppointments().get(0).getDate()+" " + beneficiaryList.get(listPosition).getAppointments().get(0).getSlot());
                    holder.dose_appointment_download_Linear.setVisibility(View.VISIBLE);
                    holder.dose_appointment_certificate_TV.setVisibility(View.VISIBLE);
                    holder.dose_TV.setText("Dose 1");
                    holder.vaccine_name_TV.setTextColor(Color.parseColor("#ff8c00"));
                    holder.appointment_schedule_TV.setTextColor(Color.parseColor("#ff8c00"));
                    holder.dose_TV.setTextColor(Color.parseColor("#ff8c00"));
                    holder.appointment_schedule_hospital_TV.setTextColor(Color.parseColor("#ff8c00"));
                    holder.appointment_schedule_city_TV.setTextColor(Color.parseColor("#ff8c00"));
                    holder.dose_color_linear_layout.setBackgroundColor(Color.parseColor("#ffecd8"));
                    holder.appointment_schedule_hospital_TV.setText(beneficiaryList.get(listPosition).getAppointments().get(0).getName());
                    holder.appointment_schedule_city_TV.setText(beneficiaryList.get(listPosition).getAppointments().get(0).getDistrictName()+", "+ beneficiaryList.get(listPosition).getAppointments().get(0).getStateName());
                    holder.cancel_TV.setVisibility(View.VISIBLE);
                    holder.reSchedule_now_TV.setVisibility(View.VISIBLE);
                    holder.schedule_now_TV.setVisibility(View.GONE);
                    holder.dose_appointment_certificate_TV.setText("Dose-1 appointment");
                    holder.dose_certificate_download_Linear.setVisibility(View.GONE);


            }


        } else if (beneficiaryList.get(listPosition).getVaccinationStatus().trim().equalsIgnoreCase("Partially Vaccinated")){
            holder.delete_beneficiary_IMG.setVisibility(View.GONE);
            holder.dose_certificate_download_Linear.setVisibility(View.VISIBLE);
            holder.dose_appointment_download_Linear.setVisibility(View.GONE);
            holder.dose_appointment_certificate_TV.setVisibility(View.GONE);
            holder.dose_TV.setVisibility(View.GONE);
            holder.vaccine_name_TV.setTextColor(Color.parseColor("#008F06"));
            holder.dose_color_linear_layout.setBackgroundColor(Color.parseColor("#D9F4DA"));


            holder.appointment_schedule_TV.setVisibility(View.VISIBLE);
            holder.appointment_schedule_hospital_TV.setVisibility(View.GONE);
            holder.appointment_schedule_city_TV.setVisibility(View.GONE);
            holder.dose_certificate_TV.setText("Dose-1 certificate");
            holder.cancel_TV.setVisibility(View.GONE);
            holder.reSchedule_now_TV.setVisibility(View.GONE);

            if (beneficiaryList.get(listPosition).getAppointments().size()==0){

                if (beneficiaryList.get(listPosition).getDose2Date().equals("")){
                    holder.appointment_schedule_TV.setText("Book appointment for 2 dose");
                }else {
                    holder.appointment_schedule_TV.setText(beneficiaryList.get(listPosition).getDose2Date());
                }
                holder.schedule_now_TV.setVisibility(View.VISIBLE);

            }else {
                holder.appointment_schedule_TV.setVisibility(View.GONE);
                holder.reSchedule_now_TV.setVisibility(View.VISIBLE);
                holder.cancel_TV.setVisibility(View.VISIBLE);
                holder.schedule_now_TV.setVisibility(View.GONE);
            }




        } else if (beneficiaryList.get(listPosition).getVaccinationStatus().trim().equalsIgnoreCase("Vaccinated")){
            holder.delete_beneficiary_IMG.setVisibility(View.GONE);
            holder.dose_TV.setVisibility(View.GONE);
            holder.dose_certificate_download_Linear.setVisibility(View.VISIBLE);
            holder.dose_appointment_download_Linear.setVisibility(View.GONE);
            holder.vaccine_name_TV.setTextColor(Color.parseColor("#008F06"));
            holder.dose_color_linear_layout.setBackgroundColor(Color.parseColor("#D9F4DA"));
            holder.appointment_schedule_hospital_TV.setVisibility(View.GONE);
            holder.appointment_schedule_city_TV.setVisibility(View.GONE);
            holder.cancel_TV.setVisibility(View.GONE);
            holder.schedule_now_TV.setVisibility(View.GONE);
            holder.dose_certificate_TV.setText("Vaccination certificate");
            holder.appointment_schedule_TV.setVisibility(View.GONE);
            holder.reSchedule_now_TV.setVisibility(View.GONE);

        }

        //holder.schedule_now_TV.

        //final CardView Product_Description_Card_View = holder.Product_Description_Card_View;

        holder.schedule_now_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });
         holder.dose_certificate_download_Linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });

        holder.delete_beneficiary_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });

        holder.cancel_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });

        holder.reSchedule_now_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });

        holder.dose_appointment_download_Linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return beneficiaryList.size();
        //return mDataset.size();

    }

}
