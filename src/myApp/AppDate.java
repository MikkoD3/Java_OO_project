package myApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


//Short Class in order to get The time and date for the user

public class AppDate {


		public static final String reportDate1 = null;
		//Format the date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		//Stringify the date in order to get it to JLabel
		String reportDate = dateFormat.format(date);
		
	
	
}

