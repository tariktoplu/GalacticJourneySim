package okuyucu;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.format.DateTimeFormatterBuilder;


import model.Gezegen;
import model.Insan;
import model.UzayAraci;



/** 
* Dosyaları Okuma Sınıfı
* @author Tarık Toplu tarik.toplu@sakarya.edu.tr
* @since 10.04.2025
* <p> 
* Dosyaları okuyarak gerekli parse işlemlerini gerçekleştirir
* </p> 
*/
public class DosyaOkuma {
    
	/**
     * Tarih formatlarını tanımlayan DateTimeFormatter nesnesi.
     * "d.M.yyyy" veya "dd.MM.yyyy" formatlarını destekler.
     */
	private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("[d.M.yyyy][dd.MM.yyyy]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

	 /**
     * Gezegenler dosyasını okuyarak gezegenlerin bilgilerini saklar.
     * @param filePath Gezegenler dosyasının yolu.
     * @return Gezegen isimlerini anahtar, Gezegen nesnelerini değer olarak tutan bir Map.
     * @throws IOException Dosya okuma hatası durumunda fırlatılır.
     * @throws ParseException Tarih ayrıştırma hatası durumunda fırlatılır.
     */
    public static Map<String, Gezegen> readPlanets(String filePath) throws IOException, ParseException {
        Map<String, Gezegen> gezegenMap = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] par = satir.split("#");
                if (par.length == 3) {
                    String isim = par[0];
                    int gunSaat = Integer.parseInt(par[1]);
                    LocalDate tarih = LocalDate.parse(par[2], FORMATTER);
                    gezegenMap.put(isim, new Gezegen(isim, gunSaat, tarih));
                }
            }
        }
        return gezegenMap;
    }

    /**
     * Uzay araçları dosyasını okuyarak uzay araçlarının bilgilerini saklar.
     * @param filePath Uzay araçları dosyasının yolu.
     * @return UzayAraci nesnelerini içeren bir List.
     * @throws IOException Dosya okuma hatası durumunda fırlatılır.
     * @throws ParseException Tarih ayrıştırma hatası durumunda fırlatılır.
     */
    public static List<UzayAraci> readSpacecrafts(String filePath) throws IOException, ParseException {
        List<UzayAraci> liste = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] par = satir.split("#");
                if (par.length == 5) {
                    String isim = par[0];
                    String kalkis = par[1];
                    String varis = par[2];
                    LocalDate tarih = LocalDate.parse(par[3], FORMATTER);
                    double mesafe = Double.parseDouble(par[4]);
                    liste.add(new UzayAraci(isim, kalkis, varis, tarih, mesafe));
                }
            }
        }
        return liste;
    }

    /**
     * İnsanlar dosyasını okuyarak insanların bilgilerini saklar.
     * @param filePath İnsanlar dosyasının yolu.
     * @return Insan nesnelerini içeren bir List.
     * @throws IOException Dosya okuma hatası durumunda fırlatılır.
     */
    public static List<Insan> readPeople(String filePath) throws IOException {
        List<Insan> liste = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] par = satir.split("#");
                if (par.length == 4) {
                    String isim = par[0];
                    int yas = Integer.parseInt(par[1]);
                    int omur = Integer.parseInt(par[2]);
                    String arac = par[3];
                    liste.add(new Insan(isim, yas, omur, arac));
                }
            }
        }
        return liste;
    }
}

