package com.nictbills.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.nictbills.app.R;
import com.nictbills.app.activities.health_id_abdm.dto.banner.Result;
import com.smarteist.autoimageslider.SliderViewAdapter;


import java.util.List;

public class BannerSliderAdapter extends SliderViewAdapter<BannerSliderAdapter.SliderAdapterVH> {

        private Context context;
        private List<Result> dataModelArrayList;
        //private LayoutInflater inflater;

        public BannerSliderAdapter(Context context, List<Result> dataModelArrayList) {
            this.context = context;
            //inflater = LayoutInflater.from(context);
            this.dataModelArrayList = dataModelArrayList;
        }

        @Override
        public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_adapte, null);
            return new SliderAdapterVH(inflate);
        }

        @Override
        public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
            //viewHolder.textViewDescription.setText("This is slider item " + position);

            Glide.with(viewHolder.itemView)
                    .load(dataModelArrayList.get(position).getImgSource()+"?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                    .into(viewHolder.imageViewBackground);

            viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = String.valueOf(dataModelArrayList.get(position).getRedirectTo());
                    if(url.equalsIgnoreCase("NA")){
                        // Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                    }else{
                        if (!url.startsWith("http://") && !url.startsWith("https://"))
                            url = "http://" + url;

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        context.startActivity(browserIntent);
                    }
                }
            });

        }

        @Override
        public int getCount() {
            //slider view count could be dynamic size
            return dataModelArrayList.size();
        }

        class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

            View itemView;
            ImageView imageViewBackground;
            TextView textViewDescription;

            public SliderAdapterVH(View itemView) {
                super(itemView);
                imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
                textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
                this.itemView = itemView;
            }
        }
    }
