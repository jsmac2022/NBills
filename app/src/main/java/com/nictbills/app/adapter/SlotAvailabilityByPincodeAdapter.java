package com.nictbills.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;

import com.nictbills.app.activities.BookFinalAppointmentActivity;
import com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability.Center;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class SlotAvailabilityByPincodeAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    Context context;
    private List<Center> centerList;
    private OnClickRecyclerViewInterface myListener;
    private VaccineDetailsAdapter vaccineDetailsAdapter;
    private int dose;
    private String beneficiary_id,appointmentID,status;


    public SlotAvailabilityByPincodeAdapter(Context context, List<Center> list, int dose, String beneficiary_id, String status, String appointmentID) {
        this.context = context;
        this.centerList = list;
        this.dose = dose;
        this.beneficiary_id = beneficiary_id;
        this.status = status;
        this.appointmentID = appointmentID;

    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        LayoutInflater inflater;
        baseViewHolder = new SlotAvailabilityByPincodeAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.covid_available_vaccine_slot_card, parent, false));
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {

        if (centerList != null && centerList.size() > 0) {
            return centerList.size();
        } else {
            return 0;
        }
    }


    public class ViewHolder extends BaseViewHolder {

        private TextView hospital_name_TV,book_now_TV,hospital_address_TV,amount_TV;
        private RecyclerView vaccine_details_RV;
        private LinearLayout select_LinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            hospital_name_TV = (TextView) itemView.findViewById(R.id.hospital_name_TV);
            book_now_TV = (TextView) itemView.findViewById(R.id.book_now_TV);
            hospital_address_TV = (TextView) itemView.findViewById(R.id.hospital_address_TV);
            amount_TV = (TextView) itemView.findViewById(R.id.amount_TV);
            select_LinearLayout = (LinearLayout) itemView.findViewById(R.id.select_LinearLayout);

            vaccine_details_RV = (RecyclerView) itemView.findViewById(R.id.vaccine_details_RV);

           // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            //vaccine_details_RV.setLayoutManager(linearLayoutManager);
            vaccine_details_RV.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        }

        protected void clear() {

        }

        public void onBind(final int position) {
            super.onBind(position);


          /*  if (localList.contains(mList.get(position))){
                // servicesName_TV.setChecked(true);
                servicesName_TV.setSelected(true);
            }else {
                servicesName_TV.setSelected(false);
            }*/

            hospital_name_TV.setText(centerList.get(position).getName());
            hospital_address_TV.setText(centerList.get(position).getAddress());
            amount_TV.setText(centerList.get(position).getFeeType());



            if (centerList.get(position).getSessions().size()==0){

               // Toast.makeText(context, "zero center", Toast.LENGTH_SHORT).show();

            } else {
                
                vaccineDetailsAdapter = new VaccineDetailsAdapter(context, centerList.get(position).getSessions(),dose,beneficiary_id);
                vaccine_details_RV.setAdapter(vaccineDetailsAdapter);
            }

            select_LinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  //  Toast.makeText(context, centerList.get(position).getAddress(), Toast.LENGTH_SHORT).show();

                   // String sessionId= centerList.get(position).getSessions().get(0).getSessionId();

                    if (status.equalsIgnoreCase("RESCHEDULE")){
                        Intent intent = new Intent(context, BookFinalAppointmentActivity.class);
                        intent.putExtra("SLOTS",centerList.get(position));
                        intent.putExtra("hospital_name",centerList.get(position).getName());
                        intent.putExtra("hospital_location",centerList.get(position).getAddress());
                        intent.putExtra("fee_type",centerList.get(position).getFeeType());
                        intent.putExtra("APPOINTMENT_ID",appointmentID);
                        intent.putExtra("STATUS","RESCHEDULE");
                        intent.putExtra("DOSE",dose);
                        context.startActivity(intent);
                    }else {
                        Intent intent = new Intent(context, BookFinalAppointmentActivity.class);
                        intent.putExtra("SLOTS",centerList.get(position));
                        Log.e("slotsss",centerList.get(position).toString());
                        intent.putExtra("hospital_name",centerList.get(position).getName());
                        intent.putExtra("hospital_location",centerList.get(position).getAddress());
                        intent.putExtra("fee_type",centerList.get(position).getFeeType());
                        Log.e("feee_typeee",centerList.get(position).getFeeType());
                        intent.putExtra("STATUS","SCHEDULE");
                        intent.putExtra("BENEFICIARY_ID",beneficiary_id);
                        intent.putExtra("DOSE",dose);
                        intent.putExtra("CENTER_ID",centerList.get(position).getCenterId());
                        context.startActivity(intent);
                    }

                  /*  Intent intent = new Intent(context, BookFinalAppointmentActivity.class);
                    intent.putExtra("Slots",centerList.get(position));
                    intent.putExtra("hospital_name",centerList.get(position).getName());
                    intent.putExtra("hospital_location",centerList.get(position).getAddress());
                    intent.putExtra("fee_type",centerList.get(position).getFeeType());
                    intent.putExtra("APPOINTMENT_ID",appointmentID);
                    intent.putExtra("SESSION_ID",sessionID);
                    intent.putExtra("SLOT",slot);
                    intent.putExtra("STATUS","RESCHEDULE");

                    intent.putExtra("BENEFICIARY_ID",beneficiary_id);
                    intent.putExtra("DOSE",dose);
                    context.startActivity(intent);*/

                }
            });


        }

    }

}


