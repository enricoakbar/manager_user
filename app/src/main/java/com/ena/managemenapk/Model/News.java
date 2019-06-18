package com.ena.managemenapk.Model;

public class News
{
    private String judul, deskripsi, image, date, kategori, pid, time;
    public News()
    {

    }

    public News(String judul, String deskripsi, String image, String date, String kategori, String pid, String time) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.image = image;
        this.date = date;
        this.kategori = kategori;
        this.pid = pid;
        this.time = time;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
