package com.mycompany.mavenproject4;

public class Mahasiswa {
    public Long id;
    public String name;
    public Double nim;
    public String study;
    public String purpose;

    public Mahasiswa(Long id, String name, Double nim, String study, String purpose) {
        this.id = id;
        this.name = name;
        this.nim = nim;
        this.study = study;
        this.purpose = purpose;
    }
}