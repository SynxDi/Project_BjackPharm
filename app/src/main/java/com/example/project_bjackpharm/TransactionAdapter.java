package com.example.project_bjackpharm;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_bjackpharm.database.DatabaseHelper;
import com.example.project_bjackpharm.database.Database_Transaction;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Database_Transaction> database_transactions;

    //nyimpen di array baru kalau misalnya sama baru di tampilin

    public TransactionAdapter(Context ctx, ArrayList<Database_Transaction> database_transactions){
        this.ctx = ctx;
        this.database_transactions = database_transactions;
    }



    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.transaction_card, parent, false);
        return new TransactionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        Database_Transaction newTransaction = database_transactions.get(position);
        DatabaseHelper databaseHelper = new DatabaseHelper(ctx);

        //Picasso.get().load(database_medicines.get(position).getImage_src()).into(holder.imageView);
        Picasso.get().load(database_transactions.get(position).getMedicine_image_Transaction()).into(holder.imageView);
        holder.namaMedicineTrans.setText(database_transactions.get(position).getMedicine_name_transaction());
//        holder.manufMedicineTrans.setText(database_transactions.get(position).getMedicine().getManufacture_Medicine());
        holder.priceMedicineTrans.setText(String.valueOf(database_transactions.get(position).getPrice_Transaction() * newTransaction.getJumlahQuantity()));
        holder.quantityMedicineTrans.setText(database_transactions.get(position).getJumlahQuantity().toString());
        holder.dateMedicineTrans.setText(database_transactions.get(position).getTransactionDate());



        holder.deleteBtn.setOnClickListener(e->{
            database_transactions.remove(database_transactions.get(position));
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        });

        holder.updateBtn.setOnClickListener(e -> {

            Integer quantity = Integer.parseInt(holder.quantityMedicineTrans.getText().toString());
            //set for quantity cannot less than equal than 0
            if (quantity <= 0 || quantity.equals("")) {
                Toast.makeText(e.getContext(), "Product must be more than 0", Toast.LENGTH_SHORT).show();
                return;

            } else {

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd - MM - yyyy -- HH:mm");
                LocalDateTime waktu = LocalDateTime.now();

                database_transactions.get(position).setJumlahQuantity(quantity);
                database_transactions.get(position).setTransactionDate(dateTimeFormatter.format(waktu));
                notifyItemChanged(position);
                notifyItemRangeChanged(position, getItemCount());
                return;
            }

        });
    }



    @Override
    public int getItemCount() {
        return database_transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView namaMedicineTrans , manufMedicineTrans, priceMedicineTrans, dateMedicineTrans;
        EditText quantityMedicineTrans;
        LinearLayout transactionCard;
        Button updateBtn, deleteBtn;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageMedTrans);
            namaMedicineTrans = itemView.findViewById(R.id.nameMedTrans);
            manufMedicineTrans = itemView.findViewById(R.id.manufMedTrans);
            priceMedicineTrans = itemView.findViewById(R.id.priceMedTrans);
            quantityMedicineTrans = itemView.findViewById(R.id.quantities);
            dateMedicineTrans = itemView.findViewById(R.id.dateMedTrans);
            updateBtn = itemView.findViewById(R.id.UpdateData);
            deleteBtn = itemView.findViewById(R.id.DeleteData);



            transactionCard = itemView.findViewById(R.id.transactionCardCtr);


        }
    }
}
