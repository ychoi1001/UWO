package cryptoAnalyzer.Viewers;

import cryptoAnalyzer.Strategies.Result;
import cryptoAnalyzer.selection.*;

/**
 * This class represent viewer control
 * @author gracezhu
 *
 */
public class ViewerModel {
	
	/*
	 * array of observers of type Viewer
	 */
	private Viewer[] observers;
	
	/**
	 * Constructor to initialize viewer control
	 * @param sel user selection
	 * @param res result
	 */
	public ViewerModel(Selection sel, Result res) {
		//initialize viewers
		TableViewer tv = new TableViewer(sel,res);
		LineChartViewer lv = new LineChartViewer(sel,res);
		ScatterChartViewer cv = new ScatterChartViewer(sel, res);
		BarChartViewer bv = new BarChartViewer(sel,res);
		
		Viewer[] viewer = {tv,lv,cv,bv};
		observers = viewer;
	}
	
	/**
	 * notify viewers and update them
	 * @param sel user selection
	 * @param res result
	 */
	public void notify(Selection sel, Result res) {
		for(Viewer v: observers) {
			v.update(sel, res);
		}
	}
	
	/**
	 * create chart by calling its observers
	 */
	public void createCharts() {
		for(Viewer v: observers) {
			v.generateChart();
		}
	}
}
