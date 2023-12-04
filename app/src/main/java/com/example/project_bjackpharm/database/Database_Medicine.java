package com.example.project_bjackpharm.database;


import android.content.Intent;

public class Database_Medicine {
    String nama_Medicine, desc_Medicine , image_src, manufacture_Medicine;
    Integer harga_Medicine;


    public Database_Medicine(String nama_Medicine, String desc_Medicine, String image_src, String manufacture_Medicine, Integer harga_Medicine){
        this.nama_Medicine = nama_Medicine;
        this.desc_Medicine = desc_Medicine;
        this.image_src = image_src;
        this.manufacture_Medicine = manufacture_Medicine;
        this.harga_Medicine = harga_Medicine;
//        this.jumlah_ketersediaan = jumlah_ketersediaan;
    }

    //setter getternya alt + insert -> shortcut
    //gausa pake setter karena uda ada constructor
    public String getNama_Medicine() {
        return nama_Medicine;
    }
    public String getDesc_Medicine() {
        return desc_Medicine;
    }
    public String getImage_src() {
        return image_src;
    }
    public Integer getHarga_Medicine() {
        return harga_Medicine;
    }

    public String getManufacture_Medicine() {
        return manufacture_Medicine;
    }

}
