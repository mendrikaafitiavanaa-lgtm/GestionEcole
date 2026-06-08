package lan.tovo.note;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GestionDates {
	public static String dateEnChaineFR(Date laDate)
	{
		Locale locale = Locale.FRANCE;
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy", locale);
		String date = dateFormat.format(laDate);
		return date;
	}
	
	public static String chaineDateFRversEN(String dateFR){
		String dateEn = "";
		String vAnneEN = dateFR.substring(6,10);
		String vMoisEN = dateFR.substring(3,5);
		String vJourEN = dateFR.substring(0, 2);
		dateEn = vAnneEN + "-" + vMoisEN + "-" + vJourEN;
		return dateEn;
	}
	
	public static Date dateJavaEnDateSQL(Date laDate) {
		Date dateSql = new java.sql.Date(laDate.getTime());
		return dateSql;	
	}

}
