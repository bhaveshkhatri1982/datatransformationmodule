package ee.ria.datatransformationmodule.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import ee.ria.datatransformationmodule.data.BaseStationReport;

public class Utils {

	public static int parseInt(Object obj) {
		int numValue = 0;
		try {
			if (obj != null && obj.toString().trim().length() > 0) {
				numValue = Integer.parseInt(obj.toString().trim());
			}
		} catch (NumberFormatException nfe) {
			System.out.println("common.Utils.java ::Exception while converting a string value to integer");
			nfe.printStackTrace();
			return numValue;
		}
		return numValue;
	}

	public static Long parseLong(Object obj) {
		Long numValue = 0L;
		try {
			if (obj != null && obj.toString().trim().length() > 0) {
				numValue = Long.parseLong(getPlainString(obj.toString().trim()));
			}
		} catch (NumberFormatException nfe) {
			System.out.println("common.Utils.java ::Exception while converting a string value to Long");
			nfe.printStackTrace();
			return numValue;
		}
		return numValue;
	}

	public static double parseDouble(Object obj) {
		double numValue = 0.0;
		try {
			if (obj != null && obj.toString().trim().length() > 0) {
				numValue = Double.parseDouble(obj.toString().trim());
			}
		} catch (NumberFormatException nfe) {
			System.out.println("common.Utils.java ::Exception while converting a string value to integer");
			nfe.printStackTrace();
			return numValue;
		}
		return numValue;
	}

	public static String checkNullValueForString(Object obj) {
		return ((obj == null) ? "" : obj.toString().trim());
	}

	/**
	 * This method returns the value of the parameter to a zero value from null
	 * value
	 * 
	 * @param parameter
	 * String containing parameter
	 * @return String
	 */
	public static String checkNullValueForInt(Object obj) {
		String retVal = "0";
		if (obj == null) {
			retVal = "0";
		} else {
			retVal = obj.toString();
			if (retVal.length() == 0) {
				retVal = "0";
			}
		}
		return retVal;
	}

	public static String checkNullValueForDouble(Object obj) {

		String retVal = "0.00";
		if (obj == null) {
			retVal = "0.00";
		} else {
			retVal = obj.toString();
			if (retVal.length() == 0) {
				retVal = "0.00";
			}
		}
		return retVal;
	}

	public static String getPlainString(Object obj) {
		String retString = "";
		if (obj != null) {
			retString = obj.toString().trim();
			retString = retString.replaceAll(" ", "");
			retString = retString.replaceAll("_", "");
			retString = retString.replaceAll(",", "");
		}
		return retString;
	}
	
	public static String getPostgreCompatibleDate(Date date)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String strDate = format.format(date);
        return strDate;
	}
	
	public static LocalDateTime getParsedDate(String strDate)
	{
		DateTimeFormatter  format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDate = LocalDateTime.parse(strDate, format);
        return localDate;
	}

}
