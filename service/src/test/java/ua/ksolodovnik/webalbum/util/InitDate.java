package ua.ksolodovnik.webalbum.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Setting the date.
 * It is used for testing.
 * @author ksolodovnik
 */
public class InitDate {

    public static Date setDate(){
        Date myDate;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 9);
        cal.set(Calendar.DATE, 20);
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.HOUR,13);
        cal.set(Calendar.MINUTE,45);
        cal.set(Calendar.SECOND,52);
        myDate = cal.getTime();
        return myDate;
    }
}
