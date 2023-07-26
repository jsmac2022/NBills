package com.nictbills.app.activities.LabTest.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.LabTest.model.LabListModel;

import java.util.ArrayList;
import java.util.List;

public class LabListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<LabListModel> userArrayList;

    public LabListAdapter() {
        this.userArrayList = new ArrayList<LabListModel>();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bllodtest_lay_lablistadapter,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        LabListModel user = userArrayList.get(position);
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;

        Log.e("user.getTitle()",""+user.getTitle());
        Log.e("user.getdesc()",""+user.getDescription());
        viewHolder.txtView_title.setText(user.getTitle());
        viewHolder.txtView_description.setText(user.getDescription());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public void updateUserList(final List<LabListModel> userArrayList) {
        this.userArrayList.clear();
        this.userArrayList = userArrayList;
        notifyDataSetChanged();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView_icon;
        TextView txtView_title;
        TextView txtView_description;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView_icon = itemView.findViewById(R.id.imgView_icon);
            txtView_title = itemView.findViewById(R.id.txtView_title);
            txtView_description = itemView.findViewById(R.id.txtView_description);


        }
    }
}
