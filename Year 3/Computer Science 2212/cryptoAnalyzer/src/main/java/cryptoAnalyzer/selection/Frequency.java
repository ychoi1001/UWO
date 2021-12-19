package cryptoAnalyzer.selection;
/***
 * 
 * @author Yeonsil Choi
 *
 */
public class Frequency {
	
	public String freq;
	
	/***
	 * constructor
	 * @param f
	 */
	public Frequency(String f) {
		freq = f;
		
	}
	
	/***
	 * getting frequency interval
	 * @return this.freq
	 */
	public String getInterval() {
		return this.freq;
	}
	
	/***
	 * setting new frequency interval
	 * @param newFreq
	 */
	public void setFreq(String newFreq) {
		this.freq = newFreq;
	}
}
