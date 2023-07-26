package com.nictbills.app.adapter.dawai_4_u;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.medicine_order_history.Datum;

import java.util.List;

public class UserMedicineOrderHistoryAdapter extends RecyclerView.Adapter<UserMedicineOrderHistoryAdapter.MyViewHolder> {
    Context context;

    private List<Datum> data;
   // private OnClickRecyclerViewInterface myListener;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView user_name_TV, mobile_number_Tv, order_date_Tv,order_status_TV,chemist_shop_name_Tv;
       // private LinearLayout reward_point_LL;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.user_name_TV = (TextView) itemView.findViewById(R.id.user_name_TV);
            this.mobile_number_Tv = (TextView) itemView.findViewById(R.id.mobile_number_Tv);
            this.order_date_Tv = (TextView) itemView.findViewById(R.id.order_date_Tv);
            this.order_status_TV = (TextView) itemView.findViewById(R.id.order_status_TV);
            this.chemist_shop_name_Tv = (TextView) itemView.findViewById(R.id.chemist_shop_name_Tv);

        }

    }

    public UserMedicineOrderHistoryAdapter(Context context, List<Datum> data) {
        this.data = data;
        this.context = context;
      /*  this.myListener = listener;*/
    }

    @Override
    public UserMedicineOrderHistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medicine_history_status_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        UserMedicineOrderHistoryAdapter.MyViewHolder myViewHolder = new UserMedicineOrderHistoryAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final UserMedicineOrderHistoryAdapter.MyViewHolder holder, final int listPosition) {


      /*  holder.reward_point_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });
*/

        holder.order_date_Tv.setText(data.get(listPosition).getCreatedDt());
        holder.user_name_TV.setText(data.get(listPosition).getName());
        holder.mobile_number_Tv.setText(data.get(listPosition).getMobileNo());
        holder.chemist_shop_name_Tv.setText(data.get(listPosition).getStoreName());

        if (data.get(listPosition).getReqStatus().equalsIgnoreCase("Delivered")) {

            holder.order_status_TV.setText(data.get(listPosition).getReqStatus());
            holder.order_status_TV.setTextColor(Color.parseColor("#228B22"));


        } else if (data.get(listPosition).getReqStatus().equalsIgnoreCase("Rejected")) {

            holder.order_status_TV.setText(data.get(listPosition).getReqStatus());
            holder.order_status_TV.setTextColor(Color.parseColor("#FF0000"));

        } else if (data.get(listPosition).getReqStatus().equalsIgnoreCase("In-process")) {

            holder.order_status_TV.setText(data.get(listPosition).getReqStatus());
            holder.order_status_TV.setTextColor(Color.parseColor("#FFA500"));

        } else if (data.get(listPosition).getReqStatus().equalsIgnoreCase("Dispatched")) {

            holder.order_status_TV.setText(data.get(listPosition).getReqStatus());
            holder.order_status_TV.setTextColor((Color.BLUE));

        } else if (data.get(listPosition).getReqStatus().equalsIgnoreCase("Pending")) {

            holder.order_status_TV.setText(data.get(listPosition).getReqStatus());
            holder.order_status_TV.setTextColor(Color.parseColor("#8B8000"));

        }



    }

    @Override
    public int getItemCount() {
        return data.size();
        //return mDataset.size();

    }
}