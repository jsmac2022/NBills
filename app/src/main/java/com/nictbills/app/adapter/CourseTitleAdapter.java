package com.nictbills.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.nictbills.app.R;
import com.nictbills.app.activities.MSBM_Course_SelectActivity;
import com.nictbills.app.activities.health_id_abdm.dto.MsbmResponse;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CourseTitleAdapter extends RecyclerView.Adapter<CourseTitleAdapter.MyViewHolder> {
    Context context;

    private ArrayList<MsbmResponse> coursesearchResultList;
    private OnClickRecyclerViewInterface mListener;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView course_title_TV,duration_TV,time_TV,amount_TV;
        private Button book_course_BTN;
        private ImageView course_IMG;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.course_title_TV = (TextView) itemView.findViewById(R.id.course_title_TV);
            this.duration_TV = (TextView) itemView.findViewById(R.id.duration_TV);
            this.time_TV = (TextView) itemView.findViewById(R.id.time_TV);
            this.amount_TV = (TextView) itemView.findViewById(R.id.amount_TV);
            this.book_course_BTN = (Button) itemView.findViewById(R.id.book_course_BTN);
            this.course_IMG = (ImageView) itemView.findViewById(R.id.course_IMG);

        }
    }

    public CourseTitleAdapter(Context context, ArrayList<MsbmResponse> data, MSBM_Course_SelectActivity listener) {
        this.coursesearchResultList = data;
        this.context =  context;
        this.mListener = listener;
    }

    @Override
    public CourseTitleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_show_card, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        CourseTitleAdapter.MyViewHolder myViewHolder = new CourseTitleAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CourseTitleAdapter.MyViewHolder holder, final int listPosition) {

        TextView course_title_TV = holder.course_title_TV;
        TextView duration_TV = holder.duration_TV;
        TextView time_TV = holder.time_TV;
        TextView amount_TV = holder.amount_TV;
        Button book_course_BTN = holder.book_course_BTN;
        ImageView course_IMG = holder.course_IMG;

        //final CardView Product_Description_Card_View = holder.Product_Description_Card_View;

        book_course_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListItem(v, listPosition);

            }
        });


        Picasso.with(context).invalidate(coursesearchResultList.get(listPosition).getCourseIMG());
        Picasso.with(context)
                .load(coursesearchResultList.get(listPosition).getCourseIMG())
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.msbm_logo)
                .into(course_IMG);

        Picasso.with(context).invalidate(coursesearchResultList.get(listPosition).getCourseIMG());

        Picasso.with(context).load(coursesearchResultList.get(listPosition).getCourseIMG()).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(course_IMG);


        course_title_TV.setText(coursesearchResultList.get(listPosition).getCourseTitle());
        duration_TV.setText(coursesearchResultList.get(listPosition).getDuration());
        time_TV.setText(coursesearchResultList.get(listPosition).getEffort());
        amount_TV.setText("₹ "+coursesearchResultList.get(listPosition).getAmount());

        //place_TV.setText(searchResultList.get(listPosition).getPlace().toString());

    }

    @Override
    public int getItemCount() {
        return coursesearchResultList.size();
        //return mDataset.size();

    }

}
