package net.imakrisna.mertabusana.model;

import java.io.Serializable;

/**
 * Created by Mustika on 27/12/2017.
 */

public class BusanaModel implements Serializable{
    private int id_busana;
    private String nama_busana,harga_sewa,jenis_busana,deskripsi,status,stok,gambar;

    public int getId_busana() {
        return id_busana;
    }

    public void setId_busana(int id_busana) {
        this.id_busana = id_busana;
    }

    public String getNama_busana() {
        return nama_busana;
    }

    public void setNama_busana(String nama_busana) {
        this.nama_busana = nama_busana;
    }

    public String getHarga_sewa() {
        return harga_sewa;
    }

    public void setHarga_sewa(String harga_sewa) {
        this.harga_sewa = harga_sewa;
    }

    public String getJenis_busana() {
        return jenis_busana;
    }

    public void setJenis_busana(String jenis_busana) {
        this.jenis_busana = jenis_busana;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
