package com.nictbills.app.activities.tbo.bus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.nictbills.app.activities.tbo.bus.model.bordingpointmodel.DropingPointsDetailsModelResponse;

import java.util.ArrayList;

public class DropingPointAdapter extends ArrayAdapter<DropingPointsDetailsModelResponse> {
    //    private final ArrayList<TvSyscdfl00> receptype_list;
    ArrayList<DropingPointsDetailsModelResponse> dropinglist = new ArrayList<>();

    public DropingPointAdapter(Context context, int textViewResourceId, ArrayList<DropingPointsDetailsModelResponse> dropinglist) {
        super(context, textViewResourceId, dropinglist);
        this.dropinglist = dropinglist;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @Nullable
    @Override
    public DropingPointsDetailsModelResponse getItem(int position) {
        return dropinglist.get(position);

    }

    @Override
    public int getCount() {
        int count = dropinglist.size();
        return count;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    private View getCustomView(int position, ViewGroup parent) {
        DropingPointsDetailsModelResponse model = getItem(position);

        View spinnerRow = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        TextView label = spinnerRow.findViewById(android.R.id.text1);
        label.setText(model.getDroping_pointname());
        return spinnerRow;
    }
}