package com.nictbills.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.PermissionChecker;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nictbills.app.R;
import com.nictbills.app.utils.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class TransactionDescriptionsActivity extends AppCompatActivity {

    private TextView ivrs_TV,location_TV,service_TV,amount_TV,TransactionId_TV,orderId_TV,date_time_TV,convenience_fee_TV,
            rej_ivrs_TV,rej_service_TV,rej_amount_TV,failed_orderId_TV,failed_info_TV,rej_date_TV,pen_ivrs_TV,pen_location_TV,pen_service_TV,pen_amount_TV,pen_orderId_TV,pen_method_TV,pen_date_time_TV;

    private  String fee,amount, pay_id, order_id, currency, status, method, notes_service, notes_service_id, error_description, receipt,notes_service_loc,created_at;

    private LinearLayout pending_transaction_LinearLayout,failure_Linear_Layout,success_Linear_Layout,download_invoice_LinearLayout,pay_another_bill_LinearLayout,help_LinearLayout;
    private ImageView backArrow_IMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_descriptions);

        ivrs_TV = findViewById(R.id.ivrs_TV);
        location_TV = findViewById(R.id.location_TV);
        service_TV = findViewById(R.id.service_TV);
        amount_TV = findViewById(R.id.amount_TV);
        TransactionId_TV = findViewById(R.id.TransactionId_TV);
        orderId_TV = findViewById(R.id.orderId_TV);
        date_time_TV = findViewById(R.id.date_time_TV);
        rej_ivrs_TV = findViewById(R.id.rej_ivrs_TV);
        rej_service_TV = findViewById(R.id.rej_service_TV);
        rej_amount_TV = findViewById(R.id.rej_amount_TV);
        failed_orderId_TV = findViewById(R.id.failed_orderId_TV);
        failed_info_TV = findViewById(R.id.failed_info_TV);
        rej_date_TV = findViewById(R.id.rej_date_TV);
        failure_Linear_Layout = findViewById(R.id.failure_Linear_Layout);
        success_Linear_Layout = findViewById(R.id.success_Linear_Layout);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        convenience_fee_TV = findViewById(R.id.convenience_fee_TV);
        download_invoice_LinearLayout = findViewById(R.id.download_invoice_LinearLayout);
        pay_another_bill_LinearLayout = findViewById(R.id.pay_another_bill_LinearLayout);
        help_LinearLayout = findViewById(R.id.help_LinearLayout);
        pending_transaction_LinearLayout = findViewById(R.id.pending_transaction_LinearLayout);
        pen_ivrs_TV = findViewById(R.id.pen_ivrs_TV);
        pen_location_TV = findViewById(R.id.pen_location_TV);
        pen_service_TV = findViewById(R.id.pen_service_TV);
        pen_amount_TV = findViewById(R.id.pen_amount_TV);
        pen_orderId_TV = findViewById(R.id.pen_orderId_TV);
        pen_method_TV = findViewById(R.id.pen_method_TV);
        pen_date_time_TV = findViewById(R.id.pen_date_time_TV);


        final Intent intent = getIntent();
        status = intent.getStringExtra("status");



        if (status.equalsIgnoreCase("captured")||status.equalsIgnoreCase("success")){

            success_Linear_Layout.setVisibility(View.VISIBLE);
            failure_Linear_Layout.setVisibility(View.GONE);
            pending_transaction_LinearLayout.setVisibility(View.GONE);

            amount = intent.getStringExtra("amount");
            pay_id = intent.getStringExtra("pay_id");
            order_id = intent.getStringExtra("order_id");
            currency = intent.getStringExtra("currency");
            method = intent.getStringExtra("method");
            notes_service = intent.getStringExtra("notes_service");
            notes_service_id = intent.getStringExtra("notes_service_id");
           // error_description = intent.getStringExtra("error_description");
            receipt = intent.getStringExtra("receipt");
            notes_service_loc = intent.getStringExtra("notes_service_loc");
            created_at = intent.getStringExtra("created_at");
            fee = intent.getStringExtra("fee");

            ivrs_TV.setText(notes_service_id);
            location_TV.setText(notes_service_loc);
            service_TV.setText(notes_service);
            amount_TV.setText("₹ "+ amount);
            TransactionId_TV.setText(receipt);
            orderId_TV.setText(order_id);
            convenience_fee_TV.setText("₹ "+fee);
            Date tr_date = new Date(Long.parseLong((created_at+"")) * 1000);
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            String successDateTransaction = format.format(tr_date);
            date_time_TV.setText(successDateTransaction);




        }else if (status.toLowerCase().startsWith("fail")){


            success_Linear_Layout.setVisibility(View.GONE);
            failure_Linear_Layout.setVisibility(View.VISIBLE);
            pending_transaction_LinearLayout.setVisibility(View.GONE);

        amount = intent.getStringExtra("amount");
        pay_id = intent.getStringExtra("pay_id");
        order_id = intent.getStringExtra("order_id");
        currency = intent.getStringExtra("currency");
        method = intent.getStringExtra("method");
        notes_service = intent.getStringExtra("notes_service");
        notes_service_id = intent.getStringExtra("notes_service_id");
        error_description = intent.getStringExtra("error_description");
        receipt = intent.getStringExtra("receipt");
       // notes_service_loc = intent.getStringExtra("notes_service_loc");
        created_at = intent.getStringExtra("created_at");


            rej_ivrs_TV.setText(notes_service_id);
           /* rej_location_TV.setText(notes_service_loc);*/
            rej_service_TV.setText(notes_service);
            rej_amount_TV.setText("₹ "+amount);
            failed_orderId_TV.setText(order_id);
            failed_info_TV.setText(error_description);

            Date tr_date = new Date(Long.parseLong((created_at+"")) * 1000);
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            String rejectDateTransaction = format.format(tr_date);
            rej_date_TV.setText(rejectDateTransaction);


        }else if (status.toLowerCase().startsWith("pending")){


            success_Linear_Layout.setVisibility(View.GONE);
            failure_Linear_Layout.setVisibility(View.GONE);
            pending_transaction_LinearLayout.setVisibility(View.VISIBLE);

            amount = intent.getStringExtra("amount");
            pay_id = intent.getStringExtra("pay_id");
            order_id = intent.getStringExtra("order_id");
            currency = intent.getStringExtra("currency");
            method = intent.getStringExtra("method");
            notes_service = intent.getStringExtra("notes_service");
            notes_service_id = intent.getStringExtra("notes_service_id");
            //error_description = intent.getStringExtra("error_description");
            receipt = intent.getStringExtra("receipt");
            notes_service_loc = intent.getStringExtra("notes_service_loc");
            created_at = intent.getStringExtra("created_at");


            pen_ivrs_TV.setText(notes_service_id);
            pen_location_TV.setText(notes_service_loc);
            /* rej_location_TV.setText(notes_service_loc);*/
            pen_service_TV.setText(notes_service);
            pen_amount_TV.setText("₹ "+amount);
            pen_orderId_TV.setText(pay_id);
            pen_method_TV.setText(method);

            Date tr_date = new Date(Long.parseLong((created_at+"")) * 1000);
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            String rejectDateTransaction = format.format(tr_date);
            pen_date_time_TV.setText(rejectDateTransaction);


        }



        download_invoice_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (ActivityCompat.checkSelfPermission(TransactionDescriptionsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(TransactionDescriptionsActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

                    }else if (ActivityCompat.checkSelfPermission(TransactionDescriptionsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(TransactionDescriptionsActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                    }
                    else {
                        getDownloadManager("https://nictbills.com/b2c_liveapi/downloadReceiptRzp/"+pay_id+".pdf");
                    }

                } else {
                    getDownloadManager("https://nictbills.com/b2c_liveapi/downloadReceiptRzp/"+pay_id+".pdf");
                }

            }
        });


        pay_another_bill_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(TransactionDescriptionsActivity.this,AddIVRSActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                finish();

            }
        });

        help_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(TransactionDescriptionsActivity.this,SupportActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                finish();
            }
        });


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TransactionDescriptionsActivity.this, TransactionHistoryActivity.class);
                startActivity(intent);

            }
        });

    }

    private void getDownloadManager(String address) {
        DownloadManager downloadmanager = (DownloadManager) TransactionDescriptionsActivity.this.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(address.replace(" ", "%20"));

       // File folderName = new File(String.valueOf(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));

     /*  if (!folderName.exists()){
            folderName.mkdir();
        }

        Log.e("URIII+",uri+"");*/

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(URLUtil.guessFileName(address, null, null));
        request.setDescription(getResources().getString(R.string.downloading));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(address, null, null));
        downloadmanager.enqueue(request);
        Toast.makeText(this, getString(R.string.download_done), Toast.LENGTH_SHORT).show();


        /*File newFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "sample.pdf");
        Log.e("vinod", newFile.toString());
        Utils.openFile(newFile, TransactionDescriptionsActivity.this);
*/
       /* Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(browserIntent);*/

       /* File receipt = new File(address);

        Uri photoURI = FileProvider.getUriForFile(TransactionDescriptionsActivity.this, this.getApplicationContext().getPackageName() + ".provider", receipt);

        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
        // pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        pdfOpenintent.setDataAndType(photoURI, "application/pdf");
        try {
            startActivity(pdfOpenintent);
        }
        catch (ActivityNotFoundException e) {

        }
*/
        //Utility.showToastMessage(MainActivity.this, getResources().getString(R.string.downloading_started_see_notification));

    }


    @Override
    public void onBackPressed() {
            Intent intent = new Intent(TransactionDescriptionsActivity.this, TransactionHistoryActivity.class);
            startActivity(intent);
        }

}
