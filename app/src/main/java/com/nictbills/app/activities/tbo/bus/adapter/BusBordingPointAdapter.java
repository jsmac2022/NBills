package com.nictbills.app.activities.tbo.bus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nictbills.app.activities.tbo.bus.model.bordingpointmodel.BoardingPointsDetailsModelResponse;

import java.util.ArrayList;

public class BusBordingPointAdapter extends ArrayAdapter<BoardingPointsDetailsModelResponse> {
    //    private final ArrayList<TvSyscdfl00> receptype_list;
    ArrayList<BoardingPointsDetailsModelResponse> bordinglist = new ArrayList<>();

    public BusBordingPointAdapter(Context context, int textViewResourceId, ArrayList<BoardingPointsDetailsModelResponse> bordinglist) {
        super(context, textViewResourceId, bordinglist);
        this.bordinglist = bordinglist;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @Nullable
    @Override
    public BoardingPointsDetailsModelResponse getItem(int position) {
        return bordinglist.get(position);

    }

    @Override
    public int getCount() {
        int count = bordinglist.size();
        return count;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    private View getCustomView(int position, ViewGroup parent) {
        BoardingPointsDetailsModelResponse model = getItem(position);

        View spinnerRow = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        TextView label = spinnerRow.findViewById(android.R.id.text1);
        label.setText(model.getBoarding_pointname());
        return spinnerRow;
    }
}