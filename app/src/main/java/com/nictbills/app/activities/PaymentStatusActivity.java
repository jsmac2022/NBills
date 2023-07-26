package com.nictbills.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nictbills.app.R;
import com.nictbills.app.activities.lic.LicBillsActivity;
import com.nictbills.app.activities.health_id_abdm.dto.update_order.PaymentEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentStatusActivity extends AppCompatActivity {

    private String Status, transactionNo,transaction_date,paymentReceipt,service,service_id, paymentMethod,PayUSING,TXNAmount;
    private int amount;
    private LinearLayout success_LinearLayout,failed_LinearLayout,pending_transaction_LinearLayout;
    private ImageView backArrow_IMG;
    private TextView ivrs_failed_msg_TV,transaction_number_TV,amount_TV,fail_transaction_number_TV,fail_amount_TV,trans_date_TV,ivrs_success_msg_TV;
    private Button try_again_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_status);

        success_LinearLayout = findViewById(R.id.success_LinearLayout);
        failed_LinearLayout = findViewById(R.id.failed_LinearLayout);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        transaction_number_TV = findViewById(R.id.transaction_number_TV);
        amount_TV = findViewById(R.id.amount_TV);
        fail_transaction_number_TV = findViewById(R.id.fail_transaction_number_TV);
        fail_amount_TV = findViewById(R.id.fail_amount_TV);
        try_again_Button = findViewById(R.id.try_again_Button);
        trans_date_TV = findViewById(R.id.trans_date_TV);
        ivrs_success_msg_TV = findViewById(R.id.ivrs_success_msg_TV);
        ivrs_failed_msg_TV = findViewById(R.id.ivrs_failed_msg_TV);
        pending_transaction_LinearLayout = findViewById(R.id.pending_transaction_LinearLayout);


        Intent intent = getIntent();


        paymentReceipt = intent.getStringExtra("paymentReceipt");
        service = intent.getStringExtra("service");
        service_id = intent.getStringExtra("service_id");
        PayUSING = intent.getStringExtra("PayUSING");


        if (PayUSING.equalsIgnoreCase("Razorpay")){


        PaymentEntity entity = (PaymentEntity) intent.getSerializableExtra("Details");

        paymentMethod = entity.getMethod();
        transactionNo = entity.getId();

        Status = entity.getStatus();
        amount = entity.getAmount();

        double total = Double.parseDouble(String.valueOf(amount));
        total = total / 100;



        if (Status.equals("captured")){

            transaction_date = intent.getStringExtra("Date");

            Date tr_date = new Date(Long.parseLong(transaction_date) * 1000);

            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

            String dtStartOK = format.format(tr_date);
            //String stringDate = DateFormat.getDateTimeInstance().format(dtStartOK);

            trans_date_TV.setText(dtStartOK);
            //   transaction_Date_amount_TV.setText(DateFormat.getTimeInstance().format(dtStartOK));


            success_LinearLayout.setVisibility(View.VISIBLE);
            failed_LinearLayout.setVisibility(View.GONE);
            pending_transaction_LinearLayout.setVisibility(View.GONE);
            transaction_number_TV.setText(transactionNo);
            amount_TV.setText("₹ "+total);

            if (service.equalsIgnoreCase("MOBILE")){

                ivrs_success_msg_TV.setText(getString(R.string.bill_payment_successful_mobile_number)+" "+ service_id);

            }else if (service.equalsIgnoreCase("DTH")){

                ivrs_success_msg_TV.setText(getString(R.string.bill_payment_successful_dth_number)+" "+ service_id);

            } else if (service.equalsIgnoreCase("LIC")) {

                ivrs_success_msg_TV.setText(getString(R.string.bill_payment_successful_lic_number)+" "+ service_id);

            } else {

                ivrs_success_msg_TV.setText(getString(R.string.bill_payment_successful_of_IVRS_number) + " " + service_id);
            }


        }else if (Status.equals("failed")){

            success_LinearLayout.setVisibility(View.GONE);
            pending_transaction_LinearLayout.setVisibility(View.GONE);
            failed_LinearLayout.setVisibility(View.VISIBLE);
            fail_transaction_number_TV.setText(transactionNo);
            fail_amount_TV.setText("₹ "+total);

            if (service.equalsIgnoreCase("MOBILE")){

                ivrs_failed_msg_TV.setText(getString(R.string.bill_payment_failed_of_mobile_number)+" "+ service_id);

            }else if (service.equalsIgnoreCase("DTH")){

                ivrs_failed_msg_TV.setText(getString(R.string.bill_payment_failed_of_dth_number)+" "+ service_id);

            }else if (service.equalsIgnoreCase("LIC")){

                ivrs_failed_msg_TV.setText(getString(R.string.bill_payment_failed_of_lic_number)+" "+ service_id);

            }else {

                ivrs_failed_msg_TV.setText(getString(R.string.bill_payment_failed_of_IVRS_number)+" "+ service_id);
            }


        }


        } else if (PayUSING.equalsIgnoreCase("UPI_VPA")){

            TXNAmount = intent.getStringExtra("amount");
            Status = intent.getStringExtra("STATUS");

            if (Status.equalsIgnoreCase("SUCCESS")){

                transaction_date = intent.getStringExtra("txnDate");

                trans_date_TV.setText(transaction_date);

                success_LinearLayout.setVisibility(View.VISIBLE);
                failed_LinearLayout.setVisibility(View.GONE);
                pending_transaction_LinearLayout.setVisibility(View.GONE);
                transaction_number_TV.setText(paymentReceipt);
                amount_TV.setText("₹ "+ TXNAmount);

                if (service.equalsIgnoreCase("MOBILE")){

                    ivrs_success_msg_TV.setText(getString(R.string.bill_payment_successful_mobile_number)+" "+ service_id);

                }else if (service.equalsIgnoreCase("DTH")){

                    ivrs_success_msg_TV.setText(getString(R.string.bill_payment_successful_dth_number)+" "+ service_id);

                }else if (service.equalsIgnoreCase("LIC")) {

                    ivrs_success_msg_TV.setText(getString(R.string.bill_payment_successful_lic_number)+" "+ service_id);

                }else {

                    ivrs_success_msg_TV.setText(getString(R.string.bill_payment_successful_of_IVRS_number)+" "+ service_id);
                }

            }else if (Status.equalsIgnoreCase("FAILED")){

                success_LinearLayout.setVisibility(View.GONE);
                pending_transaction_LinearLayout.setVisibility(View.GONE);
                failed_LinearLayout.setVisibility(View.VISIBLE);
                fail_transaction_number_TV.setText(paymentReceipt);
                fail_amount_TV.setText("₹ "+ TXNAmount);

                if (service.equalsIgnoreCase("MOBILE")){

                    ivrs_failed_msg_TV.setText(getString(R.string.bill_payment_failed_of_mobile_number)+" "+ service_id);

                }else if (service.equalsIgnoreCase("DTH")){

                    ivrs_failed_msg_TV.setText(getString(R.string.bill_payment_failed_of_dth_number)+" "+ service_id);

                }else if (service.equalsIgnoreCase("LIC")){

                    ivrs_failed_msg_TV.setText(getString(R.string.bill_payment_failed_of_lic_number)+" "+ service_id);

                }else {

                    ivrs_failed_msg_TV.setText(getString(R.string.bill_payment_failed_of_IVRS_number)+" "+ service_id);
                }



            } else if (Status.equalsIgnoreCase("PENDING")){

                pending_transaction_LinearLayout.setVisibility(View.VISIBLE);
                success_LinearLayout.setVisibility(View.GONE);
                failed_LinearLayout.setVisibility(View.GONE);

            }

        }



        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (failed_LinearLayout.getVisibility() == View.VISIBLE){

                    if (service.equalsIgnoreCase("MOBILE")){

                        Intent intent = new Intent(PaymentStatusActivity.this,MobileRechargeDasboardActivity.class);
                        startActivity(intent);
                        finish();

                    }else if (service.equalsIgnoreCase("DTH")){

                        Intent intent = new Intent(PaymentStatusActivity.this,DTHRechargeActivity.class);
                        startActivity(intent);
                        finish();

                    }else if (service.equalsIgnoreCase("LIC")){

                        Intent intent = new Intent(PaymentStatusActivity.this, LicBillsActivity.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Intent intent = new Intent(PaymentStatusActivity.this,AddIVRSActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }else {

                    Intent intent = new Intent(PaymentStatusActivity.this,DashboardActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });



        try_again_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (failed_LinearLayout.getVisibility() == View.VISIBLE){

                    if (service.equalsIgnoreCase("MOBILE")){

                        Intent intent = new Intent(PaymentStatusActivity.this,MobileRechargeDasboardActivity.class);
                        startActivity(intent);
                        finish();

                    }else if (service.equalsIgnoreCase("DTH")){

                        Intent intent = new Intent(PaymentStatusActivity.this,DTHRechargeActivity.class);
                        startActivity(intent);
                        finish();

                    }else if (service.equalsIgnoreCase("LIC")){

                        Intent intent = new Intent(PaymentStatusActivity.this, LicBillsActivity.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Intent intent = new Intent(PaymentStatusActivity.this,AddIVRSActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }else {

                    Intent intent = new Intent(PaymentStatusActivity.this,DashboardActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });
    }

    @Override
    public void onBackPressed() {

        if (failed_LinearLayout.getVisibility() == View.VISIBLE){

            if (service.equalsIgnoreCase("MOBILE")){

                Intent intent = new Intent(PaymentStatusActivity.this,MobileRechargeDasboardActivity.class);
                startActivity(intent);
                finish();

            }else if (service.equalsIgnoreCase("DTH")){

                Intent intent = new Intent(PaymentStatusActivity.this,DTHRechargeActivity.class);
                startActivity(intent);
                finish();

            }else if (service.equalsIgnoreCase("LIC")){

                Intent intent = new Intent(PaymentStatusActivity.this, LicBillsActivity.class);
                startActivity(intent);
                finish();

            }else {
                Intent intent = new Intent(PaymentStatusActivity.this,AddIVRSActivity.class);
                startActivity(intent);
                finish();
            }
        }else {

            Intent intent = new Intent(PaymentStatusActivity.this,DashboardActivity.class);
            startActivity(intent);
            finish();

        }
    }


}
