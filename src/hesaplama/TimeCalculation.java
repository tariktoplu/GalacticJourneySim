package hesaplama;
/** 
* Zaman hesaplamaları yapan yardımcı sınıf
* @author Tarık Toplu tarik.toplu@sakarya.edu.tr
* @since 12.04.2025
* <p> 
* Gezegenler arası yolculuk sürelerini ve tarih hesaplamalarını yapar
* </p> 
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeCalculation {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    
    /**
     * Tam yolculuk tarihini hesaplar
     * @param departurePlanetDate Kalkış gezegenindeki mevcut tarih
     * @param spacecraftDepartureDate Uzay aracının kalkış tarihi
     * @param departureHoursInDay Kalkış gezegenindeki bir günün saat sayısı
     * @param distanceInHours İki gezegen arası mesafe (saat)
     * @param destinationCurrentDate Varış gezegenindeki mevcut tarih
     * @param destinationHoursInDay Varış gezegenindeki bir günün saat sayısı
     * @return Tahmini varış tarihi
     * @throws ParseException Tarih parse hatası
     */

    public static String calculateCompleteJourneyDate(String departurePlanetDate,
                                                      String spacecraftDepartureDate,
                                                      int departureHoursInDay,
                                                      double distanceInHours,
                                                      String destinationCurrentDate,
                                                      int destinationHoursInDay) throws ParseException {
        Date destDate = DATE_FORMAT.parse(destinationCurrentDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(destDate);

        int waitingHours = calculateHoursBetweenDates(departurePlanetDate, spacecraftDepartureDate, departureHoursInDay);
        int totalJourneyHours = waitingHours + (int)Math.floor(distanceInHours);
        int daysToAdd = totalJourneyHours / destinationHoursInDay;

        cal.add(Calendar.DAY_OF_MONTH, daysToAdd);
        return DATE_FORMAT.format(cal.getTime());
    }
    
    /**
     * İki tarih arasındaki saat farkını hesaplar
     * @param startDate Başlangıç tarihi
     * @param endDate Bitiş tarihi
     * @param hoursInDay Bir gündeki saat sayısı
     * @return İki tarih arası saat farkı
     * @throws ParseException Tarih parse hatası
     */

    public static int calculateHoursBetweenDates(String startDate,
                                                  String endDate,
                                                  int hoursInDay) throws ParseException {
        Date d1 = DATE_FORMAT.parse(startDate);
        Date d2 = DATE_FORMAT.parse(endDate);
        long diff = Math.abs(d2.getTime() - d1.getTime());
        long days = diff / (24 * 60 * 60 * 1000);
        return (int)(days * hoursInDay);
    }
}