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
import android.widget.TextView;
import android.widget.Toast;

import com.looptrace.gadsleaderboard.R;
import com.looptrace.gadsleaderboard.dataSource.GadsApi;
import com.looptrace.gadsleaderboard.dataSource.RetrofitBase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {

    private Button mConfirmYes;
    private AlertDialog mAlertDialog;
    private View mDialogView, mFormContainer;
    private ImageView mCancelBtn, mBackBtn;
    private TextView mFirstName, mLastName, mEmailAddress, mProjectLink;

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
                ValidateFields(v);
            }
        });

        mFirstName = findViewById(R.id.first_name);
        mLastName = findViewById(R.id.last_name);
        mEmailAddress = findViewById(R.id.email_address);
        mProjectLink = findViewById(R.id.project_link);
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
                SubmitProject(v);
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

    void ValidateFields(View v) {
        if (mFirstName.getText().toString().equals("") || mLastName.getText().toString().equals("") || mEmailAddress.getText().toString().equals("")
                || mProjectLink.getText().toString().equals("")) {
            Toast.makeText(this, "Missing field, all fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }
        ConfirmationDialog(v);
    }

    void SubmitProject(final View v) {
        String firstName = mFirstName.getText().toString();
        String lastName = mLastName.getText().toString();
        String emailAddress = mEmailAddress.getText().toString();
        String projectLink = mProjectLink.getText().toString();

        GadsApi api = RetrofitBase.getInstance().buildRetrofit(RetrofitBase.POST_BASE_URL).create(GadsApi.class);
        api.submitForm(emailAddress, firstName, lastName, projectLink).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SubmitActivity.this, "A " + response.code() + " occurred", Toast.LENGTH_SHORT).show();
                    FailedDialog(v);
                    return;
                }
                SuccessDialog(v);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SubmitActivity.this, t.getMessage() + ", try again later.", Toast.LENGTH_SHORT).show();
                FailedDialog(v);
            }
        });

    }
}
