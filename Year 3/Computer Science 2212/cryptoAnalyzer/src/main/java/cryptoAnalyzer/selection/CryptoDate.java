package cryptoAnalyzer.selection;

import java.util.Calendar;
import java.util.Date;

/***
 * 
 * @author Yeonsil Choi
 *
 */
public class CryptoDate {

	private int day;
	private int month;
	private int year;
	private String[] months= {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};
	

	/***
	 * constructor
	 * @param d
	 * @param m
	 * @param y
	 */
	public CryptoDate(int d, int m, int y) {
		day = d;
		month = m;
		year = y;
	}

 	/***
	 * getting day
	 * @return this.day
	 */
	public int getDay() {
		return day;
	}
	
	/***
	 * setting day
	 * @param newDay
	 */
	public void setDay(int newDay) {
		day = newDay;
	}
	
	/***
	 * getting month
	 * @return this.month
	 */
	public int getMonth() {
		return month;
	}
	
	/***
	 * setting month
	 * @param newMon
	 */
	public void setMon(int newMon) {
		month = newMon;
	}
	
	/***
	 * getting year
	 * @return this.year
	 */
	public int getYear() {
		return year;
	}
	
	/***
	 * setting year
	 * @param newYear
	 */
	public void setYear(int newYear) {
		year = newYear;
	}
	
	/***
	 * to print date; in form of 01-Sept
	 * @return date
	 */
	public String printString() {
		if(day<10) {
			return "0"+day+"-"+months[month-1];
		}else {
			return day+"-"+months[month-1];
		}
	}

	/***
	 * to print date; in form of 13-09-2021
	 * @return date
	 */
	public String printInt() {
		if(month<10) {
			if(day<10) {
				return "0"+day+"-0"+month+"-"+year;
			}else {
				return day+"-0"+month+"-"+year;
			}
		}else {
			if(day<10) {
				return "0"+day+"-"+month+"-"+year;
			}else {
				return day+"-"+month+"-"+year;
			}
		}
	}
	
}
