package com.nictbills.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.covidPhotoId.Type;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import java.util.List;

public class ListOfCovidIdPhotoAdapter extends RecyclerView.Adapter<ListOfCovidIdPhotoAdapter.MyViewHolder> {
    Context context;

    private List<Type> idPhotoResultList;
    private OnClickRecyclerViewInterface myListener;


    /*SearchPujaAdapter(SearchPujaRecyclerViewClickListner listener) {
        mListener = listener;
    }*/


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView photo_id_name_TV;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.photo_id_name_TV = (TextView) itemView.findViewById(R.id.photo_id_name_TV);

        }
    }

    public ListOfCovidIdPhotoAdapter(Context context, List<Type> data, OnClickRecyclerViewInterface listener) {
        this.idPhotoResultList = data;
        this.context =  context;
        this.myListener = listener;
    }

    @Override
    public ListOfCovidIdPhotoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vaccine_id_photo_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        ListOfCovidIdPhotoAdapter.MyViewHolder myViewHolder = new ListOfCovidIdPhotoAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ListOfCovidIdPhotoAdapter.MyViewHolder holder, final int listPosition) {

        TextView photo_id_name_TV = holder.photo_id_name_TV;


        //final CardView Product_Description_Card_View = holder.Product_Description_Card_View;


        photo_id_name_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onListItem(v, listPosition);

            }
        });


        photo_id_name_TV.setText(idPhotoResultList.get(listPosition).getType());



    }

    @Override
    public int getItemCount() {
        return idPhotoResultList.size();
        //return mDataset.size();

    }
}
