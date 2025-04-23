package model;

/** 
* İnsan model sınıfı
* @author Tarık Toplu tarik.toplu@sakarya.edu.tr
* @since 10.04.2025
* <p> 
* Mürettebat bilgilerini ve yaşam sürelerini yönetir
* </p> 
*/

public class Insan {private final String isim;
private final int yas;
private int kalanOmurSaat;
private final String aracAdi;
private boolean yasiyor;


/**
 * Insan constructor
 * @param isim Kişi adı
 * @param yas Kişi yaşı
 * @param kalanOmurSaat Kalan ömür (saat)
 * @param aracAdi Bağlı olduğu uzay aracı
 */
public Insan(String isim, int yas, int kalanOmurSaat, String aracAdi) {
    this.isim = isim;
    this.yas = yas;
    this.kalanOmurSaat = kalanOmurSaat;
    this.aracAdi = aracAdi;
    this.yasiyor = true;
}


/**
 * Kişinin zamanını ilerletir (ömrünü azaltır)
 * @param saat İlerletilecek saat miktarı
 */
public void zamanIlerle(int saat) {
    if (!yasiyor) return;
    kalanOmurSaat -= saat;
    if (kalanOmurSaat <= 0) {
        yasiyor = false;
        kalanOmurSaat = 0;
    }
}

//Getter methodları
public String getIsim() { return isim; }
public int getYas() { return yas; }
public int getKalanOmurSaat() { return kalanOmurSaat; }
public String getAracAdi() { return aracAdi; }
public boolean isYasiyor() { return yasiyor; }
}