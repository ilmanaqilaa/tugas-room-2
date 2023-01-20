package com.denjand.tugasroom.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Kasir implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nama")
    private String nama;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "jns_kelamin")
    public String jns_kelamin;

    @ColumnInfo(name = "nohp")
    public String nohp;



    /*
     * Getters and Setters
     * */

//    id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    nama

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
//    email

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }



//    jns_kelamin

    public String getJns_Kelamin() {
        return jns_kelamin;
    }

    public void setJns_Kelamin(String jns_kelamin) {
        this.jns_kelamin = jns_kelamin;
    }
//    nohp

    public String getNoHP() {
        return nohp;
    }

    public void setNoHP(String nohp) {
        this.nohp = nohp;
    }
}
