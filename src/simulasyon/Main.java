package simulasyon;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Tarık Toplu
 * @since 23.04.2025
 * <p>
 * Simülasyonun ana metodunu çalıştırır.
 * </p>
 */

public class Main {

	public static void main(String[] args) {
		 try {
	            // Simülasyon başlatılıyor
	            Simulation simulation = new Simulation();
	            simulation.run();
	        } catch (IOException e) {
	            System.err.println("Dosya okuma hatası: " + e.getMessage());
	        } catch (ParseException e) {
	            System.err.println("Tarih ayrıştırma hatası: " + e.getMessage());
	        } catch (InterruptedException e) {
	            System.err.println("Simülasyon kesildi: " + e.getMessage());
	        }

	}

}
