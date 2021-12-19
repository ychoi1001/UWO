package cryptoAnalyzer.Strategies;

import cryptoAnalyzer.selection.*;

/**
 * This class represent the result of calculations
 * @author gracezhu
 *
 */

public class Result {

	/*
	 * table of result in form of
	 * |Cryptocurrency	|Date1		|Date2		|Date3		|
	 * |crypto1			|value11	|value12	|value13	|
	 * |crypto2			|value21	|value22	|value23	|
	 */
	private Object[][] res;

	/*
	 * number of rows of table of result
	 */
	private int numRow;
	
	/*
	 * number of columns of table of result
	 */
	private int numCol;
	
	/**
	 * constructor to initialize class
	 * @param numCrypto number of cryptos
	 * @param numValues number of dates
	 */
	public Result(int numCrypto, int numDates) {
		numRow=numCrypto+1;
		numCol=numDates+1;
		res = new Object[numRow][numCol];
		res[0][0] = "Cryptocurrency";
		
	}
	
	/**
	 * set cryptocurrency name
	 * @param name name of crypto
	 * @param row the row crypto located
	 */
	public void setCryptoName(String name, int row) {
		res[row][0] = name;
	}
	
	/**
	 * set value of calculations
	 * @param value the result of calculation
	 * @param row the row the value is located
	 * @param col the column the value if located
	 */
	public void setValues(double value, int row, int col) {
		res[row][col]=value;
	}
	
	/**
	 * set dates
	 * @param date date
	 * @param col the column the date is located
	 */
	public void setDate(CryptoDate date, int col) {
		res[0][col] = date;
	}
	
	/**
	 * get table of result
	 * @return table of result
	 */
	public Object[][] getResult(){
		return res;
	}
	
	/**
	 * get number of rows
	 * @return number of rows
	 */
	public int getRow() {
		return numRow;
	}
	
	/**
	 * get number of columns
	 * @return number of columns
	 */
	public int getCol() {
		return numCol;
	}
}
