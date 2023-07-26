package com.nictbills.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability.Session;
import com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability.VaccineFee;
import com.nictbills.app.interfaces.ParentChildRecView;

import java.util.List;

public class SlotDetailsAdapter extends RecyclerView.Adapter<BaseViewHolder> implements SlotDetailsInnerAdapter.CallBack {
    Context context;
    private List<Session> sessionList;
    private List<VaccineFee> vaccineFees;
    private SlotDetailsInnerAdapter slotDetailsInnerAdapter;
    private ParentChildRecView childRecView;
    private int chidlPos=-1;
    private  int parentSelectedPos=-1;
    private String fee_type;


    public SlotDetailsAdapter(Context context, List<Session> list, List<VaccineFee> vaccineFees, ParentChildRecView recView,String fees_type) {
        this.context = context;
        this.sessionList = list;
        this.vaccineFees = vaccineFees;
        this.childRecView = recView;
        this.fee_type = fees_type;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        LayoutInflater inflater;
        baseViewHolder = new SlotDetailsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_details_card, parent, false));
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (sessionList != null && sessionList.size() > 0) {
            return sessionList.size();
        } else {
            return 0;
        }
    }

    @Override
    public void refreshView(int parentPosition, int position) {
        this.chidlPos=position;
        this.parentSelectedPos=parentPosition;
        notifyDataSetChanged();
       // Toast.makeText(context, "click refresh", Toast.LENGTH_SHORT).show();
    }

    public class ViewHolder extends BaseViewHolder  {

        private TextView date_TV,vaccine_name_TV,ageLimit_TV,amount_TV;
        private RecyclerView slots_details_inner_RV;

        public ViewHolder(View itemView) {
            super(itemView);

            date_TV = (TextView) itemView.findViewById(R.id.date_TV);
            vaccine_name_TV = (TextView) itemView.findViewById(R.id.vaccine_name_TV);
            ageLimit_TV = (TextView) itemView.findViewById(R.id.ageLimit_TV);
            amount_TV = (TextView) itemView.findViewById(R.id.amount_TV);
           // hospital_address_TV = (TextView) itemView.findViewById(R.id.hospital_address_TV);
           // amount_TV = (TextView) itemView.findViewById(R.id.amount_TV);

            slots_details_inner_RV = (RecyclerView) itemView.findViewById(R.id.slots_details_inner_RV);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            slots_details_inner_RV.setLayoutManager(linearLayoutManager);

            GridLayoutManager layoutManager=new GridLayoutManager(context,2);
            slots_details_inner_RV.setLayoutManager(layoutManager);

        }

        protected void clear() {

        }

        public void onBind(final int position) {
            super.onBind(position);


            date_TV.setText(sessionList.get(position).getDate());
            vaccine_name_TV.setText(sessionList.get(position).getVaccine());
            ageLimit_TV.setText("Age "+sessionList.get(position).getMinAgeLimit()+"+");


            if (sessionList.get(position).getSlots().size()==0){

                Toast.makeText(context, "No Slot Available", Toast.LENGTH_SHORT).show();

            } else {
//                Log.e(TAG, "onBind:  wwqw"+vaccineFees.size()+"" );



                if (fee_type.trim().equalsIgnoreCase("Paid")){

                    if (vaccineFees!=null){
                        amount_TV.setVisibility(View.VISIBLE);
                        for (int i = 0; i < vaccineFees.size(); i++) {
                            if (sessionList.get(position).getVaccine().trim().equalsIgnoreCase(vaccineFees.get(i).getVaccine())) {
                                amount_TV.setText("₹ "+vaccineFees.get(i).getFee());
                                break;
                            }
                        }
                    }

                    slotDetailsInnerAdapter = new SlotDetailsInnerAdapter(context, sessionList.get(position).getSlots(),childRecView,position,chidlPos,parentSelectedPos);
                    slotDetailsInnerAdapter.setCallBack(SlotDetailsAdapter.this);
                    slots_details_inner_RV.setAdapter(slotDetailsInnerAdapter);

                }else {

                    amount_TV.setVisibility(View.GONE);
                    slotDetailsInnerAdapter = new SlotDetailsInnerAdapter(context, sessionList.get(position).getSlots(),childRecView,position,chidlPos,parentSelectedPos);
                    slotDetailsInnerAdapter.setCallBack(SlotDetailsAdapter.this);
                    slots_details_inner_RV.setAdapter(slotDetailsInnerAdapter);

                }

                  //slotDetailsInnerAdapter.addData(context,sessionList.get(position).getSlots(),childRecView,position);

            }


            }

        }

    }



