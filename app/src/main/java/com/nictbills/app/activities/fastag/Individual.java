package com.nictbills.app.activities.fastag;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.LoginActivity;
import com.nictbills.app.activities.UserProfileActivity;
import com.nictbills.app.activities.fastag.Model.FasTagTypeList;
import com.nictbills.app.activities.fastag.retrofit.APIClient;
import com.nictbills.app.activities.fastag.retrofit.GetResult;
import com.nictbills.app.activities.fastag.utils.CustPrograssbar;
import com.nictbills.app.activities.fastag.utils.FilePickerUtils;
import com.nictbills.app.activities.fastag.utils.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nictbills.app.utils.Util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class Individual extends BaseActivity implements GetResult.MyListener {
    @BindView(R.id.applicant_name)
    EditText applicantName;
    @BindView(R.id.mobile_no)
    EditText mobileNo;
    @BindView(R.id.dob)
    EditText dob;
    @BindView(R.id.sector)
    EditText sector;
    @BindView(R.id.fastag_type)
    EditText fastagType;
    @BindView(R.id.pancard_no)
    EditText pancardNo;
    @BindView(R.id.pancard_photo)
    EditText pancardPhoto;
    @BindView(R.id.aadhaar_no)
    EditText aadhaarNo;
    @BindView(R.id.aadhaar_photo_front)
    EditText aadharPhotoFront;
    @BindView(R.id.aadhaar_photo_back)
    EditText aadharPhotoBack;
    @BindView(R.id.vehicle_rc_no)
    EditText vehicleRcNo;
    @BindView(R.id.vehicle_rc_photo)
    EditText vehicleRcPhoto;
    @BindView(R.id.applicant_photo)
    EditText applicantPhoto;
    @BindView(R.id.addr_name)
    EditText addrName;
    @BindView(R.id.addr_email)
    EditText addrEmail;
    @BindView(R.id.addr_pincode)
    EditText addrPincode;
    @BindView(R.id.addr_state)
    EditText addrState;
    @BindView(R.id.addr_city)
    EditText addrCity;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.addr_alt_mobile)
    EditText addrAltMobile;
    @BindView(R.id.btn_countinue)
    TextView btnContinue;
    String sector12="",fastag_type="";
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    String imagePath;
    CustPrograssbar custPrograssbar;
    int[] imageId=new int[]{R.id.pancard_photo,R.id.aadhaar_photo_front,R.id.aadhaar_photo_back,R.id.vehicle_rc_photo,R.id.applicant_photo};
    File file1,file2,file3,file4,file5;
    private String TAG = "";
    final Calendar myCalendar= Calendar.getInstance();
    private SharedPreferences sharedPreferences;
    private String reg_mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);
        ButterKnife.bind(this);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(Individual.this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // credential = sharedPreferences.getString("cred_1", "");
        reg_mobile = sharedPreferences.getString("cred_2", "");
        mobileNo.setText(reg_mobile);
        sector12=getIntent().getStringExtra("sector");
        fastag_type=getIntent().getStringExtra("certificate_type");
        sector.setText(sector12);
        fastagType.setText(fastag_type);
        sector.setEnabled(false);
        fastagType.setEnabled(false);
        DatePickerDialog.OnDateSetListener  date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog= new DatePickerDialog(Individual.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();
            }
        });
    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        dob.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    public void callback(JsonObject result, String callNo) {
        try {
            progressDialogDismiss();
            if (callNo.equalsIgnoreCase("1")) {
                Gson gson = new Gson();
                FasTagTypeList home = gson.fromJson(result.toString(), FasTagTypeList.class);

                if (home.getResponseCode().equalsIgnoreCase("200")) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Individual.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.success));
                    sweetAlertDialog.setConfirmText(getString(R.string.your_request_submitted_successfully));
                    sweetAlertDialog.setCustomImage(R.drawable.success_green);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            Intent intent = new Intent(Individual.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                    // finish();
                    // Toast.makeText(this,"Details added",Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(this,getString(R.string.something_went_wrong_please_try_again_later),Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            Log.e("Error", "-->" + e.toString());

        }
    }
    @OnClick({ R.id.btn_countinue,R.id.pancard_photo,R.id.aadhaar_photo_front,R.id.aadhaar_photo_back ,R.id.vehicle_rc_photo,R.id.applicant_photo})
    public void onClick(View view) {
        if (view.getId() == R.id.btn_countinue) {
            if(validation()){
                updateDetails();
            }
        }else if(view.getId()==R.id.pancard_photo || view.getId()==R.id.aadhaar_photo_front || view.getId()==R.id.aadhaar_photo_back || view.getId()==R.id.vehicle_rc_photo || view.getId()==R.id.applicant_photo){
            selectImage(view.getId());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String file_gen=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss").format(new Date()).replaceAll("-","").replaceAll(":","");

        if (resultCode == RESULT_OK) {
            if (requestCode<5) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                int index=requestCode;
                if(index==0){
                    file1 = bitmapToFile(getApplicationContext(),bitmap,file_gen+"pan.png");
                    ((TextView)findViewById(imageId[index])).setText(file_gen+"pan.png");
                }else if(index==1){
                    file2 = bitmapToFile(getApplicationContext(),bitmap,file_gen+"adhar_frnt.png");
                    ((TextView)findViewById(imageId[index])).setText(file_gen+"adhar_frnt.png");
                }else if(index==2){
                    file3 = bitmapToFile(getApplicationContext(),bitmap,file_gen+"adhar_back.png");
                    ((TextView)findViewById(imageId[index])).setText(file_gen+"adhar_back.png");
                }else if(index==3){
                    file4 = bitmapToFile(getApplicationContext(),bitmap,file_gen+"vhc_rc.png");
                    ((TextView)findViewById(imageId[index])).setText(file_gen+"vhc_rc.png");
                }
                else if(index==4){
                    file5 = bitmapToFile(getApplicationContext(),bitmap,file_gen+"applicant.png");
                    ((TextView)findViewById(imageId[index])).setText(file_gen+"applicant.png");
                }

            } else  {
                // Get the Image from data
                Uri fileUri = data.getData();
                String filename = FilePickerUtils.getFileName(getApplicationContext(), fileUri);

                File fileData = null;
                try {
                    fileData = FilePickerUtils.getFileFromContentUri(getApplicationContext(), fileUri, filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Set the Image in ImageView for Previewing the Media
                try{
                    int index=requestCode-5;
                    ((TextView)findViewById(imageId[index])).setText(filename);
                    if(index==0){
                        file1=fileData;
                    }else if(index==1){
                        file2=fileData;
                    }else if(index==2){
                        file3=fileData;
                    }else if(index==3){
                        file4=fileData;
                    }
                    else if(index==4){
                        file5=fileData;
                    }

                } catch (Exception ex) {

                }
            }
        }
    }



    private void updateDetails() {
        progressDialogShow();
        RequestBody addr_id= RequestBody.create(MediaType.parse("text/plain"), mobileNo.getText().toString().trim());;
        RequestBody applicant_name= RequestBody.create(MediaType.parse("text/plain"),applicantName.getText().toString().trim());
        RequestBody mobile_no=RequestBody.create(MediaType.parse("text/plain"),mobileNo.getText().toString());
        RequestBody dob1=RequestBody.create(MediaType.parse("text/plain"),dob.getText().toString().trim());
        RequestBody sector1=RequestBody.create(MediaType.parse("text/plain"),sector.getText().toString());
        RequestBody fastag_type=RequestBody.create(MediaType.parse("text/plain"),fastagType.getText().toString().trim());
        RequestBody pancard_no=RequestBody.create(MediaType.parse("text/plain"),pancardNo.getText().toString().trim());
        RequestBody addr_name=RequestBody.create(MediaType.parse("text/plain"),addrName.getText().toString().trim());
        RequestBody addr_email=RequestBody.create(MediaType.parse("text/plain"),addrEmail.getText().toString().trim());
        RequestBody addr_pincode=RequestBody.create(MediaType.parse("text/plain"),addrPincode.getText().toString().trim());
        RequestBody addr_state=RequestBody.create(MediaType.parse("text/plain"),addrState.getText().toString().trim());
        RequestBody addr_city=RequestBody.create(MediaType.parse("text/plain"),addrCity.getText().toString().trim());
        RequestBody address1=RequestBody.create(MediaType.parse("text/plain"),address.getText().toString().trim());
        RequestBody alt_no=RequestBody.create(MediaType.parse("text/plain"),addrAltMobile.getText().toString().trim());
        RequestBody aadhaar_no=RequestBody.create(MediaType.parse("text/plain"),aadhaarNo.getText().toString().trim());
        RequestBody vehicle_rc_no=RequestBody.create(MediaType.parse("text/plain"),vehicleRcNo.getText().toString().trim());


        //creating request body for file
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        MultipartBody.Part body1 = MultipartBody.Part.createFormData("pancard_photo", file1.getName(), requestFile1);


        //creating request body for file
        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
        MultipartBody.Part body2 = MultipartBody.Part.createFormData("aadhaar_photo_front", file2.getName(), requestFile2);


        //creating request body for file
        RequestBody requestFile3 = RequestBody.create(MediaType.parse("multipart/form-data"), file3);
        MultipartBody.Part body3 = MultipartBody.Part.createFormData("aadhaar_photo_back", file3.getName(), requestFile3);


        //creating request body for file
        RequestBody requestFile4 = RequestBody.create(MediaType.parse("multipart/form-data"), file4);
        MultipartBody.Part body4 = MultipartBody.Part.createFormData("vehicle_rc_photo", file4.getName(), requestFile4);


        //creating request body for file
        RequestBody requestFile5 = RequestBody.create(MediaType.parse("multipart/form-data"), file5);
        MultipartBody.Part body5 = MultipartBody.Part.createFormData("applicant_photo", file5.getName(), requestFile5);

        try {
            Map<String, String> map = new HashMap<>();
            map.put("Authorization", "Bearer "+ Utility.authorization);
            Call<JsonObject> call = APIClient.getInterface().addFasTagRequestForIndividuals(map,addr_id,applicant_name,mobile_no,dob1,sector1,fastag_type,pancard_no,body1,body2,body3,body4,body5,addr_name,addr_email,addr_pincode,addr_state,addr_city,address1,alt_no,aadhaar_no, vehicle_rc_no);

            GetResult getResult = new GetResult();
            getResult.setMyListener(this);
            getResult.callForLogin(call, "1");

        }catch(Exception e){
            e.printStackTrace();
        }
    }



    private void selectImage(int id) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(Individual.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    int id1=0;
                    for(int i=0;i<imageId.length;i++) {
                        if(imageId[i]==id){
                            id1=i;
                        }
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                        {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                        }
                        else
                        {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, id1);
                        }
                    }
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    int id1=0;
                    for(int i=0;i<imageId.length;i++) {
                        if(imageId[i]==id){
                            id1=i;
                        }
                    }
                    Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    galleryIntent.setType("*/*");
                    startActivityForResult(galleryIntent, id1+5);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean validation() {
        boolean checkError=true;
        if (addrAltMobile.getText().toString().isEmpty() || addrAltMobile.getText().length()!=10) {
            addrAltMobile.setError("Enter Alternate Mobile no");
            checkError= false;
            addrAltMobile.requestFocus();
        }

        if (address.getText().toString().isEmpty()) {
            address.setError("Enter Address");
            checkError= false;
            address.requestFocus();
        }

        if (addrCity.getText().toString().isEmpty()) {
            addrCity.setError("Enter City");
            checkError= false;
            addrCity.requestFocus();
        }

        if (addrState.getText().toString().isEmpty()) {
            addrState.setError("Enter State");
            checkError= false;
            addrState.requestFocus();
        }

        if (addrPincode.getText().toString().isEmpty() || addrPincode.getText().toString().length()!=6) {
            addrPincode.setError("Enter Shipping Pincode");
            checkError= false;
            addrPincode.requestFocus();
        }

        if (addrEmail.getText().toString().isEmpty()) {
            addrEmail.setError("Enter Shipping Email");
            checkError= false;
            addrEmail.requestFocus();
        }


        if (addrName.getText().toString().isEmpty()) {
            addrName.setError("Enter Shipping Name");
            checkError= false;
            addrName.requestFocus();
        }

        if (file5==null) {
            applicantPhoto.setError("Select File");
            checkError= false;
            addrName.requestFocus();
        }

        if (file4==null) {
            vehicleRcPhoto.setError("Select File");
            checkError= false;
            addrName.requestFocus();
        }

        if (vehicleRcNo.getText().toString().isEmpty()) {
            vehicleRcNo.setError("Enter Vehicle RC No.");
            checkError= false;
            vehicleRcNo.requestFocus();
        }

        if (file3==null) {
            aadharPhotoBack.setError("Select File");
            checkError= false;
            vehicleRcNo.requestFocus();
        }

        if (file2==null) {
            aadharPhotoFront.setError("Select File");
            checkError= false;
            vehicleRcNo.requestFocus();
        }
        if (aadhaarNo.getText().toString().isEmpty() || aadhaarNo.getText().toString().length()!=12) {
            aadhaarNo.setError("Enter Aadhaar no");
            checkError= false;
            aadhaarNo.requestFocus();
        }
        if (file1==null) {
            pancardPhoto.setError("Select File");
            checkError= false;
            pancardNo.requestFocus();
        }
        if (pancardNo.getText().toString().isEmpty() || pancardNo.getText().toString().length()!=10) {
            pancardNo.setError("Enter Pancard no");
            checkError= false;
            pancardNo.requestFocus();
        }
        if (dob.getText().toString().isEmpty()) {
            dob.setError("Enter DOB");
            checkError= false;
            mobileNo.requestFocus();
        }

        if (mobileNo.getText().toString().isEmpty() || mobileNo.getText().toString().length()!=10) {
            mobileNo.setError("Enter Mobile no");
            checkError= false;
            mobileNo.requestFocus();
        }
        if (applicantName.getText().toString().isEmpty()) {
            applicantName.setError("Enter Applicant Name");
            checkError= false;
            applicantName.requestFocus();
        }

        return checkError;
    }
    public  File bitmapToFile(Context context, Bitmap bitmap, String fileNameToSave) {

        // File name like "image.png"
        //create a file to write bitmap data
        File file = null;
        try {
            File cachePath = new File(this.getExternalCacheDir(), "my_images/");
            if(!cachePath.exists()) {
                cachePath.mkdirs();
            }
            //create png file
            file = new File(cachePath, fileNameToSave);
            // file = new File( fileNameToSave);
            file.createNewFile();

//Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100 , bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        }catch (Exception e){
            e.printStackTrace();
            return file; // it will return null
        }
    }
}