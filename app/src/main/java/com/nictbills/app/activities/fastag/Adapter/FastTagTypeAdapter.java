package com.nictbills.app.activities.fastag.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.fastag.Model.FasTagType;


import java.util.List;

public class FastTagTypeAdapter extends RecyclerView.Adapter<FastTagTypeAdapter.MyViewHolder>{
    private Context mContext;
    private List<FasTagType> fastTaglist;
    private RecyclerTouchListener listener;
    private String typeview;

    public interface RecyclerTouchListener {
        public void onClickFastTagTypeItem(FasTagType item, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public LinearLayout lvlclick;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txt_title);
            lvlclick = view.findViewById(R.id.lvl_itemclick);

        }
    }

    public FastTagTypeAdapter(Context mContext, List<FasTagType> mCatlist, final RecyclerTouchListener listener, String viewtype) {
        this.mContext = mContext;
        this.fastTaglist = mCatlist;
        this.listener = listener;
        this.typeview = viewtype;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fasttagtype, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        FasTagType category = fastTaglist.get(position);
        holder.title.setText(category.getType_name() + "");
        holder.lvlclick.setOnClickListener(v -> {
            listener.onClickFastTagTypeItem(category, position);
        });
    }

    @Override
    public int getItemCount() {
        return fastTaglist.size();
    }
}
