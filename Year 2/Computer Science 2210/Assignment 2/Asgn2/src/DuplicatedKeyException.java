/***
 * The class implementing the types of exceptions thrown by the put method
 * of class Dictionary
 * @author Yeonsil Choi (251126837)
 *
 */
public class DuplicatedKeyException extends Exception {
	public DuplicatedKeyException(String msg) {
		super(msg);
	}
}
