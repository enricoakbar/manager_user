package com.ena.managemenapk.Model;

public class Users {
    public String username, nip, password, image, name, judultugas, deskripsitugas, keterangantugas, pointtugastambahan, pointtugaswajib;

    public Users()
    {

    }

    public Users(String username, String nip, String password, String image, String name, String judultugas, String deskripsitugas, String keterangantugas, String pointtugastambahan, String pointtugaswajib) {
        this.username = username;
        this.nip = nip;
        this.password = password;
        this.image = image;
        this.name = name;
        this.judultugas = judultugas;
        this.deskripsitugas = deskripsitugas;
        this.keterangantugas = keterangantugas;
        this.pointtugastambahan = pointtugastambahan;
        this.pointtugaswajib = pointtugaswajib;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJudultugas() {
        return judultugas;
    }

    public void setJudultugas(String judultugas) {
        this.judultugas = judultugas;
    }

    public String getDeskripsitugas() {
        return deskripsitugas;
    }

    public void setDeskripsitugas(String deskripsitugas) {
        this.deskripsitugas = deskripsitugas;
    }

    public String getKeterangantugas() {
        return keterangantugas;
    }

    public void setKeterangantugas(String keterangantugas) {
        this.keterangantugas = keterangantugas;
    }

    public String getPointtugastambahan() {
        return pointtugastambahan;
    }

    public void setPointtugastambahan(String pointtugastambahan) {
        this.pointtugastambahan = pointtugastambahan;
    }

    public String getPointtugaswajib() {
        return pointtugaswajib;
    }

    public void setPointtugaswajib(String pointtugaswajib) {
        this.pointtugaswajib = pointtugaswajib;
    }
}
