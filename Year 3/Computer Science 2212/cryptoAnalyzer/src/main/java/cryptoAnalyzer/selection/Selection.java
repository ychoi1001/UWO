package cryptoAnalyzer.selection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
/***
 * 
 * @author Yeonsil Choi; Nicole Ni; Grace Zhu
 *
 */
public class Selection {
	
	private String[] cryptoName = null;
	private CryptoDate[] dates = null;
	private Frequency freq = null;
	private String analysisType = null;
	private Date startDate = null;
	private static Selection instance = null;
	
	/***
	 * Singleton
	 * @return instance
	 */
	public static Selection getInstance() {
		if (instance == null) {
			instance = new Selection();
		}
		return instance;
	}

	/***
	 * to get frequency
	 * @return freq
	 */
	public Frequency getFreq() {
		return freq;	
	}
	
	/***
	 * to set frequency
	 * @param f
	 */
	public void setFreq(Frequency f) {
		
		freq = f;
	}
	

	/***
	 * method for getting analysis type
	 * @return analysisType
	 */
	public String getAnalysisType() {
		return analysisType;
		
	}
	
	/***
	 * method for setting analysisType
	 * @param at
	 */
	public void setAnalysisType(String at) {
		analysisType = at;
	}
	

	/***
	 * to get list of Cryptocurrency names
	 * @return cryptoName
	 */
	public String[] getNames() {

		return cryptoName;
	}
	
	/***
	 * to add Cryptocurrency into array
	 * @param crypto
	 */
	public void addCrypto(Cryptocurrency crypto) {
		try {
				if(checkAvailability(crypto.getName())) { //check if available
					String name = crypto.getName();
					if(cryptoName==null) { //empty list
						cryptoName = new String[]{crypto.getName()};
					}else {
						String[] newArr = new String[cryptoName.length+1];
						Boolean duplicate = false;
						//check duplicate
						for(int i=0; i<cryptoName.length; i++) {
							if(cryptoName[i].equals(name)) {
								duplicate = true;
							}else {
								newArr[i] = cryptoName[i];
							}
						}
						if(duplicate) {
							//do nothing
						}else {
							newArr[cryptoName.length] = name;
							cryptoName = newArr;
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Cryptocurrency is not available");
				}
			}catch(IOException e) {
				JOptionPane.showMessageDialog(null, "Unavailable list is not found");
			}
	}
	
	/***
	 * to remove cryptocurrency from the list
	 * @param crypto
	 */
	public void removeCrypto(Cryptocurrency crypto) {
		
		String name = crypto.getName();
		if(cryptoName==null) {
			//do nothing
		}else {
			//check if crypto is in list
			Boolean notInList = true;
			for(int i=0; i<cryptoName.length; i++) {
				if(cryptoName[i].equals(name)) {
					notInList = false;
					cryptoName[i]=null;
				}
			}
			if(notInList) {
				//do nothing
			}else {
				//if only one crypto
				if(cryptoName.length==1) {
					cryptoName = null;
				}else {
					//remove the crypto
					String[] newArr = new String[cryptoName.length-1];
					int newIndex = 0;
					for(int i=0; i<cryptoName.length; i++) {
						if(cryptoName[i]!=null) {
							
							newArr[newIndex]=cryptoName[i];
							newIndex++;
						}
					}
					cryptoName = newArr;
				}
			}
		}
	}
	
	/***
	 * to set start date
	 * @param d
	 */
	public void setStartDate(Date d) {
		Date curDate = Calendar.getInstance().getTime();
		
		if(d.after(curDate)) {
			JOptionPane.showMessageDialog(null, "Select date before current date");
		}else {
			startDate = d;
		}
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	/***
	 * to get date list
	 * @return dates
	 */
	public CryptoDate[] getDateList() {

		dates = null;
		Date curDate = Calendar.getInstance().getTime();
		calculateDate(startDate, curDate);

		return dates;
	}
	
	/***
	 * to calculate dates between two dates
	 * @param sDate
	 * @param curDate
	 */
	private void calculateDate(Date sDate, Date curDate) {
		dates = null;
		Calendar start = Calendar.getInstance();
		start.setTime(sDate);
		
		//while start date is before current date
		while(!(start.getTime()).after(curDate)) {
			CryptoDate cd = new CryptoDate(start.get(Calendar.DATE), start.get(Calendar.MONTH)+1, start.get(Calendar.YEAR));
			addDate(cd);
			
			//add date according to interval
			if(freq.getInterval().equals("Daily")) {
				start.add(Calendar.DATE, 1);
			}else if(freq.getInterval().equals("Weekly")) {
				start.add(Calendar.DATE, 7);
			}else if(freq.getInterval().equals("Monthly")) {
				start.add(Calendar.MONTH, 1);
			}else {
				start.add(Calendar.YEAR, 1);
			}
			
		}
		
	}
	
	/***
	 * to add date into crypto date list
	 * @param cd
	 */
	private void addDate(CryptoDate cd) {

		if(dates==null) {
			dates = new CryptoDate[] {cd};
		}else {
			CryptoDate[] newArr = new CryptoDate[dates.length+1];
			
			for(int i=0; i<dates.length; i++) {
				newArr[i] = dates[i];
			}
			newArr[dates.length] = cd;
			dates = newArr;
		}
	}
	
	/***
	 * This is a class that checks if the selected crypto is allowed to be fetched
	 * @param c
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean checkAvailability(String c) throws FileNotFoundException {
		BufferedReader inFile = new BufferedReader(new FileReader("notavailablecrypto.txt"));
		try {
			StringBuilder sb = new StringBuilder();
			String line = inFile.readLine();
			while(line != null) { // while the end of the file hasn't been reached
				if (line.equals(c)) {
					return false; // the selected crypto is not allowed to be fetched
				}
				line = inFile.readLine();
			}
			inFile.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	
	
}
