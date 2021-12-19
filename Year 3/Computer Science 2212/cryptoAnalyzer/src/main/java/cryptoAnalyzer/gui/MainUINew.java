package cryptoAnalyzer.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import cryptoAnalyzer.Strategies.AnalysisFactory;
import cryptoAnalyzer.Strategies.AnalysisServerFacade;
import cryptoAnalyzer.Strategies.Result;
import cryptoAnalyzer.Strategies.Strategy;
import cryptoAnalyzer.selection.CryptoDate;
import cryptoAnalyzer.selection.Cryptocurrency;
import cryptoAnalyzer.selection.Frequency;
import cryptoAnalyzer.selection.Selection;
import cryptoAnalyzer.utils.AvailableCryptoList;
import cryptoAnalyzer.utils.DataVisualizationCreator;


/***
 * This is a class for main UI
 * @author Group 11
 *
 */
public class MainUINew extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static MainUINew instance;
	private JPanel stats, chartPanel, tablePanel;
	
	//a reference to a separate object in actual implementation
	private List<String> selectedList;
	private Selection select;
	
	private JTextArea selectedCryptoList;
	private JComboBox<String> cryptoList;


	public static MainUINew getInstance() {
		if (instance == null)
			instance = new MainUINew();

		return instance;
	}

	private MainUINew() {
		
		// Set window title
		super("Crypto Analysis Tool");

		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a cryptocurrency: ");
		String[] cryptoNames = AvailableCryptoList.getInstance().getAvailableCryptos();
		cryptoList = new JComboBox<String>(cryptoNames);
		
		selectedList = new ArrayList<>();
		select = Selection.getInstance();
		
		JButton addCrypto = new JButton("+");
		addCrypto.setActionCommand("add");
		addCrypto.addActionListener(this);
		
		JButton removeCrypto = new JButton("-");
		removeCrypto.setActionCommand("remove");
		removeCrypto.addActionListener(this);
		
		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(cryptoList);
		north.add(addCrypto);
		north.add(removeCrypto);
		

		// Set bottom bar
		JLabel from = new JLabel("From");
		UtilDateModel dateModel = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
		@SuppressWarnings("serial")
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new AbstractFormatter() {
			private String datePatern = "dd/MM/yyyy";

		    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePatern);

		    @Override
		    public Object stringToValue(String text) throws ParseException {

		        return dateFormatter.parseObject(text);
		    }

		    @Override
		    public String valueToString(Object value) throws ParseException {
		        if (value != null) {
		            Calendar cal = (Calendar) value;
		            return dateFormatter.format(cal.getTime());
		        }

		        return "";
		    }
		});
		datePicker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JDatePanelImpl picker = (JDatePanelImpl) event.getSource();
				Date selectedDate = (Date) picker.getModel().getValue();
				select.setStartDate(selectedDate);
			}
		});
		
		
		JButton refresh = new JButton("Refresh");
		refresh.setActionCommand("refresh");
		refresh.addActionListener(this);

		JLabel metricsLabel = new JLabel("        Metrics: ");

		Vector<String> metricsNames = new Vector<String>();
		metricsNames.add("Price");
		metricsNames.add("MarketCap");
		metricsNames.add("Volume");
		metricsNames.add("Coins in Circulation");
		metricsNames.add("Percent Change of Price");
		metricsNames.add("Percent Change of MarketCap");
		metricsNames.add("Percent Change of Volume");
		metricsNames.add("Percent Change of Coins in Circulation");
		JComboBox<String> metricsList = new JComboBox<String>(metricsNames);
		select.setAnalysisType(metricsList.getSelectedItem().toString());
		metricsList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedMetric = combo.getSelectedItem().toString();

				select.setAnalysisType(selectedMetric);
			}
		});
	
		JLabel intervalLabel = new JLabel("        Choose interval: ");

		Vector<String> intervalNames = new Vector<String>();
		intervalNames.add("Daily");
		intervalNames.add("Weekly");
		intervalNames.add("Monthly");
		intervalNames.add("Yearly");

		JComboBox<String> intervalList = new JComboBox<String>(intervalNames);
		select.setFreq(new Frequency(intervalList.getSelectedItem().toString()));

		intervalList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedInterval = combo.getSelectedItem().toString();
				
				Frequency f = new Frequency(selectedInterval.toString());
				select.setFreq(f);
				
			}
		});
		JPanel south = new JPanel();
		south.add(from);
		south.add(datePicker);
		
		south.add(metricsLabel);
		south.add(metricsList);

		south.add(intervalLabel);
		south.add(intervalList);
		south.add(refresh);

		
		JLabel selectedCryptoListLabel = new JLabel("List of selected cryptocurrencies: ");
		selectedCryptoList = new JTextArea(16, 10);
		JScrollPane selectedCryptoScrollPane = new JScrollPane(selectedCryptoList);
		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(selectedCryptoListLabel);
		east.add(selectedCryptoScrollPane);

		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(1250,650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));
		
		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
	}
	
	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		//System.out.println(command);
		if ("refresh".equals(command)) {
			stats.removeAll();
		
			Selection sel =  Selection.getInstance();

			AnalysisServerFacade server = new AnalysisServerFacade();
			server.performAnalysis(sel);
			
			
		} else if ("add".equals(command)) {
			try {
				if(select.checkAvailability(cryptoList.getSelectedItem().toString())) {
					selectedList.add(cryptoList.getSelectedItem().toString());
					String text = "";
					for (String crypto: selectedList)
						text += crypto + "\n";
					
					selectedCryptoList.setText(text);
					
					Cryptocurrency cc = new Cryptocurrency(cryptoList.getSelectedItem().toString());
					select.addCrypto(cc);
				}
				else {
					JOptionPane.showMessageDialog(null, "Cryptocurrency is not available");
				}
			}catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
		} else if ("remove".equals(command)) {
			selectedList.remove(cryptoList.getSelectedItem());
			String text = "";
			for (String crypto: selectedList)
				text += crypto + "\n";
			
			selectedCryptoList.setText(text);
			
			Cryptocurrency c = new Cryptocurrency(cryptoList.getSelectedItem().toString());
			select.removeCrypto(c);
		}
	}
	
}
