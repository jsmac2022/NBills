package com.nictbills.app.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nictbills.app.R;

public class MyBottomSheetDialog extends BottomSheetDialogFragment {

    private CallBack callBack;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);

        TextView cancel_Payment_TV = v.findViewById(R.id.cancel_Payment_TV);
        //Button course_button = v.findViewById(R.id.course_button);

        cancel_Payment_TV.setPaintFlags(cancel_Payment_TV.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        cancel_Payment_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), getString(R.string.payment_cancelled), Toast.LENGTH_SHORT).show();
                callBack.cancelBottomView();
                dismiss();
            }
        });

        return v;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack=callBack;
    }

    public interface CallBack{

        void cancelBottomView ();

    }


    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        callBack.cancelBottomView();
        super.onCancel(dialog);
    }

}
