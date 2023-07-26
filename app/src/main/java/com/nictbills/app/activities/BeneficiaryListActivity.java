package com.nictbills.app.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.adapter.CovidBeneficiaryListAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.beneficiary_dto.Beneficiary;
import com.nictbills.app.activities.health_id_abdm.dto.beneficiary_dto.CovidBeneficiaryListDTO;
import com.nictbills.app.activities.health_id_abdm.dto.cancel_beneficiary.CovidBeneficiaryCancelDTO;
import com.nictbills.app.activities.health_id_abdm.dto.delete_beneficiary.BeneficiaryDeleteDtoRequest;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.nictbills.app.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class BeneficiaryListActivity extends AppCompatActivity implements OnClickRecyclerViewInterface {

    private RecyclerView beneficiary_list_recy;
    private RetrofitInterface retroFitInterface;
    private RecyclerView.LayoutManager layoutManager;
    private List<Beneficiary> beneficiaryList;
    private SharedPreferences sharedPreferences;
    private String authTok,appointmentId,beneficiary_reference_id;
    private Button add_new_beneficiary_BTN;
    private RelativeLayout bottomView;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotificationManager;
    private List<String> deleteBeneficiary = new ArrayList<String>();
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private ImageView backArrow_IMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_beneficiary_list);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        /* credential = sharedPreferences.getString("cred_1", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");*/
        authTok = sharedPreferences.getString("cred_vaccine", "");

        beneficiary_list_recy = findViewById(R.id.beneficiary_list_recy);
        add_new_beneficiary_BTN = findViewById(R.id.add_new_beneficiary_BTN);
        bottomView = findViewById(R.id.bottomView);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);


        beneficiary_list_recy.setHasFixedSize(true);

        //   layoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false);

        layoutManager = new LinearLayoutManager(BeneficiaryListActivity.this);
        beneficiary_list_recy.setLayoutManager(layoutManager);
        beneficiary_list_recy.setItemAnimator(new DefaultItemAnimator());
        getBeneficiaryDetails();


        add_new_beneficiary_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BeneficiaryListActivity.this, AddNewCovidBeneficiaryActivity.class);
                intent.putExtra("COME","ADDMORE");
                startActivity(intent);
                finish();

            }
        });


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(BeneficiaryListActivity.this)
                        //.setTitle("Really Exit?")
                        .setMessage(getString(R.string.are_you_sure_you_want_to_exit))
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(BeneficiaryListActivity.this,DashboardActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }).create().show();

            }
        });

    }

    private void setAdapter() {
        CovidBeneficiaryListAdapter adapter = new CovidBeneficiaryListAdapter(BeneficiaryListActivity.this, beneficiaryList, this);
        beneficiary_list_recy.setAdapter(adapter);
    }

    private void getBeneficiaryDetails() {
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(BeneficiaryListActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<CovidBeneficiaryListDTO> call = retroFitInterface.getBeneficiaryListDetails("Bearer "+authTok,Constant.USER_AGENT);

        call.enqueue(new Callback<CovidBeneficiaryListDTO>() {
            @Override
            public void onResponse(Call<CovidBeneficiaryListDTO> call, Response<CovidBeneficiaryListDTO> response) {
                progressLog.dismiss();

                CovidBeneficiaryListDTO body = response.body();

                if (response.code() == 200) {

                    if (body.getBeneficiaries().size()==0){


                        beneficiary_list_recy.setVisibility(View.GONE);

                    }else {
                       
                        beneficiary_list_recy.setVisibility(View.VISIBLE);
                        bottomView.setVisibility(View.VISIBLE);
                        beneficiaryList = body.getBeneficiaries();
                        setAdapter();
                    }


                } else if (response.code() == 500) {

                    new SweetAlertDialog(BeneficiaryListActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("covid_status", false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(BeneficiaryListActivity.this,VaccineDashboardActivity.class);
                   startActivity(intent);
                   finish();

                } else {

                    Toast.makeText(BeneficiaryListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CovidBeneficiaryListDTO> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(BeneficiaryListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void onListItem(View view, final int adapterPosition) {

        switch (view.getId()){

            case R.id.schedule_now_TV:

                Intent intent = new Intent(BeneficiaryListActivity.this,BookCovidSlotActivity.class);
                intent.putExtra("BENEFICIARY_ID",beneficiaryList.get(adapterPosition).getBeneficiaryReferenceId());
                intent.putExtra("STATUS","SCHEDULE");
                startActivity(intent);
                break;


            case R.id.dose_certificate_download_Linear:

                beneficiary_reference_id = beneficiaryList.get(adapterPosition).getBeneficiaryReferenceId();
               /* getDownloadManager("https://cdn-api.co-vin.in/api/v2/registration/certificate/download/beneficiary_reference_id="+beneficiary_reference_id);
                downloadCovidCertificate();*/
                downloadCovidCertificate();
               // Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();

                break;

            case R.id.delete_beneficiary_IMG:

                final SweetAlertDialog sad = new SweetAlertDialog(BeneficiaryListActivity.this, SweetAlertDialog.WARNING_TYPE);
                sad.setTitleText(getString(R.string.delete));
                sad.setContentText("Are you sure you want to delete");
                sad.setConfirmText(getString(R.string.exit_yes));
                sad.setCanceledOnTouchOutside(false);
                sad.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();

                        BeneficiaryDeleteDtoRequest deleteDtoRequest = new BeneficiaryDeleteDtoRequest();
                        deleteDtoRequest.setBeneficiary_reference_id(beneficiaryList.get(adapterPosition).getBeneficiaryReferenceId());
                        deleteBeneficiary(deleteDtoRequest);

                    }
                })
                        .setCancelButton(getString(R.string.exit_no), new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismiss();
                            }
                        })
                        .show();

                break;

            case R.id.cancel_TV:
                deleteBeneficiary.clear();
                deleteBeneficiary.add(beneficiaryList.get(adapterPosition).getBeneficiaryReferenceId());

                final SweetAlertDialog sweet = new SweetAlertDialog(BeneficiaryListActivity.this, SweetAlertDialog.WARNING_TYPE);
                sweet.setContentText("Are you sure you want to cancel");
                sweet.setConfirmText(getString(R.string.exit_yes));
                sweet.setCanceledOnTouchOutside(false);
                sweet.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        CovidBeneficiaryCancelDTO covidBeneficiaryCancelDTO= new CovidBeneficiaryCancelDTO();
                        covidBeneficiaryCancelDTO.setAppointmentId(beneficiaryList.get(adapterPosition).getAppointments().get(0).getAppointmentId());
                        covidBeneficiaryCancelDTO.setBeneficiariesToCancel(deleteBeneficiary);
                        cancelBeneficiary(covidBeneficiaryCancelDTO);

                    }
                })
                        .setCancelButton(getString(R.string.exit_no), new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismiss();
                            }
                        })
                        .show();

                break;

            case R.id.reSchedule_now_TV:

                Intent in = new Intent(BeneficiaryListActivity.this,BookCovidSlotActivity.class);
                in.putExtra("APPOINTMENT_ID",beneficiaryList.get(adapterPosition).getAppointments().get(0).getAppointmentId());
                //in.putExtra("SESSION_ID",beneficiaryList.get(adapterPosition).getAppointments().get(0).getSessionId());
               // in.putExtra("SLOT",beneficiaryList.get(adapterPosition).getAppointments().get(0).getSlot());
                in.putExtra("STATUS","RESCHEDULE");
                startActivity(in);
                break;

            case R.id.dose_appointment_download_Linear:

                appointmentId = beneficiaryList.get(adapterPosition).getAppointments().get(0).getAppointmentId();

                // getDownloadManager("https://cdndemo-api.co-vin.in/api/v2/appointment/appointmentslip/download?appointment_id="+);
                downloadAppointmentCertificate();
                // Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();

                break;


        }


    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                //.setTitle("Really Exit?")
                .setMessage(getString(R.string.are_you_sure_you_want_to_exit))
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(BeneficiaryListActivity.this,DashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }


   /* private void getDownloadManager(String address) {
        DownloadManager downloadmanager = (DownloadManager) BeneficiaryListActivity.this.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(address.replace(" ", "%20"));

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.addRequestHeader("Authorization", "Basic " + authTok);
        request.setTitle(URLUtil.guessFileName(address, null, null));
        request.setDescription(getResources().getString(R.string.downloading));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(address, null, null));
        downloadmanager.enqueue(request);
        Toast.makeText(this, getString(R.string.download_done), Toast.LENGTH_SHORT).show();

        //Utility.showToastMessage(MainActivity.this, getResources().getString(R.string.downloading_started_see_notification));

    }*/

    private void downloadAppointmentCertificate() {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(BeneficiaryListActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<ResponseBody> call = retroFitInterface.getAppointmentSlipDownload("Bearer "+authTok,Constant.USER_AGENT,appointmentId);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressLog.dismiss();

                ResponseBody body = response.body();

                if (response.code() == 200) {
                       writeResponseBodyToDisk(response.body(),"Appointment_certificate"+appointmentId);
                    //Toast.makeText(CovidSlotsAvailabilityActivity.this, "200", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {

                    new SweetAlertDialog(BeneficiaryListActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("covid_status", false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(BeneficiaryListActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(BeneficiaryListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(BeneficiaryListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void downloadCovidCertificate() {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(BeneficiaryListActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<ResponseBody> call = retroFitInterface.getVaccinationCertificateDownload("Bearer "+authTok,Constant.USER_AGENT,beneficiary_reference_id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressLog.dismiss();

                ResponseBody body = response.body();

                if (response.code() == 200) {

                    writeResponseBodyToDisk(response.body(),"Vaccination_certificate"+beneficiary_reference_id);
                    //Toast.makeText(CovidSlotsAvailabilityActivity.this, "200", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(BeneficiaryListActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("covid_status", false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(BeneficiaryListActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(BeneficiaryListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(BeneficiaryListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void deleteBeneficiary(BeneficiaryDeleteDtoRequest beneficiaryDeleteDtoRequest) {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(BeneficiaryListActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<ResponseBody> call = retroFitInterface.deleteBeneficiary("Bearer "+authTok,Constant.USER_AGENT,beneficiaryDeleteDtoRequest);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressLog.dismiss();

                ResponseBody body = response.body();

                if (response.code() == 204) {

                    Toast.makeText(BeneficiaryListActivity.this, "The beneficiary was deleted successfully.", Toast.LENGTH_SHORT).show();
                    getBeneficiaryDetails();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(BeneficiaryListActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("covid_status", false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(BeneficiaryListActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else if (response.code() == 409){

                Toast.makeText(BeneficiaryListActivity.this,
                                "Individual cannot be deleted if he/she has an active appointment or is already vaccinated.", Toast.LENGTH_SHORT).show();

                }  else {

                    Toast.makeText(BeneficiaryListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(BeneficiaryListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }



    private void cancelBeneficiary(CovidBeneficiaryCancelDTO covidBeneficiaryCancelDTO) {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(BeneficiaryListActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<ResponseBody> call = retroFitInterface.cancelBeneficiary("Bearer "+authTok,Constant.USER_AGENT,covidBeneficiaryCancelDTO);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressLog.dismiss();

                ResponseBody body = response.body();

                if (response.code() == 204) {

                    Toast.makeText(BeneficiaryListActivity.this, "Appointment cancelled successfully.", Toast.LENGTH_SHORT).show();
                    getBeneficiaryDetails();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(BeneficiaryListActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("covid_status", false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(BeneficiaryListActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();

                }else if (response.code() == 409){

                    if(!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                            } catch (JSONException e) {
                                Toast.makeText(BeneficiaryListActivity.this,
                                        "This appointment cannot be cancelled.", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(BeneficiaryListActivity.this,
                                        "This appointment cannot be cancelled.", Toast.LENGTH_SHORT).show();
                            }
                            String errorCode = jsonObject.getString("error");
                            Toast.makeText(BeneficiaryListActivity.this, errorCode, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(BeneficiaryListActivity.this,
                                    "This appointment cannot be cancelled.", Toast.LENGTH_SHORT).show();                        }
                    }

                } else {

                    Toast.makeText(BeneficiaryListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(BeneficiaryListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
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

            File vaccine = new File(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator +certificateName+".pdf");


            Log.e("vaccine+++++",vaccine.toString());

           // String filePath= getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator +certificateName+".pdf";

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(vaccine);

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


                   /* String format = filePath;
                    String fullPath = String.format(Locale.ENGLISH, format, "PDF_URL_HERE");
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fullPath));
                    startActivity(browserIntent);*/

                  //  Toast.makeText(this, "Download Completed", Toast.LENGTH_SHORT).show();


                    //createNotification("NICT Bills","Test",vaccine);
                }

               /* try
                {
                    Intent myIntent = new Intent(android.content.Intent.ACTION_VIEW);
                    File file = new File(vaccine.getAbsolutePath());
                    String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
                    String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                    myIntent.setDataAndType(Uri.fromFile(file),mimetype);
                    startActivity(myIntent);
                }
                catch (Exception e)
                {
                    // TODO: handle exception
                    String data = e.getMessage();
                    Log.e("dataaaaa",data);
                }*/


                Uri photoURI = FileProvider.getUriForFile(BeneficiaryListActivity.this, this.getApplicationContext().getPackageName() + ".provider", vaccine);

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

   /* public void createNotification(String title, String message, File vaccine)
    {

        Uri apkURI = FileProvider.getUriForFile(
                this,
                getApplicationContext()
                        .getPackageName() + ".provider", vaccine);


        Log.d("Test", "File to download = " + String.valueOf(vaccine));
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        String ext=vaccine.getName().substring(vaccine.getName().indexOf(".")+1);
        String type = mime.getMimeTypeFromExtension(ext);
        Intent openFile = new Intent(Intent.ACTION_VIEW, apkURI);
        openFile.setDataAndType(apkURI, type);
        openFile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent p = PendingIntent.getActivity(this, 0, openFile, 0);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(false)
                .setContentIntent(p)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        mBuilder.setProgress(100, 0, true);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(0 *//* Request Code *//*, mBuilder.build());
    }*/
}