package com.nictbills.app.activities.tbo.hotel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.tbo.hotel.adapter.HotelInfoImageAdapter;
import com.nictbills.app.activities.tbo.hotel.model.hotelinfoimg.HotelinfoImageModel;
import com.nictbills.app.activities.tbo.hotel.model.hotelinfomodel.HotelDetails;
import com.nictbills.app.activities.tbo.hotel.model.hotelinfomodel.HotelGetInfoResponse;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityHotelInfoBinding;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HotelInfoActivity extends BaseActivity {
    ActivityHotelInfoBinding binding;
    SharedPreferences shared;
    String gettrace_id, getcountry_code, getcity_code,gethotel_categoryid , gethotel_index, gethotel_code, gettokenhotel_id;
    HashMap<String, String> param;
    RetrofitInterface retroFitInterface;
    HotelGetInfoResponse hotelGetInfoResponse;
    ArrayList<HotelinfoImageModel> hotelimglist = new ArrayList<HotelinfoImageModel>();
    HotelInfoImageAdapter hotelInfoImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hotel_info);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        gettrace_id = shared.getString("TraceId", "");
        gettokenhotel_id = shared.getString("Tokenid_Hotel", "");
        getcountry_code = shared.getString("CountryCode", "");
        getcity_code = shared.getString("CityCode", "");
        gethotel_index = shared.getString("Hotelindex", "");
        gethotel_code = shared.getString("Hotelcode", "");
        gethotel_categoryid = shared.getString("catgeoryid", "");

        param = new HashMap<>();
        param.put("EndUserIp", "");
        param.put("ResultIndex", gethotel_index);
        param.put("HotelCode", gethotel_code);
        param.put("TokenId", gettokenhotel_id);
        param.put("TraceId", gettrace_id);
        param.put("CategoryId", gethotel_categoryid);
        onclick();

    }

    public void onclick() {
        get_HotelInfo(param);

        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //description
        binding.layDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgUpDesc.setVisibility(View.VISIBLE);
                binding.tvInfohotelDesc.setVisibility(View.VISIBLE);
                binding.imgDownDesc.setVisibility(View.GONE);

            }
        });

        binding.imgUpDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.imgDownDesc.setVisibility(View.VISIBLE);
                binding.tvInfohotelDesc.setVisibility(View.GONE);
                binding.imgUpDesc.setVisibility(View.GONE);

            }
        });
        //hotelimg

        binding.layHoteIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgDownHotelImg.setVisibility(View.GONE);
                binding.imgUpHotelImg.setVisibility(View.VISIBLE);
                binding.ViewListHotelImg.setVisibility(View.VISIBLE);
                binding.layHoteldy.setVisibility(View.VISIBLE);

            }
        });

        binding.imgUpHotelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.imgDownHotelImg.setVisibility(View.VISIBLE);
                binding.imgUpHotelImg.setVisibility(View.GONE);
                binding.ViewListHotelImg.setVisibility(View.GONE);
                binding.layHoteldy.setVisibility(View.GONE);

            }
        });

        //policy
        binding.layHotePolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgUpHotelPolicy.setVisibility(View.VISIBLE);
                binding.imgDownHotelPolicy.setVisibility(View.GONE);
                binding.tvInfohotelPolicy.setVisibility(View.VISIBLE);

            }
        });

        binding.imgUpHotelPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.imgUpHotelPolicy.setVisibility(View.GONE);
                binding.imgDownHotelPolicy.setVisibility(View.VISIBLE);
                binding.tvInfohotelPolicy.setVisibility(View.GONE);

            }
        });


        //facility
        binding.layHoteFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgDownHotelFaclities.setVisibility(View.GONE);
                binding.imgUpHotelFacilities.setVisibility(View.VISIBLE);
                binding.tvInfohotelFacilities.setVisibility(View.VISIBLE);
            }
        });


        binding.imgUpHotelFacilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvInfohotelFacilities.setVisibility(View.GONE);
                binding.imgUpHotelFacilities.setVisibility(View.GONE);
                binding.imgDownHotelFaclities.setVisibility(View.VISIBLE);
            }
        });

        binding.btnconti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HotelInfoActivity.this, HotelRoomGetActivity.class);
                startActivity(intent);
            }
        });

    }

    public void get_HotelInfo(HashMap<String, String> param) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
        Call<HotelGetInfoResponse> call = retroFitInterface.gethotelinfo(param);
        call.enqueue(new Callback<HotelGetInfoResponse>() {
            @Override
            public void onResponse(Call<HotelGetInfoResponse> call, Response<HotelGetInfoResponse> response) {
                progressDialogDismiss();
                hotelGetInfoResponse = response.body();
                Log.e("hotel info", "onResponse: ");

                if (response.code() == 200) {
                    if (hotelGetInfoResponse.getHotelInfoResult().getResponseStatus() == 1) {
                        HotelDetails hotelDetails = new HotelDetails();
                        ArrayList<String> array_image = new ArrayList<String>();
                        binding.layHotel.setVisibility(View.VISIBLE);
                        binding.layDesc.setVisibility(View.VISIBLE);
                        binding.layHoteContinue.setVisibility(View.VISIBLE);
                        binding.tvInfohotelName.setText(hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getHotelName());
                        binding.tvInfohotelAddress.setText(hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getAddress());


                        if (hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getHotelContactNo() == null) {
                            binding.layConatct.setVisibility(View.GONE);

                        } else {
                            binding.tvInfohotelContact.setText(hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getHotelContactNo());
                            binding.layConatct.setVisibility(View.VISIBLE);

                        }
                        if (hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getFaxNumber() == null) {
                            binding.layFax.setVisibility(View.GONE);

                        } else {
                            binding.layFax.setVisibility(View.VISIBLE);
                            binding.tvInfohotelFax.setText(hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getFaxNumber());

                        }
                        if (hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getPinCode() == null) {
                            binding.layPincode.setVisibility(View.GONE);

                        } else {
                            binding.layPincode.setVisibility(View.VISIBLE);
                            binding.tvInfohotelPincode.setText(hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getPinCode());
                        }

                        if (hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getImages() != null) {
                            binding.layHoteIMG.setVisibility(View.VISIBLE);


                             if(hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getImages().size()==0)
                             {

                             }
                             else
                             {
                                 for (int i = 0; i < hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getImages().size(); i++) {

                                     HotelinfoImageModel hotelinfoImageModel = new HotelinfoImageModel();

                                     Log.e("img", "save" + hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getImages().get(i).trim());


                                     hotelinfoImageModel.setHotelimg(hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getImages().get(i).trim());

                                     hotelimglist.add(hotelinfoImageModel);
//                                    if (hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getImages().size() > 0) {
//
//                                        Log.e("img", "save" + hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getImages().get(i).trim());
//                                        String urlimg = hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getImages().get(i);
//                                        ImageView imageView = new ImageView(HotelInfoActivity.this);
//                                        Glide.with(HotelInfoActivity.this)
//                                                .asBitmap()
//                                                .load(urlimg)
//                                                .error(R.drawable.hotel)
//                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                                .into(new CustomTarget<Bitmap>() {
//                                                    @Override
//                                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                                                        imageView.setImageBitmap(resource);
//                                                        imageView.buildDrawingCache();
//                                                    }
//
//                                                    @Override
//                                                    public void onLoadCleared(@Nullable Drawable placeholder) {
//                                                    }
//                                                });
//
//                                        addvieW(imageView, 200, 200);
//                                    }
                                 }


                                 LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                                 binding.ViewListHotelImg.setLayoutManager(linearLayoutManager);
                                 hotelInfoImageAdapter = new HotelInfoImageAdapter(HotelInfoActivity.this, hotelimglist);
                                 binding.ViewListHotelImg.setAdapter(hotelInfoImageAdapter);
                             }




                        }
                        if (hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getHotelFacilities() != null) {
                            binding.layHoteFacility.setVisibility(View.VISIBLE);
                            for (int i = 0; i < hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getHotelFacilities().size(); i++) {
                                String hotelfacility = hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getHotelFacilities().get(i);
                                Log.e("hotelfaxility", "save/..." + hotelfacility);
                                binding.tvInfohotelFacilities.setText(hotelfacility);

                            }
                        }

                        if (hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getHotelPolicy() == null) {
                        } else {
                            binding.layHotePolicy.setVisibility(View.VISIBLE);
                            String yourString = String.valueOf(hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getHotelPolicy());
                            String finaldata = Jsoup.parse(yourString).text();
                            binding.tvInfohotelPolicy.setText(finaldata);
                        }

                        if (hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getDescription() == null) {

                        } else {
                            String yourString = hotelGetInfoResponse.getHotelInfoResult().getHotelDetails().getDescription();
                            String finaldata = Jsoup.parse(yourString).text();
                            binding.tvInfohotelDesc.setText(finaldata);
                        }
//


                    } else {
                        Toast.makeText(HotelInfoActivity.this, "Api  Technical Response issue", Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 401) {
                    Toast.makeText(HotelInfoActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {
                    Toast.makeText(HotelInfoActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 400) {
                    Toast.makeText(HotelInfoActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HotelGetInfoResponse> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: ");
            }
        });


    }

    private void addvieW(ImageView imageView, int width, int height) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        // setting the margin in linearlayout
        params.setMargins(3, 10, 3, 3);
        imageView.setLayoutParams(params);
        // adding the image in layout
        binding.layHoteldy.addView(imageView);
    }

//    HotelInfoImageAdapter
}