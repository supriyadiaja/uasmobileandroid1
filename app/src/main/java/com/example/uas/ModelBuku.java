package com.example.uas;

public class ModelBuku {
    private String buku;
    private String kode;
    private String key;

    public ModelBuku (){

    }

    public ModelBuku(String buku, String kode) {
        this.buku = buku;
        this.kode = kode;
    }

    public String getBuku() {
        return buku;
    }

    public void setBuku(String buku) {
        this.buku = buku;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
