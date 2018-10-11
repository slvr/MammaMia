package ca.mcgill.ecse.MammaMia.View;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse.MammaMia.Controller.InvalidInputException;
import ca.mcgill.ecse.MammaMia.Controller.MammaMiaController;
import ca.mcgill.ecse.MammaMia.Model.Customer;
import ca.mcgill.ecse.MammaMia.Model.MammaMia;
import ca.mcgill.ecse.MammaMia.Model.Menu;
import ca.mcgill.ecse.MammaMia.Model.Order;
import ca.mcgill.ecse.MammaMia.Model.OrderDetails;
import ca.mcgill.ecse.MammaMia.Model.Pizza;

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
	private JTextField customerPhoneTextField;
	private JLabel customerPhoneLabel;
	private JButton customerRegisterButton;
	private JComboBox<String> pizzaList;
	private JLabel pizzaCalories;
	private JLabel pizzaCaloriesInt;
	private JLabel pizzaExtrasLabel;
	private JLabel pizzaQuantity;
	private JTextField pizzaQuantityInt;
	private JButton pizzaAddButton;
	private JLabel pizzaPriceLabel;
	private JLabel pizzaPriceFloat;
	private JTable orderDetailsTable;
	private JLabel orderPriceLabel;
	private JLabel orderPriceFloat;
	private JButton orderCompleteButton;
	private JLabel orderCaloriesLabel;
	private JLabel orderCaloriesInt;
	private JTable pizzaExtrasTable;
	private JTextField extraPeppers;
	private JTextField extraMushrooms;
	
	//data
	private String error = null;
	private Customer selectedCustomer = null;
	private Order order = null;	
	
	public MammaMiaPage(){
		initComponents();
		refreshData();
	}
	
	private void initComponents(){
		//error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		//global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Mamma Mia Pizzeria!");
		
		//customer registration
		customerNameTextField = new JTextField();
		customerNameLabel = new JLabel("Name: ");
		customerEmailTextField = new JTextField();
		customerEmailLabel = new JLabel("Email Address: ");
		customerAddressTextField = new JTextField();
		customerAddressLabel = new JLabel("Address: ");
		customerPhoneTextField = new JTextField();
		customerPhoneLabel = new JLabel("Phone Number: ");		
		customerRegisterButton = new JButton("Register");
		customerRegisterButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createCustomerButtonActionPerformed(evt);
			}
		});
		
		//pizza creation
		pizzaList = new JComboBox<String>(new String[] {"Moon Cheese", "Veggie Tales", "Custom"});
		pizzaCalories = new JLabel("Calories: ");
		pizzaCaloriesInt = new JLabel();
		pizzaExtrasLabel = new JLabel("With Extra: ");
		extraPeppers = new JTextField();
		extraMushrooms = new JTextField();
		pizzaExtrasTable = new JTable(new Object[][]{{"Peppers", extraPeppers},{"Mushrooms", extraMushrooms}}, 
				new String[]{"Ingredient", "Quantity"});
		pizzaQuantity = new JLabel("Quantity");
		pizzaQuantityInt = new JTextField();
		pizzaPriceLabel = new JLabel();
		pizzaAddButton = new JButton("Add Pizza");
		pizzaAddButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createPizzaButtonActionPerformed(evt);
			}
		});
		
		//order details
		orderDetailsTable = new JTable(new Object[][] {{}}, new String[] {"Pizza Type", "Extras", "Remove?"});
		orderPriceLabel = new JLabel("Total Price: ");
		orderCaloriesLabel = new JLabel("Total Calories: ");
		orderPriceFloat = new JLabel();
		orderCaloriesInt = new JLabel();
		orderCompleteButton = new JButton("Order Complete");
		orderCompleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createOrderButtonActionPerformed(evt);
			}
		});
		
		//Layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(customerNameLabel)
								.addComponent(customerAddressLabel)
								.addComponent(customerPhoneLabel)
								.addComponent(customerEmailLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(customerNameTextField)
								.addComponent(customerAddressTextField)
								.addComponent(customerPhoneTextField)
								.addComponent(customerEmailTextField)
								.addComponent(pizzaList)
								.addComponent(pizzaExtrasLabel)
								.addComponent(pizzaExtrasTable)
								.addComponent(pizzaAddButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(customerRegisterButton)
								.addComponent(pizzaQuantity)
								.addComponent(pizzaCalories)
								.addComponent(pizzaPriceLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(pizzaQuantityInt)
								.addComponent(pizzaCaloriesInt)
								.addComponent(pizzaPriceFloat))
						.addGroup(layout.createParallelGroup()
								.addComponent(new JSeparator(SwingConstants.VERTICAL)))
						.addGroup(layout.createParallelGroup()
								.addComponent(orderDetailsTable)
								.addComponent(orderCompleteButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(orderCaloriesLabel)
								.addComponent(orderPriceLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(orderCaloriesInt)
								.addComponent(orderPriceFloat)
						)		
				)				
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {customerRegisterButton, customerEmailTextField});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {pizzaAddButton, pizzaList});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {orderCompleteButton, pizzaAddButton});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(customerNameLabel)
						.addComponent(customerNameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(customerAddressLabel)
						.addComponent(customerAddressTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(customerPhoneLabel)
						.addComponent(customerPhoneTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(customerEmailLabel)
						.addComponent(customerEmailTextField)
						.addComponent(customerRegisterButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(new JSeparator(SwingConstants.HORIZONTAL)))
				.addGroup(layout.createParallelGroup()
						.addComponent(pizzaList)
						.addComponent(pizzaQuantity)
						.addComponent(pizzaQuantityInt)
						.addComponent(orderDetailsTable)
						.addComponent(orderCaloriesLabel)
						.addComponent(orderCaloriesInt))
				.addGroup(layout.createParallelGroup()
						.addComponent(pizzaExtrasLabel)
						.addComponent(pizzaCalories)
						.addComponent(pizzaCaloriesInt)
						.addComponent(orderPriceLabel)
						.addComponent(orderPriceFloat))
				.addGroup(layout.createParallelGroup()
						.addComponent(pizzaExtrasTable)
						.addComponent(pizzaPriceLabel)
						.addComponent(pizzaPriceFloat))
				.addGroup(layout.createParallelGroup()
						.addComponent(pizzaAddButton)
						.addComponent(orderCompleteButton))				
		);
		pack();
	}
	
	//TODO
	private void refreshData(){
		MammaMia mm = MammaMia.getInstance();
		errorMessage.setText(error);
		if(error == null || error.length() == 0){
			
		}
		pack();
	}
	
	private void refreshAllData(){
		
	}
	
	//TODO Set customer info as permanent
	private void createCustomerButtonActionPerformed(java.awt.event.ActionEvent evt){
		MammaMiaController mm = new MammaMiaController();
		error = null;
		try{
			selectedCustomer = mm.createCustomer(customerNameTextField.getText(), Long.parseLong(customerPhoneTextField.getText()), customerEmailTextField.getText(), customerAddressTextField.getText());
		}
		catch(InvalidInputException iie){
			error = iie.getMessage();
		}
		refreshData();
	}
	
	//TODO
	private void createOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
		MammaMiaController mm = new MammaMiaController();
		error = null;
		try{
			mm.saveOrder(order);
		}
		catch(InvalidInputException iie){
			error = iie.getMessage();
		}
		refreshAllData();
	}
	
	//TODO
	private void deleteOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
		MammaMiaController mm = new MammaMiaController();

	}
	
	private void createPizzaButtonActionPerformed(java.awt.event.ActionEvent evt){
		error = "";
		MammaMiaController mm = new MammaMiaController();
		try{
			if(selectedCustomer == null){
				error = error + "A customer must first be registered. ";
			}
			if(order == null){
				order = mm.createOrder(selectedCustomer);
			}
			Pizza p;
			if(pizzaList.getSelectedItem().equals("Moon Cheese")){
				p = mm.createPizza("Moon Cheese", 1000, 5.00f, order, new Menu());
			}
			else if(pizzaList.getSelectedItem().equals("Veggie Tales")){
				p = mm.createPizza("Veggie Tales", 1500, 7.00f, order, new Menu());
			}
			else{
				p = mm.createPizza("Custom", 1500, 7.00f, order, new Menu());
			}
			OrderDetails od = mm.addPizzaToOrder(p, order, selectedCustomer, Integer.parseInt(pizzaQuantityInt.getText()));
			updateDetails(od);
		}
		catch(InvalidInputException iie){
			error = error + iie.getMessage();
		}
		refreshData();
	}
	
	//TODO
	private void updateDetails(OrderDetails od){
		
	}
	
}