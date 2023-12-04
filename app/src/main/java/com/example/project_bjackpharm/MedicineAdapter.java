package com.example.project_bjackpharm;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_bjackpharm.activities.DetailActivity;
import com.example.project_bjackpharm.database.Database_Medicine;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {

    Integer userIds;
    Integer constant = 1;
    Context ctx;
    ArrayList<Database_Medicine> database_medicines;

    public MedicineAdapter(Integer userIds, Context ctx, ArrayList<Database_Medicine> database_medicines){
        this.userIds = userIds;
        this.ctx = ctx;
        this.database_medicines = database_medicines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(ctx).inflate(R.layout.medicine_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Database_Medicine newDatabase = database_medicines.get(position);


        Picasso.get().load(database_medicines.get(position).getImage_src()).fit().into(holder.imageView);
        holder.nameMedicine.setText(database_medicines.get(position).getNama_Medicine());
        holder.manufactueMedicine.setText(database_medicines.get(position).getManufacture_Medicine());
        holder.priceMedicine.setText(database_medicines.get(position).getHarga_Medicine().toString());

        holder.itemView.setOnClickListener(e->{
            Intent intent = new Intent(holder.imageView.getContext(), DetailActivity.class);
            intent.putExtra("medicine_image", database_medicines.get(position).getImage_src());
            intent.putExtra("medicine_name", database_medicines.get(position).getNama_Medicine());
            intent.putExtra("medicine_description", database_medicines.get(position).getDesc_Medicine());
            intent.putExtra("medicine_price", database_medicines.get(position).getHarga_Medicine().toString());
            intent.putExtra("medicine_manufacture", database_medicines.get(position).getManufacture_Medicine() );
            intent.putExtra("userIdposition", userIds);
            intent.putExtra("medicine_ID", position + constant);
            e.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return database_medicines.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nameMedicine, manufactueMedicine, priceMedicine;
        LinearLayout medicineCont;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageTV);
            nameMedicine = itemView.findViewById(R.id.nameTV);
            manufactueMedicine = itemView.findViewById(R.id.manufTV);
            priceMedicine = itemView.findViewById(R.id.priceTV);
            medicineCont = itemView.findViewById(R.id.medicineCardctr);
        }
    }

}
