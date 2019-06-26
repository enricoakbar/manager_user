package com.ena.managemenapk.Model;

public class Tugas {
    public String judultugaspegawai, deskripsipegawai, tugaswajib, tugastambahan;

    public Tugas() {

    }

    public Tugas(String judultugaspegawai, String deskripsipegawai, String tugaswajib, String tugastambahan) {
        this.judultugaspegawai = judultugaspegawai;
        this.deskripsipegawai = deskripsipegawai;
        this.tugaswajib = tugaswajib;
        this.tugastambahan = tugastambahan;
    }

    public String getJudultugaspegawai() {
        return judultugaspegawai;
    }

    public void setJudultugaspegawai(String judultugaspegawai) {
        this.judultugaspegawai = judultugaspegawai;
    }

    public String getDeskripsipegawai() {
        return deskripsipegawai;
    }

    public void setDeskripsipegawai(String deskripsipegawai) {
        this.deskripsipegawai = deskripsipegawai;
    }

    public String getTugaswajib() {
        return tugaswajib;
    }

    public void setTugaswajib(String tugaswajib) {
        this.tugaswajib = tugaswajib;
    }

    public String getTugastambahan() {
        return tugastambahan;
    }

    public void setTugastambahan(String tugastambahan) {
        this.tugastambahan = tugastambahan;
    }
}
