package cryptoAnalyzer.selection;
/***
 * 
 * @author Yeonsil Choi
 *
 */
public class AnalysisType {
	
	private String type;
	
	/***
	 * constructor
	 * @param t
	 */
	public AnalysisType (String t) {
		this.type = t;
	}
	
	/***
	 * getting analysis type
	 * @return this.type;
	 */
	public String getType() {
		return this.type;
		
	}
	
	/****
	 * setting new analysis type
	 * @param newType
	 */
	public void setType(String newType) {
		this.type = newType;
	}
}
