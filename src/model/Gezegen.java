package model;

import java.time.LocalDate;

/** 
* Gezegen bilgilerini tutan model sınıfı
* @author Tarık Toplu tarik.toplu@sakarya.edu.tr
* @since 10.04.2025
* <p> 
* Gezegenlerin zaman akışını ve temel özelliklerini yönetir
* </p> 
*/

/**
 * Gezegen constructor
 * @param isim Gezegen adı
 * @param gunSaatSayisi Gezegendeki bir günün saat uzunluğu
 * @param baslangicTarih Başlangıç tarihi
 */
public class Gezegen {
	private final String isim;
    private final int gunSaatSayisi;
    private LocalDate mevcutTarih;
    private int birikenSaat;

    public Gezegen(String isim, int gunSaatSayisi, LocalDate baslangicTarih) {
        this.isim = isim;
        this.gunSaatSayisi = gunSaatSayisi;
        this.mevcutTarih = baslangicTarih;
        this.birikenSaat = 0;
    }
    
    
    /**
     * Gezegen zamanını ilerletir
     * @param saat İlerletilecek saat miktarı
     */
    public void zamanIlerle(int saat) {
        birikenSaat += saat;
        int ekGun = birikenSaat / gunSaatSayisi;
        birikenSaat %= gunSaatSayisi;
        mevcutTarih = mevcutTarih.plusDays(ekGun);
    }

    public String getIsim() { return isim; }
    public int getGunSaatSayisi() { return gunSaatSayisi; }
    public LocalDate getMevcutTarih() { return mevcutTarih; }
    
    /**
     * Gezegenin mevcut tarihini string olarak döndürür
     * @return Formatlı tarih stringi
     */
    public String getTarihAsString() { return mevcutTarih.format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy")); }
}
