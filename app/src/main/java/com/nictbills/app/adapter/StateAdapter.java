package com.nictbills.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.covid_state_list.State;

import java.util.List;

public class StateAdapter extends BaseAdapter {
    LayoutInflater inflator;
    List<State> stateList;

    public StateAdapter(Context context, List<State> timeSlots) {
        inflator = LayoutInflater.from(context);
//        this.timeSlots =
        timeSlots.add(0, new State(-1, "kindly select State"));
        this.stateList = timeSlots;

    }

    @Override
    public int getCount() {
        return stateList.size();
    }

    @Override
    public Object getItem(int position) {
        return stateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflator.inflate(R.layout.state_view_card, null);
        TextView tv = (TextView) convertView.findViewById(R.id.state_slot_TV);
        tv.setText(stateList.get(position).getStateName());

        return convertView;
    }
}