package com.example.uas;

public class ModelUser {
    private String nama;
    private String npm;
    private String key;

    public ModelUser(String nama, String npm) {
        this.nama = nama;
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
