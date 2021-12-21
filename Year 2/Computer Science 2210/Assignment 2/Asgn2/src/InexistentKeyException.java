/***
 * The class implementing the types of exceptions thrown by the remove method
 * of class Dictionary
 * @author Yeonsil Choi (251126837)
 *
 */
public class InexistentKeyException extends Exception {
	public InexistentKeyException(String msg) {
		super(msg);
	}
}
