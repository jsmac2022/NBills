package com.nictbills.app.activities.household.adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.household.retrofit.APIClient;
import com.nictbills.app.activities.household.model.SubcatDatum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {

    private List<SubcatDatum> mCatlist;
    private RecyclerTouchListener listener;
    private String typeview;
    Context context;
    public interface RecyclerTouchListener {
        public void onClickServiceItem(SubcatDatum dataItem, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView txtSubtitle;
        public ImageView imgItem;
        public LinearLayout lvlclick;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txt_title);
            txtSubtitle = (TextView) view.findViewById(R.id.txt_subtitle);
            imgItem = view.findViewById(R.id.image_service);
            lvlclick = view.findViewById(R.id.lvl_itemclick);
        }
    }

    public ServiceAdapter(Context context, List<SubcatDatum> mCatlist, final RecyclerTouchListener listener, String typeview) {

        this.mCatlist = mCatlist;
        this.listener = listener;
        this.typeview = typeview;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (typeview.equalsIgnoreCase("viewall")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_service_view, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_service, parent, false);
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        SubcatDatum category = mCatlist.get(position);
        holder.title.setText(category.getSub_serv() + "");
        holder.txtSubtitle.setText("" );

        Picasso.with(context).load(category.getLogo()).into(holder.imgItem);

        holder.lvlclick.setOnClickListener(v -> {

            listener.onClickServiceItem(category, position);
        });
    }

    @Override
    public int getItemCount() {
        if(typeview.equals("home") && mCatlist.size()>4){
            return 4;
        }else {
            return mCatlist.size();
        }
    }
}