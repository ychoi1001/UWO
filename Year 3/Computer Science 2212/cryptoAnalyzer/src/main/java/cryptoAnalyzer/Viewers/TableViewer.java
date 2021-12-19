package cryptoAnalyzer.Viewers;

import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import cryptoAnalyzer.Strategies.Result;
import cryptoAnalyzer.selection.*;
import cryptoAnalyzer.gui.MainUINew;

/**
 * This class represent table viewer
 * @author gracezhu
 *
 */
public class TableViewer extends Viewer{

	/*
	 * table of result
	 */
	private Object[][] result;
	
	/*
	 * user selection
	 */
	private Selection select;
	
	/*
	 * number of rows in table of result
	 */
	private int row;
	
	/*
	 * number of columns in table of result
	 */
	private int col;
	
	/**
	 * Constructor to initialize table viewer
	 * @param sel user selection
	 * @param res result
	 */
	protected TableViewer(Selection sel, Result res) {
		super(sel, res);
		result = res.getResult();
		select = sel;
		row = res.getRow();
		col = res.getCol();
	}
	
	/**
	 * update table viewer
	 * @param newSel new user selection
	 * @param newRes new result
	 */
	protected void update(Selection newSel, Result newRes) {
		result = newRes.getResult();
		select = newSel;
		row = newRes.getRow();
		col = newRes.getCol();
	}
	
	/**
	 * generate table
	 */
	protected void generateChart() {
			
		//has format like this: columnNames = {"Cryptocurrency","13-Sept","14-Sept","15-Sept","16-Sept","17-Sept"};
		Object[] columnNames = new Object[col];
		for(int i=0; i<col; i++) {
			if(i==0) {
				columnNames[i] = result[0][i];
			}else {
				columnNames[i] = ((CryptoDate)(result[0][i])).printString();
			}
			
		}		
		
		//has format like this: 
		//Object[][] data = {{"Bitcoin", "50368.67", "51552.05", "47228.30", "45263.90", "46955.41"},
		//                   {"Ethereum", "3912.28", "3927.27", "3460.48", "3486.09", "3550.29"},
		//                   {"Cardano", "2.87", "2.84", "2.41", "2.43", "2.59"}}

		Object[][] data = new Object[row-1][col];
		for(int i=1; i<row; i++) {
			for(int j=0; j<col; j++) {
				if(j==0) {
					data[i-1][j] = result[i][j].toString();
				}else {
					
					double db = (double) result[i][j];
					String s = "";
					if(db>=1E12) {
						db = (double) Math.round(db/(1E12)*10000);
						s = s + db/10000 + "T";
						//System.out.println(s);
					}else if(db>=1E9) {
						db =  (double) Math.round(db/(1E9)*10000);
						s = s + db/10000 + "B";
					}else if(db>=1E6) {
						db = (double) Math.round(db/(1E6)*10000);
						s = s + db/10000 + "M";
					}else {
						//data[i-1][j] = result[i][j].toString();
						s = s + (double) Math.round(db*10000)/10000;
					}
					data[i-1][j] = s;

				}
			}
		}
		

		JTable table = new JTable(data, columnNames);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
               select.getFreq().getInterval()+" "+select.getAnalysisType()+" Summary Table",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
	
		
		scrollPane.setPreferredSize(new Dimension(600, 300));
		table.setFillsViewportHeight(true);;
		
		MainUINew.getInstance().updateStats(scrollPane);
	}
	
}
