package com.looptrace.gadsleaderboard.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.looptrace.gadsleaderboard.R;

public class SubmitActivity extends AppCompatActivity {

    private Button mConfirmYes;
    private AlertDialog mAlertDialog;
    private View mDialogView, mFormContainer;
    private ImageView mCancelBtn, mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        mBackBtn = findViewById(R.id.btn_back);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFormContainer = findViewById(R.id.form_container);
        Button submitBtn = findViewById(R.id.btn_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmationDialog(v);
            }
        });
    }

    void ConfirmationDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        mDialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.confirmation_dialog, viewGroup, false);
        mConfirmYes = mDialogView.findViewById(R.id.yes_btn);
        mConfirmYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
                SuccessDialog(v);
            }
        });
        mCancelBtn = mDialogView.findViewById(R.id.cancel_btn);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
                FailedDialog(v);
            }
        });

        builder.setView(mDialogView);
        mAlertDialog = builder.create();
        mAlertDialog.setCanceledOnTouchOutside(false);
        mAlertDialog.show();
    }

    void SuccessDialog(View v) {
        mFormContainer.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        mDialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.success_dialog, viewGroup, false);
        builder.setView(mDialogView);
        mAlertDialog = builder.create();
        mAlertDialog.show();
        mAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mFormContainer.setVisibility(View.VISIBLE);
            }
        });
    }

    void FailedDialog(View v) {
        mFormContainer.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        mDialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.failed_dialog, viewGroup, false);
        builder.setView(mDialogView);
        mAlertDialog = builder.create();
        mAlertDialog.show();
        mAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mFormContainer.setVisibility(View.VISIBLE);
            }
        });
    }
}
