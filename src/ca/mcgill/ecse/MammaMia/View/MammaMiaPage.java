package ca.mcgill.ecse.MammaMia.View;

import java.awt.Color;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse.MammaMia.Controller.InvalidInputException;
import ca.mcgill.ecse.MammaMia.Controller.MammaMiaController;
import ca.mcgill.ecse.MammaMia.Model.MammaMia;

public class MammaMiaPage extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5686840125055340758L;

	//UI
	private JLabel errorMessage;
	private JTextField customerNameTextField;
	private JLabel customerNameLabel;
	private JTextField customerEmailTextField;
	private JLabel customerEmailLabel;
	private JTextField customerAddressTextField;
	private JLabel customerAddressLabel;
	private JFormattedTextField customerPhoneTextField;
	private JLabel customerPhoneLabel;
	
	//data
	private String error = null;
	private int selectedCustomer = -1;
	
	
	
	public MammaMiaPage(){
		initComponents();
		refreshData();
	}
	
	private void initComponents(){
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		
	}
	
	private void refreshData(){
		MammaMia mm = MammaMia.getInstance();
		errorMessage.setText(error);
		if(error == null || error.length() == 0){
			
		}
		else{
			System.out.println("THIS MESSAGE SHOULD NEVER APPEAR");
		}
		pack();
	}
	
	private void createCustomerButtonActionPerformed(java.awt.event.ActionEvent evt){
		MammaMiaController mm = new MammaMiaController();
		error = null;
		try{
			mm.createCustomer(customerNameTextField.getText(), Long.parseLong(customerPhoneTextField.getText()), customerEmailTextField.getText(), customerAddressTextField.getText());
		}
		catch(InvalidInputException iie){
			error = iie.getMessage();
		}
		refreshData();
	}
	
	private void createOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
		
	}
	
	private void deleteOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
	
	}
	
	private void createPizzaButtonActionPerformed(java.awt.event.ActionEvent evt){
		
		
		refreshData();
	}
	
}