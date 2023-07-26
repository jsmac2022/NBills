package com.nictbills.app.adapter.mobile_recharge_paysprint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.nictbills.app.R;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class CirclePaySprintAdapter extends RecyclerView.Adapter<CirclePaySprintAdapter.MyViewHolder> {
    Context context;

    private List<String> circle;
    private OnClickRecyclerViewInterface myListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView circle_name_TV;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.circle_name_TV = (TextView) itemView.findViewById(R.id.circle_name_TV);


        }
    }

    public CirclePaySprintAdapter(Context context, List<String> data, OnClickRecyclerViewInterface listener) {
        this.circle = data;
        this.context = context;
        this.myListener = listener;
    }

    @Override
    public CirclePaySprintAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.circle_select_paysprint, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        CirclePaySprintAdapter.MyViewHolder myViewHolder = new CirclePaySprintAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CirclePaySprintAdapter.MyViewHolder holder, final int listPosition) {

        holder.circle_name_TV.setText(circle.get(listPosition).trim());


        holder.circle_name_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return circle.size();
        //return mDataset.size();

    }
}
