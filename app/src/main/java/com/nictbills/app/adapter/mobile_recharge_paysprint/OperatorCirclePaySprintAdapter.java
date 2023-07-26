package com.nictbills.app.adapter.mobile_recharge_paysprint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.Datum;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class OperatorCirclePaySprintAdapter extends RecyclerView.Adapter<OperatorCirclePaySprintAdapter.MyViewHolder> {
    Context context;

    private List<Datum> operators;
    private OnClickRecyclerViewInterface myListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView operator_name_TV;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.operator_name_TV = (TextView) itemView.findViewById(R.id.operator_name_TV);


        }
    }

    public OperatorCirclePaySprintAdapter(Context context, List<Datum> data, OnClickRecyclerViewInterface listener) {
        this.operators = data;
        this.context = context;
        this.myListener = listener;
    }

    @Override
    public OperatorCirclePaySprintAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mobile_operator_circle_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        OperatorCirclePaySprintAdapter.MyViewHolder myViewHolder = new OperatorCirclePaySprintAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final OperatorCirclePaySprintAdapter.MyViewHolder holder, final int listPosition) {

        //fina CardView Product_Description_Card_View = holder.Product_Description_Card_View;

        holder.operator_name_TV.setText(operators.get(listPosition).getName().trim());

       /* for (int i = 0 ; i<=operators.size() ; i++){

            if (operators.get(listPosition).getType().trim().equalsIgnoreCase("mobile")){
                holder.operator_name_TV.setText(operators.get(listPosition).getName().trim());
            }

        }
*/

        holder.operator_name_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);

            }
        });

    }

    @Override
    public int getItemCount() {
        return operators.size();
        //return mDataset.size();

    }
}
