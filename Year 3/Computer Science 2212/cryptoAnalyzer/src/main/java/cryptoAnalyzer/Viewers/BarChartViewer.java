package cryptoAnalyzer.Viewers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

import cryptoAnalyzer.Strategies.Result;
import cryptoAnalyzer.selection.*;
import cryptoAnalyzer.gui.MainUINew;

/**
 * This class represent bar chart viewer
 * @author gracezhu
 *
 */
public class BarChartViewer extends Viewer{
	
	/*
	 * table of results
	 */
	private Object[][] result;
	/*
	 * user selection
	 */
	private Selection select;
	/*
	 * number of rows in table of results
	 */
	private int row;
	/*
	 * number of columns in table of results
	 */
	private int col;
	
	/**
	 * Constructor to initialize private variables
	 * @param sel user selection
	 * @param res result of strategy
	 */
	protected BarChartViewer(Selection sel, Result res) {
		super(sel, res);
		result = res.getResult();
		select = sel;
		row = res.getRow();
		col = res.getCol();
	}
	
	/**
	 * Method to update user selection and result
	 * @param newSel new selection
	 * @param newRes new result
	 */
	protected void update(Selection newSel, Result newRes) {
		result = newRes.getResult();
		select = newSel;
		row = newRes.getRow();
		col = newRes.getCol();
	}
	
	/**
	 * Method to generate bar chart
	 */
	protected void generateChart() {
			
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i=1; i<row; i++) {
			for(int j=1; j<col; j++) {
				CryptoDate d = (CryptoDate)result[0][j];
				dataset.setValue((double)result[i][j], result[i][0].toString(), d.printString());
			}
		}
		
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Date");
		plot.setDomainAxis(domainAxis);
		if(select.getAnalysisType().equals("Price")) {
			LogAxis rangeAxis = new LogAxis("Price(CAD)");
			rangeAxis.setRange(new Range(1.0, 70000.0));
			plot.setRangeAxis(rangeAxis);
		}else {
			LogAxis rangeAxis = new LogAxis(select.getAnalysisType());
			rangeAxis.setRange(new Range(1.0, 70000.0));
			plot.setRangeAxis(rangeAxis);
		}
		
		JFreeChart barChart = new JFreeChart(select.getFreq().getInterval()+" "+select.getAnalysisType()+" Bar Chart", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUINew.getInstance().updateStats(chartPanel);
	}
	
}
