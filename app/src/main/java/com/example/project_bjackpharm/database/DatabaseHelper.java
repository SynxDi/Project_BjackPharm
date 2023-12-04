package com.example.project_bjackpharm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databasename = "BlueJackPharmacy.db";

    public DatabaseHelper(Context ctx) {
        super(ctx, databasename, null, 1);
    }

    //ketika tabel dibuat
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabel Users
        String Users = "CREATE TABLE users (userID INTEGER PRIMARY KEY autoincrement, " +
                "name varchar(50), email varchar(50) , password varchar(50) , phone varchar(50), isVerified varchar(50))";
        db.execSQL(Users);

        // Tabel Medicines
        String Medicines = "CREATE TABLE medicines (medicineID INTEGER PRIMARY KEY autoincrement, " +
                "name varchar(50), manufacturer varchar(50), price INTEGER, image varchar(50), description varchar(50))";
        db.execSQL(Medicines);

        // Tabel Transactions
        String Transaction = "CREATE TABLE transactions (transactionID INTEGER PRIMARY KEY autoincrement, " +
                "userID INTEGER, medicineID INTEGER,transactionDate date, quantity integer(10), " +
                "FOREIGN KEY(userID) REFERENCES users(userID), " + "FOREIGN KEY(medicineID) REFERENCES users(medicineID))";
        db.execSQL(Transaction);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table If Exists users");
        db.execSQL("Drop Table If Exists medicines");
        db.execSQL("Drop Table If Exists transactions");

        onCreate(db);
    }

    public Boolean insertUsers(String email, String password, String name, String phoneNum, String isVerified){
        // untuk melakukan insert data ke dalam database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("phone", phoneNum);
        contentValues.put("isVerified", isVerified);


        long result = db.insert("users", null, contentValues);
        db.close();

        if(result == -1 ){return false;}
        else {return true;}
    }

    public Boolean checkEmail(String email){
        //check emailnya udah ada atau belum
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email = ?", new String[]{email});

        boolean emailExists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return emailExists;

    }

    public Boolean checkEmailandPass(String email, String password) {
        //checl email dan password yang di inputkan sesuai atau engga dengan yang di data base
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});

        boolean credentialsMatch = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return credentialsMatch;
    }

    public int getID_user(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT userID FROM users WHERE email = ?", new String[]{email});

        int userID = -1;
        if (cursor.moveToFirst()) {
            userID = cursor.getInt(cursor.getColumnIndexOrThrow("userID"));
        }

        cursor.close();
        db.close();
        return userID;

        }

    public void addMedicine_into_Database(ArrayList<Database_Medicine> meds) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM medicines", null);

        if (!cursor.moveToFirst()) {
            for (Database_Medicine med : meds) {
                String query = "INSERT INTO medicines (name, manufacturer, price, image, description) VALUES (?, ?, ?, ?, ?)";
                db.execSQL(query, new Object[]{med.getNama_Medicine(), med.getManufacture_Medicine(), med.getHarga_Medicine(), med.getImage_src(), med.getDesc_Medicine()});
            }
        }
        cursor.close();
    }


    public void addTransactions(Integer medicineID, Integer userID, String transactionDate, Integer jumlahqty) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "INSERT INTO transactions (medicineID, userID, transactionDate, quantity) VALUES (?, ?, ?, ?)";
        db.execSQL(query, new Object[]{medicineID, userID, transactionDate, jumlahqty});
    }

    public ArrayList<Database_Transaction> getTransac(Integer usId) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT transactionID AS `id`, medicines.name AS `name`, medicines.description AS `desc`, " +
                "medicines.image AS `image`, medicines.price AS `price`, transactionDate AS `date`, quantity " +
                "FROM transactions " +
                "INNER JOIN users ON transactions.userID=users.userID " +
                "INNER JOIN medicines ON transactions.medicineID=medicines.medicineID " +
                "WHERE transactions.userID=?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(usId)});


        ArrayList<Database_Transaction> transactions = new ArrayList<>();
        while (cursor.moveToNext()) {
            Integer jumlahqty = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
            String name_med = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            Integer transactionID = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String img_src_med = cursor.getString(cursor.getColumnIndexOrThrow("image"));
            String transactionDate = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            Integer priceTransac = cursor.getInt(cursor.getColumnIndexOrThrow("price"));

            transactions.add(new Database_Transaction(transactionID, jumlahqty, priceTransac,transactionDate,img_src_med,name_med));
        }
        cursor.close();
        db.close();
        return transactions;
    }








}

