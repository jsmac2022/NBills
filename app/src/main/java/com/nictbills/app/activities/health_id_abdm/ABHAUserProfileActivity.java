package com.nictbills.app.activities.health_id_abdm;

import static android.os.Environment.getExternalStoragePublicDirectory;

import androidx.core.content.FileProvider;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_user_profile.ABHAProfileResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.update_mobile_number.UpdateMobileNumberRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.update_mobile_number.UpdateMobileNumberResponse;
import com.nictbills.app.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ABHAUserProfileActivity extends BaseActivity {

    private ImageView backArrow_IMG,account_qr_IMG;
    private SharedPreferences sharedPreferences,sharedHI;
    private String accessToken,xToken,abha_certificate_name,healthIdNumber;
    private RetrofitInterface retroFitInterface;
    private TextView user_name_TV,abha_address_TV,copy_abha_address_TV,abha_id_TV,user_full_name_card_TV,user_year_of_birth_card_TV,user_gender_card_TV,
            user_mobile_card_TV,user_address_card_TV,abha_address_card_TV,abha_card_id_TV,edit_mobile_card_TV;
    private CircleImageView userProfile_Image,userProfileCard_Image;
    private LinearLayout logout_ABHA_card_LL,setABHA_Address_LL,download_ABHA_card_LL,setUser_Profile_LL,profile_ABHA_card_LL;
    private Dialog updateUSerProfileDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abhauser_profile);

        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        user_name_TV = findViewById(R.id.user_name_TV);
        abha_address_TV = findViewById(R.id.abha_address_TV);
        copy_abha_address_TV = findViewById(R.id.copy_abha_address_TV);
        abha_id_TV = findViewById(R.id.abha_id_TV);
        userProfile_Image = findViewById(R.id.userProfile_Image);
        account_qr_IMG = findViewById(R.id.account_qr_IMG);
        setABHA_Address_LL = findViewById(R.id.setABHA_Address_LL);
        download_ABHA_card_LL = findViewById(R.id.download_ABHA_card_LL);
        setUser_Profile_LL = findViewById(R.id.setUser_Profile_LL);

        user_full_name_card_TV = findViewById(R.id.user_full_name_card_TV);
        user_year_of_birth_card_TV = findViewById(R.id.user_year_of_birth_card_TV);
        user_gender_card_TV = findViewById(R.id.user_gender_card_TV);
        user_mobile_card_TV = findViewById(R.id.user_mobile_card_TV);
        user_address_card_TV = findViewById(R.id.user_address_card_TV);
        userProfileCard_Image = findViewById(R.id.userProfileCard_Image);
        abha_address_card_TV = findViewById(R.id.abha_address_card_TV);
        abha_card_id_TV = findViewById(R.id.abha_card_id_TV);
        profile_ABHA_card_LL = findViewById(R.id.profile_ABHA_card_LL);
        edit_mobile_card_TV = findViewById(R.id.edit_mobile_card_TV);
        logout_ABHA_card_LL = findViewById(R.id.logout_ABHA_card_LL);

        /*sharedPreferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
        credential = sharedPreferences.getString("cred_1", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");*/

       // sharedHI = getSharedPreferences("", Context.MODE_PRIVATE);

        try {
            sharedHI = Util.getSharedPreferenceInstance(this,"HI",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        accessToken = sharedHI.getString("accessToken", "");
        xToken = sharedHI.getString("x_token", "");

       // Log.e("hereeee",accessToken);

        getProfileDetails();

        profile_ABHA_card_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setABHA_Address_LL.setVisibility(View.GONE);
                setUser_Profile_LL.setVisibility(View.VISIBLE);

            }
        });

        edit_mobile_card_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMobileNUmberPopUp();
            }
        });

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (setUser_Profile_LL.getVisibility() == View.VISIBLE) {
                    // Its visible
                    setUser_Profile_LL.setVisibility(View.GONE);
                    setABHA_Address_LL.setVisibility(View.VISIBLE);

                } else {
                    // Either gone or invisible

                    final SweetAlertDialog sad = new SweetAlertDialog(ABHAUserProfileActivity.this, SweetAlertDialog.WARNING_TYPE);
                    sad.setTitleText(getString(R.string.back));
                    sad.setContentText(getString(R.string.are_you_sure_you_want_to_back));
                    sad.setConfirmText(getString(R.string.exit_yes));
                    sad.setCanceledOnTouchOutside(false);
                    sad.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            SharedPreferences preferences =getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(ABHAUserProfileActivity.this,ABHALandingPageActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    })
                            .setCancelButton(getString(R.string.exit_no), new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                }
                            })
                            .show();


                }

            }
        });


        copy_abha_address_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(abha_address_TV.getText().toString());
                    Toast.makeText(ABHAUserProfileActivity.this, getString(R.string.abha_address_copied), Toast.LENGTH_SHORT).show();
                } else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Address Address copy",abha_address_TV.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(ABHAUserProfileActivity.this, getString(R.string.abha_address_copied), Toast.LENGTH_SHORT).show();

                }
            }
        });


        logout_ABHA_card_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SweetAlertDialog sad = new SweetAlertDialog(ABHAUserProfileActivity.this, SweetAlertDialog.WARNING_TYPE);
                sad.setTitleText(getString(R.string.back));
                sad.setContentText(getString(R.string.are_you_sure_you_want_to_back));
                sad.setConfirmText(getString(R.string.exit_yes));
                sad.setCanceledOnTouchOutside(false);
                sad.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        SharedPreferences preferences =getSharedPreferences("HI", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();
                        Intent intent = new Intent(ABHAUserProfileActivity.this,ABHALandingPageActivity.class);
                        startActivity(intent);
                        finish();

                    }
                })
                        .setCancelButton(getString(R.string.exit_no), new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        download_ABHA_card_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadABHACard();
            }
        });


    }


    private void updateMobileNUmberPopUp(){
        updateUSerProfileDialog = new Dialog(ABHAUserProfileActivity.this,R.style.Theme_Dialog);
        updateUSerProfileDialog.setContentView(R.layout.update_abha_mobile_number_pop_up);
        updateUSerProfileDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        updateUSerProfileDialog.setCancelable(false);
        final EditText update_mobile_number_TV = updateUSerProfileDialog.findViewById(R.id.update_mobile_number_TV);
        Button submit_mobile_button = updateUSerProfileDialog.findViewById(R.id.submit_mobile_button);



        submit_mobile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpty(update_mobile_number_TV)){

                    Toast.makeText(ABHAUserProfileActivity.this, getString(R.string.mobile_number_is_required), Toast.LENGTH_SHORT).show();

                }else {

                    UpdateMobileNumberRequest updateMobileNumberRequest = new UpdateMobileNumberRequest();
                    updateMobileNumberRequest.setMobile(update_mobile_number_TV.getText().toString());
                    generateUpdateABHAMobileNumber(updateMobileNumberRequest);
                }

            }
        });

        updateUSerProfileDialog.show();
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }


    private void generateUpdateABHAMobileNumber(UpdateMobileNumberRequest updateMobileNumberRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<UpdateMobileNumberResponse> call = retroFitInterface.updateABHAMobileNumber("Bearer "+accessToken,Constant.HIP_ID,"Bearer "+xToken,updateMobileNumberRequest);

        call.enqueue(new Callback<UpdateMobileNumberResponse>() {
            @Override
            public void onResponse(Call<UpdateMobileNumberResponse> call, Response<UpdateMobileNumberResponse> response) {
                progressDialogDismiss();
                UpdateMobileNumberResponse body = response.body();

                if (response.code() == 200) {
                    updateUSerProfileDialog.dismiss();


                } else if (response.code() == 401) {
                    updateUSerProfileDialog.dismiss();
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ABHAUserProfileActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(ABHAUserProfileActivity.this,ABHALandingPageActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    updateUSerProfileDialog.dismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(ABHAUserProfileActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHAUserProfileActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    updateUSerProfileDialog.dismiss();
                    progressDialogDismiss();
                    Toast.makeText(ABHAUserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UpdateMobileNumberResponse> call, Throwable t) {
                progressDialogDismiss();
                updateUSerProfileDialog.dismiss();
                Toast.makeText(ABHAUserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getProfileDetails() {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<ABHAProfileResponse> call = retroFitInterface.getABHAUsersProfile("Bearer "+accessToken,Constant.HIP_ID,"Bearer "+xToken);

        call.enqueue(new Callback<ABHAProfileResponse>() {
            @Override
            public void onResponse(Call<ABHAProfileResponse> call, Response<ABHAProfileResponse> response) {

                ABHAProfileResponse body = response.body();

                if (response.code() == 200) {

                    getProfileQR();

                    if (body.getProfilePhoto()!=null){
                        byte[] decodedString = Base64.decode(body.getProfilePhoto(), Base64.DEFAULT);

                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                        userProfile_Image.setImageBitmap(decodedByte);
                        userProfileCard_Image.setImageBitmap(decodedByte);
                    }



                    abha_certificate_name= body.getName();
                    user_name_TV.setText(body.getName());
                    abha_address_TV.setText(body.getHealthId());
                    abha_address_card_TV.setText(body.getHealthId());
                    abha_id_TV.setText(body.getHealthIdNumber());
                    abha_card_id_TV.setText(body.getHealthIdNumber());
                    healthIdNumber = body.getHealthIdNumber();
                    user_full_name_card_TV.setText(body.getName());
                    user_year_of_birth_card_TV.setText(body.getYearOfBirth());
                    user_gender_card_TV.setText(body.getGender());
                    user_mobile_card_TV.setText(body.getMobile());
                    user_address_card_TV.setText(body.getAddress());


                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ABHAUserProfileActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(ABHAUserProfileActivity.this, ABHALandingPageActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(ABHAUserProfileActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHAUserProfileActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(ABHAUserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ABHAProfileResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(ABHAUserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getProfileQR() {

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<ResponseBody> call = retroFitInterface.getABHAProfileQR("Bearer "+accessToken,Constant.HIP_ID,"Bearer "+xToken);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                progressDialogDismiss();

                ResponseBody body = response.body();

                if (response.code() == 200) {

                    byte[] bytes = new byte[0];
                    try {
                        bytes = body.bytes();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    account_qr_IMG.setImageBitmap(bitmap);

                    setABHA_Address_LL.setVisibility(View.VISIBLE);
                   // Log.e("profilePic",bitmap.toString());


                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ABHAUserProfileActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(ABHAUserProfileActivity.this, ABHALandingPageActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(ABHAUserProfileActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHAUserProfileActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(ABHAUserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(ABHAUserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void downloadABHACard() {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(ABHAUserProfileActivity.this);
        progressLog.setTitle(getString(R.string.download_certificate));
        progressLog.setMessage(getString(R.string.downloading));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();


        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<ResponseBody> call = retroFitInterface.downloadABHACard("Bearer "+accessToken,Constant.HIP_ID,"Bearer "+xToken);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                progressLog.dismiss();

                ResponseBody body = response.body();

                if (response.code() == 200) {

                    writeResponseBodyToDisk(response.body(),"ABHA_certificate"+abha_certificate_name);

                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ABHAUserProfileActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(ABHAUserProfileActivity.this, ABHALandingPageActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressLog.dismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(ABHAUserProfileActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHAUserProfileActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(ABHAUserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressLog.dismiss();

                Toast.makeText(ABHAUserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, String certificateName) {
        try {
            // todo change the file location/name according to your needs
            File folderName = new File(String.valueOf(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));

            if (!folderName.exists()){
                folderName.mkdir();
            }

            File abha_file = new File(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator +" "+certificateName+".pdf");


            //Log.e("vaccine+++++",abha_file.toString());

            // String filePath= getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator +certificateName+".pdf";

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(abha_file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.e("TAG", "file download: " + fileSizeDownloaded + " of " + fileSize);

                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();



                }

                Uri photoURI = FileProvider.getUriForFile(ABHAUserProfileActivity.this, this.getApplicationContext().getPackageName() + ".provider", abha_file);

                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                // pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pdfOpenintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pdfOpenintent.setDataAndType(photoURI, "application/pdf");
                try {
                    startActivity(pdfOpenintent);
                }
                catch (ActivityNotFoundException e) {

                }
            }
        } catch (IOException e) {
            return false;
        }
    }


    @Override
    public void onBackPressed() {

        if (setUser_Profile_LL.getVisibility() == View.VISIBLE) {
            // Its visible
            setUser_Profile_LL.setVisibility(View.GONE);
            setABHA_Address_LL.setVisibility(View.VISIBLE);

        } else {
            // Either gone or invisible
            final SweetAlertDialog sad = new SweetAlertDialog(ABHAUserProfileActivity.this, SweetAlertDialog.WARNING_TYPE);
            sad.setTitleText(getString(R.string.back));
            sad.setContentText(getString(R.string.are_you_sure_you_want_to_back));
            sad.setConfirmText(getString(R.string.exit_yes));
            sad.setCanceledOnTouchOutside(false);
            sad.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.dismissWithAnimation();
                    SharedPreferences preferences =getSharedPreferences("HI", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();
                    Intent intent = new Intent(ABHAUserProfileActivity.this,ABHALandingPageActivity.class);
                    startActivity(intent);
                    finish();

                }
            })
                    .setCancelButton(getString(R.string.exit_no), new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                        }
                    })
                    .show();
        }


    }
}