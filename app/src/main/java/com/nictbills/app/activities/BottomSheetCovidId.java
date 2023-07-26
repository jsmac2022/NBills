package com.nictbills.app.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nictbills.app.R;

public class BottomSheetCovidId extends BottomSheetDialogFragment {

    //private BottomSheetDialog.CallBack callBack;

    public BottomSheetCovidId(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.covid_photoid_bottom_sheet, container, false);
        return view;
    }

}