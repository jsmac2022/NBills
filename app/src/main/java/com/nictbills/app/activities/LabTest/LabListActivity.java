package com.nictbills.app.activities.LabTest;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.LabTest.adapter.LabListAdapter;
import com.nictbills.app.activities.LabTest.model.LabListModel;
import com.nictbills.app.activities.LabTest.viewmodel.LabListViewModel;
import com.nictbills.app.databinding.ActivityLabListBinding;

import java.util.ArrayList;

public class LabListActivity extends BaseActivity implements LifecycleOwner {
    ActivityLabListBinding binding;
    LabListActivity context;
    LabListViewModel labListViewModel;
    LabListAdapter labListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lab_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lab_list);
//        labListViewModel = ViewModelProviders(this, LabListActivity.this).get(LabListViewModel.class);
//        ViewModelProviders.
//        LabListViewModel viewModel = new ViewModelProvider(this, myViewModelFactory).get(MyViewModel.class);
//        LabListViewModel labListViewModel = new ViewModelProvider(this).get(LabListViewModel.class);

        labListAdapter= new LabListAdapter();
        binding.recyclerViewLablist.setAdapter(labListAdapter);
        labListViewModel = new ViewModelProvider(this).get(LabListViewModel.class);
        labListViewModel.getUserMutableLiveData().observe(this, userListUpdateObserver);
        click();

    }

    public void click() {
        binding.backArrowIMG.setOnClickListener(v -> {
            onBackPressed();
        });

    }

    Observer<ArrayList<LabListModel>> userListUpdateObserver = new Observer<ArrayList<LabListModel>>() {
        @Override
        public void onChanged(ArrayList<LabListModel> userArrayList) {
            labListAdapter.updateUserList(userArrayList);
//            binding.recyclerViewLablist.updateUserList(userArrayList);
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LabListActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}