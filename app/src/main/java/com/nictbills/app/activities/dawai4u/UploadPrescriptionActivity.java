package com.nictbills.app.activities.dawai4u;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.ImageCompression;
import com.nictbills.app.activities.LoginActivity;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.upload_prescription.CreateMedicalOrderDawai4UResponse;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.upload_prescription.CreateMedicalOrderRequest;
import com.nictbills.app.utils.Util;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadPrescriptionActivity extends BaseActivity {

    private Button submit_BTN,gob_doc_IMG_BTN;
    private EditText user_name_ET,age_ED,mobile_number_ET,altername_Et,state_Et,city_Et,pincode_Et,address_Et;
    private String gender="Male",encodedImageString,mobileNumber,alternate_mobile,city,state,address,pincode,email,name,store_name,accessToken,file_extension;
    private TextView chemist_shop_name_TV;
    private final int camera = 113;
    private int store_id;
    private RadioGroup radioSex;
    private String document_file="";
    private ImageView GovDocPreview_Img;
    private RetrofitInterface retroFitInterface;
    private SharedPreferences dawai4uShared;
    private LinearLayout view_LL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_prescription);

        submit_BTN= findViewById(R.id.submit_BTN);
        user_name_ET= findViewById(R.id.user_name_ET);
        age_ED= findViewById(R.id.age_ED);
        mobile_number_ET= findViewById(R.id.mobile_number_ET);
        altername_Et= findViewById(R.id.altername_Et);
        state_Et= findViewById(R.id.state_Et);
        city_Et= findViewById(R.id.city_Et);
        pincode_Et= findViewById(R.id.pincode_Et);
        address_Et= findViewById(R.id.address_Et);
        chemist_shop_name_TV= findViewById(R.id.chemist_shop_name_TV);
        gob_doc_IMG_BTN= findViewById(R.id.gob_doc_IMG_BTN);
        GovDocPreview_Img= findViewById(R.id.GovDocPreview_Img);
        radioSex= findViewById(R.id.radioSex);
        view_LL= findViewById(R.id.view_LL);


        dawai4uShared = this.getSharedPreferences("Dawai4U", Context.MODE_PRIVATE);
        accessToken= dawai4uShared.getString("token_dawai4u", "");

        Intent intent = getIntent();
        mobileNumber = intent.getStringExtra("MOBILE");
        alternate_mobile = intent.getStringExtra("ALTERNATE_MOBILE");
        city = intent.getStringExtra("CITY");
        state = intent.getStringExtra("STATE");
        address = intent.getStringExtra("ADDRESS");
        pincode = intent.getStringExtra("PINCODE");
        email = intent.getStringExtra("EMAIL");
       // gender = intent.getStringExtra("GENDER");
        name = intent.getStringExtra("NAME");
        store_name = intent.getStringExtra("STORE_NAME");
        store_id = intent.getIntExtra("STORE_ID",0);

        chemist_shop_name_TV.setText(store_name);
        user_name_ET.setText(name);
        mobile_number_ET.setText(mobileNumber);
        altername_Et.setText(alternate_mobile);
        state_Et.setText(state);
        city_Et.setText(city);
        pincode_Et.setText(pincode);
        address_Et.setText(address);

        gob_doc_IMG_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenCameraGallary(0);
                //EasyImage.openChooserWithGallery(UploadDocumentsActivity.this,"Choose Image",0);

            }
        });


        radioSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioMale:

                        gender = "Male";
                        break;

                    case R.id.radioFemale:

                        gender = "Female";
                        break;
                }

            }
        });

        GovDocPreview_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (document_file==null || document_file.isEmpty()){
                    Toast.makeText(UploadPrescriptionActivity.this, getString(R.string.kindly_upload_prescription), Toast.LENGTH_SHORT).show();
                    return;
                }
                showImageDialog(document_file);

            }
        });


        submit_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (isEmpty(user_name_ET)){
                    Snackbar.make(view, getString(R.string.kindly_enter_your_full_name), Snackbar.LENGTH_SHORT).show();
                } else if (isEmpty(age_ED)){
                    Snackbar.make(view, getString(R.string.kindly_enter_age), Snackbar.LENGTH_SHORT).show();

                } else if (isEmpty(mobile_number_ET)){
                    Snackbar.make(view, getString(R.string.mobile_number_is_required), Snackbar.LENGTH_SHORT).show();

                }/* else if (isEmpty(altername_Et)){
                    Snackbar.make(view, getString(R.string.alter), Snackbar.LENGTH_SHORT).show();

                }*/ else if (isEmpty(state_Et)){
                    Snackbar.make(view, getString(R.string.kindly_enter_state), Snackbar.LENGTH_SHORT).show();

                } else if (isEmpty(city_Et)){
                    Snackbar.make(view, getString(R.string.kindly_select_city), Snackbar.LENGTH_SHORT).show();

                } else if (isEmpty(pincode_Et)){
                    Snackbar.make(view, getString(R.string.pincode_required), Snackbar.LENGTH_SHORT).show();

                } else if (isEmpty(address_Et)){
                    Snackbar.make(view, getString(R.string.kindly_enter_address), Snackbar.LENGTH_SHORT).show();

                } else if (document_file.equalsIgnoreCase("")){
                    Snackbar.make(view, getString(R.string.kindly_upload_prescription), Snackbar.LENGTH_SHORT).show();

                } else {
                    CreateMedicalOrderRequest saveProfileRequest = new CreateMedicalOrderRequest();
                    saveProfileRequest.setStore_id(store_id);
                    saveProfileRequest.setAddr_id(mobile_number_ET.getText().toString());
                    saveProfileRequest.setName(user_name_ET.getText().toString());
                    saveProfileRequest.setAge(age_ED.getText().toString());
                    saveProfileRequest.setGender(gender);
                    saveProfileRequest.setMobile_no(mobile_number_ET.getText().toString());
                    saveProfileRequest.setAddr_name(user_name_ET.getText().toString());
                    saveProfileRequest.setAddr_email("addr_email");
                    saveProfileRequest.setAddr_pincode(pincode_Et.getText().toString());
                    saveProfileRequest.setAddr_state(state_Et.getText().toString());
                    saveProfileRequest.setAddr_city(city_Et.getText().toString());
                    saveProfileRequest.setAddress(address_Et.getText().toString());
                    saveProfileRequest.setFile_extension("jpg");
                    saveProfileRequest.setPrescription(encodedImageString);

                    if (isEmpty(altername_Et)){
                        saveProfileRequest.setAddr_alt_mobile(mobile_number_ET.getText().toString());
                    } else {
                        saveProfileRequest.setAddr_alt_mobile(altername_Et.getText().toString());
                    }
                    createOrderDawai4U(saveProfileRequest);

                }

            }
        });


    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> list, EasyImage.ImageSource imageSource, int i) {
                doCompressImage(list.get(0).getAbsolutePath(), i);

                Log.e("imageee",list.get(0).getAbsolutePath());

            }
        });
    }

    private void doCompressImage(String imagePath, final int selectedPosition) {
        try {
            ImageCompression imageCompression = new ImageCompression(this);
            imageCompression.execute(imagePath);
            imageCompression.setOnTaskFinishedEvent(new ImageCompression.AsyncResponse() {
                @Override
                public void processFinish(String imagePath) {
                    if (selectedPosition==0){
                        document_file = imagePath;
                        Log.e("new_image",imagePath);

                        file_extension = Util.getMimeType(UploadPrescriptionActivity.this,Uri.fromFile(new File(imagePath)));

                        InputStream inputStream = null; // You can get an inputStream using any I/O API
                        try {
                            inputStream = new FileInputStream(imagePath);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        byte[] bytes;
                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        ByteArrayOutputStream output = new ByteArrayOutputStream();

                        try {
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                output.write(buffer, 0, bytesRead);
                            }
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                        bytes = output.toByteArray();
                       // String encodedImageString = Base64.encodeToString(bytes, Base64.DEFAULT);
                        encodedImageString =new String(Base64.encodeBase64(bytes));



                       /* File f = new File(imagePath);
                        FileInputStream fin = null;
                        try {
                            fin = new FileInputStream(f);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        byte imagebytearray[] = new byte[(int)f.length()];
                        try {
                            fin.read(imagebytearray);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                      //  String imagetobase64 = Base64.encodeBase64String(imagebytearray);
                        encodedImageString = new String(Base64.encodeBase64(imagebytearray));
                       // Log.e("baseeee64",encodedString);
                        try {
                            fin.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/

                    }

                    /*else if (selectedPosition==1){
                        // aadharFront=imagePath;
                        panImg=imagePath;
                    }else if (selectedPosition==2){
                        aadharFront=imagePath;
                        // aadharBack=imagePath;
                    } else if (selectedPosition==3){
                        // aadharFront=imagePath;
                        aadharBack=imagePath;
                    }*/
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void doOpenCameraGallary(int select_position){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission(UploadPrescriptionActivity.this, Manifest.permission.CAMERA) != PermissionChecker.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UploadPrescriptionActivity.this, new String[]{Manifest.permission.CAMERA}, camera);

            } else if (ActivityCompat.checkSelfPermission(UploadPrescriptionActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UploadPrescriptionActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, camera);

            }else if (ActivityCompat.checkSelfPermission(UploadPrescriptionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UploadPrescriptionActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, camera);
            }
            else {
                EasyImage.openChooserWithGallery(UploadPrescriptionActivity.this,"Choose Image",select_position);
            }

        } else {

            EasyImage.openChooserWithGallery(UploadPrescriptionActivity.this,"Choose Image",select_position);
        }
    }
    private void showImageDialog(String img_path){

        Dialog dialog=new Dialog(UploadPrescriptionActivity.this);
        dialog.setContentView(R.layout.documents_image_view);
        ImageView doc_IV = dialog.findViewById(R.id.doc_IV);

        Glide.with(UploadPrescriptionActivity.this)
                .load(new File(img_path))
                .error(R.drawable.nict_bills_services_logo) //error
                .override(900, 400)
                .fitCenter()
                .into(doc_IV);

        dialog.show();
    }


    private void createOrderDawai4U(CreateMedicalOrderRequest saveProfileRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.TEST_VINOD).create(RetrofitInterface.class);

        Call<CreateMedicalOrderDawai4UResponse> call = retroFitInterface.createOrderDawai4U("Bearer "+accessToken,saveProfileRequest);

        call.enqueue(new Callback<CreateMedicalOrderDawai4UResponse>() {
            @Override
            public void onResponse(Call<CreateMedicalOrderDawai4UResponse> call, Response<CreateMedicalOrderDawai4UResponse> response) {
                progressDialogDismiss();

                CreateMedicalOrderDawai4UResponse body = response.body();

                if (response.code() == 200) {
                    view_LL.setVisibility(View.GONE);
                    //Toast.makeText(UploadPrescriptionActivity.this, "Upload", Toast.LENGTH_SHORT).show();
                    order_successful_PonUpDialog();

                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(UploadPrescriptionActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(UploadPrescriptionActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {

                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(UploadPrescriptionActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(UploadPrescriptionActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(UploadPrescriptionActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CreateMedicalOrderDawai4UResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(UploadPrescriptionActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void order_successful_PonUpDialog() {
        final Dialog successDialog;
        successDialog = new Dialog(UploadPrescriptionActivity.this, R.style.Theme_Dialog);
        successDialog.setContentView(R.layout.successfully_upload_message_dawai_for_u);
        successDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        successDialog.setCancelable(false);

        Button ok_Button = successDialog.findViewById(R.id.ok_Button);

        ok_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                successDialog.dismiss();

                Intent intent = new Intent(UploadPrescriptionActivity.this, OrderHistoryDawai4UActivity.class);
                startActivity(intent);
                finish();
            }
        });

        successDialog.show();
    }

}