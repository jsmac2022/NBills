package com.nictbills.app.activities.fastag.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.fastag.Model.FasTagCertificate;


import java.util.List;

public class CertificateAdapter extends RecyclerView.Adapter<CertificateAdapter.MyViewHolder> {

    private List<FasTagCertificate> mCatlist;
    private RecyclerTouchListener listener;
    private String typeview;

    public interface RecyclerTouchListener {
        public void onClickServiceItem(FasTagCertificate dataItem, int position);
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
            lvlclick = view.findViewById(R.id.lvl_itemclick);
        }
    }

    public CertificateAdapter(List<FasTagCertificate> mCatlist, final RecyclerTouchListener listener, String typeview) {

        this.mCatlist = mCatlist;
        this.listener = listener;
        this.typeview = typeview;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (typeview.equalsIgnoreCase("viewall")) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_view, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        FasTagCertificate category = mCatlist.get(position);
        holder.title.setText(category.getCertificate_name() + "");
        holder.txtSubtitle.setText("" );
      //  Picasso.get().load("").into(holder.imgItem);

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