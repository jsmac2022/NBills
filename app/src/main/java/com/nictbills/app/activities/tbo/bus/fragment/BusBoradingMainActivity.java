package com.nictbills.app.activities.tbo.bus.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.bus.AddBusMemberActivity;
import com.nictbills.app.activities.tbo.bus.BusSeatActivity;

import java.util.ArrayList;
import java.util.List;

public class BusBoradingMainActivity extends AppCompatActivity {

    private ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
    public static ViewPager viewPager;
    public static Button btncontinue;
    ImageView imageViewback;
    SharedPreferences shared ,sharedPreferences;
    String getoriginname,getdropingname ,getbording_index ,getdroping_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_borading_main);
//        binding= DataBindingUtil.setContentView(this,R.layout.activity_bus_borading_main);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);

        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        btncontinue=(Button)findViewById(R.id.btncontinu);
        imageViewback=(ImageView)findViewById(R.id.backArrow_IMG);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.add(new BoardingFragment(), "Pickup Point");
        viewPagerAdapter.add(new DroppingFragment(), "Drop Point");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getoriginname = shared.getString("originname", "");
                getdropingname = shared.getString("Dropinname", "");
                getbording_index = shared.getString("Bordingindex", "");
                getdroping_index = shared.getString("Dropingindex", "");

                if(getbording_index.equals(""))
                {
                    Toast.makeText(BusBoradingMainActivity.this, "Select PickUp Point", Toast.LENGTH_SHORT).show();

                }
                else if(getdroping_index.equals(""))
                {

                    Toast.makeText(BusBoradingMainActivity.this, "Select Drop Point", Toast.LENGTH_SHORT).show();

                }
                else
                {
//                    Toast.makeText(BusBoradingMainActivity.this, "Selected Both", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BusBoradingMainActivity.this, AddBusMemberActivity.class);
                    startActivity(intent);

                }




            }
        });

        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm)
        {
            super(fm);
        }

        public void add(Fragment fragment, String title)
        {
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull @Override public Fragment getItem(int position)
        {
            return fragments.get(position);
        }

        @Override public int getCount()
        {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position)
        {
            return fragmentTitle.get(position);
        }
    }
}