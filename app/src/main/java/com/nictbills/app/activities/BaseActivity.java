package com.nictbills.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.nictbills.app.R;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void progressDialogShow(){

        if (progressBar==null) {
            progressBar = new ProgressDialog(BaseActivity.this);
            progressBar.setTitle(getString(R.string.please_wait));
            progressBar.setMessage(getString(R.string.one_moment_please));
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setCancelable(false);
            progressBar.show();

        } else if (!progressBar.isShowing()){

                progressBar = new ProgressDialog(BaseActivity.this);
                progressBar.setTitle(getString(R.string.please_wait));
                progressBar.setMessage(getString(R.string.one_moment_please));
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressBar.setCancelable(false);
                progressBar.show();

        }

    }


    public void progressDialogDismiss(){
        if (progressBar!=null && progressBar.isShowing()){

            progressBar.dismiss();

        }


    }


    public void progressCallBackDialogShow(){

        if (progressBar==null) {
            progressBar = new ProgressDialog(BaseActivity.this);
            progressBar.setTitle(getString(R.string.please_wait));
            progressBar.setMessage(getString(R.string.do_not_press_back_button));
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setCancelable(false);
            progressBar.show();

        } else if (!progressBar.isShowing()){

            progressBar = new ProgressDialog(BaseActivity.this);
            progressBar.setTitle(getString(R.string.please_wait));
            progressBar.setMessage(getString(R.string.do_not_press_back_button));
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setCancelable(false);
            progressBar.show();

        }

    }

    public void progressCallBackDialogDismiss(){

        if (progressBar!=null && progressBar.isShowing()){

            progressBar.dismiss();

        }
    }


}
