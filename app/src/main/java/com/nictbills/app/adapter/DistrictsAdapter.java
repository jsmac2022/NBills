package com.nictbills.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.covid_state_list.District;

import java.util.List;

public class DistrictsAdapter extends BaseAdapter {
    LayoutInflater inflator;
    List<District> districtList;

    public DistrictsAdapter(Context context, List<District> districts) {
        inflator = LayoutInflater.from(context);
//        this.timeSlots =
        districts.add(0, new District(-1, "kindly select district"));
        this.districtList = districts;

    }

    @Override
    public int getCount() {
        return districtList.size();
    }

    @Override
    public Object getItem(int position) {
        return districtList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflator.inflate(R.layout.districts_view_card, null);
        TextView tv = (TextView) convertView.findViewById(R.id.districts_slot_TV);
        tv.setText(districtList.get(position).getDistrictName());
        return convertView;
    }
}