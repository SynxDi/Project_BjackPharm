package com.example.project_bjackpharm.activities;

import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_bjackpharm.R;


import com.example.project_bjackpharm.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Activity_OTP_Send extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_send);

        final EditText inputMobile = findViewById(R.id.InputPhoneNumber);
        Button GetOTP = findViewById(R.id.GetOTP);

        String userIdPosition = getIntent().getStringExtra("userIdposition");
        int Id_User_now = userIdPosition != null ? Integer.parseInt(userIdPosition) : 0;

        GetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputMobile.getText().toString().trim().isEmpty()){
                    Toast.makeText(Activity_OTP_Send.this, "Enter Mobile", Toast.LENGTH_SHORT).show();
                    return;
                }

                PhoneAuthOptions options = PhoneAuthOptions.newBuilder()
                        .setPhoneNumber("+62"+inputMobile.getText().toString())
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(Activity_OTP_Send.this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                GetOTP.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                GetOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(Activity_OTP_Send.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                GetOTP.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(getApplicationContext(),Activity_OTP_Verification.class);
                                intent.putExtra("userIdposition", Id_User_now);
                                intent.putExtra("Mobile", inputMobile.getText().toString());
                                intent.putExtra("verificationID",verificationID);
                                startActivity(intent);
                            }
                        })
                        .build();

                PhoneAuthProvider.verifyPhoneNumber(options);

//                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                        "+62"+inputMobile.getText().toString(),
//                        60,
//                        TimeUnit.SECONDS,
//                        Activity_OTP_Send.this,
//                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
//
//                            @Override
//                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                                GetOTP.setVisibility(View.VISIBLE);
//                            }
//
//                            @Override
//                            public void onVerificationFailed(@NonNull FirebaseException e) {
//                                GetOTP.setVisibility(View.VISIBLE);
//                                Toast.makeText(Activity_OTP_Send.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                GetOTP.setVisibility(View.VISIBLE);
//                                Intent intent = new Intent(getApplicationContext(),Activity_OTP_Verification.class);
//                                intent.putExtra("userIdposition", Id_User_now);
//                                intent.putExtra("Mobile", inputMobile.getText().toString());
//                                intent.putExtra("verificationID",verificationID);
//                                startActivity(intent);
//                            }
//                        }
//                );

            }
        });
    }
}