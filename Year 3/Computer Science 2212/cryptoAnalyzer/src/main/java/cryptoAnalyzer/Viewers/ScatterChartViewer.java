package cryptoAnalyzer.Viewers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import cryptoAnalyzer.Strategies.Result;
import cryptoAnalyzer.selection.*;
import cryptoAnalyzer.gui.MainUINew;

/**
 * This class represent scatter chart viewer
 * @author gracezhu
 *
 */
public class ScatterChartViewer extends Viewer{

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
	 * Constructor to initialize scatter chart viewer
	 * @param sel user selection
	 * @param res result
	 */
	protected ScatterChartViewer(Selection sel, Result res) {
		super(sel, res);
		result = res.getResult();
		select = sel;
		row = res.getRow();
		col = res.getCol();
	}
	
	/**
	 * update the viewer
	 * @param newSel new user selection
	 * @param newRes new result
	 */
	protected void update(Selection newSel, Result newRes) {
		//super(interval, res);
		result = newRes.getResult();
		select = newSel;
		row = newRes.getRow();
		col = newRes.getCol();
	}
	
	/**
	 * generate the scatter chart
	 */
	protected void generateChart() {
			
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		for(int i=1; i<row; i++) {
			String s = result[i][0]+" - "+select.getFreq().getInterval();
			TimeSeries series = new TimeSeries(s);
			for(int j=1; j<col; j++) {
				CryptoDate d = (CryptoDate)result[0][j];
				series.add(new Day(d.getDay(),d.getMonth(),d.getYear()),(double)result[i][j]);
				
			}
			dataset.addSeries(series);
		}
		
		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

		plot.setDataset(0, dataset);
		plot.setRenderer(0, itemrenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		if(select.getAnalysisType().equals("Price")) {
			plot.setRangeAxis(new LogAxis("Price(CAD)"));
		}else {
			plot.setRangeAxis(new LogAxis(select.getAnalysisType()));
		}
		
		JFreeChart scatterChart = new JFreeChart(select.getFreq().getInterval()+" "+select.getAnalysisType()+" Scatter Chart",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUINew.getInstance().updateStats(chartPanel);
	}
	
}
