package cryptoAnalyzer.Strategies;

import cryptoAnalyzer.selection.*;
import cryptoAnalyzer.utils.DataFetcher;

/**
 * This class represent calculation for volume
 * @author gracezhu
 *
 */

public class VolumeStrategy extends Strategy{

	/**
	 * perform calculation for volume
	 * @param sel user selection
	 * @return result
	 */
	protected Result perform(Selection sel) {

		//initialization
		DataFetcher dFetcher = new DataFetcher();
		
		int numRow = sel.getNames().length;
		int numCol = sel.getDateList().length;
		String[] cryptoList = sel.getNames();
		CryptoDate[] dateList = sel.getDateList();
		
		Result res = new Result(numRow,numCol);

		//calculation
		for(int i=0; i<res.getRow(); i++) {
			for(int j=0; j<res.getCol(); j++) {
				if(i==0) {
					if(j!=0) {
						res.setDate(dateList[j-1], j);//first row is list of dates
					}
				}else {
					if(j==0) {
						res.setCryptoName(cryptoList[i-1], i); //first column is list of crypto
					}else {
						String cName = ((String) res.getResult()[i][0]).toLowerCase();
						String cDate = ((CryptoDate) res.getResult()[0][j]).printInt();
						double value = dFetcher.getVolumeForCoin(cName, cDate);
						
						value = Math.round(value*10000);
						res.setValues(value/10000, i, j);
					}
				}
			}	
		}
		return res;		
	}
	
}
