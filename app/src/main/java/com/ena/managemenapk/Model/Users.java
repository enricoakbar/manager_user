package com.ena.managemenapk.Model;

public class Users {
    public String username, nip, password, image, name, judultugaswajib, deskripsitugaswajib;

    public Users()
    {

    }

    public Users(String username, String nip, String password, String image, String name, String judultugaswajib, String deskripsitugaswajib) {
        this.username = username;
        this.nip = nip;
        this.password = password;
        this.image = image;
        this.name = name;
        this.judultugaswajib = judultugaswajib;
        this.deskripsitugaswajib = deskripsitugaswajib;
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

    public String getJudultugaswajib() {
        return judultugaswajib;
    }

    public void setJudultugaswajib(String judultugaswajib) {
        this.judultugaswajib = judultugaswajib;
    }

    public String getDeskripsitugaswajib() {
        return deskripsitugaswajib;
    }

    public void setDeskripsitugaswajib(String deskripsitugaswajib) {
        this.deskripsitugaswajib = deskripsitugaswajib;
    }
}
