package com.nictbills.app.activities.household.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.household.adepter.ServiceAdapter;
import com.nictbills.app.activities.household.model.SubcatDatum;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceAllActivity extends BasicActivity implements ServiceAdapter.RecyclerTouchListener {

    @BindView(R.id.recycler_service)
    RecyclerView recyclerService;
    ArrayList<SubcatDatum> subcatDataItems=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_all);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("View All Service");
       // subcatDataItems = getIntent().getParcelableArrayListExtra("ListExtra");

        ServiceAdapter itemAdp = new ServiceAdapter(this,subcatDataItems ,this, "viewall");
        recyclerService.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerService.setAdapter(itemAdp);
    }

    @Override
    public void onClickServiceItem(SubcatDatum dataItem, int position) {

        startActivity(new Intent(this, SubCategoryActivity.class)
                .putExtra("vurl","")
                .putExtra("name", dataItem.getSub_serv())
                .putExtra("named", "")
                .putExtra("cid","")
                .putExtra("sid",dataItem.getSsc()));
    }
}