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
import com.nictbills.app.activities.household.model.SearchChildcatdataItem;
import com.nictbills.app.activities.household.retrofit.APIClient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private List<SearchChildcatdataItem> mCatlist;
    private RecyclerTouchListener listener;
    Context context;

    public interface RecyclerTouchListener {
        public void onClickSearch(SearchChildcatdataItem dataItem, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ImageView imgItem;
        public LinearLayout lvlclick;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txt_title);
            imgItem = view.findViewById(R.id.image_service);
            lvlclick = view.findViewById(R.id.lvl_itemclick);
        }
    }

    public SearchAdapter(Context context, List<SearchChildcatdataItem> mCatlist, final RecyclerTouchListener listener) {

        this.mCatlist = mCatlist;
        this.listener = listener;
        this.context = context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        SearchChildcatdataItem category = mCatlist.get(position);
        holder.title.setText(category.getChildserv() + "");
        Picasso.with(context).load(category.getLogo()).into(holder.imgItem);

        holder.lvlclick.setOnClickListener(v -> {

            listener.onClickSearch(category, position);
        });
    }

    @Override
    public int getItemCount() {
        return mCatlist.size();


    }
}