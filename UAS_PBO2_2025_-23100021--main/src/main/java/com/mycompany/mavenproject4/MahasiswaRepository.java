package com.mycompany.mavenproject4;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaRepository {
   private static List<Mahasiswa> mahasiswaList = new ArrayList<>();
   private static long idCounter = 1;

   public static Mahasiswa add(String name, Double nim, String study, String purpose) {
       Mahasiswa mahasiswa = new Mahasiswa(idCounter++, name, nim, study, purpose);
       mahasiswaList.add(mahasiswa);
       return mahasiswa;
   }

   public static List<Mahasiswa> findAll() {
       return mahasiswaList;
   }

   public static Mahasiswa findById(Long id) {
       return mahasiswaList.stream().filter(p -> p.id.equals(id)).findFirst().orElse(null);
   }
    public static Mahasiswa update(Long id, String name, Double nim, String study, String purpose) {
        Mahasiswa mahasiswa = findById(id);
           if (mahasiswa != null) {
               mahasiswa.name = name;
               mahasiswa.nim = nim;
               mahasiswa.study = study;
               mahasiswa.purpose = purpose;
           }
           return mahasiswa;
       }

   public static boolean delete(Long id) {
       return mahasiswaList.removeIf(p -> p.id.equals(id));
   }
}