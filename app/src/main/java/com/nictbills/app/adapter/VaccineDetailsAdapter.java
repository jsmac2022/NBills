package com.nictbills.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability.Session;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class VaccineDetailsAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    Context context;

    private List<Session> sessionsList;
    private OnClickRecyclerViewInterface myListener;
    private int dose;
    private String beneficiary_id;

    public VaccineDetailsAdapter(Context context, List<Session> list, int dose, String beneficiary_id) {
        this.context = context;
        this.sessionsList=list;
        this.dose=dose;
        this.beneficiary_id=beneficiary_id;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        LayoutInflater inflater;
        baseViewHolder = new VaccineDetailsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vaccine_details_card, parent, false));
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (sessionsList != null && sessionsList.size() > 0) {
           // return 20;
            return sessionsList.size();
        } else {
            return 0;
        }
    }


    public class ViewHolder extends BaseViewHolder {

        private TextView  vaccine_name_TV,avilable_slot_TV,age_TV;
        public ViewHolder(View itemView) {
            super(itemView);

            avilable_slot_TV =  itemView.findViewById(R.id.avilable_slot_TV);
            vaccine_name_TV =  itemView.findViewById(R.id.vaccine_name_TV);
            age_TV =  itemView.findViewById(R.id.age_TV);


        }

        protected void clear() {

        }

        public void onBind(final int position) {
            super.onBind(position);

            avilable_slot_TV.setText(sessionsList.get(position).getAvailableCapacityDose1()+"");
            age_TV.setText("Age "+sessionsList.get(position).getMinAgeLimit()+"+");
            vaccine_name_TV.setText(sessionsList.get(position).getVaccine());

           /* if (!sessionsList.get(position).getAllowAllAge()){

                if (sessionsList.get(position).getMinAgeLimit()>=44){

                    vaccine_name_TV.setText(sessionsList.get(position).getVaccine());

                    if (dose==1){

                        if (sessionsList.get(position).getAvailableCapacityDose1()==0){

                            Toast.makeText(context, "No Dose Available", Toast.LENGTH_SHORT).show();

                        }else {
                            avilable_slot_TV.setText(sessionsList.get(position).getAvailableCapacityDose1()+"");
                            age_TV.setText(sessionsList.get(position).getMinAgeLimit()+"");

                        }


                    } else if (dose==2){
                        if (sessionsList.get(position).getAvailableCapacityDose2()==0){

                            Toast.makeText(context, "No Dose Available", Toast.LENGTH_SHORT).show();

                        }else {
                            avilable_slot_TV.setText(sessionsList.get(position).getAvailableCapacityDose2()+"");
                            age_TV.setText(sessionsList.get(position).getMinAgeLimit()+"");
                        }

                    }

                } else if (sessionsList.get(position).getMinAgeLimit()<=45){

                    vaccine_name_TV.setText(sessionsList.get(position).getVaccine());

                    if (dose==1){

                        if (sessionsList.get(position).getAvailableCapacityDose1()==0){
                            Toast.makeText(context, "No Dose Available", Toast.LENGTH_SHORT).show();
                        }else {
                            avilable_slot_TV.setText(sessionsList.get(position).getAvailableCapacityDose1()+"");
                            age_TV.setText(sessionsList.get(position).getMinAgeLimit()+"");
                        }


                    } else if (dose==2){

                        if (sessionsList.get(position).getAvailableCapacityDose2()==0){
                            Toast.makeText(context, "No Dose Available", Toast.LENGTH_SHORT).show();
                        }else {
                            avilable_slot_TV.setText(sessionsList.get(position).getAvailableCapacityDose2()+"");
                            age_TV.setText(sessionsList.get(position).getMinAgeLimit()+"");
                        }

                    }

                }

            } else {
                vaccine_name_TV.setText(sessionsList.get(position).getVaccine());
                avilable_slot_TV.setText(sessionsList.get(position).getAvailableCapacity()+"");

            }
*/


        }
    }
}
