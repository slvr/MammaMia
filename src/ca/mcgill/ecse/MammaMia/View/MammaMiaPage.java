package ca.mcgill.ecse.MammaMia.View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse.MammaMia.Application.MammaMiaApplication;
import ca.mcgill.ecse.MammaMia.Controller.InvalidInputException;
import ca.mcgill.ecse.MammaMia.Controller.MammaMiaController;
import ca.mcgill.ecse.MammaMia.model.*;

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
//	private JTextField extraPeppers;
//	private JTextField extraMushrooms;
	private JButton deleteButton;
	private JComboBox<String> pastCustomers;
	private JButton selectCustomerButton;
	private JButton updateCustomerButton;
	private JButton deleteCustomerButton;
	
	//data
	private String error = null;
	private Customer selectedCustomer = null;
	private Order order = null;	
	private int customerIndex = -1;
	
	public MammaMiaPage(){
		initComponents();
//		refreshData();
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
		selectCustomerButton = new JButton("Select Customer");
		selectCustomerButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				existingCustomerSelected(evt);
			}
		});
		updateCustomerButton = new JButton("Update Info");
		updateCustomerButton.setEnabled(false);
		updateCustomerButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateCustomerSelected(evt);
			}
		});
		deleteCustomerButton = new JButton("Remove Customer");
		deleteCustomerButton.setEnabled(false);
		deleteCustomerButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteCustomerSelected(evt);
			}
		});
		
		MammaMia mm = MammaMiaApplication.getMammaMia();
		pastCustomers = new JComboBox<String>();
		for(Customer c : mm.getCustomers()){
			if((c.getPhoneNumber()) != -1){
				pastCustomers.addItem(c.getName() + " " + c.getPhoneNumber());
			}
			else{
				pastCustomers.addItem(c.getName() + " " + c.getEmail());
			}
		}
		
		//pizza creation
		pizzaList = new JComboBox<String>(new String[] {"Moon Cheese", "Veggie Tales", "Custom"});
		pizzaCalories = new JLabel("Calories: ");
		pizzaCaloriesInt = new JLabel("0");
		pizzaExtrasLabel = new JLabel("With Extra: ");
//		extraPeppers = new JTextField();
//		extraMushrooms = new JTextField();
		try{
//			pizzaExtrasTable = new JTable(new Object[][]{{"Peppers", extraPeppers},{"Mushrooms", extraMushrooms}}, 
//				new String[]{"Ingredient", "Quantity"});
			pizzaExtrasTable = new JTable(0, 2);
			DefaultTableModel model = (DefaultTableModel) pizzaExtrasTable.getModel();
			model.addRow(new String[]{"Ingredient", "Quantity"});
			model.addRow(new String[]{"Peppers", "0"});
			model.addRow(new String[]{"Mushrooms", "0"});
		}catch(ArrayIndexOutOfBoundsException e){
			error = error + e.getMessage();
		}
		pizzaQuantity = new JLabel("Quantity");
		pizzaQuantityInt = new JTextField();
		pizzaPriceLabel = new JLabel("Price: ");
		pizzaPriceFloat = new JLabel("$ 0.00");
		pizzaAddButton = new JButton("Add Pizza");
		pizzaAddButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(!Objects.isNull(selectedCustomer)){
					createPizzaButtonActionPerformed(evt);
				}
				else{
					error = error + "Please Register as a customer before creating your order. ";
				}
			}
		});
		errorMessage.setText(error);
		
		//order details
		try{
//			orderDetailsTable = new JTable(new Object[][] {{}}, new String[] {"Pizza Type", "Extras", "Quantity", "Remove?"});
			orderDetailsTable = new JTable(0, 4);
			DefaultTableModel model = (DefaultTableModel) orderDetailsTable.getModel();
			model.addRow(new String[] {"Pizza Type", "Extras", "Quantity", "Remove?"});

		}catch(ArrayIndexOutOfBoundsException e){
			error = error + e.getMessage();
		}
		orderPriceLabel = new JLabel("Total Price: ");
		orderCaloriesLabel = new JLabel("Total Calories: ");
		orderPriceFloat = new JLabel("$ 0.00");
		orderCaloriesInt = new JLabel("0");
		orderCompleteButton = new JButton("Order Complete");
		orderCompleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createOrderButtonActionPerformed(evt);
			}
		});
		deleteButton = new JButton("Delete Order");
		deleteButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteOrderButtonActionPerformed(evt);
			}
		});
		initLayout();
	}
	
	private void initLayout(){
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
								.addComponent(pastCustomers)
								.addComponent(selectCustomerButton)
								.addComponent(updateCustomerButton)
								.addComponent(customerRegisterButton)
								.addComponent(pizzaQuantity)
								.addComponent(pizzaCalories)
								.addComponent(pizzaPriceLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(pizzaQuantityInt)
								.addComponent(pizzaCaloriesInt)
								.addComponent(pizzaPriceFloat))
						.addGroup(layout.createParallelGroup()
								.addComponent(deleteCustomerButton)
								.addComponent(orderDetailsTable)
								.addComponent(orderCompleteButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(orderCaloriesLabel)
								.addComponent(orderPriceLabel)
								.addComponent(deleteButton))
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
						.addComponent(customerNameTextField)
						.addComponent(pastCustomers))
				.addGroup(layout.createParallelGroup()
						.addComponent(customerAddressLabel)
						.addComponent(customerAddressTextField)
						.addComponent(selectCustomerButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(customerPhoneLabel)
						.addComponent(customerPhoneTextField)
						.addComponent(updateCustomerButton)
						.addComponent(deleteCustomerButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(customerEmailLabel)
						.addComponent(customerEmailTextField)
						.addComponent(customerRegisterButton))
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
						.addComponent(orderCompleteButton)
						.addComponent(deleteButton))				
		);
		errorMessage.setText(error);
		pack();
	}
	

	
	private void refreshAllData(){
		errorMessage.setText(error);
		if(error == null || error.length() == 0){
			customerNameTextField.setEditable(true);
			customerAddressTextField.setEditable(true);
			customerPhoneTextField.setEditable(true);
			customerEmailTextField.setEditable(true);
			customerNameTextField.setText("");
			customerAddressTextField.setText("");
			customerPhoneTextField.setText("");
			customerEmailTextField.setText("");
			try{
				DefaultTableModel dm = (DefaultTableModel) orderDetailsTable.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 1; i--) {
				    dm.removeRow(i);
				}
			}catch(ArrayIndexOutOfBoundsException e){
				error = error + e.getMessage();
			}
			orderCaloriesInt.setText("0");
			orderPriceFloat.setText("$ " + "0.00");
			errorMessage.setText(error);
		}
	}
	
	private void createCustomerButtonActionPerformed(java.awt.event.ActionEvent evt){
		MammaMiaController mm = new MammaMiaController();
		MammaMia mia = MammaMiaApplication.getMammaMia();
		error = null;
		try{
			if(!customerPhoneTextField.getText().equals("")){
				selectedCustomer = mm.createCustomer(customerNameTextField.getText(), Long.parseLong(customerPhoneTextField.getText()), customerEmailTextField.getText(), customerAddressTextField.getText());
			}
			else{
				selectedCustomer = mm.createCustomer(customerNameTextField.getText(), -1, customerEmailTextField.getText(), customerAddressTextField.getText());
			}
			customerNameTextField.setEditable(false);
			customerAddressTextField.setEditable(false);
			customerPhoneTextField.setEditable(false);
			customerEmailTextField.setEditable(false);
			pastCustomers.removeAllItems();
			for(Customer c : mia.getCustomers()){
				pastCustomers.addItem(c.getName() + " " + c.getPhoneNumber());
			}
			
		}
		catch(InvalidInputException iie){
			error = iie.getMessage();
		}
		catch(NumberFormatException nfe){
			error = nfe.getMessage();
		}
		errorMessage.setText(error);
		initLayout();
	}
	
	private void existingCustomerSelected(java.awt.event.ActionEvent evt){
		MammaMia mia = MammaMiaApplication.getMammaMia();
		try{
			customerIndex = pastCustomers.getSelectedIndex();
			selectedCustomer = mia.getCustomer(customerIndex);
			customerNameTextField.setText(selectedCustomer.getName());
			customerAddressTextField.setText(selectedCustomer.getAddress());
			if(selectedCustomer.getPhoneNumber() == -1){
				customerPhoneTextField.setText("");
			}
			else{
				customerPhoneTextField.setText(String.valueOf(selectedCustomer.getPhoneNumber()));
			}
			customerEmailTextField.setText(selectedCustomer.getEmail());
			deleteCustomerButton.setEnabled(true);
			updateCustomerButton.setEnabled(true);
		} catch(NumberFormatException nfe){
			error = "Select from existing customers";
		}
		errorMessage.setText(error);
		initLayout();
	}
	
	private void updateCustomerSelected(java.awt.event.ActionEvent evt){
		MammaMiaController mm = new MammaMiaController();
		MammaMia mia = MammaMiaApplication.getMammaMia();
		try{
			if(!customerPhoneTextField.getText().equals("")){
				mm.updateCustomer(customerIndex, customerNameTextField.getText(), Long.parseLong(customerPhoneTextField.getText()), customerEmailTextField.getText(), customerAddressTextField.getText());
			}
			else{
				mm.updateCustomer(customerIndex, customerNameTextField.getText(), -1, customerEmailTextField.getText(), customerAddressTextField.getText());
			}
			pastCustomers.removeAllItems();
			for(Customer c : mia.getCustomers()){
				if(Objects.isNull(c.getPhoneNumber())){
					pastCustomers.addItem(c.getName() + " " + c.getPhoneNumber());
				}
				else{
					pastCustomers.addItem(c.getName() + " " + c.getEmail());
				}
			}
			initLayout();
			
		} catch(NumberFormatException nfe){
			error = "Select from existing customers";
		}
		catch(InvalidInputException iie){
			error = error + " Invalid inputs";
		}
	}
	
	private void deleteCustomerSelected(java.awt.event.ActionEvent evt){
		MammaMiaController mm = new MammaMiaController();
		MammaMia mia = MammaMiaApplication.getMammaMia();
		try{
			selectedCustomer = mia.getCustomer(customerIndex);
			mm.deleteCustomer(selectedCustomer);
			pastCustomers.removeAllItems();
			for(Customer c : mia.getCustomers()){
				if(Objects.isNull(c.getPhoneNumber())){
					pastCustomers.addItem(c.getName() + " " + c.getPhoneNumber());
				}
				else{
					pastCustomers.addItem(c.getName() + " " + c.getEmail());
				}
			}
			customerNameTextField.setText("");
			customerAddressTextField.setText("");
			customerPhoneTextField.setText("");
			customerEmailTextField.setText("");
			customerIndex = -1;
			selectedCustomer = null;
			initLayout();
		}
		catch(Exception e){
			error = error + " Cannot delete Customer";
		}
	}
	
	private void createOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
		MammaMiaController mm = new MammaMiaController();
		error = null;
		try{
			mm.saveOrder(order);
			JOptionPane.showMessageDialog(null, "Your order number is " + order.getOrderNumber(), "Success", JOptionPane.INFORMATION_MESSAGE);
			updateCustomerButton.setEnabled(false);
			deleteCustomerButton.setEnabled(false);
			refreshAllData();
			initLayout();
		}
		catch(InvalidInputException iie){
			error = iie.getMessage();
		}
	}
	
	private void deleteOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
		MammaMiaController mm = new MammaMiaController();
		try{
			if(order != null){
				mm.deleteOrder(order);
				JOptionPane.showMessageDialog(null, "Your order has been deleted", "Deleted", JOptionPane.INFORMATION_MESSAGE);
				refreshAllData();
			}
		}
		catch(InvalidInputException iie){
			error = error + iie.getMessage();
		}
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
			int extraPeppers = Integer.parseInt((String)pizzaExtrasTable.getModel().getValueAt(1, 1));
			int extraMushrooms = Integer.parseInt((String)pizzaExtrasTable.getModel().getValueAt(2, 1));
			for(int i = extraPeppers; i > 0; i--){
				p.addIngredient(new Pepper(100, 1.00f, p, new Menu()));
			}
			for(int i = extraMushrooms; i > 0; i--){
				p.addIngredient(new Mushroom(150, 1.10f, p, new Menu()));
			}
			OrderDetails od = mm.addPizzaToOrder(p, order, selectedCustomer, Integer.parseInt(pizzaQuantityInt.getText()));
			updateDetails(od, p.getClass());
		}
		catch(InvalidInputException iie){
			error = error + iie.getMessage();
		}
		catch(NumberFormatException nfe){
			error = error + "Include pizza quantity. ";
		}
		errorMessage.setText(error);
//		refreshData();
	}
	
	private void updateDetails(OrderDetails od, Class<? extends Pizza> class1){
		JButton thisRowButton = new JButton("Remove this item");
		try{
		DefaultTableModel model = (DefaultTableModel) orderDetailsTable.getModel();
		String name;
		if ("CheesePizza".equals(class1.getSimpleName())){
			name = "Moon Cheese";
		}
		else if("VeggiePizza".equals(class1.getSimpleName())){
			name = "Veggie Tales";
		}
		else{
			name = "Custom";
		}
		thisRowButton.addActionListener(new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent arg0) {
		        // check for selected row first
		        if (orderDetailsTable.getSelectedRow() != -1) {
		            // remove selected row from the model
		        	try{
		            model.removeRow(orderDetailsTable.getSelectedRow());
		        	}catch(ArrayIndexOutOfBoundsException e){
		        		error = error + e.getMessage();
		    		}
		        }
		    }
		});
		model.addRow(new Object[]{name, "", od.getQuantity(), thisRowButton});
		}catch(ArrayIndexOutOfBoundsException e){
			error = error + e.getMessage();
		}
		orderCaloriesInt.setText(Integer.toString(od.getItems().getCalories()));
		orderPriceFloat.setText("$ " + Float.toString(od.getItems().getPrice()));
		errorMessage.setText(error);
	}
	
}