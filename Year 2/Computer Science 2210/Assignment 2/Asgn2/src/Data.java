/***
 * This class represents the records that will be stored in the objects of class Dictionary.java.
 * @author Yeonsil Choi (251126837)
 *
 */

public class Data {
	
	//instance variables
	private String key;
	private int score;
	private int level;


	/***
	 * The constructor for the class
	 * @param key
	 * @param score
	 * @param level
	 */
	public Data(String key, int score, int level) {
		this.key = key;
		this.score = score;
		this.level = level;
	}
	
	/***
	 * 
	 * @return the string stored in this Data object
	 */
	public String getKey() {
		return this.key;
	}

	/***
	 * 
	 * @return the first integer stored in this Data object
	 */
	public int getScore() {
		return this.score;
	}

	/***
	 * 
	 * @return the second integer stored in this Data object
	 */
	public int getLevel() {
		return this.level;
	}

}
