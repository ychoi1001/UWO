/*
 Pavle Alurovic
 User Login Class - Main UI subsystem
 */

package cryptoAnalyzer.Login;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

import cryptoAnalyzer.gui.MainUINew;
import cryptoAnalyzer.gui.*;

public class UserLogin implements ActionListener{
	
	//Generate new frame
	JFrame frame = new JFrame();
	
	//Generate user name and password InputFields in order for user to enter credentials
	JTextField userNameInputField = new JTextField();
	JPasswordField userPasswordInputField = new JPasswordField();
	
	//Label each respective input InputField
	JLabel userNameLabel = new JLabel("Username:");
	JLabel userPasswordLabel = new JLabel("Password:");
	
	//Generate Submit! button
	JButton submitButton = new JButton("Submit!");
	
	//Generate area for messages to be displayed to the user
	JLabel alertLabel = new JLabel();
	
	HashMap<String,String> logininfo = new HashMap<String,String>();
	
	UserLogin(HashMap<String,String> loginInfoOriginal){
		//Bring in the login information (HashMap) we generated after reading the Login DB
		logininfo = loginInfoOriginal;
		
		//Set up boundaries for each element that is in the frame
		
		//User name and password bounds
		userNameLabel.setBounds(50,25,75,25);
		userPasswordLabel.setBounds(50,75,75,25);

		//User name and password input field boundaries
		userNameInputField.setBounds(125,25,200,25);
		userPasswordInputField.setBounds(125,75,200,25);
		
		//Submit button boundaries
		submitButton.setBounds(125,125,100,25);
		
		//Allow submit button to be focused
		submitButton.setFocusable(false);
		
		//Add action listener for when the submit button is pressed
		submitButton.addActionListener(this);
		
		//Set boundaries for the alert area
		alertLabel.setBounds(125,175,250,35);
		
		//Make the alert larger so the user can see it more easily
		alertLabel.setFont(new Font(null,Font.PLAIN,27));
		
		//Display the above elements by adding them to the frame
		frame.add(userNameLabel);
		frame.add(userPasswordLabel);
		frame.add(alertLabel);
		frame.add(userNameInputField);
		frame.add(userPasswordInputField);
		frame.add(submitButton);
		
		//Allow the program to close with the use of the 'X' button at the top right of the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set the size of the whole frame
		frame.setSize(400,250);
		
		//Set the title of the frame
		frame.setTitle("Login");
		
		//Set layout manager to null
		frame.setLayout(null);
		
		//Make the frame visible
		frame.setVisible(true);
	}

	//Error message method
	public void errorAlert (String message) {
		//Set the message color to red
		alertLabel.setForeground(Color.red);
		//Set the alert to display the correct error message
		alertLabel.setText(message);
		
		//Create a timer
	    Timer timer = new Timer();
	    //Create a simple timer task that will terminate the program
		TimerTask terminate = new TimerTask() {
			@Override
			public void run() {
				System.exit(0);
			}
			
		};
		
		//Schedule the timer to run the terminate task after 2.5 seconds (2500 milliseconds)
	    timer.schedule(terminate, 2500);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//Once the user has clicked the Submit! button
		if(e.getSource()==submitButton) {
			
			//Get the text the user has entered in order to use it later
			String userName = userNameInputField.getText();
			String password = String.valueOf(userPasswordInputField.getPassword());
			
			//If the user name is in the database
			if(logininfo.containsKey(userName)) {
				
				//If the password for the given user name matches
				if(logininfo.get(userName).equals(password)) {
					//Set the text to the correct message and change the color to green
					alertLabel.setForeground(Color.green);
					alertLabel.setText("Login successful");
					
					//Close the login frame
					frame.dispose();
					
					//Open Main UI
					JFrame frame = MainUINew.getInstance();
					frame.setSize(900, 600);
					frame.pack();
					frame.setVisible(true);
				}
				//Else, (if the password does not match the given user name
				else {
					//call error alert with the correct message as a parameter
					errorAlert("Wrong password");
				}

			}
			//Else, (if the user name is not in the database)
			else {
				//call error alert with the correct message as a parameter
				errorAlert("User does not exist");
			}
		}
	}

}