package com.rini.bajuonline;

public class Baju {

    String nama;
    String gambar;
    String harga;

    public Baju() {
    }

    public Baju(String nama, String gambar, String harga) {
        this.nama = nama;
        this.gambar = gambar;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
