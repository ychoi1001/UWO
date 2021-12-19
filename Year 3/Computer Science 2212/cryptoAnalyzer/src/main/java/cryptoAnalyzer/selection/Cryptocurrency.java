package cryptoAnalyzer.selection;

/***
 * 
 * @author Yeonsil Choi
 *
 */
public class Cryptocurrency {
	
	private String name;
	
	/***
	 * constructor
	 * @param n
	 */
	public Cryptocurrency(String n) {
		this.name = n;
	}
	
	/***
	 * getting cryptocurrency name
	 * @return this.name
	 */
	public String getName() {
		return this.name;		
	}
	
	/***
	 * setting new cryptocurrency name
	 * @param newName
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/***
	 * to check if names are same
	 * @param c
	 * @return boolean value
	 */
	public Boolean isEqual(Cryptocurrency c) {
		if(this.getName().equals(c.getName())) {
			return true;
		}else {
			return false;
		}
	}
	
}
