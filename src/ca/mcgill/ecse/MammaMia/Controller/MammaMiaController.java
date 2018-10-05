package ca.mcgill.ecse.MammaMia.Controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import ca.mcgill.ecse.MammaMia.Model.Customer;
import ca.mcgill.ecse.MammaMia.Model.Item;
import ca.mcgill.ecse.MammaMia.Model.MammaMia;
import ca.mcgill.ecse.MammaMia.Model.Order;
import ca.mcgill.ecse.MammaMia.Model.OrderDetails;
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
		MammaMia mm = MammaMia.getInstance();
		Customer c = new Customer(aName, aPhoneNumber, aEmail, aAddress, mm);
		PersistenceObjectStream.saveToXMLwithXStream(mm);
	}
	
	public void createOrder(Customer c) throws InvalidInputException{
		if(c == null){
			throw new InvalidInputException("Order must have a customer!");
		}
		int orderNumber = PersistenceObjectStream.getNextOrderNumber();
		Order o = new Order(c);
		o.setOrderNumber(orderNumber);
		PersistenceObjectStream.saveToXMLwithXStream(MammaMia.getInstance());
	}
	
	public void deleteOrder(Order order) throws InvalidInputException{
		if(!(order.getStatus().equals(Order.Status.Delivered))){
			throw new InvalidInputException("Order must be deliveredto be deleted");
		}
		List<Pizza> pizzas = order.getPizza();
		for(Pizza p : pizzas){
			p.delete();
		}
		order.delete();
		PersistenceObjectStream.saveToXMLwithXStream(MammaMia.getInstance());
	}
	
	public void addPizzaToOrder(Pizza pizza, Order order, Customer customer, int quantity) throws InvalidInputException{
		String error = "";
		if(pizza == null){
			error += error + "A pizza must be created. ";
		}
		else if(customer == null && order == null){
			error += error + "Either a customer or an order must be specified. ";
		}
		error = error.trim();
		if(error.length() > 0){
			throw new InvalidInputException(error);
		}
		if(order != null){
			order.addPizza(pizza);
		}
		else{
			order = new Order(customer);
			order.addPizza(pizza);
		}
		OrderDetails od = order.addDetail(quantity, new Item(pizza.getCalories(), pizza.getPrice()));
		order.addDetail(od);
		PersistenceObjectStream.saveToXMLwithXStream(MammaMia.getInstance());

	}
	
}
