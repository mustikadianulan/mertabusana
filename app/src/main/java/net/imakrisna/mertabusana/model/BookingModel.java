package net.imakrisna.mertabusana.model;

/**
 * Created by Mustika on 28/12/2017.
 */

public class BookingModel {
    private int id_booking;
    private String nama_busana,tgl_pinjam,tgl_kembali,jumlah_busana,gambar;

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getNama_busana() {
        return nama_busana;
    }

    public void setNama_busana(String nama_busana) {
        this.nama_busana = nama_busana;
    }

    public int getId_booking() {
        return id_booking;
    }

    public void setId_booking(int id_booking) {
        this.id_booking = id_booking;
    }

    public String getTgl_pinjam() {
        return tgl_pinjam;
    }

    public void setTgl_pinjam(String tgl_pinjam) {
        this.tgl_pinjam = tgl_pinjam;
    }

    public String getTgl_kembali() {
        return tgl_kembali;
    }

    public void setTgl_kembali(String tgl_kembali) {
        this.tgl_kembali = tgl_kembali;
    }

    public String getJumlah_busana() {
        return jumlah_busana;
    }

    public void setJumlah_busana(String jumlah_busana) {
        this.jumlah_busana = jumlah_busana;
    }
}
