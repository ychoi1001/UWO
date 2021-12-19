/*
 Pavle Alurovic
 Login Server Class - Server subsystem
 */

package cryptoAnalyzer.Login;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class LoginServer {

	//Generate hash map to hold login info from the Login DB text file
	HashMap<String,String> loginHashMap = new HashMap<String,String>();
	
	LoginServer(){
		//Debugging -- ADMIN account in case DB fails
		loginHashMap.put("admin","admin");
		
		try {
			//Point to the Login DB text file and generate a scanner for it
			File loginDB = new File("./Login DB.txt");
			Scanner scan = new Scanner(loginDB);
			
			//While loop that scans through the provided text file until there are no new lines to read
			while(scan.hasNextLine()) {
				//Scan the next line in the given text file
				String line = scan.nextLine();
				
				//Split the line from the credentials into 2, user name and password
				String[] Credentials = line.split(",");
				String username = Credentials[0];
				String password = Credentials[1];
				
				//Add the user name and password to login hash map
				loginHashMap.put(username,password);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	//Getter method to return the login Hash Map we have generated
	public HashMap getLoginInfo(){
		return loginHashMap;
	}
}
