package com.example.project_bjackpharm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_bjackpharm.MainActivity;
import com.example.project_bjackpharm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class Activity_OTP_Verification extends AppCompatActivity {


    private EditText Code1,Code2,Code3,Code4,Code5,Code6;
    private String verificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        Code1 = findViewById(R.id.Code1);
        Code2 = findViewById(R.id.Code2);
        Code3 = findViewById(R.id.Code3);
        Code4 = findViewById(R.id.Code4);
        Code5 = findViewById(R.id.Code5);
        Code6 = findViewById(R.id.Code6);

        setupOTPinput();
        final Button VerifyButton = findViewById(R.id.Verify);
        verificationID = getIntent().getStringExtra("verificationID");

        VerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Code1.getText().toString().trim().isEmpty()
                        ||Code2.getText().toString().trim().isEmpty()
                        ||Code3.getText().toString().trim().isEmpty()
                        ||Code4.getText().toString().trim().isEmpty()
                        ||Code5.getText().toString().trim().isEmpty()
                        ||Code6.getText().toString().trim().isEmpty()){
                    Toast.makeText(Activity_OTP_Verification.this, "Please enter Valid code", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code =
                        Code1.getText().toString() +
                                Code2.getText().toString() +
                                Code3.getText().toString() +
                                Code4.getText().toString() +
                                Code5.getText().toString() +
                                Code6.getText().toString();

                if(verificationID != null){
                    VerifyButton.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationID,code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    VerifyButton.setVisibility(View.VISIBLE);
                                    if(task.isSuccessful()){
                                        String userIdPosition = getIntent().getStringExtra("userIdposition");
                                        int Id_User_now = userIdPosition != null ? Integer.parseInt(userIdPosition) : 0;

                                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class); //debug

                                        intent.putExtra("userIdposition", Id_User_now);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }else {
                                        Toast.makeText(Activity_OTP_Verification.this, "The Verification code entered was wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
    private void setupOTPinput() {
        Code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    Code2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    Code3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    Code4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    Code5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Code5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    Code6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
