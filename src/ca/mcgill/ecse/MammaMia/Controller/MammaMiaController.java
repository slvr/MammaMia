package ca.mcgill.ecse.MammaMia.Controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import ca.mcgill.ecse.MammaMia.Model.Customer;
import ca.mcgill.ecse.MammaMia.Model.Order;
import ca.mcgill.ecse.MammaMia.Model.Pizza;
import ca.mcgill.ecse.MammaMia.Persistence.PersistenceObjectStream;



public class MammaMiaController {

	public MammaMiaController(){
	}
	
	public void createCustomer(String aName, long aPhoneNumber, String aEmail, String aAddress) throws InvalidInputException{
		if(aName == null || aName.length() == 0 || aName.length() > 20){
			throw new InvalidInputException("Customer name cannot be empty");
		}
		if((aPhoneNumber < 1000000000L || aPhoneNumber > 9999999999L) && aEmail == null){
			throw new InvalidInputException("Please enter a phone number or email");
		}
		if((aEmail == null || aEmail.length() == 0) && Objects.isNull(aPhoneNumber)){
			throw new InvalidInputException("Please enter a phone number or email");
		}
		if(aAddress == null || aAddress.length() == 0){
			throw new InvalidInputException("Please enter a delivery address");
		}
		Customer c = new Customer(aName, aPhoneNumber, aEmail, aAddress);
		PersistenceObjectStream.saveToXMLwithXStream(c);
	}
	
	public void createOrder(Customer c){
		
	}
	
	public void deleteOrder(int orderNumber){
		
	}
	
	public void addPizzaToOrder(Pizza pizza, Order order){
		
	}
	
}
