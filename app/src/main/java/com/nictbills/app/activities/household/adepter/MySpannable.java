package com.nictbills.app.activities.household.adepter;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

public class MySpannable extends ClickableSpan {
    private boolean isUnderline=true;

    public MySpannable(boolean isUnderline){
        this.isUnderline=isUnderline;
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        ds.setUnderlineText(isUnderline);
        ds.setColor(Color.parseColor("#1b76d3"));
    }

    @Override
    public void onClick(@NonNull View view) {

    }
}
