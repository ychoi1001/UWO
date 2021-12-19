package cryptoAnalyzer.Viewers;

import cryptoAnalyzer.Strategies.Result;
import cryptoAnalyzer.selection.*;

/**
 * This class represent abstract parent class for viewers
 * @author gracezhu
 *
 */
public abstract class Viewer {
	
	/**
	 * Constructor to initialize viewer
	 * @param sel user selection
	 * @param res result
	 */
	protected Viewer(Selection sel, Result res) {

	}
	
	/**
	 * to generate chart
	 */
	protected void generateChart() {
		
	}
	
	/**
	 * to update result
	 * @param newSel new user selection
	 * @param newRes new result
	 */
	protected void update(Selection newSel, Result newRes) {
		
	}
}
