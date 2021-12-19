package cryptoAnalyzer.Login;

/**
 * This class represent the login proxy
 * @author gracezhu
 *
 */
public class LoginProxy {
	/*
	 * actual user login
	 */
	private UserLogin ulogin;
	
	/**
	 * This function is to call the login of actual subject
	 */
	public void request() {
		if(ulogin == null) {
			//Generate login database using the Login Server class
			LoginServer loginDatabase = new LoginServer();
			
			//Create the login panel itself with the given database being passed along
			UserLogin loginPanel = new UserLogin(loginDatabase.getLoginInfo());
		}
	}

}
