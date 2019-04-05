package com.vanard.vian_1202154186_si4008_pab_modul5;

public class Article {
    String penulis;
    String judul;
    String deskripsi;
    String created_at;

    public Article(String penulis, String judul, String deskripsi, String created_at) {
        this.penulis = penulis;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.created_at = created_at;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return created_at;
    }
}
