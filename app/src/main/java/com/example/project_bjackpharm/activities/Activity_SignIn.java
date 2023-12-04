package com.example.project_bjackpharm.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project_bjackpharm.MainActivity;
import com.example.project_bjackpharm.R;
import com.example.project_bjackpharm.database.DatabaseHelper;
import com.example.project_bjackpharm.database.Database_Medicine;
import com.example.project_bjackpharm.databinding.ActivitySignInBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Activity_SignIn extends AppCompatActivity {


    ActivitySignInBinding binding;
    DatabaseHelper db ;
    public static ArrayList<Database_Medicine> Meds = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        binding = ActivitySignInBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());


//        emailLoginTxt = findViewById(R.id.EmailLoginTxt);
//        passLoginTxt = findViewById(R.id.passwordLoginTxt);
//        loginBtn = findViewById(R.id.signInBtnLogin);
//        goToRegisterBtn = findViewById(R.id.gotoRegisterBtn);

        db = new DatabaseHelper(this);
        if(Meds.isEmpty()){
            fetchMedData();
        }

        binding.signInBtnLogin.setOnClickListener(e->{
            String emailLogin = binding.EmailLoginTxt.getText().toString();
            String passLogin = binding.passwordLoginTxt.getText().toString();


            if(emailLogin.isEmpty() && passLogin.isEmpty()){
                Toast.makeText(Activity_SignIn.this, "All fields must be filled", Toast.LENGTH_SHORT ).show();
                return;
            }

            if(emailLogin.isEmpty()){
                Toast.makeText(Activity_SignIn.this,"Email must be filled", Toast.LENGTH_SHORT).show();
                return;
            } else if(passLogin.isEmpty()) {
                Toast.makeText(Activity_SignIn.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                return;
            }else{
                if (!emailLogin.endsWith(".com")) {
                    Toast.makeText(Activity_SignIn.this, "Email must end with '.com'", Toast.LENGTH_SHORT).show();
                    return;
                }


                Boolean checkCreds = db.checkEmailandPass(emailLogin,passLogin);
                if(checkCreds == true){
                    Integer userIdposition = db.getID_user(emailLogin);
                    Log.i("ABCD", userIdposition.toString());
                    Toast.makeText(Activity_SignIn.this,"Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(getApplicationContext(), HomeActivity.class);

                    a.putExtra("userIdposition", userIdposition);
                    startActivity(a);
                }else{
                    Integer userIdposition = db.getID_user(emailLogin);
                    Toast.makeText(Activity_SignIn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(getApplicationContext(), Activity_OTP_Send.class);

                    a.putExtra("userIdposition", userIdposition);
                    startActivity(a);
                }
            }
        });

        binding.gotoRegisterBtn.setOnClickListener(e->{
            Intent a = new Intent(Activity_SignIn.this, Activity_SignUp.class);
            startActivity(a);
        });
    }

    private void fetchMedData() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());

            //fetch information url
            String jsondata = "https://mocki.io/v1/ae13b04b-13df-4023-88a5-7346d5d3c7eb";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, jsondata, response -> {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("medicines");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Database_Medicine tempMed = new Database_Medicine(
                                jsonArray.getJSONObject(i).getString("name"),
                                jsonArray.getJSONObject(i).getString("description"),
                                jsonArray.getJSONObject(i).getString("image"),
                                jsonArray.getJSONObject(i).getString("manufacturer"),
                                jsonArray.getJSONObject(i).getInt("price")

                        );
                        Meds.add(tempMed);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                Toast.makeText(Activity_SignIn.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            });
            requestQueue.add(stringRequest);
        });
    }
}