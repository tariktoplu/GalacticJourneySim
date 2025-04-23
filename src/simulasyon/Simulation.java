package simulasyon;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import model.Gezegen;
import model.Insan;
import model.UzayAraci;
import okuyucu.DosyaOkuma;
import sunum.Presenter;

/**
 * @author Tarık Toplu
 * @since 23.04.2025
 * <p>
 * Uzay simülasyonunu yönetir. Gezegenlerin zamanını ilerletir, insanların ömürlerini azaltır ve uzay araçlarının durumlarını kontrol eder.
 * </p>
 */
public class Simulation {

    private Map<String, Gezegen> gezegenler;
    private List<UzayAraci> uzayAraclari;
    private List<Insan> kisiler;
    private int saat;
    private boolean tumAraclarVarisYapti;

    public Simulation() throws IOException, ParseException {
        // Dosyalar okunuyor
        this.gezegenler = DosyaOkuma.readPlanets("Gezegenler.txt");
        this.uzayAraclari = DosyaOkuma.readSpacecrafts("Araclar.txt");
        this.kisiler = DosyaOkuma.readPeople("Kisiler.txt");
        this.saat = 0;
        this.tumAraclarVarisYapti = false;

        System.out.println("Dosyalar başarıyla okundu.\nSimülasyon başlıyor...\n");
    }

    public void run() throws InterruptedException {
        while (!tumAraclarVarisYapti) {
            saat++;
            clearScreen();

            gezegenler.values().forEach(gezegen -> gezegen.zamanIlerle(1));
            kisiler.forEach(insan -> insan.zamanIlerle(1));

            for (UzayAraci arac : uzayAraclari) {
                Gezegen kalkisGezegeni = gezegenler.get(arac.getKalkisGezegeni());
                Gezegen varisGezegeni = gezegenler.get(arac.getVarisGezegeni());

                arac.durumGuncelle(kalkisGezegeni, varisGezegeni);
                arac.yolculukIlerle(1, kisiler);
            }

            Presenter.display(saat, gezegenler, uzayAraclari, kisiler);

            tumAraclarVarisYapti = uzayAraclari.stream()
                    .allMatch(arac -> arac.getDurum() == UzayAraci.Durum.VARDI || arac.getDurum() == UzayAraci.Durum.IMHA);

            Thread.sleep(1000);
        }

        // Simülasyon tamamlandıktan sonra son durumu tekrar göster
        clearScreen();
        Presenter.display(saat, gezegenler, uzayAraclari, kisiler);
        System.out.println("\nSimülasyon tamamlandı. Tüm uzay araçları hedeflerine ulaştı veya imha oldu.");
    }

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
}
