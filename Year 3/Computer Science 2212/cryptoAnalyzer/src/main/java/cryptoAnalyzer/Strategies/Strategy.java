package cryptoAnalyzer.Strategies;

import cryptoAnalyzer.selection.*;

/**
 * This abstract parent class is represent class of strategy
 * @author gracezhu
 *
 */
public abstract class Strategy {

	/*
	 * result
	 */
	private Result res;
	
	
	/**
	 * perform calculation of strategy
	 * @param sel user selection
	 * @return result
	 */
	protected Result perform(Selection sel) {
		return res;
	}
}
