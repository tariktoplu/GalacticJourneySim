package model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


/** 
* Uzay aracı model sınıfı
* @author Tarık Toplu tarik.toplu@sakarya.edu.tr
* @since 10.04.2025
* <p> 
* Uzay araçlarının durumlarını ve yolculuk bilgilerini yönetir
* </p> 
*/
public class UzayAraci {
	 /** Uzay aracı durum enum'ı */
	public enum Durum { BEKLIYOR, YOLDA, VARDI, IMHA }

    private final String isim;
    private final String kalkisGezegeni;
    private final String varisGezegeni;
    private final LocalDate kalkisTarih;
    private final double mesafeSaat;
    private double kalanSaat;
    private Durum durum;

    public UzayAraci(String isim, String kalkisGezegeni, String varisGezegeni,
                      LocalDate kalkisTarih, double mesafeSaat) {
        this.isim = isim;
        this.kalkisGezegeni = kalkisGezegeni;
        this.varisGezegeni = varisGezegeni;
        this.kalkisTarih = kalkisTarih;
        this.mesafeSaat = mesafeSaat;
        this.kalanSaat = mesafeSaat;
        this.durum = Durum.BEKLIYOR;
    }
    
    /**
     * Uzay aracı durumunu günceller
     * @param kalkis Kalkış gezegeni
     * @param varis Varış gezegeni
     */
    public void durumGuncelle(Gezegen kalkis, Gezegen varis) {
        if (durum == Durum.IMHA) return;
        if (durum == Durum.BEKLIYOR && kalkis.getTarihAsString().equals(kalkisTarih.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))) {
            durum = Durum.YOLDA;
        }
    }

    /**
     * Yolculuğu ilerletir ve mürettebat durumunu kontrol eder
     * @param saat İlerletilecek saat miktarı
     * @param kisiler Mürettebat listesi
     */
    public void yolculukIlerle(int saat, List<Insan> kisiler) {
    	 // Mürettebattan hayatta olan var mı kontrolü
    	boolean hayatta = kisiler.stream()
                .filter(p -> p.getAracAdi().equals(isim))
                .anyMatch(Insan::isYasiyor);
        if (!hayatta) {
            durum = Durum.IMHA;
            return;
        }
        if (durum == Durum.YOLDA) {
            kalanSaat -= saat;
            if (kalanSaat <= 0) {
                kalanSaat = 0;
                durum = Durum.VARDI;
            }
        }
    }

    //getter'lar:
    public String getIsim() { return isim; }
    public String getKalkisGezegeni() { return kalkisGezegeni; }
    public String getVarisGezegeni() { return varisGezegeni; }
    public double getKalanSaat() { return kalanSaat; }
    public Durum getDurum() { return durum; }
    public String getDurumAsString() { return durum.name(); }
    public double getMesafeSaat() { return mesafeSaat; }
   
    /**
     * Kalkış tarihini string olarak döndürür
     * @return Formatlı tarih stringi
     */
    public String getKalkisTarihAsString() {
        return kalkisTarih.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}