package cryptoAnalyzer.gui;

import javax.swing.JFrame;

import cryptoAnalyzer.Login.LoginProxy;

/**
 * This class is main function of the software 
 * @author gracezhu
 *
 */
public class CryptoAnalyzer {

	public static void main(String[] args) {
		LoginProxy lp = new LoginProxy();
		lp.request();
	}
}
