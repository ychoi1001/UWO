package cryptoAnalyzer.Strategies;

import javax.swing.JOptionPane;

import cryptoAnalyzer.Viewers.BarChartViewer;
//import cryptoAnalyzer.Viewers.DataVisualizationCreator;
import cryptoAnalyzer.Viewers.LineChartViewer;
import cryptoAnalyzer.Viewers.ScatterChartViewer;
import cryptoAnalyzer.Viewers.TableViewer;
import cryptoAnalyzer.Viewers.Viewer;
import cryptoAnalyzer.Viewers.ViewerModel;
import cryptoAnalyzer.selection.Selection;

/**
 * This class represent analysis server using facade design pattern
 * @author gracezhu
 *
 */
public class AnalysisServerFacade {
	
	/**
	 * perform analysis based on given user selection
	 * @param sel user selection
	 */
	public void performAnalysis(Selection sel) {
		try {
			if(checkSelect(sel).equals("")) { //if all requirements are selected
				
				//perform calculation and get result
				AnalysisFactory aFac = new AnalysisFactory();
				Strategy strategy = aFac.create(sel.getAnalysisType());
				Result res = strategy.perform(sel);
				
				//display charts
				ViewerModel vControl = new ViewerModel(sel, res);
				vControl.notify(sel, res);
				vControl.createCharts();
				
			}else {
				JOptionPane.showMessageDialog(null, checkSelect(sel));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * check if all configurations are selected
	 * @param sel user selection
	 * @return string of message of missing configuration; if no missing configuration, string is empty
	 */
	private String checkSelect(Selection sel) {
		String str = "";
		if(sel.getNames()==null) {
			str = "Cryptocurrencies are not selected.";

		}
		else if(sel.getStartDate()==null) {

			str = "Start date is not selected.";
		}
		else if(sel.getFreq()==null) {
			str = "Interval is not selected.";
		}
		else if(sel.getAnalysisType()==null) {

			str = "Analysis type is not selected.";
		}
		return str;
	}
	
}
