package com.example.project_bjackpharm.activities;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_bjackpharm.R;
import com.example.project_bjackpharm.database.DatabaseHelper;
import com.example.project_bjackpharm.database.Database_Medicine;
import com.example.project_bjackpharm.database.Database_Transaction;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    TextView nameMedicineDetail, descMedicineDetail, priceMedicineDetail, manufactureMedicineDetail;
    ImageView medicineImageView;
    Button buyBtn;
    EditText quantityMedicine;
    Integer constant = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String medicineNameDetail = getIntent().getStringExtra("medicine_name");
        String medicineDescDetail = getIntent().getStringExtra("medicine_description");
        String medicinePriceDetail = getIntent().getStringExtra("medicine_price");
        String medicineSourceDetail = getIntent().getStringExtra("medicine_image");
        String medicineManufDetail = getIntent().getStringExtra("medicine_manufacture");
        Integer medicineId = getIntent().getIntExtra("medicine_ID",0);
        Integer userId = getIntent().getIntExtra("userIdposition", 0);

        Log.i("ABC", userId.toString());

        setTitle(medicineNameDetail);

        nameMedicineDetail = findViewById(R.id.namaObatDetail);
        descMedicineDetail = findViewById(R.id.descripsiObatDetail);
        priceMedicineDetail = findViewById(R.id.priceObatDetail);
        manufactureMedicineDetail = findViewById(R.id.manufactureObatDetail);
        medicineImageView = findViewById(R.id.imageObatDetail);
        buyBtn = findViewById(R.id.buyBtnDetail);
        quantityMedicine = findViewById(R.id.inputQuantityMed);

        nameMedicineDetail.setText(medicineNameDetail);
        descMedicineDetail.setText(medicineDescDetail);
        priceMedicineDetail.setText(medicinePriceDetail);
        manufactureMedicineDetail.setText(medicineManufDetail);
        Picasso.get().load(medicineSourceDetail).into(medicineImageView);


        buyBtn.setOnClickListener(e->{
            String quantitymedicines = quantityMedicine.getText().toString();

            if (quantitymedicines.equals("")){
                Toast.makeText(DetailActivity.this, "Please input quantity", Toast.LENGTH_SHORT).show();
                return;
            }

            int jumlahnya = Integer.parseInt(quantitymedicines);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd - MM - yyyy -- HH:mm");
            LocalDateTime waktu = LocalDateTime.now();
            String waktubeli = dateTimeFormatter.format(waktu).toString();

            if (jumlahnya <= 0) {
                Toast.makeText(DetailActivity.this, "At least 1 medicine", Toast.LENGTH_SHORT).show();
                return;
            } else{

//                HomeActivity.transaksi.add(new Database_Transaction(
//                        DatabaseHelper.,
//                        new Database_Medicine(medicineNameDetail,medicineDescDetail,medicineSourceDetail, medicineManufDetail,Integer.valueOf(medicinePriceDetail)),
//                        dateTimeFormatter.format(waktu).toString(),
//                        Integer.parseInt(quantityMedicine.getText().toString())));
                databaseHelper.addTransactions(medicineId,userId,waktubeli, jumlahnya);
                Toast.makeText(getApplicationContext(),"Added product to the Cart", Toast.LENGTH_SHORT).show();
                finish();

                Intent balik_ke_awal = new Intent(DetailActivity.this, HomeActivity.class);
                startActivity(balik_ke_awal);

            }

        });


    }
}