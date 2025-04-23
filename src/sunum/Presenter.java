package sunum;

import java.util.List;
import java.util.Map;

import hesaplama.TimeCalculation;
import model.Gezegen;
import model.Insan;
import model.UzayAraci;

/**
 * Sunum Sınıfı
 * @author Tarık Toplu tarik.toplu@sakarya.edu.tr
 * @since 13.04.2025
 * <p>
 * Simülasyonun durumunu konsola yazdırır.
 * </p>
 */


public class Presenter {
	/**
	 * Simülasyonun durumunu konsola yazdırır.
	 * @param saat Simülasyonun şu anki saati.
	 * @param gezegenler Gezegenlerin isimlerini anahtar, Gezegen nesnelerini değer olarak tutan bir Map.
	 * @param araclar UzayAraci nesnelerini içeren bir List.
	 * @param kisiler Insan nesnelerini içeren bir List.
	 */
		public static void display(int saat, Map<String, Gezegen> gezegenler,
	            List<UzayAraci> araclar, List<Insan> kisiler) {
	System.out.println("Iterasyon (saat): " + saat);
	
	// Gezegenler
	System.out.print("Gezegenler:\t");
	gezegenler.values().forEach(p -> System.out.printf("%-15s", p.getIsim()));
	System.out.println();
	
	// Tarihler
	System.out.print("Tarih:\t\t");
	gezegenler.values().forEach(p -> System.out.printf("%-15s", p.getTarihAsString()));
	System.out.println();
	
	// Nüfus
	System.out.print("Nüfus:\t\t");
	gezegenler.values().forEach(p -> {
	// İlgili gezegendeki yaşayan insan sayısını hesaplar
	long pop = kisiler.stream()
	 .filter(Insan::isYasiyor) // Yaşayan insanları filtrele
	 .filter(k -> k.getAracAdi() != null)
	 .filter(k -> {
	     UzayAraci a = araclar.stream().filter(x -> x.getIsim().equals(k.getAracAdi())).findFirst().orElse(null);
	     return a != null && (a.getDurum() == UzayAraci.Durum.VARDI && a.getVarisGezegeni().equals(p.getIsim())
	             || a.getDurum() == UzayAraci.Durum.BEKLIYOR && a.getKalkisGezegeni().equals(p.getIsim()));
	 }).count();
	System.out.printf("%-15d", pop);
	});
	System.out.println("\n");
	
	// Uzay araçları
	System.out.println("Uzay Araçları:");
	System.out.printf("%-15s%-15s%-15s%-15s%-20s%-25s%n",
	"Araç Adı", "Durum", "Çıkış", "Varış", "Hedefe Kalan Saat", "Hedefe Varış Tarihi");
	
	araclar.forEach(a -> {
		// Kalan saati formata uygun olarak al
	String kalan = a.getDurum() == UzayAraci.Durum.IMHA ? "--" : String.format("%.0f", a.getKalanSaat());
	String varisT = "--";
	try {
		// Eğer araç imha olmadıysa, varış tarihini hesapla
	if (a.getDurum() != UzayAraci.Durum.IMHA) {
	 varisT = TimeCalculation.calculateCompleteJourneyDate(
	         gezegenler.get(a.getKalkisGezegeni()).getTarihAsString(),
	         a.getKalkisTarihAsString(),
	         gezegenler.get(a.getKalkisGezegeni()).getGunSaatSayisi(),
	         a.getMesafeSaat(),
	         gezegenler.get(a.getVarisGezegeni()).getTarihAsString(),
	         gezegenler.get(a.getVarisGezegeni()).getGunSaatSayisi()
	 );
	}
	} catch (Exception e) {
	varisT = "--";
	}
	
	System.out.printf("%-15s%-15s%-15s%-15s%-20s%-25s%n",
	 a.getIsim(), a.getDurumAsString(), a.getKalkisGezegeni(), a.getVarisGezegeni(), kalan, varisT);
	});
	}
}