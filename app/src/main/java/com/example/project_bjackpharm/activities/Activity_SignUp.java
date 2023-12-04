package com.example.project_bjackpharm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_bjackpharm.R;
import com.example.project_bjackpharm.database.DatabaseHelper;

public class Activity_SignUp extends AppCompatActivity {
    EditText nameSignUp, emailSignUp, passSignUp, conPassSignUp, phoneSignUp;
    Button regisBtn, gotoLoginBtn;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //UI Component dari page Registernya
        nameSignUp = findViewById(R.id.nameSignUpTxt);
        emailSignUp = findViewById(R.id.EmailSignUpTxt);
        passSignUp = findViewById(R.id.passwordSignUpTxt);
        conPassSignUp = findViewById(R.id.conpasswordSignUpTxt);
        phoneSignUp = findViewById(R.id.phoneNumberSignUpTxt);
        regisBtn = findViewById(R.id.registerFrstBtn);
        gotoLoginBtn = findViewById(R.id.gotoLoginBtn);

        databaseHelper = new DatabaseHelper(this);

        //register Button Clicked
        regisBtn.setOnClickListener(e->{
            String namaUser = nameSignUp.getText().toString();
            String emailUser = emailSignUp.getText().toString();
            String passUser = passSignUp.getText().toString();
            String confPassUser = conPassSignUp.getText().toString();
            String phoneUser = phoneSignUp.getText().toString();

            //Semua validasi disini
            //Kalo ada component yang ga diisi
//            if(namaUser.isEmpty() || emailUser.isEmpty() || passUser.isEmpty() || confPassUser.isEmpty()||phoneUser.isEmpty()){
//                Toast.makeText(Activity_SignUp.this, "All fields must be filled", Toast.LENGTH_SHORT ).show();
//            } else{
//
//                // panjang nama harus setidaknya lebih dari sama dengan 5 character
//                if(namaUser.length() < 5){
//                    Toast.makeText(Activity_SignUp.this, "Name at least 5 characters long", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                //email diakhiri dengan '.com'
//                if(!emailUser.endsWith(".com") ){
//                    Toast.makeText(Activity_SignUp.this, "Email must be ended with '.com'", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                //alphanumeric password
//                if(!passUser.matches("[a-zA-Z0-9]+$")){
//                    Toast.makeText(Activity_SignUp.this, "Password must be alphanumeric", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // jika passwordnya tidak sama
//                if(!passUser.equals(confPassUser)){
//                    Toast.makeText(Activity_SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//
//                if(passUser.equals(confPassUser)){
//                    Boolean checkEmail = databaseHelper.checkEmail(emailUser);
//
//                    if(checkEmail == false){
//                        Boolean insertNewData = databaseHelper.insertUsers(emailUser,passUser, namaUser, phoneUser, "true");
//                        if(insertNewData == true){
//                            Toast.makeText(Activity_SignUp.this, "Successfully added", Toast.LENGTH_SHORT).show();
//                            Intent i = new Intent(getApplicationContext(), Activity_SignIn.class);
//                            startActivity(i);
//
//                        } else{
//                            Toast.makeText(Activity_SignUp.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }else{
//                        Toast.makeText(Activity_SignUp.this, "User Already Exist", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(Activity_SignUp.this, "Invalid Passsword", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//
//
//            Intent i = new Intent(Activity_SignUp.this, HomeActivity.class);
//            startActivity(i);
//            Toast.makeText(Activity_SignUp.this, "Register succeed", Toast.LENGTH_SHORT).show();


            if (namaUser.isEmpty() || emailUser.isEmpty() || passUser.isEmpty() || confPassUser.isEmpty() || phoneUser.isEmpty()) {
                Toast.makeText(Activity_SignUp.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate name length
            if (namaUser.length() < 5) {
                Toast.makeText(Activity_SignUp.this, "Name must be at least 5 characters long", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate email format
            if (!emailUser.endsWith(".com")) {
                Toast.makeText(Activity_SignUp.this, "Email must end with '.com'", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate alphanumeric password
            if (!passUser.matches("[a-zA-Z0-9]+")) {
                Toast.makeText(Activity_SignUp.this, "Password must be alphanumeric", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate password match
            if (!passUser.equals(confPassUser)) {
                Toast.makeText(Activity_SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if the email already exists
            if (databaseHelper.checkEmail(emailUser)) {
                Toast.makeText(Activity_SignUp.this, "User already exists", Toast.LENGTH_SHORT).show();
                return;
            }

            // Insert new user data
            boolean insertNewData = databaseHelper.insertUsers(emailUser, passUser, namaUser, phoneUser, "true");
            if (insertNewData) {

                Integer userIdposition = databaseHelper.getID_user(emailUser);
                Toast.makeText(Activity_SignUp.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Activity_SignUp.this, Activity_OTP_Send.class);

                //passing user idnya
                i.putExtra("userIdposition", userIdposition);
                startActivity(i);
            } else {
                Toast.makeText(Activity_SignUp.this, "Sign up failed", Toast.LENGTH_SHORT).show();
            }
        });


        //Login Button Clicked
        gotoLoginBtn.setOnClickListener(e->{
            Intent i = new Intent(Activity_SignUp.this, Activity_SignIn.class);
            startActivity(i);
        });


    }
}