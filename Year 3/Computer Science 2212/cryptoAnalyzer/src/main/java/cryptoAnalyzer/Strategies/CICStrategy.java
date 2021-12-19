package cryptoAnalyzer.Strategies;

import cryptoAnalyzer.selection.CryptoDate;
import cryptoAnalyzer.selection.Selection;
import cryptoAnalyzer.utils.DataFetcher;

/**
 * This class represent coins in circulation strategy
 * @author gracezhu
 *
 */

public class CICStrategy extends Strategy{

	/**
	 * perform the calculation for coins in circulation
	 * @param sel user selections
	 * @return result of performance
	 */
	protected Result perform(Selection sel) {

		DataFetcher dFetcher = new DataFetcher(); 
		
		int numRow = sel.getNames().length; 
		int numCol = sel.getDateList().length;
		String[] cryptoList = sel.getNames(); //list of cryptocurrencies
		CryptoDate[] dateList = sel.getDateList(); //list of dates which data needs to be fetched
		
		Result res = new Result(numRow,numCol); //initialize result

		for(int i=0; i<res.getRow(); i++) {
			for(int j=0; j<res.getCol(); j++) {
				
				if(i==0) { 
					if(j!=0) {
						res.setDate(dateList[j-1], j); //first row stores dates
					}
				}else {
					if(j==0) {
						res.setCryptoName(cryptoList[i-1], i); //first column stores cryptocurrencies
					}else {
						//other rows and columns stores values of calculation
						String cName = ((String) res.getResult()[i][0]).toLowerCase(); //get name of crypto
						String cDate = ((CryptoDate) res.getResult()[0][j]).printInt(); //get date of crypto
						double marCap = dFetcher.getMarketCapForCoin(cName, cDate);
						double price = dFetcher.getPriceForCoin(cName, cDate);
						double value = marCap/price; //value of coins in circulation
						
						res.setValues(value, i, j); //add values to result
					}
				}
			}	
		}
		return res;			
	}	
	
}
