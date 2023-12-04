package com.example.project_bjackpharm.fragments;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_bjackpharm.MedicineAdapter;
import com.example.project_bjackpharm.R;
import com.example.project_bjackpharm.activities.Activity_SignIn;
import com.example.project_bjackpharm.activities.HomeActivity;
import com.example.project_bjackpharm.database.Database_Medicine;

import java.util.ArrayList;

public class Fragment_ListMedicine extends Fragment {

    RecyclerView medicineRV;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__list_medicine, container,false);
//        database_medicines.add(new Database_Medicine("Siladex DMP", "SILADEX DMP merupakan sirup obat yang digunakan untuk meredakan batuk tidak berdahak atau batuk kering yang disertai alergi. Obat ini mengandung Dextromethorphan HBr dan Diphenhydramine HCL.", "https://images.tokopedia.net/img/cache/700/hDjmkQ/2022/2/15/86aabef1-6027-4e2d-ac77-7c1ab9562131.jpg", "Konimex Manufacture", 16000));
//        database_medicines.add(new Database_Medicine("Sanadryl Expectoran", "SANADRYL EXPECTORANT SIRUP mengandung Diphenhydramin HCl, Ammonium Chlorida, Kalium Sulfaguaiakolat, Natrium Sitrat, Menthol, digunakan untuk mengatasi batuk berdahak yang disebabkan oleh reaksi alergi.", "https://res.cloudinary.com/dk0z4ums3/image/upload/v1678694553/attached_image/pilihan-obat-batuk-berdahak-yang-ampuh-5-alodokter.jpg", "Kalbe Farma", 20000));
//        database_medicines.add(new Database_Medicine("Actifec Merah", "ACTIFED PLUS COUGH SUPPRESANT merupakan obat batuk kering dan yang membantu meredakan pembengkakan pembuluh darah di dalam hidung, sehingga saluran napas lebih terbuka dan napas menjadi lega dan sebagai anti alergi untuk meredakan gejala alergi seperti bersin-bersin.", "https://cf.shopee.co.id/file/cc74e8c01342d376d58b8a7c8e72e698", "PT Sterling", 65000));

        ArrayList<Database_Medicine> database_medicines;

        Integer usIds = HomeActivity.userIds;
        database_medicines = Activity_SignIn.Meds;

        medicineRV = (RecyclerView) view.findViewById(R.id.medicineRV);
        MedicineAdapter medicineAdapter = new MedicineAdapter(usIds,getContext(), database_medicines);
        medicineRV.setAdapter(medicineAdapter);
        medicineRV.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}